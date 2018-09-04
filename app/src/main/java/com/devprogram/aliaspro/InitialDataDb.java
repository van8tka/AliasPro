package com.devprogram.aliaspro;

import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Difficulty;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

public class InitialDataDb {
    private IDbService dbService;

    public InitialDataDb(IDbService dbService)
    {
        this.dbService = dbService;
    }


    public void InitializeDbRealm() {
int k = 8;
        if(dbService.getETaskService().getTasks().size()==0)
        {
            //язык
            String uuidRus = dbService.getELanguageService().createLanguage("Рус", "languageimage_russia64");
            String uuidEng = dbService.getELanguageService().createLanguage("Eng", "languageimage_usa64");
            dbService.getELanguageService().createLanguage("Deu", "languageimage_germany64");
            String uuidBel = dbService.getELanguageService().createLanguage("Бел", "languageimage_belarus64");
            dbService.getELanguageService().createLanguage("Укр", "languageimage_ukraine64");
            dbService.getELanguageService().createLanguage("Kaz", "languageimage_kazakhstan64");
            dbService.getELanguageService().createLanguage("Fra", "languageimage_france64");
            dbService.getELanguageService().createLanguage("Ita", "languageimage_italy64");

            Language langRus = dbService.getELanguageService().getLanguage(uuidRus);
            Language langEng = dbService.getELanguageService().getLanguage(uuidEng);
            Language langBel = dbService.getELanguageService().getLanguage(uuidBel);
            //teams
            dbService.getETeamService().createTeam("Охотники за приведениями","teamimage_ghost64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Весёлые котята","teamimage_cat64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Могучие львы","teamimage_lion64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Бесстрашные воины","teamimage_helmet64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Проворные калибри","teamimage_hummingbird64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Летающие в небесах","teamimage_airplane64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Зелёные человчки","teamimage_alien",0,0,false,langRus);
            dbService.getETeamService().createTeam("Молот и ки","teamimage_auction64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Боевые орлы","teamimage_armyhelicopter64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Смехатворы","teamimage_alien64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Крутые пацаны","teamimage_baby64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Серьезные девченки","teamimage_babygirl64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Пчелки","teamimage_bee64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Сбежавшие с Ноева ковчега","teamimage_cruise64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Дракоши","teamimage_dragon64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Эльфы","teamimage_elf64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Золотые полосатики","teamimage_fish64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Ромашки","teamimage_flower64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Ежики","teamimage_hedgehog64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Русалки","teamimage_mermaid64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Любители денег","teamimage_moneybag64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Око за око","teamimage_monster64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Знатоки","teamimage_owl64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Полицейские","teamimage_police64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Задорные алхимики","teamimage_potion64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Андроиды","teamimage_robot64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Ракетчики","teamimage_spaceship64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Подсолнухи","teamimage_sunflower64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Танкисты игрушечных танков","teamimage_tank64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Единороги","teamimage_unicorn64",0,0,false,langRus);
            dbService.getETeamService().createTeam("Маги земли","teamimage_wizard64",0,0,false,langRus);
            //статус слов
            dbService.getEWordStatusService().createWordStatus("отгадано");
            String idwordStatusDefault = dbService.getEWordStatusService().createWordStatus("не отгадано");
            dbService.getEWordStatusService().createWordStatus("удалить");
            WordStatus wordStatusDefault = dbService.getEWordStatusService().getWordStatus(idwordStatusDefault);

            //слова
            dbService.getEWordService().createWord("рыба",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("мясо ",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("книга",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("журнал",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("магазин",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("конь",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("шаурма",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("игрушка",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("лось",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("тетрадь",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("чашка",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("телефон",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("храп",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("сон",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("бег",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("мышь",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("игра",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("жатва",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("еда",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("ложка",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("кружка",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("подушка",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("варенье",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("смех",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("окно",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("дверь",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("ребро",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("цветок",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("колесо",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("ключ",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("розетка",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("телевизор",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("антена",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("градусник",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("микроволновка",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("рубашка",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("самолет",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("танк",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("стол",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("платье",langRus,wordStatusDefault);
            dbService.getEWordService().createWord("краб",langRus,wordStatusDefault);
            //список слов
            List<Word> listWords = dbService.getEWordService().getWords();
            //уровни сложности
            String iddifficulty = dbService.getEDifficultyService().createDifficulty("простой",langRus);
            dbService.getEDifficultyService().createDifficulty("средний",langRus);
            dbService.getEDifficultyService().createDifficulty("сложный",langRus);
            String iddifficulty2 = dbService.getEDifficultyService().createDifficulty("очень простой",langRus);
            dbService.getEDifficultyService().createDifficulty("очень сложный",langRus);
            Difficulty difficultyEmpty = dbService.getEDifficultyService().getDifficulty(iddifficulty);
            Difficulty difficultyHard = dbService.getEDifficultyService().getDifficulty(iddifficulty2);
            //словари
            dbService.getEDictionaryService().createDictionary(listWords,"Для всех","cost_new64",null,"Самые простые слова для всех возврастов",langRus,difficultyEmpty);
            dbService.getEDictionaryService().createDictionary(listWords,"Сложный набор","cost_dollar64","0,5","Сложный набор слов, специфическая терминология",langEng,difficultyHard);
            dbService.getEDictionaryService().createDictionary(listWords,"Базовый","cost_new64","2,99","Самые простые слова для всех возврастов",langRus,difficultyEmpty);
            dbService.getEDictionaryService().createDictionary(listWords,"Фильмы","cost_free64",null,"Сложный набор слов, специфическая терминология",langRus,difficultyHard);
            dbService.getEDictionaryService().createDictionary(listWords,"Техника","cost_free64",null,"Сложный набор слов, специфическая терминология",langBel,difficultyHard);
            dbService.getEDictionaryService().createDictionary(listWords,"Детские слова","cost_new64",null,"Сложный набор слов, специфическая терминология",langRus,difficultyHard);

            //tasks
            dbService.getETaskService().createTask("Улыбайся","В процессе объяснения слов необходимо постоянно улыбаться","task_happysmile64", false,2,langRus);
            dbService.getETaskService().createTask("Стой на одной ноге","В процессе объяснения слов необходимо стоять на одной ноге","taskimage_oneleg64", false,2,langRus);
            dbService.getETaskService().createTask("Черепашка","После каждого объяснения слова необходимо повторять выражение - Я черепашка","taskimage_tortoise64", false,2,langRus);
            dbService.getETaskService().createTask("Скоро новый год","После каждого объяснения слова необходимо повторять выражение - Скоро новый год","taskimage_christmas64", false,2,langRus);
            dbService.getETaskService().createTask("Попрыгунчик","В процессе объяснения слов необходимо постоянно прыгать","taskimage_jump64", false,2,langRus);
            dbService.getETaskService().createTask("Посудомойка","В процессе объяснения слов необходимо имитировать мытье посуды","taskimage_dishwash64", false,2,langRus);
            dbService.getETaskService().createTask("Закрытые глазки","Объяснять слова необходимо с закрытыми глазами","taskimage_closeyes64", false,2,langRus);

        }

    }


}
