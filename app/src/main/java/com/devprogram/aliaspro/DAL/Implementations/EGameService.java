package com.devprogram.aliaspro.DAL.Implementations;

import com.devprogram.aliaspro.DAL.Interfaces.IGameService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

//GAME SERVICE

public class EGameService implements IGameService {
    Realm realm;
    public EGameService(Realm realm)
    {
        this.realm = realm;
    }

    @Override
    public Game getGame(String idgame) {
        return realm.where(Game.class).equalTo("idgame",idgame).findFirst();
    }

    @Override
    public List<Game> getGames() {
        return realm.where(Game.class).findAll();
    }

    @Override
    public String createGame(String iddictionary, boolean istask, boolean islastword, boolean penalty, int countwords, int seconds, boolean isfinishgame) {
        String idgame = UUID.randomUUID().toString();
        realm.beginTransaction();
        Game game = realm.createObject(Game.class,idgame);
        game.setDictionary(iddictionary);
        game.setIstask(istask);
        game.setIslastword(islastword);
        game.setPenalty(penalty);
        game.setCountwords(countwords);
        game.setIsfinishgame(isfinishgame);
        game.setSeconds(seconds);
        realm.commitTransaction();
        return idgame;
    }

    @Override
    public String updateGame(String idgame, String iddictionary, boolean istask, boolean islastword, boolean penalty, int countwords, int seconds, boolean isfinishgame) {
        realm.beginTransaction();
        Game game = realm.where(Game.class).equalTo("idgame",idgame).findFirst();
        game.setDictionary(iddictionary);
        game.setIstask(istask);
        game.setIslastword(islastword);
        game.setPenalty(penalty);
        game.setCountwords(countwords);
        game.setIsfinishgame(isfinishgame);
        game.setSeconds(seconds);
        realm.commitTransaction();
        return idgame;
    }





    @Override
    public String deleteGame(String idgame) {
        realm.beginTransaction();
        realm.where(Game.class).equalTo("idgame",idgame).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return idgame;
    }

    @Override
    public String setFinishGame(String idgame, boolean isfinishgame) {
        realm.beginTransaction();
        Game game = realm.where(Game.class).equalTo("idgame",idgame).findFirst();
        game.setIsfinishgame(isfinishgame);
        realm.commitTransaction();
        return idgame;
    }

    @Override
    public Game getLastGame() {
        return realm.where(Game.class).findAll().last();
    }
}
