package com.devprogram.aliaspro;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;

public class BeginGameActivity extends AppCompatActivity {

    IDbService dbService;
    Game currentGame;
    ListView listView;
    TextView tvNameTeamGame;
    ImageView imgAvatarTeamGame;
    List<Team> listTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String idGame = getIntent().getStringExtra("idGame");
        dbService = new DbService();
        currentGame = dbService.getEGameService().getGame(idGame);
        listTeam = currentGame.getTeams();
        listView = findViewById(R.id.lvTeamInGame);
        tvNameTeamGame = findViewById(R.id.tvPlayTeamName);
        imgAvatarTeamGame = findViewById(R.id.imgPlayTeamAvatar);

        listView.setAdapter(new TeamGameAdapter(this,listTeam));
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
