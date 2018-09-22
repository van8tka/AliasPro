package com.devprogram.aliaspro.Models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Dictionary  extends RealmObject {
    @Required
    @PrimaryKey
    String iddictionary;
    String name;
    String avatar;
    String price;
    String description;
    String idlanguage;
    String iddifficulty;
    boolean isfinishgame;

    public void setIddictionary(String iddictionary) {
        this.iddictionary = iddictionary;
    }

    public String getIddictionary() {
        return iddictionary;
    }

    public String getDifficulty() {
        return iddifficulty;
    }

    public void setDifficulty(String iddifficulty) {
        this.iddifficulty = iddifficulty;
    }

    public String getLanguage() {
        return idlanguage;
    }

    public void setLanguage(String idlanguage) {
        this.idlanguage = idlanguage;
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

    public boolean getIsfinishgame(){return isfinishgame;}
    public void setIsfinishgame(Boolean isfinishgame){this.isfinishgame = isfinishgame;}


}
