package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;

import java.util.List;

import io.realm.RealmList;

public interface IRoundService {
    Round getRound(String idround);
    List<Round> getRounds();
    String createRound(String name, List<Team>idteam, List<Word> words, Task task);
    String updateRound(String idround, String name, List<Team>idteam, List<Word> words, Task task);
    String deleteRound(String idround);
}
