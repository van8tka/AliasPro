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
    Language language;
    WordStatus wordstatus;
    Boolean isshowed;

    public void setWordstatus(WordStatus wordstatus) {
        this.wordstatus = wordstatus;
    }

    public Boolean getIsshowed() {
        return isshowed;
    }
    public void setIsshowed(boolean isshow)
    {
        isshowed = isshow;
    }

    public WordStatus getWordstatus() {
        return wordstatus;
    }

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
