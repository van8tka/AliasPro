package com.devprogram.aliaspro.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Round extends RealmObject {
    @PrimaryKey
    int idround;
    String name;
    RealmList<Team> idteam;
    RealmList<Word> words;
    int idtask;
}
