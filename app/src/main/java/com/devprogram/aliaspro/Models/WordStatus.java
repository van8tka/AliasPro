package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WordStatus  extends RealmObject {
    @PrimaryKey
    int idwordstatus;
    String status;
}
