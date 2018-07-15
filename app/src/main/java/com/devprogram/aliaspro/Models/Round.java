package com.devprogram.aliaspro.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Round extends RealmObject {
    @Required
    @PrimaryKey
    String idround;
    String name;
    Team team;
    RealmList<Word> words;
    Task task;
    Game game;
    int number;

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTeam(Team team) {
            this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setWords(RealmList<Word> words) {
        this.words = words;
    }

    public RealmList<Word> getWords() {
        return words;
    }

    public String getIdround() {
        return idround;
    }

    public void setIdround(String idround) {
        this.idround = idround;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
