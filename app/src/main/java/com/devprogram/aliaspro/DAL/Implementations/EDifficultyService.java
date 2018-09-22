package com.devprogram.aliaspro.DAL.Implementations;

import com.devprogram.aliaspro.DAL.Interfaces.IDifficultyService;
import com.devprogram.aliaspro.Models.Difficulty;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;

//DIFFICULTY SERVICE

public class EDifficultyService implements IDifficultyService {
    Realm realm;
    public EDifficultyService(Realm realm){
            this.realm = realm;
        }


    @Override
    public Difficulty getDifficulty(String iddifficulty) {
        return realm.where(Difficulty.class).equalTo("iddifficulty",iddifficulty).findFirst();
    }

    @Override
    public List<Difficulty> getDifficulties() {
        return  realm.where(Difficulty.class).findAll();
    }

    @Override
    public String createDifficulty(String name, String idlanguage) {
        realm.beginTransaction();
        String iddifficulty = UUID.randomUUID().toString();
        Difficulty myDiff = realm.createObject(Difficulty.class,iddifficulty);
        myDiff.setName(name);
        myDiff.setLanguage(idlanguage);
        realm.commitTransaction();
        return iddifficulty;
    }

    @Override
    public String updateDifficulty(String iddifficulty, String name, String idlanguage) {
        realm.beginTransaction();
        Difficulty myDiff = realm.where(Difficulty.class).equalTo("iddifficulty",iddifficulty).findFirst();
        myDiff.setName(name);
        myDiff.setLanguage(idlanguage);
        realm.commitTransaction();
        return iddifficulty;
    }



    @Override
    public String deleteDifficulty(String iddifficulty) {
        realm.beginTransaction();
        realm.where(Difficulty.class).equalTo("iddifficulty",iddifficulty).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return iddifficulty;
    }
}
