package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.IWordService;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;

public class EWordService implements IWordService {
    Realm realm;
    public EWordService(Realm realm)
    {this.realm = realm;}

    @Override
    public Word getWord(String idword) {
        return realm.where(Word.class).equalTo("idword",idword).findFirst();
    }

    @Override
    public List<Word> getWords() {
        return realm.where(Word.class).findAll();
    }

    @Override
    public String createWord(String name,String idDictionary, Language language, WordStatus wordstatus) {
        String idword = UUID.randomUUID().toString();
        realm.beginTransaction();
        Word word = realm.createObject(Word.class,idword);
        word.setIddictionary(idDictionary);
        word.setName(name);
        word.setLanguage(language);
        word.setWordstatus(wordstatus);
        word.setIsshowed(false);
        realm.commitTransaction();
        return idword;
    }

    @Override
    public String updareWord(String idword, String name, Language language, WordStatus wordstatus, boolean isshowed) {
        realm.beginTransaction();
        Word word = realm.where(Word.class).equalTo("idword",idword).findFirst();
        word.setName(name);
        word.setLanguage(language);
        word.setWordstatus(wordstatus);
        word.setIsshowed(isshowed);
        realm.commitTransaction();
        return idword;
    }

    @Override
    public String resetWord(String idword) {
        realm.beginTransaction();
        Word word = realm.where(Word.class).equalTo("idword",idword).findFirst();
        WordStatus ws = realm.where(WordStatus.class).equalTo("status","не отгадано").findFirst();
        word.setWordstatus(ws);
        word.setIsshowed(false);
        realm.commitTransaction();
        return idword;
    }

    public String updateStatusOfWord(String idword, WordStatus ws){
        realm.beginTransaction();
        Word word = realm.where(Word.class).equalTo("idword",idword).findFirst();
        word.setWordstatus(ws);
        realm.commitTransaction();
        return idword;
    }
    public String updateShowedOfWord(String idword, boolean isshowed){
        realm.beginTransaction();
        Word word = realm.where(Word.class).equalTo("idword",idword).findFirst();
        word.setIsshowed(isshowed);
        realm.commitTransaction();
        return idword;
    }

    @Override
    public String deleteWord(String idword) {
         realm.where(Word.class).equalTo("idword",idword).findFirst().deleteFromRealm();return idword;
    }
}
