package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {
    @PrimaryKey
   int idtask;
   String name;
   String description;
   String avatar;
   boolean complete;
   int addscore;
   int idlanguage;
}
