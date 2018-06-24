package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

public interface IWordStatusService {
    WordStatus getWordStatus(String idwordstatus);
    List<WordStatus> getWordsStatus();
    String createWordStatus(String name);
    String updateWordStatus(String idwordstatus, String name);
    String deleteWordStatus(String idwordstatus);
}
