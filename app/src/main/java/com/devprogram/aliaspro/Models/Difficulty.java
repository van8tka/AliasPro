package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Difficulty extends RealmObject {
    @PrimaryKey
    int iddifficulty;
    String name;
}
