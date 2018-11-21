package com.devprogram.aliaspro.Messaging;


import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class CustomMessageService extends FirebaseMessagingService
{
    @Override
    public void onNewToken(String s)
    {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);
        Log.i("MSG_FROM","FROM: "+remoteMessage.getFrom());
        Log.i("MSG_BODY","BODY:"+remoteMessage.getNotification().getBody());
        showNotification(remoteMessage.getNotification().getBody());
    }

    private void showNotification(String body) {
    }
}
