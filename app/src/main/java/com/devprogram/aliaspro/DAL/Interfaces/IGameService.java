package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;

import io.realm.RealmList;

public interface IGameService {
    Game getGame(String idgame);
    List<Game> getGames();
    String createGame(String iddictionary, boolean istask, boolean islastword
            , boolean penalty, int countwords, int seconds,boolean isfinishgame);
    String updateGame(String idgame, String iddictionary,  boolean istask, boolean islastword
            ,boolean penalty, int countwords, int seconds,boolean isfinishgame);
    String deleteGame(String idgame);
    String setFinishGame(String idgame,boolean isfinishgame);
    Game getLastGame();
}
