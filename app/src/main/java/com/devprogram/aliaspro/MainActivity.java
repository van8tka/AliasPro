package com.devprogram.aliaspro;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.crashlytics.android.Crashlytics;
import com.devprogram.aliaspro.DAL.Implementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Helpers.AdMobCreater;
import com.devprogram.aliaspro.Helpers.Constants;
import com.devprogram.aliaspro.Helpers.IAdMobCreater;
import com.devprogram.aliaspro.Initializer.Emplementations.InitialDataDb;
import com.devprogram.aliaspro.Initializer.Interfaces.IInitDB;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.RestService.NetConnection;
import com.devprogram.aliaspro.RestService.RestServiceGetVersionApp;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    IInitDB initDb;
    IDbService dbService;
    Game lastGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            CheckVersionApp();
            setContentView(R.layout.activity_main);
            ChechInvokeLinkFromNotification();
            ConfigRealm();
            CreateAdMob();
            dbService = new DbService();
            initDb = new InitialDataDb(dbService, this);
            initDb.InitializeItems();
        } catch (Exception er) {
            Crashlytics.logException(er);
            Log.e("MAIN_ERR", er.getMessage());
        }
    }
///метод проверки обновления приложения и предложения обновить
    private void CheckVersionApp(){
        try
        {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),0);
            Float version = Float.parseFloat( info.versionName );
            RestServiceGetVersionApp restService = new RestServiceGetVersionApp(new NetConnection(this), version, this);
            restService.execute();
        }
        catch (Exception e)
        {
            Crashlytics.logException(e);
        }
    }



    //переход по ссылке в уведомлении
    private void ChechInvokeLinkFromNotification() {
        try{
            String link = getIntent().getStringExtra("link");
            if(link!=null)
            {
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }
        catch(Exception er)
        {
            Crashlytics.logException(er);
            Log.e("CHECKLINKInMAIN",er.getMessage());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        CheckLastGameIsFinish();
    }


    //проверка последней игры - закончена то кнопка продолжить задизэйблена или не закончена то продолжить
    //необходимо активити для продолжения и время прерывания для старта в playgame
    private void CheckLastGameIsFinish() {
        try {
            Button btnContinue = findViewById(R.id.btnContinue);
            if (!dbService.getEGameService().getGames().isEmpty()) {
                lastGame = dbService.getEGameService().getLastGame();
                Boolean isFinish = lastGame.getIsfinishgame();
                if (isFinish) {
                    btnContinue.setEnabled(false);
                    btnContinue.setBackgroundResource(R.drawable.button_round_corner_disable);
                } else {
                    btnContinue.setEnabled(true);
                    btnContinue.setBackgroundResource(R.drawable.button_rounded_corner);
                }
            } else {
                btnContinue.setEnabled(false);
                btnContinue.setBackgroundResource(R.drawable.button_round_corner_disable);
            }
        } catch (Exception er) {
            Crashlytics.logException(er);
            Log.e("MAIN_CHECKLAST", er.getMessage());
        }
    }

    private void CreateAdMob() {
        IAdMobCreater adMobCreater = new AdMobCreater();
        adMobCreater.InitAdMobBanner(findViewById(R.id.bannerAdmobFragmentMain), getApplicationContext(), getResources().getString(R.string.admob_pub_id));
    }


    //конфиг БД
    private void ConfigRealm() {
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name(Constants.REALM_DB_NAME)
                .schemaVersion(1)
               .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    //новая игра
    public void btnNewGame_Click(View v) {
        Intent intent = new Intent(MainActivity.this, SettingsGameActivity.class);
        startActivity(intent);
    }

    //продолжить иру
    public void btnContinue_Click(View v) {
        try {
            Round round = dbService.getERoundService().getLastRoundByGame(lastGame.getIdgame());
            Intent intent;
            //если игра прервалась в процессе раунда
            if (round.getTimeToFinish() > -1) {
                intent = new Intent(this, PlayGameActivity.class);
                intent.putExtra("idRound", round.getIdround());
            } else//если игра прервалась по окончании раунда
            {
                intent = new Intent(this, BeginGameActivity.class);
                intent.putExtra("idRound", round.getIdround());
                intent.putExtra("idGame", lastGame.getIdgame());
            }
            startActivity(intent);
        } catch (Exception er) {
            Crashlytics.logException(er);
            Log.e("MAIN_CONTINUEBTN", er.getMessage());
        }
    }

    //правила игры
    public void btnHowToPlay_Click(View v) {
        Intent intent = new Intent(MainActivity.this, RulesActivity.class);
        startActivity(intent);
    }

    public void btnLike_Click(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_GOOGLEPLAY_ALIASPRO));
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        dbService.CloseDb();
        super.onDestroy();
    }
}
