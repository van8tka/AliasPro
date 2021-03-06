package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Difficulty extends RealmObject {
    @Required
    @PrimaryKey
    String iddifficulty;
    String name;
    Language language;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public void setIddifficulty(String iddifficulty) {
        this.iddifficulty = iddifficulty;
    }

    public String getIddifficulty() {
        return iddifficulty;
    }

}
