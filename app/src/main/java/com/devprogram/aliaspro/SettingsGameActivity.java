package com.devprogram.aliaspro;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.devprogram.aliaspro.DAL.Implementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.realm.RealmList;

public class SettingsGameActivity extends AppCompatActivity {

    TextView tvTime;
    TextView tvWord;
    Switch swTask;
    Switch swFine;
    Switch swLast;
    LinearLayout containerView;
    List<Team> listTeam;
    IDbService dbService;
    public Dictionary dictionary;
    public  RealmList<Team> teamListInGame;
    private static final String  TAG_LOG = "SettingsGameActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
            dbService = new DbService();
            teamListInGame = new RealmList<Team>();
            listTeam = dbService.getETeamService().getTeams();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            containerView = findViewById(R.id.llContainerComandItemsFragment);
            CreateDefaultComands();

            swFine = findViewById(R.id.swBan);
            swLast = findViewById(R.id.swLastWord);
            swTask = findViewById(R.id.swDopTask);

            tvTime = findViewById(R.id.tvTimeSet);
            tvWord = findViewById(R.id.tvWordSet);
        }
      catch (Exception er)
      {
          Log.e(TAG_LOG,er.getMessage());
      }
    }








//for create fragments default with comands
    private void CreateDefaultComands()
    {
        int i1 = new Random().nextInt(listTeam.size()-1);
        int i2;
        do {
           i2 = new Random().nextInt(listTeam.size()-1);
        }
        while(i1==i2);
        CreateItemFragment(listTeam.get(i1));
        CreateItemFragment(listTeam.get(i2));
        teamListInGame.add(listTeam.get(i1));
        teamListInGame.add(listTeam.get(i2));
    }






//create fragments command
    private void CreateItemFragment(Team team) {
        Bundle bnd= new Bundle();
        bnd.putSerializable("team",(Serializable) team);
        ComandItemFragment fr = new ComandItemFragment();
        FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
        transaction.add(containerView.getId(), fr,"FragmentComandDefault");
        transaction.commit();
        fr.setArguments(bnd);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }





   int index=2;
   public void btnAddComand_click(View v)
   {
       DialogFragment dialogSetTeam = new DialogFragmentSetTeam();
       dialogSetTeam.show(getFragmentManager(),"dialogsetteam");
   }




   public void AddComand(Team team)
   {
       CreateItemFragment(team);
       teamListInGame.add(team);
   }



   public void RemoveComand(Team team)
   {
           teamListInGame.remove(team);
   }





//ПРОВЕРКА НАСТРОЕК ИГРЫ И ПЕРЕХОД К УСТАНОВКАМ РАУНДА
    public void btnNextToChooseWords_Click(View v)
    {
        try{
            if(dictionary == null)
            {
                String getDictionaryString = getResources().getString(R.string.getDictionaryStr);
                Toast.makeText(this, getDictionaryString,Toast.LENGTH_LONG).show();
                return;
            }
            DateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String timeCreate = dtFormat.format(date).toString();
            int cwords = Integer.parseInt(tvWord.getText().toString());
            int ctime =Integer.parseInt(tvTime.getText().toString());
            boolean isTask = swTask.isChecked();
            boolean isFine = swFine.isChecked();
            boolean isLast = swLast.isChecked();
            Task taskInGame = null;
            if(isTask)
                taskInGame = dbService.getETaskService().getTaskRandom();
            Team teamFirst = dbService.getETeamService().getTeam(teamListInGame.get(0).getIdteam());
            String game = dbService.getEGameService().createGame(dictionary,teamListInGame,isTask,isLast,isFine,cwords, ctime,false, timeCreate);
             String round = dbService.getERoundService().createRound("Роунд 1",teamFirst,null,taskInGame,dbService.getEGameService().getGame(game), 0, 0);
            Intent intent = new Intent(SettingsGameActivity.this, BeginGameActivity.class);
            intent.putExtra("idGame",game);
            intent.putExtra("idRound",round);
            startActivity(intent);
        }
        catch(Exception er)
        {
            Log.e(TAG_LOG, er.getMessage());
        }

    }


    int REQUEST_CODE_SELECT_DICTIONARY = 1;
//ВЫБОР СЛОВАРЯ ДЛЯ ИГРЫ
    public void addDictionaryToGame_Click(View view)
    {
        Intent intent = new Intent(SettingsGameActivity.this,DictionaryActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SELECT_DICTIONARY);
    }
    @Override
    protected  void onActivityResult(int recuestCode, int resultCode, Intent data)
    {
        if(data==null){return;}
        String idDictionary = data.getStringExtra("idDictionarySelect");
        dictionary = dbService.getEDictionaryService().getDictionary(idDictionary);
        TextView tvDictionaryName = findViewById(R.id.tvDictionaryName);
        tvDictionaryName.setText(dictionary.getName());
    }

//НАСТРОЙКА КОЛИЧЕСТВА СЛОВ ДЛЯ ПОБЕДЫ
    int increment = 10;
   //word count
    public void minusWords_onClick(View view) {

       String count = tvWord.getText().toString();
       int c = Integer.parseInt(count);
       if(c>10)
       {
           c-=increment;
           tvWord.setText(String.valueOf(c));
       }
    }
    public void plusWords_onClick(View view) {

        String count = tvWord.getText().toString();
        int c = Integer.parseInt(count);
        if(c<1000)
        {
            c+=increment;
            tvWord.setText(String.valueOf(c));
        }
    }
    //НАСТРОЙКА ВРЕМЕНИ РАУНДА
    public void minusSeconds_onClick(View view) {

        String count = tvTime.getText().toString();
        int c = Integer.parseInt(count);
        if(c>10)
        {
            c-=increment;
            tvTime.setText(String.valueOf(c));
        }
    }
    public void plusSeconds_onClick(View view) {

        String count = tvTime.getText().toString();
        int c = Integer.parseInt(count);
        if(c<1000)
        {
            c+=increment;
            tvTime.setText(String.valueOf(c));
        }
    }

    @Override
    public void onDestroy()
    {
        dbService.CloseDb();
        super.onDestroy();
    }

}
