package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Language extends RealmObject {
    @PrimaryKey
    int idlanguage;
    String name;
}
