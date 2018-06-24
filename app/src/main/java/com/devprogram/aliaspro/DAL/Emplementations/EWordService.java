package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.IWordService;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

import io.realm.Realm;

public class EWordService implements IWordService {
    Realm realm;
    public EWordService(Realm realm)
    {this.realm = realm;}

    @Override
    public Word getWord(String idword) {
        return null;
    }

    @Override
    public List<Word> getWords() {
        return null;
    }

    @Override
    public String createWord(String name, Language language, WordStatus wordstatus) {
        return null;
    }

    @Override
    public String updareWord(String idword, String name, Language language, WordStatus wordstatus) {
        return null;
    }

    @Override
    public String deleteWord(String idword) {
        return null;
    }
}
