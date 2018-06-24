package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.IRoundService;
import com.devprogram.aliaspro.Models.Round;
import com.devprogram.aliaspro.Models.Task;
import com.devprogram.aliaspro.Models.Team;
import com.devprogram.aliaspro.Models.Word;

import java.util.List;

import io.realm.Realm;

public class ERoundService implements IRoundService {
    Realm realm;
    public ERoundService(Realm realm)
    {
        this.realm = realm;
    }
    @Override
    public Round getRound(String idround) {
        return null;
    }

    @Override
    public List<Round> getRounds() {
        return null;
    }

    @Override
    public String createRound(String name, List<Team> idteam, List<Word> words, Task task) {
        return null;
    }

    @Override
    public String updateRound(String idround, String name, List<Team> idteam, List<Word> words, Task task) {
        return null;
    }

    @Override
    public String deleteRound(String idround) {
        return null;
    }
}
