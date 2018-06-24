package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;

public interface ITeamService {
    Team getTeam(String id);
    List<Team> getTeams();
    String createTeam(String name,String avatar,int score,Boolean winner,Language language);
    String updateTeam(String idteam,String name,String avatar,int score,Boolean winner,Language language);
    String deleteTeam(String idteam);
}
