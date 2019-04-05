package com.devprogram.aliaspro.Initializer.Emplementations;

import android.util.Log;
import android.widget.Toast;

import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Helpers.IXmlParser;
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

    public InitialDataDb(IDbService dbService)
    {
        this.dbService = dbService;
    }


    public void InitializeItems() {
        if(dbService.getETaskService().getTasks().size()==0)
        {
            //язык
            String uuidRus = dbService.getELanguageService().createLanguage("Рус", "languageimage_russia64");
            String uuidEng = dbService.getELanguageService().createLanguage("Eng", "languageimage_usa64");
//            String uuidDe = dbService.getELanguageService().createLanguage("Deu", "languageimage_germany64");
//            String uuidBel = dbService.getELanguageService().createLanguage("Бел", "languageimage_belarus64");
//            String uuidUa = dbService.getELanguageService().createLanguage("Укр", "languageimage_ukraine64");
//            String uuidKaz = dbService.getELanguageService().createLanguage("Kaz", "languageimage_kazakhstan64");
//            String uuidFr = dbService.getELanguageService().createLanguage("Fra", "languageimage_france64");
//            String uuidIta = dbService.getELanguageService().createLanguage("Ita", "languageimage_italy64");


            //teams
            dbService.getETeamService().createTeam("Охотники за приведениями","teamimage_ghost64",uuidRus);
            dbService.getETeamService().createTeam("Весёлые котята","teamimage_cat64",uuidRus);
            dbService.getETeamService().createTeam("Могучие львы","teamimage_lion64",uuidRus);
            dbService.getETeamService().createTeam("Бесстрашные воины","teamimage_helmet64",uuidRus);
            dbService.getETeamService().createTeam("Проворные калибри","teamimage_hummingbird64",uuidRus);
            dbService.getETeamService().createTeam("Летающие в небесах","teamimage_airplane64",uuidRus);
            dbService.getETeamService().createTeam("Зелёные человчки","teamimage_alien",uuidRus);
            dbService.getETeamService().createTeam("Молот и ки","teamimage_auction64",uuidRus);
            dbService.getETeamService().createTeam("Боевые орлы","teamimage_armyhelicopter64",uuidRus);
            dbService.getETeamService().createTeam("Смехатворы","teamimage_alien64",uuidRus);
            dbService.getETeamService().createTeam("Крутые пацаны","teamimage_baby64",uuidRus);
            dbService.getETeamService().createTeam("Серьезные девченки","teamimage_babygirl64",uuidRus);
            dbService.getETeamService().createTeam("Пчелки","teamimage_bee64",uuidRus);
            dbService.getETeamService().createTeam("Сбежавшие с Ноева ковчега","teamimage_cruise64",uuidRus);
            dbService.getETeamService().createTeam("Дракоши","teamimage_dragon64",uuidRus);
            dbService.getETeamService().createTeam("Эльфы","teamimage_elf64",uuidRus);
            dbService.getETeamService().createTeam("Золотые полосатики","teamimage_fish64",uuidRus);
            dbService.getETeamService().createTeam("Ромашки","teamimage_flower64",uuidRus);
            dbService.getETeamService().createTeam("Ежики","teamimage_hedgehog64",uuidRus);
            dbService.getETeamService().createTeam("Русалки","teamimage_mermaid64",uuidRus);
            dbService.getETeamService().createTeam("Любители денег","teamimage_moneybag64",uuidRus);
            dbService.getETeamService().createTeam("Око за око","teamimage_monster64",uuidRus);
            dbService.getETeamService().createTeam("Знатоки","teamimage_owl64",uuidRus);
            dbService.getETeamService().createTeam("Полицейские","teamimage_police64",uuidRus);
            dbService.getETeamService().createTeam("Задорные алхимики","teamimage_potion64",uuidRus);
            dbService.getETeamService().createTeam("Андроиды","teamimage_robot64",uuidRus);
            dbService.getETeamService().createTeam("Ракетчики","teamimage_spaceship64",uuidRus);
            dbService.getETeamService().createTeam("Подсолнухи","teamimage_sunflower64",uuidRus);
            dbService.getETeamService().createTeam("Танкисты игрушечных танков","teamimage_tank64",uuidRus);
            dbService.getETeamService().createTeam("Единороги","teamimage_unicorn64",uuidRus);
            dbService.getETeamService().createTeam("Маги земли","teamimage_wizard64",uuidRus);


            //уровни сложности
            String iddifficultyEasy = dbService.getEDifficultyService().createDifficulty("простой",uuidRus);
            String iddifficultyMidle = dbService.getEDifficultyService().createDifficulty("средний",uuidRus);
            String iddifficultyHard = dbService.getEDifficultyService().createDifficulty("сложный",uuidRus);
//            String iddifficultyVeryEasy = dbService.getEDifficultyService().createDifficulty("очень простой",uuidRus);
//            String iddifficultyVeryHard = dbService.getEDifficultyService().createDifficulty("очень сложный",uuidRus);



            //словари
            String idForAllDictionary = dbService.getEDictionaryService().createDictionary("Простой набор","cost_free64",null,"Самые простые слова для всех возврастов, легко объяснять, легко отгадывать",uuidRus,iddifficultyEasy);
            String idDifficultDictionary = dbService.getEDictionaryService().createDictionary("Слова среднего уровня","cost_free64",null,"Слова средней сложности, болле интересный процесс объяснения слов и интересная игра ",uuidRus,iddifficultyMidle);
            String idBasicDictionary = dbService.getEDictionaryService().createDictionary("Супер слова","cost_dollar64","0,99","Большой набор различных слов, от самых простых до самых сложных. Процесс игры станет очень интересным и увлекательным",uuidRus,iddifficultyHard);
            String idEnglishDictionary = dbService.getEDictionaryService().createDictionary("Basic english words","cost_free64",null,"Basic english words, for them who want testing your knowledge",uuidRus,iddifficultyMidle);
//            String idIngenearDictionary = dbService.getEDictionaryService().createDictionary("Техника","cost_free64",null,"Сложный набор слов, специфическая терминология",uuidRus,iddifficultyMidle);
//            String idChildDictionary = dbService.getEDictionaryService().createDictionary("Детские слова","cost_new64",null,"Сложный набор слов, специфическая терминология",uuidRus,iddifficultyEasy);



            //tasks
            dbService.getETaskService().createTask("Улыбайся","В процессе объяснения слов необходимо постоянно улыбаться","task_happysmile64", uuidRus);
            dbService.getETaskService().createTask("Стой на одной ноге","В процессе объяснения слов необходимо стоять на одной ноге","taskimage_oneleg64", uuidRus);
            dbService.getETaskService().createTask("Черепашка","После каждого объяснения слова необходимо повторять выражение - Я черепашка","taskimage_tortoise64", uuidRus);
            dbService.getETaskService().createTask("Скоро новый год","После каждого объяснения слова необходимо повторять выражение - Скоро новый год","taskimage_christmas64", uuidRus);
            dbService.getETaskService().createTask("Попрыгунчик","В процессе объяснения слов необходимо постоянно прыгать","taskimage_jump64", uuidRus);
            dbService.getETaskService().createTask("Посудомойка","В процессе объяснения слов необходимо имитировать мытье посуды","taskimage_dishwash64", uuidRus);
            dbService.getETaskService().createTask("Закрытые глазки","Объяснять слова необходимо с закрытыми глазами","taskimage_closeyes64", uuidRus);

            IXmlParser parser = null;

            String first1 = "first505words.xml";
            String first2 = "second1010words.xml";
            String first3 = "third1000words.xml";
            String first4 = "third1000words.xml";
            setWords(parser.Parse(first1),uuidRus,idForAllDictionary);
            //todo iddictionary and set parser
//            setWords(parser.Parse(first),uuidRus,idForAllDictionary);
//            setWords(parser.Parse(first),uuidRus,idForAllDictionary);
//            setWords(parser.Parse(first),uuidRus,idForAllDictionary);


            //слова
         dbService.getEWordService().createWord("рыба",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("мясо ",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("книга",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("журнал",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("магазин",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("конь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("шаурма",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("игрушка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("лось",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("тетрадь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("чашка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("телефон",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("храп",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("сон",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("бег",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("мышь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("игра",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("жатва",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("еда",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("ложка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("рыба",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("мясо ",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("книга",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("журнал",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("магазин",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("конь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("шаурма",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("игрушка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("лось",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("тетрадь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("чашка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("телефон",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("храп",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("сон",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("бег",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("мышь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("игра",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("жатва",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("еда",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("ложка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("рыба",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("мясо ",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("книга",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("журнал",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("магазин",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("конь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("шаурма",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("игрушка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("лось",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("тетрадь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("чашка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("телефон",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("храп",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("сон",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("бег",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("мышь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("игра",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("жатва",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("еда",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("ложка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("рыба",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("мясо ",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("книга",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("журнал",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("магазин",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("конь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("шаурма",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("игрушка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("лось",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("тетрадь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("чашка",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("телефон",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("храп",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("сон",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("бег",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("мышь",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("игра",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("жатва",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("еда",idForAllDictionary,uuidRus);
//            dbService.getEWordService().createWord("ложка",idForAllDictionary,uuidRus);
//
//            dbService.getEWordService().createWord("кружка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("подушка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("варенье",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("смех",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("окно",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("дверь",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("ребро",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("цветок",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("колесо",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("ключ",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("розетка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("телевизор",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("антена",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("градусник",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("микроволновка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("рубашка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("самолет",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("танк",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("стол",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("платье",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("краб",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("дорога",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("столб",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("тратуар",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("газон",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("обочина",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("канава",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("мост",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("разметка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("тормоз",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("кружка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("подушка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("варенье",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("смех",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("окно",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("дверь",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("ребро",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("цветок",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("колесо",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("ключ",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("розетка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("телевизор",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("антена",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("градусник",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("микроволновка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("рубашка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("самолет",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("танк",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("стол",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("платье",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("краб",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("дорога",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("столб",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("тратуар",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("газон",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("обочина",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("канава",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("мост",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("разметка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("тормоз",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("кружка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("подушка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("варенье",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("смех",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("окно",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("дверь",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("ребро",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("цветок",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("колесо",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("ключ",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("розетка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("телевизор",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("антена",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("градусник",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("микроволновка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("рубашка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("самолет",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("танк",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("стол",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("платье",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("краб",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("дорога",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("столб",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("тратуар",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("газон",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("обочина",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("канава",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("мост",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("разметка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("тормоз",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("кружка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("подушка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("варенье",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("смех",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("окно",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("дверь",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("ребро",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("цветок",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("колесо",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("ключ",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("розетка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("телевизор",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("антена",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("градусник",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("микроволновка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("рубашка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("самолет",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("танк",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("стол",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("платье",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("краб",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("дорога",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("столб",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("тратуар",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("газон",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("обочина",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("канава",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("мост",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("разметка",idDifficultDictionary,uuidRus);
//            dbService.getEWordService().createWord("тормоз",idDifficultDictionary,uuidRus);
//
//
//
//
//            dbService.getEWordService().createWord("знак",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("поворот",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("бардюр",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("поребрик",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("разделительная полоса",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("зеленая зона",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("шлагбаум",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("дпс",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("светофор",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("зебра",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("пешеходный переход",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("эстакада",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("авиадук",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("разводной мост",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("стоп-линия",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("разворот",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("крюк",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("заправка",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("кемпинг",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("парковка",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("лавка",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("выезд",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("помеха справа",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("обгон",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("гонки",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("погоня",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("учитель",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("бэха",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("ремонт дороги",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("круговое движение",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("поворот на право",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("поворот на лево",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("знак",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("поворот",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("бардюр",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("поребрик",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("разделительная полоса",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("зеленая зона",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("шлагбаум",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("дпс",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("светофор",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("зебра",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("пешеходный переход",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("эстакада",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("авиадук",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("разводной мост",idBasicDictionary, uuidRus);
//            dbService.getEWordService().createWord("стоп-линия",idBasicDictionary,uuidRus);
//            dbService.getEWordService().createWord("разворот",idBasicDictionary, uuidRus);
//



        }

    }




                              //список слов,            ID языка,               id словаря
    private void setWords(ArrayList<String> parse, String idLanguage, String idDictionary) {
        for (String word:parse)
        {
            dbService.getEWordService().createWord(word,idDictionary, idLanguage);
        }
    }


}
