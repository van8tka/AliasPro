package com.devprogram.aliaspro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.devprogram.aliaspro.Helpers.AdMobCreater;
import com.devprogram.aliaspro.Helpers.IAdMobCreater;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        CreateAdMob();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void CreateAdMob() {
        IAdMobCreater adMobCreater = new AdMobCreater();
        adMobCreater.InitAdMobBanner(findViewById(R.id.bannerAdmobFragmentRules),getApplicationContext(),getResources().getString(R.string.admob_pub_id));
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
