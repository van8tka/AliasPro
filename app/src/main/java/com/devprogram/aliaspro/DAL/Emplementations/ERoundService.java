package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.IRoundService;
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
    public String createRound(String name, Team team, RealmList<Word> words, Task task, int score) {
        String idround = UUID.randomUUID().toString();
        realm.beginTransaction();
        Round round = realm.createObject(Round.class,idround);
        round.setName(name);
        round.setTask(task);
        round.setTeam(team);
        round.setWords(words);
        round.setScore(score);
        realm.commitTransaction();
        return idround;
    }

    @Override
    public String updateRound(String idround, String name, Team team, RealmList<Word> words, Task task, int score) {
        realm.beginTransaction();
        Round round = realm.where(Round.class).equalTo("idround",idround).findFirst();
        round.setName(name);
        round.setTask(task);
        round.setTeam(team);
        round.setWords(words);
        round.setScore(score);
        realm.commitTransaction();
        return idround;
    }

    @Override
    public String deleteRound(String idround){
        realm.where(Round.class).equalTo("idround",idround).findFirst().deleteFromRealm();
        return idround;
    }
}
