package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Language;

import java.util.List;

public interface ILanguageService {
    Language getLanguage(String idlanguage);
    Language getLanguageByName(String name);
    List<Language> getLanguages();
    String createLanguage(String name, String avatar);
    String updateLanguage(String idlanguage, String name, String avatar);
    String deleteLanguage(String id);
}
