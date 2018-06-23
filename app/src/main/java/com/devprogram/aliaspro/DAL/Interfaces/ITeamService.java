package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Team;

import java.util.List;

public interface ITeamService {
    Team getTeam(String id);
    List<Team> getTeams();
    String createTeam(String idteam,String name,String avatar,int score,Boolean winner,String idlanguage);
    String updateTeam(String idteam,String name,String avatar,int score,Boolean winner,String idlanguage);
    String deleteTeam(String idteam);
}
