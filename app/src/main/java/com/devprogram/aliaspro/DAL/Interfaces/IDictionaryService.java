package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Difficulty;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Word;

import java.util.List;

public interface IDictionaryService {
    Dictionary getDictionary(String iddictionary);
    List<Dictionary> getDicitionaries();
    String createDictionary(  String name, String avatar, String price, String description, Language language, Difficulty difficulty);
    String updateDictionary(String iddictionary,   String name, String avatar, String price, String description, Language language, Difficulty difficulty);
    String deleteDictionary(String iddictionary);
    int getWordsCount(String iddictionary);
    List<Word> getWordsDictionary(String iddictionary);
}
