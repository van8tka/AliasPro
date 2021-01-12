package com.devprogram.aliaspro;

import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessagingRegister();
        String link = getIntent().getStringExtra("link");
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                if(link!=null)
                    intent.putExtra("link",link);
                startActivity(intent);
                finish();
    }

    private void FirebaseMessagingRegister() {
//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(SplashScreenActivity.this, new OnSuccessListener<InstanceIdResult>() {
//            @Override
//            public void onSuccess(InstanceIdResult instanceIdResult) {
//                String newToken = instanceIdResult.getToken();
//                Log.e("GETTOKEN",newToken);
//            }
//        });
    }


}
