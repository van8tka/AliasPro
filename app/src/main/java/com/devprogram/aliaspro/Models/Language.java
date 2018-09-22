package com.devprogram.aliaspro.Models;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Language extends RealmObject implements Serializable{
    @Required
   @PrimaryKey
    String idlanguage;
    String name;
    String avatar;

    public String getIdlanguage() {
        return idlanguage;
    }

    public void setIdlanguage(String idlanguage) {
        this.idlanguage = idlanguage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
