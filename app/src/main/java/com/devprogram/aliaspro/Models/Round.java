package com.devprogram.aliaspro.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Round extends RealmObject {
    @Required
    @PrimaryKey
    String idround;
    String idteam;
    String idtask;
    String idgame;
    int number;
    int numberGame;
    String idteamLastWord;
    boolean isTaskComplete;
    int timeToFinish;

    public int getTimeToFinish(){return timeToFinish;}

    public void setTimeToFinish(int timeToFinish){this.timeToFinish = timeToFinish;}

    public int getNumberGame() {
        return numberGame;
    }

    public void setNumberGame(int numberGame) {
        this.numberGame = numberGame;
    }

    public void setGame(String idgame) {
        this.idgame = idgame;
    }

    public String getGame() {
        return idgame;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setTeam(String idteam) {
            this.idteam = idteam;
    }

    public String getTeam() {
        return idteam;
    }

    public String getIdround() {
        return idround;
    }

    public void setIdround(String idround) {
        this.idround = idround;
    }

    public String getTask() {
        return idtask;
    }

    public void setTask(String idtask) {
        this.idtask = idtask;
    }

    public String getIdteamLastWord() {
        return idteamLastWord;
    }

    public void setIdteamLastWord(String idteamLastWord) {
        this.idteamLastWord = idteamLastWord;
    }

    public Boolean getIsTaskComplete() {
        return isTaskComplete;
    }

    public void setIsTaskComplete(boolean isTaskComplete) {
        this.isTaskComplete = isTaskComplete;
    }

}
