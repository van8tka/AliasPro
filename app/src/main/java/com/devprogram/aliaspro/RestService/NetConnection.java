package com.devprogram.aliaspro.RestService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetConnection implements INetConnection {

    private final Context context;
    public NetConnection(Context context)
    {
        this.context = context;
    }
    @Override
    public boolean CheckConnection() {
        try
        {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNet = cm.getActiveNetworkInfo();
            return activeNet.isConnected();
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
