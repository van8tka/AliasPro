package com.devprogram.aliaspro.RestService;


import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RestServiceGetVersionApp extends   AsyncTask<Void,Void,Float> {
    //ctor
    public RestServiceGetVersionApp(INetConnection connection, Float currentVersion) {
        netConnection = connection;
        path = "http://devprogram.ru/api/version/android_aliaspro";
        this.currentVersion = currentVersion;
    }

    private final String path;
    private final INetConnection netConnection;
    private final  Float currentVersion;

    @Override
    protected void onPreExecute() {
        //use progress bar if need
    }

    @Override
    protected Float doInBackground(Void... voids) {
        try {
            if (netConnection.CheckConnection()) {

                URL webApiUrl = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) webApiUrl.openConnection();
                connection.setRequestProperty("User-Agent", "com.devprogram.aliaspro");
                if (connection.getResponseCode() == 200) {
                    InputStream response = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(response, "UTF-8");
                    return readData(reader);
                }
            }
            return 0f;
        } catch (MalformedURLException e) {
            Crashlytics.logException(e);
            Log.e("getAppVersion", e.getMessage());
            return 0f;
        } catch (IOException e) {
            Crashlytics.logException(e);
            Log.e("getAppVersion2", e.getMessage());
            return 0f;
        }
    }

    @Override
    protected void onPostExecute(Float response) {
        if(response>0f && response>currentVersion)
        {

        }
    }



    private Float readData(InputStreamReader reader) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            int data;
            while ((data = reader.read()) != -1) {
                char symb = (char) data;
                if (symb != '"')
                    stringBuilder.append(symb);
            }
            try {
                return Float.valueOf(stringBuilder.toString());
            } catch (NumberFormatException e) {
                throw e;
            }
        } catch (Exception e) {
            Crashlytics.logException(e);
            return 0f;
        }
    }
}
