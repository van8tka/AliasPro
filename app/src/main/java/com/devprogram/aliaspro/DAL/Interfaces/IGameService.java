package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;

import io.realm.RealmList;

public interface IGameService {
    Game getGame(String idgame);
    List<Game> getGames();
    String createGame(String idgame, String iddictionary,List<Team> teams, boolean istask, boolean islastword
            ,boolean penalty, int countwords, int seconds, boolean isfinish, String datestart);
    String updateGame(String idgame, int iddictionary,List<Team> teams, boolean istask, boolean islastword
            ,boolean penalty, int countwords, int seconds, boolean isfinish, String datestart);
    String deleteGame(String idgame);
}
