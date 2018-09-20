package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Game;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;

import java.util.List;

import io.realm.RealmList;

public interface IRoundService {
    Round getRound(String idround);
    List<Round> getRounds();
    String createRound(String idteam, String idtask, String idgame, int numberRound, int numberGame, String idTeamLastWord, Boolean taskComplete );
    String updateRound(String idround, String idteam, String idtask, String idgame, int numberRound, int numberGame, String idTeamLastWord, Boolean taskComplete );
    String deleteRound(String idround);
    String changeTaskComplete(String idround, boolean isComplete);
    String setIdTeamLastWord(String idround, String idTeam);
    String setNumberRound(String idround, int numberRound);
    String setNumberGame(String idround, int numberGame);
}
