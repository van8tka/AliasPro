package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.IDictionaryService;
import com.devprogram.aliaspro.DAL.Interfaces.IDifficultyService;
import com.devprogram.aliaspro.DAL.Interfaces.IGameService;
import com.devprogram.aliaspro.DAL.Interfaces.ILanguageService;
import com.devprogram.aliaspro.DAL.Interfaces.IRoundService;
import com.devprogram.aliaspro.DAL.Interfaces.ITaskService;
import com.devprogram.aliaspro.DAL.Interfaces.ITeamService;
import com.devprogram.aliaspro.DAL.Interfaces.IWordService;
import com.devprogram.aliaspro.DAL.Interfaces.IWordStatusService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Difficulty;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class DbService implements IDictionaryService, IDifficultyService, IGameService, ILanguageService, IRoundService, ITaskService, ITeamService, IWordService, IWordStatusService{

    private Realm realm;

    public DbService()
    {
        realm = Realm.getDefaultInstance();
    }

//dictionaries

    @Override
    public Dictionary getDictionary(String iddictionary) {
        return realm.where(Dictionary.class).equalTo("iddictionary",iddictionary).findFirst();
    }

    @Override
    public List<Dictionary> getDicitionaries() {
        return (List<Dictionary>) realm.where(Dictionary.class);
    }

    @Override
    public String createDictionary(String iddictionary, List<Word> words, String name, String avatar, String price, String description, String idlanguage, String iddifficulty) {
        realm.beginTransaction();
        Dictionary myDictionary = realm.where(Dictionary.class).equalTo("iddictionary",iddictionary).findFirst();
        myDictionary.setIddictionary(iddictionary);
        myDictionary.setWords((RealmList<Word>) words);
        myDictionary.setName(name);
        myDictionary.setAvatar(avatar);
        myDictionary.setPrice(price);
        myDictionary.setDescription(description);
        myDictionary.setIddifficulty(iddifficulty);
        myDictionary.setIdlanguage(idlanguage);
        realm.commitTransaction();
        return iddictionary;
    }

    @Override
    public String updateDictionary(String iddictionary, List<Word> words, String name, String avatar, String price, String description, String idlanguage, String iddifficulty) {
        realm.beginTransaction();
        Dictionary myDictionary = realm.createObject(Dictionary.class);
        myDictionary.setIddictionary(iddictionary);
        myDictionary.setWords((RealmList<Word>) words);
        myDictionary.setName(name);
        myDictionary.setAvatar(avatar);
        myDictionary.setPrice(price);
        myDictionary.setDescription(description);
        myDictionary.setIddifficulty(iddifficulty);
        myDictionary.setIdlanguage(idlanguage);
        realm.commitTransaction();
        return iddictionary;
    }

    @Override
    public String deleteDictionary(String iddictionary) {
        realm.beginTransaction();
        realm.where(Dictionary.class).equalTo("iddictionary",iddictionary).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return iddictionary;
    }

//difficulty

    @Override
    public Difficulty getDifficulty(String iddifficulty) {
        return null;
    }

    @Override
    public List<Difficulty> getDifficulties() {
        return null;
    }

    @Override
    public String createDifficulty(String iddifficulty, String name, String idlanguage) {
        return null;
    }

    @Override
    public String updateDifficulty(String iddifficulty, String name, String idlanguage) {
        return null;
    }

    @Override
    public String deleteDifficulty(String iddifficulty) {
        return null;
    }

    @Override
    public Game getGame(String idgame) {
        return null;
    }

    @Override
    public List<Game> getGames() {
        return null;
    }

    @Override
    public String createGame(String idgame, String iddictionary, List<Team> teams, boolean istask, boolean islastword, boolean penalty, int countwords, int seconds, boolean isfinish, String datestart) {
        return null;
    }

    @Override
    public String updateGame(String idgame, int iddictionary, List<Team> teams, boolean istask, boolean islastword, boolean penalty, int countwords, int seconds, boolean isfinish, String datestart) {
        return null;
    }

    @Override
    public String deleteGame(String idgame) {
        return null;
    }

    @Override
    public Language getLanguage(String idlanguage) {
        return null;
    }

    @Override
    public List<Language> getLanguages() {
        return null;
    }

    @Override
    public String createLanguage(String idlanguage, String name, String avatar) {
        return null;
    }

    @Override
    public String updateLanguage(String idlanguage, String name, String avatar) {
        return null;
    }

    @Override
    public String deleteLanguage(String id) {
        return null;
    }

    @Override
    public Round getRound(String idround) {
        return null;
    }

    @Override
    public List<Round> getRounds() {
        return null;
    }

    @Override
    public String createRound(String idround, String name, List<Team> idteam, List<Word> words, String idtask) {
        return null;
    }

    @Override
    public String updateRound(String idround, String name, List<Team> idteam, List<Word> words, String idtask) {
        return null;
    }

    @Override
    public String deleteRound(String idround) {
        return null;
    }

    @Override
    public Task getTask(String idtask) {
        return null;
    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public String createTask(String idtask, String name, String description, String avatar, boolean complete, int addscore, String idlanguage) {
        return null;
    }

    @Override
    public String updateTask(String idtask, String name, String description, String avatar, boolean complete, int addscore, String idlanguage) {
        return null;
    }

    @Override
    public String deleteTask(String idtask) {
        return null;
    }

    @Override
    public Team getTeam(String id) {
        return null;
    }

    @Override
    public List<Team> getTeams() {
        return null;
    }

    @Override
    public String createTeam(String idteam, String name, String avatar, int score, Boolean winner, String idlanguage) {
        return null;
    }

    @Override
    public String updateTeam(String idteam, String name, String avatar, int score, Boolean winner, String idlanguage) {
        return null;
    }

    @Override
    public String deleteTeam(String idteam) {
        return null;
    }

    @Override
    public Word getWord(String idword) {
        return null;
    }

    @Override
    public List<Word> getWords() {
        return null;
    }

    @Override
    public String createWord(String idword, String name, String idlanguage, String idwordstatus) {
        return null;
    }

    @Override
    public String updareWord(String idword, String name, String idlanguage, String idwordstatus) {
        return null;
    }

    @Override
    public String deleteWord(String idword) {
        return null;
    }

    @Override
    public WordStatus getWordStatus(String idwordstatus) {
        return null;
    }

    @Override
    public List<WordStatus> getWordsStatus() {
        return null;
    }

    @Override
    public String createWordStatus(String idwordstatus, String name) {
        return null;
    }

    @Override
    public String updateWordStatus(String idwordstatus, String name) {
        return null;
    }

    @Override
    public String deleteWordStatus(String idwordstatus) {
        return null;
    }
}
