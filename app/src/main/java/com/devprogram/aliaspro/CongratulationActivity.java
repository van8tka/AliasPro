package com.devprogram.aliaspro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Team;

public class CongratulationActivity extends AppCompatActivity {

    IDbService dbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String idWinner = getIntent().getStringExtra("idTeam");
        dbService = new DbService();
        Team team = dbService.getETeamService().getTeam(idWinner);
        TextView tvName = findViewById(R.id.tvWinerTeamName);
        TextView tvScrore = findViewById(R.id.tvWinerScore);
        tvName.setText(team.getName());
        tvScrore.setText(Integer.toString(team.getScoreAll()));
    }

    //метод выхода из активити
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    public void onDestroy()
    {
        dbService.CloseDb();
        super.onDestroy();
    }


    public void btnEndGame_Click(View view)
   {
        this.onBackPressed();
   }
}
