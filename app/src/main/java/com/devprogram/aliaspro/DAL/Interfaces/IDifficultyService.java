package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Difficulty;

import java.util.List;

public interface IDifficultyService {
    Difficulty getDifficulty(String iddifficulty);
    List<Difficulty> getDifficulties();
    String createDifficulty(String iddifficulty, String name, String idlanguage);
    String updateDifficulty(String iddifficulty, String name, String idlanguage);
    String deleteDifficulty(String iddifficulty);
}
