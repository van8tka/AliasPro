package com.devprogram.aliaspro;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class BeginGameActivity extends AppCompatActivity {

    IDbService dbService;
    Game currentGame;
    Round currentRound;
    ListView listView;
    TextView tvNameTeamGame;
    TextView textvRoundName;
    ImageView imgAvatarTeamGame;
    List<Team> listTeam;
    long countTeamsInGame;
    Boolean isChangeOrientation ;
    LinearLayout linRound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isChangeOrientation = false;
        setContentView(R.layout.activity_begin_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String idGame = getIntent().getStringExtra("idGame");
        String idRound = getIntent().getStringExtra("idRound");
        dbService = new DbService();
        currentGame = dbService.getEGameService().getGame(idGame);
        currentRound = dbService.getERoundService().getRound(idRound);
        listTeam = currentGame.getTeams();
        countTeamsInGame = listTeam.size();
        listView = findViewById(R.id.lvTeamInGame);
        tvNameTeamGame = findViewById(R.id.tvPlayTeamName);
        imgAvatarTeamGame = findViewById(R.id.imgPlayTeamAvatar);
        textvRoundName = findViewById(R.id.tvRoundName);
        linRound = findViewById(R.id.linRoundBegin);

        //след играют
        SetNextCommandPlay();
        listView.setAdapter(new TeamGameAdapter(this,listTeam));
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig)
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






private static final String TAG_BEGIN_GAME ="BeginGameActivity";
//установка параметров слледующего раунда
    private void SetNextCommandPlay() {
        try
        {
            if (!isChangeOrientation) {
                Team getLastTeam = currentRound.getTeam();
                int indexLast = listTeam.indexOf(getLastTeam);
                int numberRound = currentRound.getNumber();
                //проверитьна переполнение
                if (indexLast < countTeamsInGame) {
                    indexLast++;
                } else ;
                {
                    indexLast = 0;
                    numberRound++;
                }
                String nameRound = this.getResources().getString(R.string.strRound) + " " + Integer.toString(numberRound);
                textvRoundName.setText(nameRound);
                Team newTeamPlay = listTeam.get(indexLast);
                tvNameTeamGame.setText(newTeamPlay.getName());
                int idImg = this.getResources().getIdentifier(newTeamPlay.getAvatar(), "drawable", this.getPackageName());
                imgAvatarTeamGame.setImageResource(idImg);
                dbService.getERoundService().updateRound(currentRound.getIdround(), currentRound.getName(), newTeamPlay, null, dbService.getETaskService().getTaskRandom(), currentGame, numberRound);
            }
        }
        catch (Exception er)
        {
            Log.e(TAG_BEGIN_GAME,er.getMessage());
        }
    }


    public void btnStartGame_Click(View view) {
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}



    class TeamGameAdapter extends BaseAdapter {

    final List<Team> listTeam;
    final Context context;
        public TeamGameAdapter(Context context, List<Team> listTeam) {
            this.context = context;
            this.listTeam = listTeam;
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
                Team team = (Team)getItem(position);
                if(view==null)
                {
                    view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_teamscore,parent,false);
                }
                ImageView imgAvatar = view.findViewById(R.id.imgTeamAvatar);
                TextView teamName = view.findViewById(R.id.tvComandName);
                TextView scoreAll = view.findViewById(R.id.tvScoreAll);
                TextView scoreRound = view.findViewById(R.id.tvScoreRound);
                teamName.setText(team.getName());
                scoreAll.setText(String.valueOf(team.getScore()));
                scoreRound.setText("0");
                int idImg = context.getResources().getIdentifier(team.getAvatar(),"drawable", context.getPackageName());
                imgAvatar.setImageResource(idImg);
                return view;
            }
    }
