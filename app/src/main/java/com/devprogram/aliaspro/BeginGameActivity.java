package com.devprogram.aliaspro;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.devprogram.aliaspro.DAL.Implementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.PlayingTeams;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class BeginGameActivity extends AppCompatActivity {

    IDbService dbService;

    ListView listView;
    TextView tvNameTeamGame;
    TextView textvRoundName;
    ImageView imgAvatarTeamGame;

    List<Team> listTeam;
    Game currentGame;
    List<PlayingTeams> playingTeams;

    long countTeamsInGame;
    Boolean isChangeOrientation ;
    LinearLayout linRound;
    PlayingTeams teamPlayerWin = null;
    boolean isCheckWinnerNeed = false;
    String  idNextRound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            isChangeOrientation = false;
            setContentView(R.layout.activity_begin_game);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            String idGame = getIntent().getStringExtra("idGame");
            String idRoundLast = getIntent().getStringExtra("idRound");
            GetDefaultData(idGame);
            listView = findViewById(R.id.lvTeamInGame);
            tvNameTeamGame = findViewById(R.id.tvPlayTeamName);
            imgAvatarTeamGame = findViewById(R.id.imgPlayTeamAvatar);
            textvRoundName = findViewById(R.id.tvRoundName);
            linRound = findViewById(R.id.linRoundBegin);
            //след играют
            SetNextCommandPlay(idRoundLast);
            listView.setAdapter(new TeamGameAdapter(this,listTeam, dbService, idGame));
        }catch(Exception er)
        {
            Log.e("ONCREBEGINACT",er.getMessage());
        }
    }

    private void GetDefaultData(String idGame) {
        dbService = new DbService();
        currentGame = dbService.getEGameService().getGame(idGame);
        listTeam = dbService.getEPlayingTeamsService().getListTeamByGame(idGame);
        playingTeams = dbService.getEPlayingTeamsService().getPlayingTeamsByGame(idGame);
        countTeamsInGame = listTeam.size();
    }

    @Override
    public void onDestroy()
    {
        dbService.CloseDb();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        try
        {
            super.onConfigurationChanged(newConfig);
            isChangeOrientation = true;
            if(newConfig.orientation == ORIENTATION_LANDSCAPE)
            {
                linRound.setOrientation(LinearLayout.HORIZONTAL);
            }
            if(newConfig.orientation == ORIENTATION_PORTRAIT)
            {
                linRound.setOrientation(LinearLayout.VERTICAL);
            }
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            linRound.setLayoutParams(lp);
            linRound.setGravity(Gravity.CENTER);
        }
        catch (Exception er)
        {
            Log.e("CONFCHANGE",er.getMessage());
        }
    }






private static final String TAG_BEGIN_GAME ="BeginGameActivity";
//установка параметров слледующего раунда
    private void SetNextCommandPlay(String idLastRound ) {
        try
        {
            if (!isChangeOrientation) {
                Team nexTeam;
                int numberGameNext;
                int numberRoundNext;
                int indexNextTeam;
                //получим последний раунд или сразу создадим новый
                if(idLastRound.equalsIgnoreCase("-1"))
                {
                    indexNextTeam = 0;
                    numberGameNext = 1;
                    numberRoundNext = 1;
                }
                else
                {
                    Round roundLast = dbService.getERoundService().getRound(idLastRound);
                    Team lastTeam = dbService.getETeamService().getTeam(roundLast.getTeam());
                    int indexLast = listTeam.indexOf(lastTeam);
                    int lastNumberGame = roundLast.getNumberGame();
                    int lastNamberRound = roundLast.getNumber();
                    if(indexLast>=listTeam.size()-1)
                    {
                        indexNextTeam = 0;
                        numberGameNext = ++lastNumberGame;
                        numberRoundNext = ++lastNamberRound;
                        //необходима проверка на победителя при нажатии кнопки продолжить
                        isCheckWinnerNeed = true;

                    }
                    else
                    {//сброс результатов раунда если новый сет игры
                        ResetRoundResults(indexLast, lastNumberGame);
                        indexNextTeam = ++indexLast;
                        numberGameNext = lastNumberGame;
                        numberRoundNext = ++lastNamberRound;
                    }

                }
                nexTeam = listTeam.get(indexNextTeam);
                idNextRound = dbService.getERoundService().createRound(nexTeam.getIdteam() ,GetTask(),currentGame.getIdgame(),numberRoundNext,numberGameNext,null,false);
                             //отображение следующего игрока
                ShowNextTeamPlay(nexTeam,numberRoundNext);
            }
        }
        catch (Exception er)
        {
            Log.e(TAG_BEGIN_GAME,er.getMessage());
        }
    }
//при переходе к новому сету игры сброс показаний раунда на нуль кроме первой команды
    private void ResetRoundResults(int indexLast, int lastNumberGame) {
        if(indexLast == 0 && lastNumberGame>1)
            for(int i = 1;i<playingTeams.size();i++)
                dbService.getEPlayingTeamsService().setScoreRound(playingTeams.get(i).getId(),0);
    }
//покажем имя следующей команды
    private void ShowNextTeamPlay(Team nexTeam, int numberRoundNext) {
        String nameRound = this.getResources().getString(R.string.strRound) + " " + String.valueOf(numberRoundNext);
        textvRoundName.setText(nameRound);
        tvNameTeamGame.setText(nexTeam.getName());
        int idImg = this.getResources().getIdentifier(nexTeam.getAvatar(), "drawable", this.getPackageName());
        imgAvatarTeamGame.setImageResource(idImg);
    }

    //получение id задания если оно выбрано в игре
    private String GetTask() {
        if(currentGame.getIstask())
            return dbService.getETaskService().getTaskRandom().getIdtask();
        else
            return null;
    }


    //  ПРОВЕРКА РЕЗУЛЬТАТОВ ИГРЫ ДЛЯ ОПРЕДЕЛЕНИЯ ПОБЕДИТЕЛЯ
    private boolean CheckResultGameForDetectWinner() {
        try
        {
            int wordsToWin = currentGame.getCountwords();
            for(PlayingTeams tm: playingTeams)
            {
                if((tm.getScoreAll()>=wordsToWin && teamPlayerWin==null) || (tm.getScoreAll()>=wordsToWin && tm.getScoreAll()> teamPlayerWin.getScoreAll()))
                    teamPlayerWin = dbService.getEPlayingTeamsService ().getPlayingTeams(tm.getId());
            }
            if(teamPlayerWin!=null)
                return true;
            else
                return false;
        }
        catch(Exception er)
        {
            Log.e("CHECK_WINNER",er.getMessage());
            return false;
        }
    }


    public void btnStartGame_Click(View view) {
        try
        {

            if(isCheckWinnerNeed)
            {
                if(CheckResultGameForDetectWinner())
                    ConratulationWinner();
                else
                    NextGame();
            }

            else
            {
                NextGame();
            }

        }
        catch(Exception er)
        {
            Log.e("btnGoToPlayGAmeA",er.getMessage());
        }

    }

    private void ConratulationWinner() {
        Intent intent = new Intent(this, CongratulationActivity.class);
        intent.putExtra("idTeam", teamPlayerWin.getIdTeam());
        intent.putExtra("score", teamPlayerWin.getScoreAll());
        startActivity(intent);
    }

    private void NextGame() {
        Intent intent = new Intent(this, PlayGameActivity.class) ;
        intent.putExtra("idRound",idNextRound);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}


///АДАПТЕР ДЛЯ ВЫВОДА СПИСКА КОМАНД УЧАСТВУЮЩИХ В ИГРЕ
    class TeamGameAdapter extends BaseAdapter {
    IDbService dbService;
    final String idGame;
    final List<Team> listTeam;
    final Context context;
        public TeamGameAdapter(Context context, List<Team> listTeam, IDbService dbService, String idGame) {
            this.context = context;
            this.listTeam = listTeam;
            this.dbService = dbService;
            this.idGame = idGame;
        }

            @Override
            public int getCount() {
                return listTeam.size();
            }

            @Override
            public Object getItem(int position) {
                return listTeam.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                try{
                    Team team = (Team)getItem(position);
                    PlayingTeams playingTeam = dbService.getEPlayingTeamsService().getPlayingTeams(team.getIdteam(), idGame);
                    if(view==null)
                    {
                        view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_teamscore,parent,false);
                    }
                    ImageView imgAvatar = view.findViewById(R.id.imgTeamAvatar);
                    TextView teamName = view.findViewById(R.id.tvComandName);
                    TextView scoreAll = view.findViewById(R.id.tvScoreAll);
                    TextView scoreRound = view.findViewById(R.id.tvScoreRound);
                    teamName.setText(team.getName());
                    scoreAll.setText(String.valueOf(playingTeam.getScoreAll()));
                    scoreRound.setText(String.valueOf(playingTeam.getScoreRound()));
                    int idImg = context.getResources().getIdentifier(team.getAvatar(),"drawable", context.getPackageName());
                    imgAvatar.setImageResource(idImg);
                    return view;
                }
                catch(Exception er)
                {
                    Log.e("ADAPTTEAMSCORE", er.getMessage());
                    return view;
                }
            }

    }
