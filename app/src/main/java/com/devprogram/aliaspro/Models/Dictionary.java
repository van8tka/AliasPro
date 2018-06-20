package com.devprogram.aliaspro.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Dictionary  extends RealmObject {
    @PrimaryKey
    int iddictionary;
    RealmList<Word> words;
    String name;
    String avatar;
    String price;
    String description;
    int idlanguage;
    int iddifficulty;
}
