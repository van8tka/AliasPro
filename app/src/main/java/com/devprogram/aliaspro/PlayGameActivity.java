package com.devprogram.aliaspro;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;

import java.util.List;

public class PlayGameActivity extends AppCompatActivity {


    IDbService dbService;
    Team team;
    Game game;
    Round round;
    Task task;
    List<Word> wordList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        GetData();
        String nameTeam = round.getTeam().getName();
        this.setTitle(nameTeam);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void GetData() {
        String idGame = getIntent().getStringExtra("idGame");
        String idRound = getIntent().getStringExtra("idRound");
        dbService = new DbService();
        game = dbService.getEGameService().getGame(idGame);
        round = dbService.getERoundService().getRound(idRound);
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
