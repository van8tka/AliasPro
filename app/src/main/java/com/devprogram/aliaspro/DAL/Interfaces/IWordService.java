package com.devprogram.aliaspro.DAL.Interfaces;



import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Word;
import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

public interface IWordService {
    Word getWord(String idword);
    List<Word> getWords();
    String createWord(String name, Language language, WordStatus wordstatus);
    String updareWord(String idword, String name, Language language, WordStatus wordstatus, boolean isshowed);
    String deleteWord(String idword);
    String resetWord(String idword);
    String updateStatusOfWord(String idword, WordStatus ws);
    String updateShowedOfWord(String idword, boolean isshowed);
}
