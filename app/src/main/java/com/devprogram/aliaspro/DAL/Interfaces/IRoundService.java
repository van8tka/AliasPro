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
    String createRound(String name, Team team, RealmList<Word> words, Task task, Game game, int numberRound, int numberGame);
    String updateRound(String idround, String name, Team team, RealmList<Word> words, Task task, Game game, int numberRound, int numberGame);
    String deleteRound(String idround);
    String addWordRound(String idround, Word word);
}
