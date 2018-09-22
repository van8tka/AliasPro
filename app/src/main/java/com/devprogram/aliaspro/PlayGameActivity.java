package com.devprogram.aliaspro;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.devprogram.aliaspro.DAL.Implementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.ArrayList;
import java.util.List;

public class PlayGameActivity extends AppCompatActivity implements View.OnTouchListener {

    boolean _timeRoundIsFinish = false;
    IDbService dbService;

    Team team;
    Game game;
    Round round;
   // Dictionary dictionary;
    Task task;
    List<Word> wordList;
   // List<Word> showedWordList;
    Word showedWord;

    int countGuesedWord;
    int countSkipedWord;
    private int  _yDelta;

    TextView tvGuesed;
    TextView tvSkipped;
    TextView tvTimeDur;
    TextView tvWord;

    RelativeLayout rlDialog;
    RelativeLayout parentDialog;
    ViewConfiguration defView;
    LinearLayout linearGues;
    LinearLayout linearSkip;

    float YDef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_play_game);
            GetData();
            String nameTeam = team.getName();
            this.setTitle(nameTeam);

            tvGuesed = findViewById(R.id.tvCoutGuessed);
            tvSkipped = findViewById(R.id.tvCountSkipped);
            tvTimeDur = findViewById(R.id.tvTimeDuration);
            tvWord = findViewById(R.id.tvWordPlayGame);
            rlDialog = findViewById(R.id.rlDialogFonPlayGame);
            defView = ViewConfiguration.get(rlDialog.getContext());
            parentDialog = findViewById(R.id.rellayrootDialog);
            linearGues = findViewById(R.id.linGues);
            linearSkip = findViewById(R.id.linSkip);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            tvWord.setText(showedWord.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            tvTimeDur.setText(Integer.toString(game.getSeconds()));
            rlDialog.setOnTouchListener(this);
            countGuesedWord = 0;
            countSkipedWord = 0;
            SetTask();

        }
        catch (Exception er)
        {
            Log.e("PlayGameActivity",er.getMessage());
            Toast.makeText(this,er.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy()
    {
        dbService.CloseDb();
        super.onDestroy();
    }

    //создание диалогового окна описания дополнительной задачи
    private void CreateDialogTaskDescription() {
        CustomDialogTaskDescription taskDescriptionDialog = new CustomDialogTaskDescription(this, task);
        taskDescriptionDialog.show();
    }

    //установка задачи если выбрана в настройках
    private void SetTask() {
        try{
            LinearLayout llTask = findViewById(R.id.llTaskInGame);
            TextView tvTask = findViewById(R.id.tvTaskNameInGame);
            ImageView imgTask = findViewById(R.id.imgTaskAvatarInGame);
            if(game.getIstask())
            {
                llTask.setVisibility(View.VISIBLE);
                tvTask.setText(task.getName());
                int idres = this.getResources().getIdentifier(task.getAvatar(),"drawable",getPackageName());
                imgTask.setImageResource(idres);
                CreateDialogTaskDescription();
            }
            else
            {
                llTask.setVisibility(View.GONE);
            }
        }
        catch (Exception er)
        {
            Log.e("SETTASK",er.getMessage());
        }
    }


    //установка данных при запуске активити
    private void GetData() {
        try{
            String idRound = getIntent().getStringExtra("idRound");
            dbService = new DbService();
            round = dbService.getERoundService().getRound(idRound);
            game = dbService.getEGameService().getGame(round.getGame());
            team = dbService.getETeamService().getTeam(round.getTeam());
            if(game.getIstask())
                task = dbService.getETaskService().getTask(round.getTask());
            wordList = dbService.getEWordService().getWordsWithOutShowed(game.getDictionary(), game.getIdgame());
            showedWord = wordList.get(0);
        }
        catch(Exception er)
        {
            Log.e("ERROR GET DATA",er.getMessage());
        }
    }
//выбор следующего слова для отображения
    private void GetNextShowWord(List<Word> wordList) {
        try{
            boolean isShowedWord = true;
            int indexWord;
            int count = wordList.size()-1;
            if(count == 0)
                count = DoWordsForShow();
            indexWord = (int)(Math.random()*((count-0)+1))+0;
            showedWord = wordList.get(indexWord);
            wordList.remove(showedWord);
        }
        catch (Exception er)
        {
            Log.e("GETNEXTSHWord",er.getMessage());
            Toast.makeText(this,"No words list error!",Toast.LENGTH_LONG).show();
        }
    }



//если закончились все слова
    private int DoWordsForShow() {
        try{
          wordList = dbService.getEWordService().getWordsFromDictionary(game.getDictionary());
          dbService.getEWordStatusService().deleteWordStatusForGame(game.getIdgame());
          return wordList.size()-1;
      }
      catch (Exception er)
      {
          Log.e("finishWORDS", er.getMessage());
          return -1;
      }
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
            rlDialog.setVisibility(View.VISIBLE);
            TimerStart();
        }
        catch(Exception er)
        {
            Log.e("BTNSTARTPLAY",er.getMessage());
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

//отображение описания задачи
    public void TaskToShowDescription_OnClick(View view) {
        Toast.makeText(this, task.getDescription(), Toast.LENGTH_LONG).show();
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try
        {
            if(v instanceof RelativeLayout)
            {
                YDef = v.getTop();
                switch(event.getAction() & MotionEvent.ACTION_MASK)
                {
                    case MotionEvent.ACTION_DOWN:
                    {
                        _yDelta = (int)(v.getY() - event.getRawY());
                        break;
                    }
                    case MotionEvent.ACTION_MOVE :
                    {
                        v.animate().y(event.getRawY() + _yDelta).setDuration(0).start();
                        break;
                    }
                    case MotionEvent.ACTION_UP :
                    {
                        boolean isChangeState = CheckStateWord(v);
                        if(isChangeState)
                            tvWord.setText(showedWord.getName());
                        break;
                    }
                    default: return true;
                }
            }
            parentDialog.invalidate();
            return true;
        }
        catch(Exception er)
        {
            Log.e("ONTOUCH",er.getMessage());
            return false;
        }

    }
//при перемещении проверяем нахождение слова в границах отгаданого или пропущенного слова и смены состояния
    private boolean CheckStateWord(View v) {
       try{
           int yCenterWord = (int)(v.getY()+v.getHeight()/2);
           int guessBorder = (int) linearGues.getY()+linearGues.getHeight();
           int skipeBorder = (int) linearSkip.getY();
           boolean isChange = false;
           if(yCenterWord<guessBorder)
           {
               countGuesedWord++;
               tvGuesed.setText(String.valueOf(countGuesedWord));
               SetAnimationTransitionView(v);
               isChange = true;
               ShowNextWord(true);
           }
           if(yCenterWord>skipeBorder)
           {
               countSkipedWord++;
               tvSkipped.setText(String.valueOf(countSkipedWord));
               SetAnimationTransitionView(v);
               isChange = true;
               ShowNextWord(false);
           }
           if(_timeRoundIsFinish)
           {
               Intent intent = new Intent(PlayGameActivity.this,RoundResultActivity.class);
               intent.putExtra("idroundCurrent",round.getIdround());
               dbService.getEPlayingTeamsService ().setScoreRound(team.getIdteam(),round.getGame(),countGuesedWord);
               int countAllscroeTeam =  dbService.getEPlayingTeamsService().getPlayingTeams(team.getIdteam(),game.getIdgame()).getScoreAll();
               dbService.getEPlayingTeamsService().setScoreAll (team.getIdteam(),game.getIdgame(),countGuesedWord+countAllscroeTeam);
               startActivity(intent);
           }
           return isChange;
       }
       catch(Exception er)
       {
           Log.e("ERROR CHECK STATE WORD",er.getMessage());
           return false;
       }
    }
//for change scale view with word and set default position
    private void SetAnimationTransitionView(View v) {
        try
        {
            AnimatorSet setScale = new AnimatorSet();
            Animator scaleX = ObjectAnimator.ofFloat(v,"scaleX",0);
            Animator scaleY = ObjectAnimator.ofFloat(v,"scaleY",0);
            setScale.playTogether(scaleX,scaleY);
            setScale.setDuration(800);
            setScale.start();
            Animator defPosition = ObjectAnimator.ofFloat(v,"Y",YDef);
            Animator scaleXdef = ObjectAnimator.ofFloat(v,"scaleX",1f);
            Animator scaleYdef = ObjectAnimator.ofFloat(v,"scaleY",1f);
            AnimatorSet setDef = new AnimatorSet();
            setDef.playTogether(defPosition,scaleXdef,scaleYdef);
            setDef.setDuration(0);
            AnimatorSet setChain = new AnimatorSet();
            setChain.playSequentially(setScale,setDef);
            setChain.start();
        }
        catch(Exception er)
        {
            Log.e("SetANIM",er.getMessage());
        }
    }
//УСТАНОВКА СТАТУСА СЛОВА
    private void ShowNextWord(boolean isGues) {
        try{
           int status = 0;
            if(isGues)
            {
                status = 1;
            }
            else
            {
                status = 0;
            }
            dbService.getEWordStatusService().createWordStatus(status,showedWord.getIdword(),game.getIdgame(),round.getIdround());
            GetNextShowWord(wordList);
        }
        catch (Exception er)
        {
            Log.e("SHOWNEXTWORD",er.getMessage());
        }

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

//класс диалогового окна с описанием задачи загружается при первом появлении страницы PlayGameActivity
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
        try
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
        catch(Exception er)
        {
            Log.e("ONCREATECUSTOMDIALDESC",er.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        cancel();
    }
}

