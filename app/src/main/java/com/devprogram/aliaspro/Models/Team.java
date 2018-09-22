package com.devprogram.aliaspro.Models;
import java.io.Serializable;
import java.util.OptionalInt;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Team extends RealmObject implements Serializable{
    @Required
    @PrimaryKey
    String idteam;
    String name;
    String avatar;
    String idlanguage;




    public String getLanguage() {
        return idlanguage;
    }

    public void setLanguage(String idlanguage) {
        this.idlanguage = idlanguage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getIdteam() {
        return idteam;
    }

    public void setIdteam(String idteam) {
        this.idteam = idteam;
    }
}
