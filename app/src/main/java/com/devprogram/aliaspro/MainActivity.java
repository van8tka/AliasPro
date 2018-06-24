package com.devprogram.aliaspro;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

public class MainActivity extends AppCompatActivity   {

    private String nameDb = "aliasdb.realm";
DbService dbService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConfigRealm();
        dbService = new DbService();
       InitializeDbRealm();
    }

    private void InitializeDbRealm() {

        if(dbService.getETeamService().getTeams().size()==0)
        {
            String uuidRus = dbService.getELanguageService().createLanguage("Рус", null);
            dbService.getELanguageService().createLanguage("Анг", null);
            dbService.getELanguageService().createLanguage("Ger", null);
            dbService.getELanguageService().createLanguage("Бел", null);
            dbService.getELanguageService().createLanguage("Укр", null);
            dbService.getELanguageService().createLanguage("Каз", null);

            Language langRus = dbService.getELanguageService().getLanguage(uuidRus);
            dbService.getETeamService().createTeam("Охотники за приведениями","teamimage_ghost64",0,false,langRus);
            dbService.getETeamService().createTeam("Весёлые котята","teamimage_cat64",0,false,langRus);
            dbService.getETeamService().createTeam("Могучие львы","teamimage_lion64",0,false,langRus);
            dbService.getETeamService().createTeam("Бесстрашные воины","teamimage_helmet64",0,false,langRus);
            dbService.getETeamService().createTeam("Проворные калибри","teamimage_hummingbird64",0,false,langRus);
            dbService.getETeamService().createTeam("Летающие в небесах","teamimage_airplane64",0,false,langRus);
            dbService.getETeamService().createTeam("Зелёные человчки","teamimage_alien",0,false,langRus);
            dbService.getETeamService().createTeam("Молот и ки","teamimage_auction64",0,false,langRus);
            dbService.getETeamService().createTeam("Боевые орлы","teamimage_armyhelicopter64",0,false,langRus);
            dbService.getETeamService().createTeam("Смехатворы","teamimage_alien64",0,false,langRus);
            dbService.getETeamService().createTeam("Крутые пацаны","teamimage_baby64",0,false,langRus);
            dbService.getETeamService().createTeam("Серьезные девченки","teamimage_babygirl64",0,false,langRus);
            dbService.getETeamService().createTeam("Пчелки","teamimage_bee64",0,false,langRus);
            dbService.getETeamService().createTeam("Сбежавшие с Ноева ковчега","teamimage_cruise64",0,false,langRus);
            dbService.getETeamService().createTeam("Дракоши","teamimage_dragon64",0,false,langRus);
            dbService.getETeamService().createTeam("Эльфы","teamimage_elf64",0,false,langRus);
            dbService.getETeamService().createTeam("Золотые полосатики","teamimage_fish64",0,false,langRus);
            dbService.getETeamService().createTeam("Ромашки","teamimage_flower64",0,false,langRus);
            dbService.getETeamService().createTeam("Ежики","teamimage_hedgehog64",0,false,langRus);
            dbService.getETeamService().createTeam("Русалки","teamimage_mermaid64",0,false,langRus);
            dbService.getETeamService().createTeam("Любители денег","teamimage_moneybag64",0,false,langRus);
            dbService.getETeamService().createTeam("Око за око","teamimage_monster64",0,false,langRus);
            dbService.getETeamService().createTeam("Знатоки","teamimage_owl64",0,false,langRus);
            dbService.getETeamService().createTeam("Полицейские","teamimage_police64",0,false,langRus);
            dbService.getETeamService().createTeam("Задорные алхимики","teamimage_potion64",0,false,langRus);
            dbService.getETeamService().createTeam("Андроиды","teamimage_robot64",0,false,langRus);
            dbService.getETeamService().createTeam("Ракетчики","teamimage_spaceship64",0,false,langRus);
            dbService.getETeamService().createTeam("Подсолнухи","teamimage_sunflower64",0,false,langRus);
            dbService.getETeamService().createTeam("Танкисты игрушечных танков","teamimage_tank64",0,false,langRus);
            dbService.getETeamService().createTeam("Единороги","teamimage_unicorn64",0,false,langRus);
            dbService.getETeamService().createTeam("Маги земли","teamimage_wizard64",0,false,langRus);



        }

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
        dbService.Close();
        super.onDestroy();
    }
}
