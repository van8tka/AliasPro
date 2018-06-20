package com.devprogram.aliaspro.Models;
import java.util.OptionalInt;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Team extends RealmObject {
    @PrimaryKey
    int idteam;
    String name;
    String avatar;
    int score;
    Boolean winner;
    int idlanguage;
}
