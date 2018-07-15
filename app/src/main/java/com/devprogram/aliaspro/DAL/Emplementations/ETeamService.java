package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.ITeamService;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;

///TEAM SERVICE

public class ETeamService implements ITeamService {
    Realm realm;
    public ETeamService(Realm realm)
    {
        this.realm = realm;
    }

    @Override
    public Team getTeam(String id) {
        return realm.where(Team.class).equalTo("idteam",id).findFirst();
    }

    @Override
    public List<Team> getTeams() {
        List<Team> list;
        list = realm.copyFromRealm(realm.where(Team.class).findAll());
        return list;
    }

    @Override
    public String createTeam(String name, String avatar, int score,int scoreAll, Boolean winner, Language language) {
        realm.beginTransaction();
        String idteam = UUID.randomUUID().toString();
        Team myTeam = realm.createObject(Team.class,idteam);
        myTeam.setName(name);
        myTeam.setAvatar(avatar);
        myTeam.setScore(score);
        myTeam.setWinner(winner);
        myTeam.setLanguage(language);
        realm.commitTransaction();
        return idteam;
    }

    @Override
    public String updateTeam(String idteam, String name, String avatar, int score,int scoreAll, Boolean winner, Language language) {
        realm.beginTransaction();
        Team myTeam = realm.where(Team.class).equalTo("idteam",idteam).findFirst();
        myTeam.setName(name);
        myTeam.setAvatar(avatar);
        myTeam.setScore(score);
        myTeam.setWinner(winner);
        myTeam.setLanguage(language);
        realm.commitTransaction();
        return idteam;
    }

    @Override
    public String deleteTeam(String idteam) {
        realm.beginTransaction();
        realm.where(Team.class).equalTo("idteam",idteam).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return idteam;
    }
}
