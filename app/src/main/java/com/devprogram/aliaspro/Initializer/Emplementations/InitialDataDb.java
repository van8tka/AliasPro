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
import java.util.List;

public class InitialDataDb implements IInitDB {
    private IDbService dbService;
    private Context context;
    private IXmlParser parserXml;

    //ctor
    public InitialDataDb(IDbService dbService, Context context) {
        this.context = context;
        this.dbService = dbService;
        parserXml = new XmlParser(context);
    }

    public void InitializeItems() {
        try {
            if (dbService.getETaskService().getTasks().size() == 0) {
                //язык
                String uuidRus = dbService.getELanguageService().createLanguage("Рус", "languageimage_russia64");
                String uuidEng = dbService.getELanguageService().createLanguage("Eng", "languageimage_usa64");
                //teams
                dbService.getETeamService().createTeam("Охотники за приведениями", "teamimage_ghost64", uuidRus);
                dbService.getETeamService().createTeam("Весёлые котята", "teamimage_cat64", uuidRus);
                dbService.getETeamService().createTeam("Могучие львы", "teamimage_lion64", uuidRus);
                dbService.getETeamService().createTeam("Бесстрашные воины", "teamimage_helmet64", uuidRus);
                dbService.getETeamService().createTeam("Проворные калибри", "teamimage_hummingbird64", uuidRus);
                dbService.getETeamService().createTeam("Летающие в небесах", "teamimage_airplane64", uuidRus);
                dbService.getETeamService().createTeam("Зелёные человчки", "teamimage_alien", uuidRus);
                dbService.getETeamService().createTeam("Молот и ки", "teamimage_auction64", uuidRus);
                dbService.getETeamService().createTeam("Боевые орлы", "teamimage_armyhelicopter64", uuidRus);
                dbService.getETeamService().createTeam("Смехатворы", "teamimage_alien64", uuidRus);
                dbService.getETeamService().createTeam("Крутые пацаны", "teamimage_baby64", uuidRus);
                dbService.getETeamService().createTeam("Серьезные девченки", "teamimage_babygirl64", uuidRus);
                dbService.getETeamService().createTeam("Пчелки", "teamimage_bee64", uuidRus);
                dbService.getETeamService().createTeam("Сбежавшие с Ноева ковчега", "teamimage_cruise64", uuidRus);
                dbService.getETeamService().createTeam("Дракоши", "teamimage_dragon64", uuidRus);
                dbService.getETeamService().createTeam("Эльфы", "teamimage_elf64", uuidRus);
                dbService.getETeamService().createTeam("Золотые полосатики", "teamimage_fish64", uuidRus);
                dbService.getETeamService().createTeam("Ромашки", "teamimage_flower64", uuidRus);
                dbService.getETeamService().createTeam("Ежики", "teamimage_hedgehog64", uuidRus);
                dbService.getETeamService().createTeam("Русалки", "teamimage_mermaid64", uuidRus);
                dbService.getETeamService().createTeam("Любители денег", "teamimage_moneybag64", uuidRus);
                dbService.getETeamService().createTeam("Око за око", "teamimage_monster64", uuidRus);
                dbService.getETeamService().createTeam("Знатоки", "teamimage_owl64", uuidRus);
                dbService.getETeamService().createTeam("Полицейские", "teamimage_police64", uuidRus);
                dbService.getETeamService().createTeam("Задорные алхимики", "teamimage_potion64", uuidRus);
                dbService.getETeamService().createTeam("Андроиды", "teamimage_robot64", uuidRus);
                dbService.getETeamService().createTeam("Ракетчики", "teamimage_spaceship64", uuidRus);
                dbService.getETeamService().createTeam("Подсолнухи", "teamimage_sunflower64", uuidRus);
                dbService.getETeamService().createTeam("Танкисты игрушечных танков", "teamimage_tank64", uuidRus);
                dbService.getETeamService().createTeam("Единороги", "teamimage_unicorn64", uuidRus);
                dbService.getETeamService().createTeam("Маги земли", "teamimage_wizard64", uuidRus);
                //уровни сложности
                String iddifficultyEasy = dbService.getEDifficultyService().createDifficulty("простой", uuidRus);
                String iddifficultyMidle = dbService.getEDifficultyService().createDifficulty("средний", uuidRus);
                String iddifficultyHard = dbService.getEDifficultyService().createDifficulty("сложный", uuidRus);
                //словари
                String idEmptyDictionary = dbService.getEDictionaryService().createDictionary("Простой набор", "cost_free64", null, "Самые простые слова для всех возврастов, легко объяснять, легко отгадывать", uuidRus, iddifficultyEasy);
                String idMiddleDictionary = dbService.getEDictionaryService().createDictionary("Слова среднего уровня", "cost_free64", null, "Слова средней сложности, болле интересный процесс объяснения слов и интересная игра ", uuidRus, iddifficultyMidle);
                String idBigDictionary = dbService.getEDictionaryService().createDictionary("Супер слова", "cost_dollar64", "0,99", "Большой набор различных слов, от самых простых до самых сложных. Процесс игры станет очень интересным и увлекательным", uuidRus, iddifficultyHard);
                String idEnglishDictionary = dbService.getEDictionaryService().createDictionary("Basic english words", "cost_free64", null, "Basic english words, for them who want testing your knowledge", uuidEng, iddifficultyMidle);
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
            }
        } catch (Exception e) {
            Crashlytics.logException(e);
        }
    }
    //установка слов после парсинга xml
    //список слов,            ID языка,               id словаря
    private void setWords(List<String> parse, String idLanguage, String idDictionary) {
         for (String word : parse) {
            dbService.getEWordService().createWord(word, idDictionary, idLanguage);
        }
    }
}
