package com.devprogram.aliaspro.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Round extends RealmObject {
    @PrimaryKey
    String idround;
    String name;
    String idteam;
    RealmList<Word> words;
    String idtask;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIdteam(String idteam) {
        this.idteam = idteam;
    }

    public String getIdteam() {
        return idteam;
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

    public String getIdtask() {
        return idtask;
    }

    public void setIdtask(String idtask) {
        this.idtask = idtask;
    }
}
