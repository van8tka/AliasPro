package com.devprogram.aliaspro;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Difficulty;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

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
        Intent intent = new Intent(MainActivity.this, SettingsGameActivity.class);
        startActivity(intent);
    }

    public void btnContinue_Click(View v)
    {

    }

    public void btnHowToPlay_Click(View v)
    {
        Intent intent = new Intent(MainActivity.this, RulesActivity.class);
        startActivity(intent);
    }

    public void btnLike_Click(View v)
    {
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
