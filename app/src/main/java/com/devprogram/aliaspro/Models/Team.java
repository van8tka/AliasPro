package com.devprogram.aliaspro.Models;
import java.io.Serializable;
import java.util.OptionalInt;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Team extends RealmObject implements Serializable{
   @Required
    @PrimaryKey
    String idteam;
    String name;
    String avatar;
    int score;
    int scoreAll;
    Boolean winner;
    Language language;


    public int getScoreAll() {
        return scoreAll;
    }
    public void setScoreAll(int scoreAll)
    {
        this.scoreAll = scoreAll;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getIdteam() {
        return idteam;
    }

    public void setIdteam(String idteam) {
        this.idteam = idteam;
    }
}
