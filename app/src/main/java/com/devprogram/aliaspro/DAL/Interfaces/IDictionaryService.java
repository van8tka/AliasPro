package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Word;

import java.util.List;

public interface IDictionaryService {
    Dictionary getDictionary(String iddictionary);
    List<Dictionary> getDicitionaries();
    String createDictionary(String iddictionary, List<Word> words, String name, String avatar, String price, String description, String idlanguage, String iddifficulty);
    String updateDictionary(String iddictionary, List<Word> words, String name, String avatar, String price, String description, String idlanguage, String iddifficulty);
    String deleteDictionary(String iddictionary);
}
