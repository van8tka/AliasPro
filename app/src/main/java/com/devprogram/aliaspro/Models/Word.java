package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Word extends RealmObject{
    @PrimaryKey
    int idword;
    int iddictionary;
    String name;
    int idlanguage;
    int idwordstatus;
}
