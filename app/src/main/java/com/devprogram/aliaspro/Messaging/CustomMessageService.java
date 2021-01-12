package com.devprogram.aliaspro.Messaging;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.devprogram.aliaspro.MainActivity;
import com.devprogram.aliaspro.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.Locale;
import java.util.Map;

public class CustomMessageService extends FirebaseMessagingService
{
    @Override
    public void onNewToken(String s)
    {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
    }
    //data notification example
    //    {
    //        lang:ru;
    //        link:https://play.google.com/;
    //        click_action:MAIN
    //    }
    //set Manifest properies, SplashScreenActivity getExtras when background and MainActivity invoke link



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);
        String language = remoteMessage.getData().get("lang");
        String link = remoteMessage.getData().get("link");
        String systemLang = Locale.getDefault().getLanguage();
        if(language == null || language.equalsIgnoreCase(systemLang))
            showNotification(remoteMessage.getNotification().getBody(), link);
    }

    private void showNotification(String body, String link ) {
        Intent intent = new Intent(this, MainActivity.class);
        if(link!=null)
            intent.putExtra("link",link);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notify = new NotificationCompat.Builder(this,"CHANEL_ID")
                .setSmallIcon(R.mipmap.ic_notify)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("ALIAS PRO")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //since android oreo notification cjannel is needed
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O)
        {
            NotificationChannel chan = new NotificationChannel(channelId,"Channel human readebale title",NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(chan);
        }
        manager.notify(0,notify.build());
    }
}
