package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.IWordStatusService;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;

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
    public List<WordStatus> getWordsStatus() {
        return realm.where(WordStatus.class).findAll();
    }

    @Override
    public String createWordStatus(String name) {
        String idwordstatus = UUID.randomUUID().toString();
        realm.beginTransaction();
        WordStatus ws = realm.createObject(WordStatus.class,idwordstatus);
        ws.setStatus(name);
        realm.commitTransaction();
        return idwordstatus;
    }

    @Override
    public String updateWordStatus(String idwordstatus, String name) {
        realm.beginTransaction();
        WordStatus ws = realm.where(WordStatus.class).equalTo("idwordstatus",idwordstatus).findFirst();
        ws.setStatus(name);
        realm.commitTransaction();
        return idwordstatus;
    }

    @Override
    public String deleteWordStatus(String idwordstatus) {
        realm.beginTransaction();
        realm.where(WordStatus.class).equalTo("idwordstatus",idwordstatus).findFirst().deleteFromRealm();
        realm.commitTransaction();
        return idwordstatus;
    }
}
