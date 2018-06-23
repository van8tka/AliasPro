package com.devprogram.aliaspro.DAL.Interfaces;



import com.devprogram.aliaspro.Models.Word;

import java.util.List;

public interface IWordService {
    Word getWord(String idword);
    List<Word> getWords();
    String createWord(String idword, String name, String idlanguage, String idwordstatus);
    String updareWord(String idword, String name, String idlanguage, String idwordstatus);
    String deleteWord(String idword);
}
