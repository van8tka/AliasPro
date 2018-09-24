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
        realm.beginTransaction();
        Word word = realm.createObject(Word.class,idword);
        word.setIddictionary(idDictionary);
        word.setName(name);
        word.setLanguage(idlanguage);
        realm.commitTransaction();
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
            List<Word> listAll = realm.where(Word.class).equalTo("iddictionary",iddictionary).findAll();
            listAll = realm.copyFromRealm(listAll);
            List<WordStatus> deleteListStatus = realm.where(WordStatus.class).equalTo("idGame",idGame).findAll();

            if(deleteListStatus.size()>0)
            {
                for(int i = 0;i<deleteListStatus.size();i++)
                {
                   int index = -1;
                   WordStatus wd = deleteListStatus.get(i);
                   for(int j = 0;j<listAll.size();j++)
                   {
                       if(listAll.get(j).getIdword().equalsIgnoreCase(wd.getIdwordShowed()))
                       {
                           index = j;break;
                       }
                   }
                   if(index>-1)
                      listAll.remove(index);
                }
            }
            return listAll;
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
