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
import android.widget.ListView;
import android.widget.TextView;

import com.devprogram.aliaspro.DAL.Implementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.PlayingTeams;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

public class RoundResultActivity extends AppCompatActivity {
    List<Word> allShowedWords;
    Team team;
    int points;
    IDbService dbService;
    ListView listView;
    Round round;
    PlayingTeams playingTeams;
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
            tvScore.setText(String.valueOf(points));
            listView = findViewById(R.id.lvShowWordRound);
            listView.setAdapter(new RoundResultViewAdapter(this,allShowedWords,dbService, team,tvScore,round.getIdround(),playingTeams));
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
            allShowedWords = dbService.getEWordService().getShowedRoundWords(idRound);
            team = dbService.getETeamService().getTeam(round.getTeam());
            playingTeams = dbService.getEPlayingTeamsService().getPlayingTeams(round.getTeam(),round.getGame());
            points = playingTeams.getScoreRound();
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
            intent.putExtra("idGame",round.getGame());
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


class RoundResultViewAdapter extends BaseAdapter implements View.OnClickListener{

    private final List<Word> items;
    private final Context context;
    private IDbService dbService;
    private final String idRound;
    PlayingTeams playingTeams;
    TextView tvScore;

    WordStatus stGues;
    WordStatus stSkip;
    Team team;

    public RoundResultViewAdapter(Context context,List<Word> items, IDbService dbService,Team team, TextView tvScore,String idRound, PlayingTeams playingTeams ) {
        this.items = items;
        this.context = context;
        this.dbService = dbService;
        stGues = dbService.getEWordStatusService().getWordsStatus().get(0);
        stSkip = dbService.getEWordStatusService().getWordsStatus().get(1);
        this.team = team;
        this.tvScore = tvScore;
        this.idRound = idRound;
        this.playingTeams = playingTeams;
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
        {
            Word word = (Word)getItem(position);
            WordStatus wordStatus = dbService.getEWordStatusService().getWordStatus(word.getIdword(), idRound);
            if(view==null)
            {
                view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_showed_words, parent, false);
            }
            TextView nameWord = view.findViewById(R.id.tvWordNameShowed);
            nameWord.setText(word.getName());
            Button btnStatusWord = view.findViewById(R.id.btnShowGouseWord);
            switch (wordStatus.getStatus())
            {
                case 0:
                    btnStatusWord.setBackgroundResource(R.drawable.round_word_delete64);
                    break;
                case 1:
                    btnStatusWord.setBackgroundResource(R.drawable.round_word_add64);
                    break;
            }

            btnStatusWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int allScore = playingTeams.getScoreAll();
                    int roundScore = playingTeams.getScoreRound();
                    switch (wordStatus.getStatus())
                    {
                        case 1://если отгадано
                            //то конвертируем в неотгадано
                            ((Button)v).setBackgroundResource(R.drawable.round_word_delete64);
                            dbService.getEWordStatusService().updateWordStatus(0,word.getIdword(),idRound);
                            dbService.getEPlayingTeamsService().setScoreAll(playingTeams.getId(),--allScore);
                            dbService.getEPlayingTeamsService().setScoreRound(playingTeams.getId(),--roundScore);
                            tvScore.setText(String.valueOf(roundScore-1));
                            break;
                        case 0:
                            ((Button)v).setBackgroundResource(R.drawable.round_word_add64);
                            dbService.getEWordStatusService().updateWordStatus(0,word.getIdword(),idRound);
                            dbService.getEPlayingTeamsService().setScoreAll(playingTeams.getId(),++allScore);
                            dbService.getEPlayingTeamsService().setScoreRound(playingTeams.getId(),++roundScore);
                            tvScore.setText(String.valueOf(roundScore-1));
                            break;
                    }

                    RoundResultViewAdapter.this.notifyDataSetChanged();
                }
            });
        }
        catch (Exception er)
        {
            Log.e("GRTVIEWROUNRES",er.getMessage());
        }
        return view;
    }


    @Override
    public void onClick(View v) {

    }
}
