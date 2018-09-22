package com.devprogram.aliaspro.DAL.Implementations;

import com.devprogram.aliaspro.DAL.Interfaces.IPlayingTeams;
import com.devprogram.aliaspro.Models.PlayingTeams;
import com.devprogram.aliaspro.Models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;

public class EPlayingTeamsService implements IPlayingTeams {

    Realm realm;
    public EPlayingTeamsService(Realm realm){
        this.realm = realm;
    }

    @Override
    public List<Team> getListTeamByGame(String idGame)
    {
       List<PlayingTeams> playingTeams = realm.where(PlayingTeams.class).equalTo("idGame",idGame).findAll();
       List<Team> teams = new ArrayList<Team>() ;
       for(PlayingTeams pt:playingTeams)
       {
           teams.add(realm.where(Team.class).equalTo("idteam",pt.getIdTeam()).findFirst());
       }
       return teams;
    }



    @Override
    public PlayingTeams getPlayingTeams(String id) {
        return realm.where(PlayingTeams.class).equalTo("id",id).findFirst();
    }

    @Override
    public List<PlayingTeams> getListPlayingTeams(String id) {
        return realm.where(PlayingTeams.class).findAll();
    }

    @Override
    public PlayingTeams getPlayingTeams(String idTeam, String idGame) {
        return realm.where(PlayingTeams.class).equalTo("idTeam",idTeam).equalTo("idGame",idGame).findFirst();
    }

    @Override
    public List<PlayingTeams> getPlayingTeamsByGame(String idGame) {
        return realm.where(PlayingTeams.class).equalTo("idGame",idGame).findAll();
    }

    @Override
    public String createPlayingTeams(String idTeam, String idGame, int scoreRound, int scoreGame) {
       realm.beginTransaction();
       String id = UUID.randomUUID().toString();
       PlayingTeams play = realm.createObject(PlayingTeams.class, id);
       play.setIdTeam(idTeam);
       play.setIdGame(idGame);
       play.setScoreAll(scoreGame);
       play.setScoreRound(scoreRound);
       realm.commitTransaction();
       return id;
    }

    @Override
    public String updatePlayingTeams(String id, String idTeam, String idGame, int scoreRound, int scoreGame) {
        realm.beginTransaction();
        PlayingTeams play = realm.where(PlayingTeams.class).equalTo("id",id).findFirst();
        play.setIdTeam(idTeam);
        play.setIdGame(idGame);
        play.setScoreAll(scoreGame);
        play.setScoreRound(scoreRound);
        realm.commitTransaction();
        return id;
    }

    @Override
    public String updatePlayingTeams(String idTeam, String idGame, int scoreRound, int scoreGame) {
        realm.beginTransaction();
        PlayingTeams play = realm.where(PlayingTeams.class).equalTo("idTeam",idTeam).equalTo("idGame",idGame).findFirst();
        play.setScoreAll(scoreGame);
        play.setScoreRound(scoreRound);
        realm.commitTransaction();
        return idTeam;
    }

    @Override
    public String setScoreAll(String idTeam, String idGame, int scoreGame) {
        realm.beginTransaction();
        PlayingTeams play = realm.where(PlayingTeams.class).equalTo("idTeam",idTeam).equalTo("idGame",idGame).findFirst();
        play.setScoreAll(scoreGame);
        realm.commitTransaction();
        return idTeam;
    }

    @Override
    public String setScoreAll(String id, int scoreGame) {
        realm.beginTransaction();
        PlayingTeams play = realm.where(PlayingTeams.class).equalTo("id",id).findFirst();
        play.setScoreAll(scoreGame);
        realm.commitTransaction();
        return id;
    }

    @Override
    public String setScoreRound(String idTeam, String idGame, int scoreRound) {
        realm.beginTransaction();
        PlayingTeams play = realm.where(PlayingTeams.class).equalTo("idTeam",idTeam).equalTo("idGame",idGame).findFirst();
        play.setScoreRound(scoreRound);
        realm.commitTransaction();
        return idTeam;
    }

    @Override
    public String setScoreRound(String id, int scoreRound) {
        realm.beginTransaction();
        PlayingTeams play = realm.where(PlayingTeams.class).equalTo("id",id).findFirst();
        play.setScoreRound(scoreRound);
        realm.commitTransaction();
        return id;
    }

    @Override
    public String deletePlayingTeams(String id) {
        realm.beginTransaction();
        realm.where(PlayingTeams.class).equalTo("id",id).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return id;
    }

    @Override
    public String deletePlayingTeams(String idTeam, String idGame) {
        realm.beginTransaction();
        realm.where(PlayingTeams.class).equalTo("idTeam",idTeam).equalTo("idGame",idGame).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return idTeam;
    }
}
