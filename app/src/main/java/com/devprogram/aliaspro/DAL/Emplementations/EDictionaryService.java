package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.IDictionaryService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Difficulty;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Word;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

///DICTIONARY SERVICE

public class EDictionaryService implements IDictionaryService {

    Realm realm;
    public EDictionaryService(Realm realm)
    {
        this.realm = realm;
    }


    @Override
    public Dictionary getDictionary(String iddictionary) {
        return realm.where(Dictionary.class).equalTo("iddictionary",iddictionary).findFirst();
    }

    @Override
    public List<Dictionary> getDicitionaries() {
        return  realm.where(Dictionary.class).findAll();
    }

    @Override
    public String createDictionary(String name, String avatar, String price, String description, Language language, Difficulty difficulty) {
        realm.beginTransaction();
        String iddictionary = UUID.randomUUID().toString();
        Dictionary myDictionary = realm.createObject(Dictionary.class,iddictionary);
        myDictionary.setName(name);
        myDictionary.setAvatar(avatar);
        myDictionary.setPrice(price);
        myDictionary.setDescription(description);
        myDictionary.setDifficulty(difficulty);
        myDictionary.setLanguage(language);
        realm.commitTransaction();
        return iddictionary;
    }

    @Override
    public String updateDictionary(String iddictionary,  String name, String avatar, String price, String description, Language language, Difficulty difficulty) {
        realm.beginTransaction();
        Dictionary myDictionary = realm.where(Dictionary.class).equalTo("iddictionary",iddictionary).findFirst();
        myDictionary.setName(name);
        myDictionary.setAvatar(avatar);
        myDictionary.setPrice(price);
        myDictionary.setDescription(description);
        myDictionary.setDifficulty(difficulty);
        myDictionary.setLanguage(language);
        realm.commitTransaction();
        return iddictionary;
    }

    @Override
    public String deleteDictionary(String iddictionary) {
        realm.beginTransaction();
        realm.where(Dictionary.class).equalTo("iddictionary",iddictionary).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return iddictionary;
    }

    public int getWordsCount(String iddictionary)
    {
        int count = 0;
        realm.beginTransaction();
        List<Word> words = realm.where(Word.class).equalTo("iddictionary",iddictionary).findAll();
        count = words.size();
        realm.commitTransaction();
        return count;
    }

    public List<Word> getWordsDictionary(String iddictionary)
    {
        return realm.where(Word.class).equalTo("iddictionary",iddictionary).findAll();
    }

}
