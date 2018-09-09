package com.devprogram.aliaspro;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;
import com.devprogram.aliaspro.Models.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class PlayGameActivity extends AppCompatActivity implements View.OnTouchListener {

    boolean _timeRoundIsFinish = false;
    IDbService dbService;
    Team team;
    Game game;
    Round round;
    Dictionary dictionary;
    Task task;
    List<Word> wordList;
    List<Word> showedWordList;
    TextView tvGuesed;
    int countGuesedWord;
    int countSkipedWord;
    private int  _yDelta;

    TextView tvSkipped;
    TextView tvTimeDur;
    TextView tvWord;
    RelativeLayout rlDialog;
    RelativeLayout parentDialog;
    ViewConfiguration defView;
    LinearLayout linearGues;
    LinearLayout linearSkip;
    public static final String TAG_PLAY_GAME = "PlayGameActivity";
    float YDef;
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
            tvWord = findViewById(R.id.tvWordPlayGame);
            tvWord.setText(showedWordList.get(showedWordList.size()-1).getName());
            rlDialog = findViewById(R.id.rlDialogFonPlayGame);
            defView = ViewConfiguration.get(rlDialog.getContext());
            parentDialog = findViewById(R.id.rellayrootDialog);
            tvTimeDur.setText(Integer.toString(game.getSeconds()));
            linearGues = findViewById(R.id.linGues);
            linearSkip = findViewById(R.id.linSkip);
            rlDialog.setOnTouchListener(this);
            countGuesedWord = 0;
            countSkipedWord = 0;
            SetTask();
            boolean isTask = game.getIstask();
            if(isTask){
                CreateDialogTaskDescription();
            }
        }
        catch (Exception er)
        {
         Log.e(TAG_PLAY_GAME,er.getMessage());
            Toast.makeText(this,er.getMessage(),Toast.LENGTH_LONG);
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
            String idGame = getIntent().getStringExtra("idGame");
            String idRound = getIntent().getStringExtra("idRound");
            dbService = new DbService();
            game = dbService.getEGameService().getGame(idGame);
            round = dbService.getERoundService().getRound(idRound);
            task = round.getTask();
            dictionary = dbService.getEGameService().getGame(idGame).getDictionary();
            wordList = dbService.getEDictionaryService().getWordsDictionary(dictionary.getIddictionary());
            showedWordList = new ArrayList<Word>();
            GetNextShowWord(wordList);
        }
        catch(Exception er)
        {
            Log.e("ERROR GET DATA",er.getMessage());
        }
    }

    private void GetNextShowWord(List<Word> wordList) {
        try{
            boolean isShowedWord = true;
            int indexWord;
            int count = wordList.size()-1;
//необходима проверка есть ли непоказанные слова в списке если есть то выбираем иначе - слова закочились или заново
        boolean isNotExistShowedWord = true;
        for(int i=0;i<wordList.size()-1;i++)
         {
            if(!wordList.get(i).getIsshowed())
            {
                isNotExistShowedWord = false;
                break;
            }
         }
         if(isNotExistShowedWord)
             DoWordsForShow();

            do
            {
                indexWord = (int)(Math.random()*((count-0)+1))+0;
                isShowedWord = wordList.get(indexWord).getIsshowed();
            }
            while(isShowedWord);
            showedWordList.add(wordList.get(indexWord));
            dbService.getEWordService().updateShowedOfWord(wordList.get(indexWord).getIdword(),true);
        }
        catch (Exception er)
        {
            Log.e("GETNEXTSHWord",er.getMessage());
        }
    }
//если закончились все слова
    private void DoWordsForShow() {
        try{
          for(int i=0;i<wordList.size()-1;i++)
          {
              Boolean contains=false;

              for(int j=0;j<showedWordList.size()-1;j++)
              {
                  if(wordList.get(i) == showedWordList.get(i))
                  {
                      contains = true;
                      break;
                  }
              }
              if(!contains)
              {
                  dbService.getEWordService().updateShowedOfWord(wordList.get(i).getIdword(),false);
                  WordStatus ws = dbService.getEWordStatusService().getWordsStatus().get(1);
                  dbService.getEWordService().updateStatusOfWord(wordList.get(i).getIdword(),ws);
              }
          }
      }
      catch (Exception er)
      {
          Log.e("finishWORDS", er.getMessage());
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
                            tvWord.setText(showedWordList.get(showedWordList.size()-1).getName());
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
           int topWord = (int)v.getY()+100;
           int bottomWord = (int)v.getY()+v.getHeight()-100;
           int guessBorder = (int) linearGues.getY()+linearGues.getHeight();
           int skipeBorder = (int) linearSkip.getY();
           boolean isChange = false;
           if(topWord<guessBorder)
           {
               countGuesedWord++;
               tvGuesed.setText(String.valueOf(countGuesedWord));
               SetAnimationTransitionView(v);
               isChange = true;
               ShowNextWord(true);
           }
           if(bottomWord>skipeBorder)
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

    private void ShowNextWord(boolean isGues) {
        try{
            WordStatus status;
            if(isGues)
            {
                status = dbService.getEWordStatusService().getWordsStatus().get(0);
            }
            else
            {
                status = dbService.getEWordStatusService().getWordsStatus().get(1);
            }
            Word showedWord = showedWordList.get(showedWordList.size()-1);
            dbService.getEWordService().updateStatusOfWord(showedWord.getIdword(),status);
            dbService.getERoundService().addWordRound(round.getIdround(),showedWord);
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

