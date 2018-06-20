package com.devprogram.aliaspro.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Game extends RealmObject {
    @PrimaryKey
    int idgame;
    int iddictionary;
    RealmList<Team> teams;
    boolean istask;
    boolean islastword;
    boolean penalty;
    int countwords;
    int seconds;
    boolean isfinish;
}
