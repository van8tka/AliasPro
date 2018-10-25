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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.devprogram.aliaspro.DAL.Implementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.PlayingTeams;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class PlayGameActivity extends AppCompatActivity implements View.OnTouchListener {

    boolean _timeRoundIsFinish = false;
    IDbService dbService;
    boolean isGuesedLastWord = false;
    Team team;
    Game game;
    Round round;
    boolean IsPayFineForSkeep;
    Task task;
    List<Word> wordList;
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
    TextView _tvLastWordShow;
    float YDef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_play_game);
            InitAdMob();
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
            _tvLastWordShow = findViewById(R.id.tvLastWordShow);
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

    private void InitAdMob() {
        MobileAds.initialize(getApplicationContext(),  getResources().getString(R.string.admob_pub_id));
        AdView adViewBanner = findViewById(R.id.banneradmobunitplaygame);
        AdRequest request = new AdRequest.Builder().build();
        adViewBanner.loadAd(request);
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
            IsPayFineForSkeep = game.getIspenalty();
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
            Log.i("GNW","GetNextShowWord - выбор следующего слова для отображения");
               int indexWord;
               int count = wordList.size()-1;
               if(count == 0)
                   count = DoWordsForShow();
               indexWord = (int)(Math.random()*((count-0)+1))+0;
               showedWord = wordList.get(indexWord);
               wordList.remove(showedWord);
               Log.i("GNW","GetNextShowWord -  следующее слово"+showedWord.getName());
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
            Log.i("GNW","btnStartPlay_Click - начало игры,нажатие старта");
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
                Log.i("TIMES","onFinish - ВРЕМЯ ВЫШЛО _timeRoundIsFinish = true");
                tvTimeDur.setText("0");
                 _timeRoundIsFinish = true;
                 _tvLastWordShow.setVisibility(View.VISIBLE);
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
            Log.i("ONT","onTouch - касание для перемещения слова");
            if(v instanceof RelativeLayout )
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
           if(yCenterWord<guessBorder)
           {
               Log.i("CHST","CheckStateWord - перемещение слова в отгаданные");
               if(!_timeRoundIsFinish || !game.getIslastword())
               {
                   Log.i("CHST","CheckStateWord - перемещение слова в отгаданные, время не вышло");
                   countGuesedWord++;
               }
               else
               {
                   Log.i("CHST","CheckStateWord - перемещение слова в отгаданные, время Вышло");
                   isGuesedLastWord = true;
               }

               tvGuesed.setText(String.valueOf(countGuesedWord));
               SetAnimationTransitionView(v);
               ShowNextWord(true);
               return true;
           }
           if(yCenterWord>skipeBorder)
           {
               Log.i("CHST","CheckStateWord - перемещение слова в НЕ отгаданные");
               if(IsPayFineForSkeep)//если включен штраф за пропуск слова то минус балл
               {
                   countGuesedWord--;
                   tvGuesed.setText(String.valueOf(countGuesedWord));
               }
               countSkipedWord++;
               tvSkipped.setText(String.valueOf(countSkipedWord));
               SetAnimationTransitionView(v);
               ShowNextWord(false);
               return true;
           }
           return false;
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
            AnimatorSet setChain  = new AnimatorSet();
            setChain.playSequentially(setScale,setDef);
            setChain.start();
            setChain.addListener(new Animator.AnimatorListener()
            {
               @Override
               public void onAnimationStart(Animator animation) {     }
               @Override
               public void onAnimationEnd(Animator animation)
               {
                   Log.i("AANIM","SetAnimationTransitionView - конец анимации, вызов CheckTimeFinsh");
                   if(isStartActivityResult)
                        startActivity(intentRoundResultActivity);
               }
               @Override
               public void onAnimationCancel(Animator animation) {     }
               @Override
               public void onAnimationRepeat(Animator animation) {     }
           });
        }
        catch(Exception er)
        {
            Log.e("SetANIM",er.getMessage());
        }
    }


    boolean isCheck = true;
    //IF TIME FINISH START RESULT ACTIVITY
    private void CheckTimeFinsh()
    {
        Log.i("CH","CheckTimeFinsh - проверка если время вышло");
        if(_timeRoundIsFinish && isCheck)
        {
            if(game.getIslastword() && isGuesedLastWord)
            {
                Log.i("CH","CheckTimeFinsh - вызов диалога выбора команды отг последнее слово");
                CustomDialogLastWord last = new CustomDialogLastWord(this,dbService, game, showedWord.getIdword(),round.getIdround());
                last.show();
            }
            else
            {
                Log.i("CH","CheckTimeFinsh - вызов результата");
                RoundResultInvoke();
            }
        }
    }

    Intent intentRoundResultActivity;
     boolean isStartActivityResult = false;
    public String idTeamLastWordWin = "";
   //ОКОНЧАНИЕ РАУНДА и ВЫВОД РЕЗУЛЬТАТОВ
    public void RoundResultInvoke()   {
        isStartActivityResult = true;
        Log.i("RES","RoundResultInvoke - вызов результата");
        isCheck = false;
        intentRoundResultActivity = new Intent(PlayGameActivity.this,RoundResultActivity.class);
        intentRoundResultActivity.putExtra("idroundCurrent",round.getIdround());
        intentRoundResultActivity.putExtra("idTeamLastWordWin",idTeamLastWordWin);
        dbService.getEPlayingTeamsService ().setScoreRound(team.getIdteam(),round.getGame(),countGuesedWord);
        int countAllscroeTeam =  dbService.getEPlayingTeamsService().getPlayingTeams(team.getIdteam(),game.getIdgame()).getScoreAll();
        dbService.getEPlayingTeamsService().setScoreAll (team.getIdteam(),game.getIdgame(),countGuesedWord+countAllscroeTeam);
       if(game.getIslastword())
        startActivity(intentRoundResultActivity);
    }


    //УСТАНОВКА СТАТУСА СЛОВА
    private void ShowNextWord(boolean isGues) {
        try{
            Log.i("SHW","ShowNextWord - присвоение статуса отг-неотг");
            int status = 0;
            if(isGues)
            {
                status = 1;
            }
            else
            {
                status = 0;
            }
            if(_timeRoundIsFinish && game.getIslastword())
            {
                Log.i("SHW","ShowNextWord - время закончтилось и игра имеет посл слово для всех");
                CheckTimeFinsh();
            }
            else
            {
                Log.i("SHW","ShowNextWord - установка статуса и вызов смены слова");
                  dbService.getEWordStatusService().createWordStatus(status,showedWord.getIdword(),game.getIdgame(),round.getIdround());
                  CheckTimeFinsh();
                  GetNextShowWord(wordList);
            }
        }
        catch (Exception er)
        {
            Log.e("SHOWNEXTWORD",er.getMessage());
        }

    }
}

///------------------------------------------------------------------------------------------------
/// ПОСЛЕДНЕЕ СЛОВО КТО ВЫЙГРАЛ
///_______________________________________________________________________________________________


class CustomDialogLastWord extends Dialog {

    IDbService dbService;
    Game game;
    Activity activity;
    String iDshowedLastWord;
    String idRound;
    public CustomDialogLastWord(@NonNull Activity activity, IDbService dbService, Game game, String iDshowedLastWord, String idRound) {
        super(activity);
        this.dbService = dbService;
        this.game = game;
        this.activity = activity;
        this.iDshowedLastWord = iDshowedLastWord;
        this.idRound = idRound;
    }

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.custom_dialog_last_word_win_team);
            List<Team> Teams = GetTeams(dbService, game);
            ArrayList<String> teamsName = new ArrayList<String>();
            for(Team tn:Teams)
                teamsName.add(tn.getName());
            teamsName.add("Ни кто не отгадал");
            ListView listViewTeams = findViewById(R.id.lvLastWordTeamWin);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),R.layout.row_team_name);
            adapter.addAll(teamsName);
            listViewTeams.setAdapter(adapter);
            listViewTeams.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        final int position, long id) {
                    int status;
                   if(position<Teams.size())
                   {
                       status = 1;
                       Team tn = Teams.get(position);
                       PlayingTeams playingTeams = dbService.getEPlayingTeamsService().getPlayingTeams(tn.getIdteam(),game.getIdgame());
                       int scoreRound = playingTeams.getScoreRound()+1;
                       int scoreAll = playingTeams.getScoreAll()+1;
                       dbService.getEPlayingTeamsService().updatePlayingTeams(tn.getIdteam(),game.getIdgame(),scoreRound, scoreAll);
                       ((PlayGameActivity)activity).idTeamLastWordWin = tn.getIdteam();
                   }
                   else
                   {
                       ((PlayGameActivity)activity).idTeamLastWordWin = "";
                       status = 0;
                   }
                   dbService.getEWordStatusService().createWordStatus(status,iDshowedLastWord,game.getIdgame(),idRound);
                   ((PlayGameActivity)activity).RoundResultInvoke();
                }
            });
        }
        catch(Exception er)
        {
            Log.e("DIAL_LAST_WIN",er.getMessage());
        }
    }

    private List<Team> GetTeams(IDbService dbService, Game game) {
       List<Team> teams = dbService.getEPlayingTeamsService().getListTeamByGame(game.getIdgame());
       return teams;
    }
}



///------------------------------------------------------------------------------------------------
///класс диалогового окна с описанием задачи загружается при первом появлении страницы PlayGameActivity
///-------------------------------------------------------------------------------------------------


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

