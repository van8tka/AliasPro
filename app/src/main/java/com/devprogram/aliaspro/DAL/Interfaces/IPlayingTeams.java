package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.PlayingTeams;
import com.devprogram.aliaspro.Models.Team;

import java.util.List;

public interface IPlayingTeams {
    PlayingTeams getPlayingTeams(String id);
    PlayingTeams getPlayingTeams(String idTeam, String idGame);
    List<PlayingTeams> getPlayingTeamsByGame(String idGame);
    List<PlayingTeams> getListPlayingTeams(String id);
    List<Team> getListTeamByGame(String idGame);
    String createPlayingTeams(String idTeam, String idGame, int scoreRound, int scoreGame);
    String updatePlayingTeams(String id,String idTeam, String idGame, int scoreRound, int scoreGame);
    String updatePlayingTeams(String idTeam, String idGame, int scoreRound, int scoreGame);

    String setScoreAll(String idTeam, String idGame, int scoreGame);
    String setScoreAll(String id, int scoreGame);

    String setScoreRound(String idTeam, String idGame, int scoreRound);
    String setScoreRound(String id, int scoreRound);

    String deletePlayingTeams(String id);
    String deletePlayingTeams(String idTeam, String idGame);
}
