package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WordStatus  extends RealmObject {
    @PrimaryKey
    String idwordstatus;
    int status;
    String idwordShowed;
    String idGame;
    String idRound;

//1-отгадано 0-неотгадано

    public String getIdwordstatus() {
        return idwordstatus;
    }

    public void setIdwordstatus(String idwordstatus) {
        this.idwordstatus = idwordstatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIdwordShowed() {
        return idwordShowed;
    }

    public void setIdwordShowed(String idwordShowed) {
        this.idwordShowed = idwordShowed;
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public String getIdRound(){return idRound;}

    public void setIdRound(String idRound){this.idRound = idRound;}

}
