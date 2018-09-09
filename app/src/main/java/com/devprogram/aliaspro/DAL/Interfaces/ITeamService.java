package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;

public interface ITeamService {
    Team getTeam(String id);
    List<Team> getTeams();
    String createTeam(String name,String avatar,int score,int scoreAll,Boolean winner,Language language);
    String updateTeam(String idteam,String name,String avatar,int score,int scoreAll,Boolean winner,Language language);
    String deleteTeam(String idteam);
    int setScoreRoundTeam(String idteam, int score);
    int setScoreAllTeam(String idteam, int score);
    int getScoreRoundTeam(String idteam);
    int getScoreAllTeam(String idteam);
}
