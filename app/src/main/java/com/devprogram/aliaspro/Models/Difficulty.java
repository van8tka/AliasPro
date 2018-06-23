package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Difficulty extends RealmObject {
    @PrimaryKey
    String iddifficulty;
    String name;
    String idlanguage;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIdlanguage(String idlanguage) {
        this.idlanguage = idlanguage;
    }

    public String getIdlanguage() {
        return idlanguage;
    }

    public void setIddifficulty(String iddifficulty) {
        this.iddifficulty = iddifficulty;
    }

    public String getIddifficulty() {
        return iddifficulty;
    }

}
