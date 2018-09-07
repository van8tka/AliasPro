package com.devprogram.aliaspro;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Difficulty;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

public class MainActivity extends AppCompatActivity   {

    private String nameDb = "aliasdb.realm";
    IDbService dbService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConfigRealm();
        dbService = new DbService();
        InitialDataDb initDb = new InitialDataDb(dbService);
        initDb.InitializeDbRealm();
    }


    private void ConfigRealm() {
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name(nameDb)
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public void btnNewGame_Click(View v)
    {
        //перед началом игры сбросим все данные
        ResetDataInDB();
        Intent intent = new Intent(MainActivity.this, SettingsGameActivity.class);
        startActivity(intent);
    }
    //сброс данных в БД
    private void ResetDataInDB() {
        try {
            List<Word> words = dbService.getEWordService().getWords();
            WordStatus statusWordSkeep = dbService.getEWordStatusService().getWordsStatus().get(1);
            for (Word wd : words)
                dbService.getEWordService().updareWord(wd.getIdword(), wd.getName(), wd.getLanguage(), statusWordSkeep, false);
            List<Team> teams = dbService.getETeamService().getTeams();
            Language rus = dbService.getELanguageService().getLanguages().get(0);
            for (Team tm : teams)
                dbService.getETeamService().updateTeam(tm.getIdteam(), tm.getName(), tm.getAvatar(), 0, 0, false, rus);
            List<Task> tasks = dbService.getETaskService().getTasks();
            for (Task ts : tasks)
                dbService.getETaskService().updateTask(ts.getIdtask(), ts.getName(), ts.getDescription(), ts.getAvatar(), false, 2, ts.getLanguage());
            List<Round> rounds = dbService.getERoundService().getRounds();
          //FIXME
//            for (Round rd : rounds)
//                dbService.getERoundService().deleteRound(rd.getIdround());
        } catch (Exception er) {
            Log.e("RESETDATAinDB", er.getMessage());
            Toast.makeText(this, "При восстановлении данных в базе данных произошла ошибка: " + er.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void btnContinue_Click(View v)
    {
        //TODO сделать обработку при нажатии на продолжение игры
    }

    public void btnHowToPlay_Click(View v)
    {
        Intent intent = new Intent(MainActivity.this, RulesActivity.class);
        startActivity(intent);
    }

    public void btnLike_Click(View v)
    {
        //FIXME поменять ссылку на игру
        String urlAppInGooglePlay = "https://play.google.com/store/apps/details?id=cardsofwords.cardsofwords";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAppInGooglePlay));
        startActivity(intent);
    }

    @Override
    public void onDestroy()
    {
        dbService.CloseDb();
        super.onDestroy();
    }
}
