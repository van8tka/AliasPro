package com.devprogram.aliaspro.DAL.Implementations;

import com.devprogram.aliaspro.DAL.Interfaces.IWordStatusService;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

public class EWordStatusService implements IWordStatusService {
    Realm realm;
    public EWordStatusService(Realm realm)
    {
        this.realm = realm;
    }
    @Override
    public WordStatus getWordStatus(String idwordstatus) {
        return realm.where(WordStatus.class).equalTo("idwordstatus",idwordstatus).findFirst();
    }

    @Override
    public WordStatus getWordStatus(String idword, String idRound) {
        return realm.where(WordStatus.class).equalTo("idRound",idRound).equalTo("idwordShowed",idword).findFirst();
    }

    @Override
    public List<WordStatus> getWordsStatus() {
        return realm.where(WordStatus.class).findAll();
    }

    @Override
    public List<WordStatus> getWordsStatusShowedRound(String idRound) {
        return realm.where(WordStatus.class).equalTo("idRound",idRound).findAll();
    }

    //set status 1-win 0-not win
    @Override
    public String createWordStatus(int status, String idword, String idgame, String idRound) {
        String idwordstatus = UUID.randomUUID().toString();
        realm.beginTransaction();
        WordStatus ws = realm.createObject(WordStatus.class,idwordstatus);
        ws.setIdGame(idgame);
        ws.setIdwordShowed(idword);
        ws.setStatus(status);
        ws.setIdRound(idRound);
        realm.commitTransaction();
        return idwordstatus;
    }

    @Override
    public String updateWordStatus(String idwordstatus, int status, String idword, String idgame, String idRound) {
        realm.beginTransaction();
        WordStatus ws = realm.where(WordStatus.class).equalTo("idwordstatus",idwordstatus).findFirst();
        ws.setIdGame(idgame);
        ws.setIdwordShowed(idword);
        ws.setStatus(status);
        ws.setIdRound(idRound);
        realm.commitTransaction();
        return idwordstatus;
    }

    @Override
    public String updateWordStatus(int status, String idword, String idRound) {
        realm.beginTransaction();
        WordStatus ws = realm.where(WordStatus.class).equalTo("idwordShowed",idword)
                .equalTo("idRound",idRound).findFirst();
        ws.setStatus(status);
        realm.commitTransaction();
        return idword;
    }




    @Override
    public String deleteWordStatus(String idwordstatus) {
        realm.beginTransaction();
        realm.where(WordStatus.class).equalTo("idwordstatus",idwordstatus).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return idwordstatus;
    }

    @Override
    public String deleteWordStatusForGame(String idGame) {
        realm.beginTransaction();
        realm.where(WordStatus.class).equalTo("idGame",idGame).findAll().deleteAllFromRealm();
        realm.commitTransaction();
        return idGame;
    }

    @Override
    public int countShowedWord(String idgame) {
        realm.beginTransaction();
         List<WordStatus> ws = realm.where(WordStatus.class).equalTo("idGame",idgame).findAll();
        realm.commitTransaction();
        return ws.size();
    }
}
