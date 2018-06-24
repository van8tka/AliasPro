package com.devprogram.aliaspro;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.RealmList;

public class SettingsGameActivity extends AppCompatActivity {

    TextView tvTime;
    TextView tvWord;
    LinearLayout containerView;
    List<Team> listTeam;
    DbService dbService;
    Game newGame;
    public  List<Team> teamListInGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        dbService = new DbService();
        teamListInGame = new ArrayList<Team>();
        listTeam = dbService.getETeamService().getTeams();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        containerView = findViewById(R.id.llContainerComandItemsFragment);
        CreateDefaultComands();
        tvTime = findViewById(R.id.tvTimeSet);
        tvWord = findViewById(R.id.tvWordSet);
    }
//for create fragments default with comands
    private void CreateDefaultComands()
    {

        int i1 = new Random().nextInt(listTeam.size()-1);
        int i2 = new Random().nextInt(listTeam.size()-1);
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


    public void btnNextToChooseWords_Click(View v)
    {
        Toast.makeText(this,"Play the game",Toast.LENGTH_LONG).show();
    }

    public void addDictionaryToGame_Click(View view)
    {
        Toast.makeText(this,"Choose dictionary to play the game",Toast.LENGTH_LONG).show();
    }

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
    //second count
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
        dbService.Close();
        super.onDestroy();
    }

}
