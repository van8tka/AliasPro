package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Word extends RealmObject{
    @PrimaryKey
    String idword;
    String iddictionary;
    String name;
    String idlanguage;
    String idwordstatus;

    public void setIdwordstatus(String idwordstatus) {
        this.idwordstatus = idwordstatus;
    }

    public String getIdwordstatus() {
        return idwordstatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIdlanguage(String idlanguage) {
        this.idlanguage = idlanguage;
    }

    public void setIddictionary(String iddictionary) {
        this.iddictionary = iddictionary;
    }

    public String getIddictionary() {
        return iddictionary;
    }

    public String getIdlanguage() {
        return idlanguage;
    }

    public String getIdword() {
        return idword;
    }

    public void setIdword(String idword) {
        this.idword = idword;
    }
}
