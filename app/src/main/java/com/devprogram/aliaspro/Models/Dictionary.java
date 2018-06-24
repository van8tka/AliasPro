package com.devprogram.aliaspro.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Dictionary  extends RealmObject {
    @PrimaryKey
    String iddictionary;
    RealmList<Word> words;
    String name;
    String avatar;
    String price;
    String description;
    Language language;
    Difficulty difficulty;

    public void setIddictionary(String iddictionary) {
        this.iddictionary = iddictionary;
    }

    public String getIddictionary() {
        return iddictionary;
    }

    public RealmList<Word> getWords() {
        return words;
    }

    public void setWords(RealmList<Word> words) {
        this.words = words;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
