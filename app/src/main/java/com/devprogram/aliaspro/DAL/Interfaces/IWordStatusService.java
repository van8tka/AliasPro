package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

public interface IWordStatusService {
    WordStatus getWordStatus(String idwordstatus);
    List<WordStatus> getWordsStatus();
    String createWordStatus(int status, String idword, String idgame);
    String updateWordStatus(String idwordstatus, int status, String idword, String idgame);
    String updateWordStatus(int status, String idword, String idgame);
    String deleteWordStatus(String idwordstatus);
    int countShowedWord(String idgame);
}
