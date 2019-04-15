package com.devprogram.aliaspro.DAL.Implementations;

import android.util.Log;

import com.devprogram.aliaspro.DAL.Interfaces.IWordService;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.ArrayList;
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
    public String createWord(String name, String idDictionary, String idlanguage) {
        String idword = UUID.randomUUID().toString();
     //   realm.beginTransaction();
        Word word = realm.createObject(Word.class,idword);
        word.setIddictionary(idDictionary);
        word.setName(name);
        word.setLanguage(idlanguage);
    //    realm.commitTransaction();
        return idword;
    }

    @Override
    public String updareWord(String idword, String name, String idDictionary, String idlanguage) {
        realm.beginTransaction();
        Word word = realm.where(Word.class).equalTo("idword",idword).findFirst();
        word.setIddictionary(idDictionary);
        word.setName(name);
        word.setLanguage(idlanguage);
        realm.commitTransaction();
        return idword;
    }


    @Override
    public String deleteWord(String idword) {
         realm.where(Word.class).equalTo("idword",idword).findFirst().deleteFromRealm();
         return idword;
    }

    @Override
    public List<Word> getWordsWithOutShowed(String iddictionary, String idGame) {
        try
        {
            List<WordStatus> deleteListStatus = realm.where(WordStatus.class).equalTo("idGame",idGame).findAll();
            List<String> idNotExist = new ArrayList<String>();
            for(int i=0;i<deleteListStatus.size();i++)
                idNotExist.add(deleteListStatus.get(i).getIdwordShowed());
            List<Word> listAll = realm.where(Word.class).equalTo("iddictionary",iddictionary).not().in("idword",idNotExist.toArray(new String[0])).findAll();
            return realm.copyFromRealm(listAll);
        }
        catch(Exception er)
        {
            Log.e("E_WORD_SERV_STR_60",er.getMessage());
            return new ArrayList<Word>();
        }
    }

    @Override
    public List<Word> getWordsFromDictionary(String iddictionary) {
        List<Word> temp = realm.where(Word.class).equalTo("iddictionary",iddictionary).findAll();
       return realm.copyFromRealm(temp);
    }

    @Override
    public List<Word> getShowedRoundWords(String idround) {
        List<WordStatus> st = realm.where(WordStatus.class).equalTo("idRound",idround).findAll();
        List<Word> wd = new ArrayList<Word>();
        for(int i = 0;i<st.size();i++)
            wd.add(this.getWord(st.get(i).getIdwordShowed()));
        return wd;
    }
}
