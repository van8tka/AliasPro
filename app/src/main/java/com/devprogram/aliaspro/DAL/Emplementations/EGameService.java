package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.IGameService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;

import io.realm.Realm;

//GAME SERVICE

public class EGameService implements IGameService {
    Realm realm;
    public EGameService(Realm realm)
    {
        this.realm = realm;
    }

    @Override
    public Game getGame(String idgame) {
        return null;
    }

    @Override
    public List<Game> getGames() {
        return null;
    }

    @Override
    public String createGame(Dictionary dictionary, List<Team> teams, boolean istask, boolean islastword, boolean penalty, int countwords, int seconds, boolean isfinish, String datestart) {
        return null;
    }

    @Override
    public String updateGame(String idgame, Dictionary dictionary, List<Team> teams, boolean istask, boolean islastword, boolean penalty, int countwords, int seconds, boolean isfinish, String datestart) {
        return null;
    }

    @Override
    public String deleteGame(String idgame) {
        return null;
    }
}
