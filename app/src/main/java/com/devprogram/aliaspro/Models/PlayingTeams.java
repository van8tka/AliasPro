package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PlayingTeams extends RealmObject {
    @PrimaryKey
    String id;
    String idGame;
    String idTeam;
    int scoreRound;
    int scoreAll;

    public String getId() {
        return id;
    }


    public String getIdGame() {
        return idGame;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public int getScoreAll() {
        return scoreAll;
    }

    public int getScoreRound() {
        return scoreRound;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public void setScoreAll(int scoreAll) {
        this.scoreAll = scoreAll;
    }

    public void setScoreRound(int scoreRound) {
        this.scoreRound = scoreRound;
    }
}
