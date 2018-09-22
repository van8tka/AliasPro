package com.devprogram.aliaspro.DAL.Interfaces;



import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

public interface IWordService {
    Word getWord(String idword);
    List<Word> getWords();
    String createWord(String name,String idDictionary ,String idlanguage);
    String updareWord(String idword, String name, String idDictionary ,String idlanguage);
    String deleteWord(String idword);
    List<Word> getWordsWithOutShowed(String iddictionary, String idGame);
    List<Word> getWordsFromDictionary(String iddictionary);
    List<Word> getShowedRoundWords(String idround);
}
