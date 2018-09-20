package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Word extends RealmObject{
   @Required
    @PrimaryKey
    String idword;
    String iddictionary;
    String name;
    String idlanguage;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLanguage(String idlanguage) {
        this.idlanguage = idlanguage;
    }

    public String getLanguage() {
        return idlanguage;
    }

    public void setIddictionary(String iddictionary) {
        this.iddictionary = iddictionary;
    }

    public String getIddictionary() {
        return iddictionary;
    }

    public String getIdword() {
        return idword;
    }

    public void setIdword(String idword) {
        this.idword = idword;
    }
}
