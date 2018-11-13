package com.devprogram.aliaspro;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.devprogram.aliaspro.DAL.Implementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Helpers.AdMobCreater;
import com.devprogram.aliaspro.Helpers.IAdMobCreater;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.PlayingTeams;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

public class RoundResultActivity extends AppCompatActivity {
    List<Word> allShowedWords;
    Team team;
    Team teamWinLastWord;
    int points;
    IDbService dbService;
    ListView listView;
    Round round;
    PlayingTeams playingTeams;
    TextView tvScore;
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
            tvScore = findViewById(R.id.tvPointsCountRound);
            tvScore.setText(String.valueOf(points));
            listView = findViewById(R.id.lvShowWordRound);
            listView.setAdapter(new RoundResultViewAdapter(this,allShowedWords,dbService, team,tvScore,round.getIdround(),playingTeams));
            TextView tvTaskScore = findViewById(R.id.tvTaskScore);
            TextView tvTaskNameScore = findViewById(R.id.tvTaskScoreName);
            TextView tvNameTeamLastWordWin = findViewById(R.id.tvTeamNameWinLastWord);
            TextView tvLastWordScoreWin = findViewById(R.id.tvLastWordScore);
            SetTaskScore(tvTaskScore,tvTaskNameScore, round);
            SetTeamLastWordWin(tvNameTeamLastWordWin,tvLastWordScoreWin, teamWinLastWord);
        }
        catch(Exception er)
        {
            Crashlytics.logException(er);
            Log.e("OnCREATEROUNDRES",er.getMessage());
        }
    }



    private void SetTeamLastWordWin(TextView tvName, TextView tvLast, Team teamWinLastWord) {
        Game game = dbService.getEGameService().getGame(round.getGame());
        if(game.getIslastword() && teamWinLastWord!=null)
        {
            tvLast.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.VISIBLE);
            tvName.setText(teamWinLastWord.getName());
        }
        else
        {
            tvLast.setVisibility(View.GONE);
            tvName.setVisibility(View.GONE);
        }
    }

    private void SetTaskScore(TextView tvTaskScore, TextView tvTaskNameScore, Round round) {
        try{
            Game game = dbService.getEGameService().getGame(round.getGame());
            if(game.getIstask())//если играем с задачами
            {
                tvTaskNameScore.setVisibility(View.VISIBLE);
                tvTaskScore.setVisibility(View.VISIBLE);
                //спросим выполнена ли задача
                CustomDialogTaskCompleted ts = new CustomDialogTaskCompleted(this,round,dbService, tvTaskScore, tvScore);
                ts.show();
            }
            else
            {
                tvTaskNameScore.setVisibility(View.GONE);
                tvTaskScore.setVisibility(View.GONE);
            }

        }
        catch(Exception er)
        {
            Crashlytics.logException(er);
            Log.e("SETTASKSCORE",er.getMessage());
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
            String idTeamLastWordWin = getIntent().getStringExtra("idTeamLastWordWin");
            dbService = new DbService();
            round = dbService.getERoundService().getRound(idRound);
            //установим время раунда -1 т.е. при выборе продолжить будет запущено активити BeginGame
            dbService.getERoundService().setTimeToFinishRound(idRound,-1);
            allShowedWords = dbService.getEWordService().getShowedRoundWords(idRound);
            team = dbService.getETeamService().getTeam(round.getTeam());
            if(!idTeamLastWordWin.isEmpty())
                teamWinLastWord = dbService.getETeamService().getTeam(idTeamLastWordWin);
            playingTeams = dbService.getEPlayingTeamsService().getPlayingTeams(round.getTeam(),round.getGame());
            points = playingTeams.getScoreRound();
        }
        catch(Exception er)
        {
            Crashlytics.logException(er);
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
        }
        catch(Exception er)
        {
            Crashlytics.logException(er);
            Log.e("btnGOCHOSEnextTeam", er.getMessage());
        }
    }
}
///
///CUSTOM IS TASK COMPLETED???
///

//класс диалогового окна с описанием задачи загружается при первом появлении страницы PlayGameActivity
class CustomDialogTaskCompleted extends Dialog implements View.OnClickListener {

    IDbService dbService;
    Round round;
    Activity activity;
    TextView tvTaskScore;
    TextView tvScoreAll;

    public CustomDialogTaskCompleted(@NonNull Activity activity, Round round, IDbService dbService, TextView tvTaskScore,TextView tvScoreAll) {
        super(activity);
        this.activity = activity;
        this.round = round;
        this.dbService = dbService;
        this.tvTaskScore = tvTaskScore;
        this.tvScoreAll = tvScoreAll;
    }
    Button btnIsnt;
    Button btnComp;
    @Override
    protected void onCreate(Bundle instance)
    {
        try
        {
            super.onCreate(instance);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.custom_dialog_task_completed);
            btnIsnt = findViewById(R.id.btnTaskIsntCompleted);
            btnComp = findViewById(R.id.btnTaskCompleted);
            btnIsnt.setOnClickListener(this);
            btnComp.setOnClickListener(this);
        }
        catch(Exception er)
        {
            Crashlytics.logException(er);
            Log.e("ONCREATECUSTOMDIALDESC",er.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnComp.getId())
            SetRoundTaskCompleted(true,2);
        else if(v.getId() == btnIsnt.getId())
            SetRoundTaskCompleted(false, 0);

    }

    private void SetRoundTaskCompleted(boolean iscompleted, int score)
    {
        try{
            tvTaskScore.setText(String.valueOf(score));
            dbService.getERoundService().changeTaskComplete(round.getIdround(),iscompleted);
            int currentScoreR = dbService.getEPlayingTeamsService().getPlayingTeams(round.getTeam(),round.getGame()).getScoreRound();
            int currentScoreA = dbService.getEPlayingTeamsService().getPlayingTeams(round.getTeam(),round.getGame()).getScoreAll();
            dbService.getEPlayingTeamsService().updatePlayingTeams(round.getTeam(),round.getGame(),currentScoreR+score,currentScoreA+score);
            tvScoreAll.setText(String.valueOf(currentScoreR+score));
            cancel();
        }
        catch (Exception er)
        {
            Crashlytics.logException(er);
            Log.e("ROUNDRES_SETROUN", er.getMessage());
        }
    }
}








////
////ADAPTER RoundResult
////


class RoundResultViewAdapter extends BaseAdapter {

    private final List<Word> items;
    private final Context context;
    private IDbService dbService;
    private final String idRound;
    PlayingTeams playingTeams;
    TextView tvScore;

    WordStatus stGues;
    WordStatus stSkip;
    Team team;
    Boolean isLastWord;

    public RoundResultViewAdapter(Context context, List<Word> items, IDbService dbService, Team team, TextView tvScore, String idRound, PlayingTeams playingTeams) {
        this.items = items;
        this.context = context;
        this.dbService = dbService;
        stGues = dbService.getEWordStatusService().getWordsStatus().get(0);
        stSkip = dbService.getEWordStatusService().getWordsStatus().get(1);
        isLastWord = dbService.getEGameService().getGame(dbService.getERoundService().getRound(idRound).getGame()).getIslastword();
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
        try {
            Word word = (Word) getItem(position);
            WordStatus wordStatus = dbService.getEWordStatusService().getWordStatus(word.getIdword(), idRound);
            if (view == null) {
                view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_showed_words, parent, false);
            }
            TextView nameWord = view.findViewById(R.id.tvWordNameShowed);
            nameWord.setText(word.getName());
            Button btnStatusWord = view.findViewById(R.id.btnShowGouseWord);

            TextView lastWord = view.findViewById(R.id.tvLastWord);
            if(isLastWord && position==getCount()-1)
            {
                lastWord.setVisibility(View.VISIBLE);
                btnStatusWord.setEnabled(false);
            }
            else
                lastWord.setVisibility(View.GONE);


            switch (wordStatus.getStatus()) {
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
                    try {
                        int allScore = playingTeams.getScoreAll();
                        int roundScore = playingTeams.getScoreRound();
                        Game game = dbService.getEGameService().getGame(playingTeams.getIdGame());
                        switch (wordStatus.getStatus()) {
                            case 1://если отгадано
                                //то конвертируем в неотгадано

                                ((Button) v).setBackgroundResource(R.drawable.round_word_delete64);
                                dbService.getEWordStatusService().updateWordStatus(0, word.getIdword(), idRound);
                                roundScore = ChangeScore(roundScore,game.getIspenalty(), false);
                                allScore = ChangeScore(allScore,game.getIspenalty(), false);
                                SetValueScore(roundScore, allScore, playingTeams.getId());
                                break;
                            case 0:
                                ((Button) v).setBackgroundResource(R.drawable.round_word_add64);
                                dbService.getEWordStatusService().updateWordStatus(1, word.getIdword(), idRound);
                                roundScore = ChangeScore(roundScore,game.getIspenalty(), true);
                                allScore = ChangeScore(allScore,game.getIspenalty(), true);
                                SetValueScore(roundScore, allScore, playingTeams.getId());
                                break;
                        }
                        RoundResultViewAdapter.this.notifyDataSetChanged();
                    } catch (Exception er) {
                        Log.e("CHANGE_STATUS_WORD", er.getMessage());
                    }
                }
            });
        } catch (Exception er) {
            Log.e("GRTVIEWROUNRES", er.getMessage());
        }
        return view;
    }

    private void SetValueScore(int roundScore, int allScore, String idPlayingTeams)
    {
        dbService.getEPlayingTeamsService().setScoreAll(playingTeams.getId(), allScore);
        dbService.getEPlayingTeamsService().setScoreRound(playingTeams.getId(), roundScore);
        tvScore.setText(String.valueOf(roundScore));
    }


    private int ChangeScore(int score, boolean isPayFineSkeep, boolean isAddScore) {
        int PayFine = 1;
        if(isPayFineSkeep)
            PayFine = 2;
        if(isAddScore)
            return score + PayFine;
        else
            return score - PayFine;
    }
}
