package com.devprogram.aliaspro.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Game extends RealmObject {
   @Required
    @PrimaryKey
    String idgame;
    Dictionary dictionary;
    RealmList<Team> teams;
    boolean istask;
    boolean islastword;
    boolean penalty;
    int countwords;
    int seconds;
    boolean isfinish;
    String datestart;

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public boolean getIsfinish() {
        return isfinish;
    }
    public void setIsfinish(boolean isfinish){
        this.isfinish = isfinish;
    }

    public int getCountwords() {
        return countwords;
    }

    public void setCountwords(int countwords) {
        this.countwords = countwords;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public RealmList<Team> getTeams() {
        return teams;
    }

    public void setTeams(RealmList<Team> teams) {
        this.teams = teams;
    }

    public String getDatestart() {
        return datestart;
    }

    public void setDatestart(String datestart) {
        this.datestart = datestart;
    }

    public String getIdgame() {
        return idgame;
    }

    public void setIdgame(String idgame) {
        this.idgame = idgame;
    }

    public boolean getIslastword() {
        return islastword;
    }

    public void setIslastword(boolean islastword) {
        this.islastword = islastword;
    }

    public boolean getIstask() {
        return istask;
    }

    public void setIstask(boolean istask) {
        this.istask = istask;
    }

    public boolean getIspenalty() {
        return penalty;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }
}
