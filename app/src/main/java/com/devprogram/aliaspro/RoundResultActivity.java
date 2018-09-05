package com.devprogram.aliaspro;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;

import java.util.ArrayList;
import java.util.List;

public class RoundResultActivity extends AppCompatActivity {
    List<Word> allShowedWords;
    Team team;
    int points;
    IDbService dbService;
    ListView listView;
    Round round;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_round_result);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            GetData();
            this.setTitle(team.getName());
            TextView tvScore = findViewById(R.id.tvPointsCountRound);
            tvScore.setText(Integer.toString(points));
            listView = findViewById(R.id.lvShowWordRound);
            listView.setAdapter(new RoundResultViewAdapter(this,allShowedWords));
        }
        catch(Exception er)
        {
            Log.e("OnCREATEROUNDRES",er.getMessage());
        }
    }

    @Override
    public void onDestroy()
    {
        dbService.CloseDb();
        super.onDestroy();
    }

    //установка данных при запуске активити
    private void GetData() {
        try{
            points = 0;
            String idRound = getIntent().getStringExtra("idroundCurrent");
            dbService = new DbService();
            round = dbService.getERoundService().getRound(idRound);
            allShowedWords = round.getWords();
            team = round.getTeam();
            for(Word item: allShowedWords)
            {
                if(item.getWordstatus().getStatus()=="отгадано")
                    points++;
            }
        }
        catch(Exception er)
        {
            Log.e("ERROR GET DATA R",er.getMessage());
        }
    }
    public void btnGoToChoseNextTeam_Click(View view)
    {
        try
        {
            Intent intent = new Intent(RoundResultActivity.this, BeginGameActivity.class);
            intent.putExtra("idGame",round.getGame().getIdgame());
            intent.putExtra("idRound",round.getIdround());
            startActivity(intent);
        }catch(Exception er)
        {
            Log.e("btnGOCHOSEnextTeam", er.getMessage());
        }
    }
}

////
////ADAPTER RoundResult
////


class RoundResultViewAdapter extends BaseAdapter {

    private final List<Word> items;
    private final Context context;

    public RoundResultViewAdapter(Context context,List<Word> items) {
        this.items = items;
        this.context = context;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        try
        {Word word = (Word)getItem(position);
            if(view==null)
            {
                view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_showed_words, parent, false);
            }
            TextView nameWord = view.findViewById(R.id.tvWordNameShowed);
            nameWord.setText(word.getName());
            Button btnAdd = view.findViewById(R.id.btnShowGouseWord);
            Button btnRemove = view.findViewById(R.id.btnShowSkipWord);
            if(word.getWordstatus().getStatus()=="отгадано")
            {

            }
            else if(word.getWordstatus().getStatus()=="не отгадано")
            {

            }

        }
        catch (Exception er)
        {
            Log.e("GRTVIEWROUNRES",er.getMessage());
        }

        return view;
    }
}
