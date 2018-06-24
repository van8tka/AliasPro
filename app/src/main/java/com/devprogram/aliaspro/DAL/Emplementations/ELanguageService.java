package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.ILanguageService;
import com.devprogram.aliaspro.Models.Language;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;

///LANGUAGE SERVICE

public class ELanguageService implements ILanguageService {
    Realm realm;
    public ELanguageService(Realm realm){
        this.realm = realm;
    }


    @Override
    public Language getLanguage(String idlanguage) {
        return realm.where(Language.class).equalTo("idlanguage",idlanguage).findFirst();
    }
    @Override
    public Language getLanguageByName(String language) {
        return realm.where(Language.class).equalTo("name",language).findFirst();
    }

    @Override
    public List<Language> getLanguages() {
        return  realm.where(Language.class).findAll();
    }

    @Override
    public String createLanguage(String name, String avatar) {
        realm.beginTransaction();
        String idlanguage = UUID.randomUUID().toString();
        Language myLanguage = realm.createObject(Language.class, idlanguage);
        myLanguage.setName(name);
        myLanguage.setAvatar(avatar);
        realm.commitTransaction();
        return idlanguage;
    }

    @Override
    public String updateLanguage(String idlanguage, String name, String avatar) {
        realm.beginTransaction();
        Language myLanguage = realm.where(Language.class).equalTo("idlanguage",idlanguage).findFirst();
        myLanguage.setName(name);
        myLanguage.setAvatar(avatar);
        realm.commitTransaction();
        return idlanguage;
    }

    @Override
    public String deleteLanguage(String id) {
        realm.beginTransaction();
        realm.where(Language.class).equalTo("idlanguage",id).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return id;
    }
}
