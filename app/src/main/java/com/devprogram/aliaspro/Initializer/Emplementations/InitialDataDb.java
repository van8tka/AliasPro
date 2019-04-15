package com.devprogram.aliaspro.Initializer.Emplementations;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Helpers.Constants;
import com.devprogram.aliaspro.Helpers.IXmlParser;
import com.devprogram.aliaspro.Helpers.XmlParser;
import com.devprogram.aliaspro.Initializer.Interfaces.IInitDB;
import com.devprogram.aliaspro.Models.Difficulty;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.realm.Realm;

public class InitialDataDb implements IInitDB {
    private IDbService dbService;
    private Context context;
    private IXmlParser parserXml;
    private Realm realm;
    //ctor
    public InitialDataDb(IDbService dbService, Context context) {
        this.context = context;
        this.dbService = dbService;
        parserXml = new XmlParser(context);
        realm = dbService.getInstanceRealm();
    }

    public void InitializeItems() {
        try {
            if (dbService.getETaskService().getTasks().size() == 0) {
                //язык
                String uuidRus = dbService.getELanguageService().createLanguage("Рус", "languageimage_russia64");
                String uuidEng = dbService.getELanguageService().createLanguage("Eng", "languageimage_usa64");
                //уровни сложности
                String iddifficultyEasy = dbService.getEDifficultyService().createDifficulty("простой", uuidRus);
                String iddifficultyMidle = dbService.getEDifficultyService().createDifficulty("средний", uuidRus);
                String iddifficultyHard = dbService.getEDifficultyService().createDifficulty("сложный", uuidRus);
                //словари
                String idEmptyDictionary = dbService.getEDictionaryService().createDictionary("Простой набор", "cost_free64", null, "Самые простые слова для всех возврастов, легко объяснять, легко отгадывать", uuidRus, iddifficultyEasy);
                String idMiddleDictionary = dbService.getEDictionaryService().createDictionary("Слова среднего уровня", "cost_free64", null, "Слова средней сложности, болле интересный процесс объяснения слов и интересная игра ", uuidRus, iddifficultyMidle);
                String idBigDictionary = dbService.getEDictionaryService().createDictionary("Супер слова", "cost_dollar64", "0,99", "Большой набор различных слов, от самых простых до самых сложных. Процесс игры станет очень интересным и увлекательным", uuidRus, iddifficultyHard);
                String idEnglishDictionary = dbService.getEDictionaryService().createDictionary("Basic english words", "cost_free64", null, "Basic english words, for them who want testing your knowledge", uuidEng, iddifficultyMidle);
                //teams
              dbService.RealmBeginTransaction();
                Map<String,String> teamList = new HashMap<String, String>(){
                    {
                        put("Охотники за приведениями","teamimage_ghost64");
                        put("Весёлые котята","teamimage_cat64");
                        put("Могучие львы","teamimage_lion64");
                        put("Бесстрашные воины","teamimage_helmet64");
                        put("Проворные калибри","teamimage_hummingbird64");
                        put("Летающие в небесах","teamimage_airplane64");
                        put("Зелёные человчки","teamimage_alien");
                        put("Молот и ки","teamimage_auction64");
                        put("Боевые орлы","teamimage_armyhelicopter64");
                        put("Смехатворы","teamimage_alien64");
                        put("Крутые пацаны","teamimage_baby64");
                        put("Серьезные девченки","teamimage_babygirl64");
                        put("Пчелки","teamimage_bee64");
                        put("Сбежавшие с Ноева ковчега","teamimage_cruise64");
                        put("Дракоши","teamimage_dragon64");
                        put("Эльфы","teamimage_elf64");
                        put("Золотые полосатики","teamimage_fish64");
                        put("Ромашки","teamimage_flower64");
                        put("Ежики","teamimage_hedgehog64");
                        put("Русалки","teamimage_mermaid64");
                        put("Любители денег","teamimage_moneybag64");
                        put("Око за око","teamimage_monster64");
                        put("Знатоки","teamimage_owl64");
                        put("Полицейские","teamimage_police64");
                        put("Задорные алхимики","teamimage_potion64");
                        put("Андроиды","teamimage_robot64");
                        put("Ракетчики","teamimage_spaceship64");
                        put("Подсолнухи","teamimage_sunflower64");
                        put("Танкисты игрушечных танков","teamimage_tank64");
                        put("Единороги","teamimage_unicorn64");
                        put("Маги земли","teamimage_wizard64");
                    }
                };
                setTeamsData(teamList, uuidRus);
                  //tasks
                dbService.getETaskService().createTask("Улыбайся", "В процессе объяснения слов необходимо постоянно улыбаться", "task_happysmile64", uuidRus);
                dbService.getETaskService().createTask("Стой на одной ноге", "В процессе объяснения слов необходимо стоять на одной ноге", "taskimage_oneleg64", uuidRus);
                dbService.getETaskService().createTask("Черепашка", "После каждого объяснения слова необходимо повторять выражение - Я черепашка", "taskimage_tortoise64", uuidRus);
                dbService.getETaskService().createTask("Скоро новый год", "После каждого объяснения слова необходимо повторять выражение - Скоро новый год", "taskimage_christmas64", uuidRus);
                dbService.getETaskService().createTask("Попрыгунчик", "В процессе объяснения слов необходимо постоянно прыгать", "taskimage_jump64", uuidRus);
                dbService.getETaskService().createTask("Посудомойка", "В процессе объяснения слов необходимо имитировать мытье посуды", "taskimage_dishwash64", uuidRus);
                dbService.getETaskService().createTask("Закрытые глазки", "Объяснять слова необходимо с закрытыми глазами", "taskimage_closeyes64", uuidRus);
                //words
                setWords(parserXml.Parse(Constants.EMPTY_WORDS_FILE), uuidRus, idEmptyDictionary);
                setWords(parserXml.Parse(Constants.MIDDLE_WORDS_FILE), uuidRus, idMiddleDictionary);
                setWords(parserXml.Parse(Constants.BIG_WORDS_FILE), uuidRus, idBigDictionary);
                setWords(parserXml.Parse(Constants.ENG_WORDS_FILE), uuidRus, idEnglishDictionary);
           dbService.RealmCommitTransaction();
            }
        } catch (Exception e) {
            Crashlytics.logException(e);
        }
    }

    private void setTeamsData(Map<String, String> teamList, String uuidRus) {
       // realm.beginTransaction();
        for(Map.Entry<String, String> teamPair:teamList.entrySet())
        {
            Team myTeam = realm.createObject(Team.class,UUID.randomUUID().toString());
            myTeam.setName(teamPair.getKey());
            myTeam.setAvatar(teamPair.getValue());
            myTeam.setLanguage(uuidRus);
        }
      //  realm.commitTransaction();
    }

    //установка слов после парсинга xml
    //список слов,            ID языка,               id словаря
    private void setWords(List<String> parse, String idLanguage, String idDictionary) {
  //      realm.beginTransaction();
         for (int i=0;i<parse.size();i++) {
           // dbService.getEWordService().createWord(parse.get(i), idDictionary, idLanguage);
                 Word word = realm.createObject(Word.class, UUID.randomUUID().toString());
                 word.setIddictionary(idDictionary);
                 word.setName(parse.get(i));
                 word.setLanguage(idLanguage);
        }
   //     realm.commitTransaction();
    }
}
