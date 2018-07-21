package com.devprogram.aliaspro;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;

import java.util.List;

public class PlayGameActivity extends AppCompatActivity {

    boolean _timeRoundIsFinish = false;
    IDbService dbService;
    Team team;
    Game game;
    Round round;
    Task task;
    List<Word> wordList;
    TextView tvGuesed;
    TextView tvSkipped;
    TextView tvTimeDur;
    public static final String TAG_PLAY_GAME = "PlayGameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_play_game);
            GetData();
            String nameTeam = round.getTeam().getName();
            this.setTitle(nameTeam);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            tvGuesed = findViewById(R.id.tvCoutGuessed);
            tvSkipped = findViewById(R.id.tvCountSkipped);
            tvTimeDur = findViewById(R.id.tvTimeDuration);
            tvTimeDur.setText(Integer.toString(game.getSeconds()));
            SetTask();
            CreateDialogTaskDescription();

        }
        catch (Exception er)
        {
         Log.e(TAG_PLAY_GAME,er.getMessage());
        }
    }

    private void CreateDialogTaskDescription() {
        CustomDialogTaskDescription taskDescriptionDialog = new CustomDialogTaskDescription(this, task);
        taskDescriptionDialog.show();
    }

    //установка задачи если выбрана в настройках
    private void SetTask() {
        LinearLayout llTask = findViewById(R.id.llTaskInGame);
        TextView tvTask = findViewById(R.id.tvTaskNameInGame);
        ImageView imgTask = findViewById(R.id.imgTaskAvatarInGame);

        if(game.getIstask())
        {
            llTask.setVisibility(View.VISIBLE);
            tvTask.setText(task.getName());
            int idres = this.getResources().getIdentifier(task.getAvatar(),"drawable",getPackageName());
            imgTask.setImageResource(idres);
        }
        else
        {
            llTask.setVisibility(View.GONE);
        }
    }


    //установка данных при запуске активити
    private void GetData() {
        String idGame = getIntent().getStringExtra("idGame");
        String idRound = getIntent().getStringExtra("idRound");
        dbService = new DbService();
        game = dbService.getEGameService().getGame(idGame);
        round = dbService.getERoundService().getRound(idRound);
        task = round.getTask();
    }

//метод выхода из активити
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


//начало игры
    public void btnStartPlay_Click(View view) {
        try{
            Button btnStart = findViewById(R.id.btnStartPlayGame);
            btnStart.setVisibility(View.GONE);
            TimerStart();
        }
        catch(Exception er)
        {
            Log.e(TAG_PLAY_GAME,er.getMessage());
        }

    }


    //отсечка времени раунда
    private void TimerStart() {
        new CountDownTimer((long)game.getSeconds()*1000,1000){

            @Override
            public void onTick(long millisUntilFinished){
                tvTimeDur.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                tvTimeDur.setText("0");
                 _timeRoundIsFinish = true;
            }
        }.start();
    }


    public void TaskToShowDescription_OnClick(View view) {
        Toast.makeText(this, task.getDescription(), Toast.LENGTH_LONG).show();
    }
}
class CustomDialogTimeFinish extends Dialog {

    public CustomDialogTimeFinish(@NonNull Activity activity) {
        super(activity);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.custom_dialog);
//        yes = (Button) findViewById(R.id.btn_yes);
//        no = (Button) findViewById(R.id.btn_no);
//        yes.setOnClickListener(this);
//        no.setOnClickListener(this);
//
//    }
}


class CustomDialogTaskDescription extends Dialog implements View.OnClickListener {

    Task task;
    Activity activity;

    public CustomDialogTaskDescription(@NonNull Activity activity, Task task) {
        super(activity);
        this.activity = activity;
        this.task = task;
    }


    @Override
    protected void onCreate(Bundle instance)
    {
        super.onCreate(instance);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_task_description);
        TextView tvTaskName = findViewById(R.id.tvTaskNameDialog);
        TextView tvTaskDesc = findViewById(R.id.tvTaskDescriptionDialog);
        ImageView imgTaskAvatar = findViewById(R.id.imgTaskAvatarDialog);
        tvTaskName.setText(task.getName());
        tvTaskDesc.setText(task.getDescription());
        int id = activity.getResources().getIdentifier(task.getAvatar(),"drawable",activity.getPackageName());
        imgTaskAvatar.setImageResource(id);
        Button btn = findViewById(R.id.btnCloseTaskDescriptionDialog);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        cancel();
    }
}

