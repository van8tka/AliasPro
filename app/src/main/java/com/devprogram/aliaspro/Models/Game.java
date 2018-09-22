package com.devprogram.aliaspro.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Game extends RealmObject {
    @Required
    @PrimaryKey
    String idgame;
    String iddictionary;
    boolean istask;
    boolean islastword;
    boolean penalty;
    int countwords;
    int seconds;
    boolean isfinishgame;


    public String getDictionary() {
        return iddictionary;
    }

    public void setDictionary(String iddictionary) {
        this.iddictionary = iddictionary;
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

    public boolean getIsfinishgame(){return isfinishgame;}
    public void setIsfinishgame(boolean isfinishgame){this.isfinishgame = isfinishgame;}
}
