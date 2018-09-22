package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.WordStatus;

import java.util.List;

public interface IWordStatusService {
    WordStatus getWordStatus(String idwordstatus);
    WordStatus getWordStatus(String idword, String idRound);
    List<WordStatus> getWordsStatus();
    List<WordStatus> getWordsStatusShowedRound(String idRound);
    String createWordStatus(int status, String idword, String idgame, String idRound);
    String updateWordStatus(String idwordstatus, int status, String idword, String idgame, String idRound);
    String updateWordStatus(int status, String idword, String idRound);
    String deleteWordStatus(String idwordstatus);
    String deleteWordStatusForGame(String idGame);

    int countShowedWord(String idgame);
}
