package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.IWordStatusService;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

import io.realm.Realm;

public class EWordStatusService implements IWordStatusService {
    Realm realm;
    public EWordStatusService(Realm realm)
    {
        this.realm = realm;
    }
    @Override
    public WordStatus getWordStatus(String idwordstatus) {
        return null;
    }

    @Override
    public List<WordStatus> getWordsStatus() {
        return null;
    }

    @Override
    public String createWordStatus(String name) {
        return null;
    }

    @Override
    public String updateWordStatus(String idwordstatus, String name) {
        return null;
    }

    @Override
    public String deleteWordStatus(String idwordstatus) {
        return null;
    }
}
