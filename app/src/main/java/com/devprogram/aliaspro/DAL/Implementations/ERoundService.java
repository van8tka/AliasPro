package com.devprogram.aliaspro.DAL.Implementations;

import android.util.Log;

import com.devprogram.aliaspro.DAL.Interfaces.IRoundService;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

public class ERoundService implements IRoundService {
    Realm realm;
    public ERoundService(Realm realm)
    {
        this.realm = realm;
    }

    @Override
    public Round getRound(String idround) {
        return realm.where(Round.class).equalTo("idround",idround).findFirst();
    }

    @Override
    public List<Round> getRounds() {
        return realm.where(Round.class).findAll();
    }

    @Override
    public String createRound(String idteam, String idtask, String idgame, int numberRound, int numberGame, String idTeamLastWord, Boolean taskComplete, int timeToFinish ) {
        String idround = UUID.randomUUID().toString();
        realm.beginTransaction();
        Round round = realm.createObject(Round.class,idround);
        round.setTeam(idteam);
        round.setTask(idtask);
        round.setGame(idgame);
        round.setNumber(numberRound);
        round.setNumberGame(numberGame);
        round.setIdteamLastWord(idTeamLastWord);
        round.setIsTaskComplete(taskComplete);
        round.setTimeToFinish(timeToFinish);
        realm.commitTransaction();
        return idround;
    }

    @Override
    public String updateRound(String idround, String idteam, String idtask, String idgame, int numberRound, int numberGame, String idTeamLastWord, Boolean taskComplete, int timeToFinish ) {
        realm.beginTransaction();
        Round round = realm.where(Round.class).equalTo("idround",idround).findFirst();
        round.setTeam(idteam);
        round.setTask(idtask);
        round.setGame(idgame);
        round.setNumber(numberRound);
        round.setNumberGame(numberGame);
        round.setIdteamLastWord(idTeamLastWord);
        round.setIsTaskComplete(taskComplete);
        round.setTimeToFinish(timeToFinish);
        realm.commitTransaction();
        return idround;
    }

    @Override
    public String deleteRound(String idround){
        realm.beginTransaction();
        realm.where(Round.class).equalTo("idround",idround).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return idround;
    }

    @Override
    public String changeTaskComplete(String idround, boolean isComplete) {
        realm.beginTransaction();
        Round round = realm.where(Round.class).equalTo("idround",idround).findFirst();
        round.setIsTaskComplete(isComplete);
        realm.commitTransaction();
        return idround;
    }

    @Override
    public String setIdTeamLastWord(String idround, String idTeam) {
        realm.beginTransaction();
        Round round = realm.where(Round.class).equalTo("idround",idround).findFirst();
        round.setIdteamLastWord(idTeam);
        realm.commitTransaction();
        return idround;
    }

    @Override
    public String setNumberRound(String idround, int numberRound) {
        realm.beginTransaction();
        Round round = realm.where(Round.class).equalTo("idround",idround).findFirst();

        round.setNumber(numberRound);

        realm.commitTransaction();
        return idround;
    }

    @Override
    public String setNumberGame(String idround, int numberGame) {
        realm.beginTransaction();
        Round round = realm.where(Round.class).equalTo("idround",idround).findFirst();
        round.setNumberGame(numberGame);
        realm.commitTransaction();
        return idround;
    }

    @Override
    public Round getLastRoundByGame(String idgame) {
        Round round = realm.where(Round.class).equalTo("idgame",idgame).findAll().last();
        return round;
    }

    @Override
    public String setTimeToFinishRound(String idround, int timeToFinish) {
         realm.beginTransaction();
         Round round = realm.where(Round.class).equalTo("idround",idround).findFirst();
         round.setTimeToFinish(timeToFinish);
         realm.commitTransaction();
         return idround;
    }
}
