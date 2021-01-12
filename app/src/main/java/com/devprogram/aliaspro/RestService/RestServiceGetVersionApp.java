package com.devprogram.aliaspro.RestService;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.devprogram.aliaspro.Helpers.Constants;
import com.devprogram.aliaspro.R;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RestServiceGetVersionApp extends   AsyncTask<Void,Void,Float> {
    //ctor
    public RestServiceGetVersionApp(INetConnection connection, Float currentVersion, Context context) {
        netConnection = connection;
        path = "http://devprogram.ru/api/version/android_aliaspro";
        this.currentVersion = currentVersion;
        this.context = context;
    }
    private final String path;
    private final INetConnection netConnection;
    private final  Float currentVersion;
    private final Context context;
    @Override
    protected void onPreExecute() {
        //use progress bar if need
    }
    //выполненеи асинхронного метода для получения данных о версии приложения по сети
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
            Log.e("getAppVersion", e.getMessage());
            return 0f;
        } catch (IOException e) {
            Log.e("getAppVersion2", e.getMessage());
            return 0f;
        }
    }
//вызов диалогового окна с предложением обновить приложение
    @Override
    protected void onPostExecute(Float response) {
        if(response>0f && response>currentVersion)
        {
            DialogUpdateApp dialogUpdateApp = new DialogUpdateApp(context, response.toString(), currentVersion.toString());
            dialogUpdateApp.show();
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
            return 0f;
        }
    }
}
class DialogUpdateApp extends Dialog implements View.OnClickListener {

    public DialogUpdateApp(Context context, String versionAppNew, String versionAppOld) {
        super(context);
        this.versionAppNew = versionAppNew;
        this.versionAppOld = versionAppOld;
        this.context = context;
    }
    private Context context;
    private String versionAppNew;
    private String versionAppOld;
    private Button btnIsnt;
    private Button btnComp;

    @Override
    protected void onCreate(Bundle instance) {
        super.onCreate(instance);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_task_completed);
        btnComp = findViewById(R.id.btnTaskCompleted);
        btnIsnt = findViewById(R.id.btnTaskIsntCompleted);
        btnIsnt.setOnClickListener(this);
        btnComp.setOnClickListener(this);
        TextView title = findViewById(R.id.tvCustomDialogTitle);
        title.setText("Обновить приложение?");
        TextView content = findViewById(R.id.tvCustomDialogContent);
        content.setText("Доступна новая версия приложения " + versionAppNew + ", Вы используете версию "+versionAppOld+". Хотите обновить?");
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == btnComp.getId()) {
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(Constants.URL_GOOGLEPLAY_ALIASPRO));
            context.startActivity(intent);
        } else if (view.getId() == btnIsnt.getId()) {
            cancel();
        }
    }
}
