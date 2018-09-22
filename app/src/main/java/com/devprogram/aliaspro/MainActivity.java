package com.devprogram.aliaspro;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.devprogram.aliaspro.DAL.Implementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Initializer.Emplementations.InitialDataDb;
import com.devprogram.aliaspro.Initializer.Interfaces.IInitDB;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity   {

    private String nameDb = "aliasdb.realm";
    IInitDB initDb;
    IDbService dbService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConfigRealm();
        dbService = new DbService();
        initDb = new InitialDataDb(dbService);
        initDb.InitializeItems();
    }

//конфиг БД
    private void ConfigRealm() {
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name(nameDb)
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    //новая игра
    public void btnNewGame_Click(View v)
    {
        Intent intent = new Intent(MainActivity.this, SettingsGameActivity.class);
        startActivity(intent);
    }

   //продолжить
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
