package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.DAL.Implementations.EDictionaryService;
import com.devprogram.aliaspro.DAL.Implementations.EDifficultyService;
import com.devprogram.aliaspro.DAL.Implementations.EGameService;
import com.devprogram.aliaspro.DAL.Implementations.ELanguageService;
import com.devprogram.aliaspro.DAL.Implementations.EPlayingTeamsService;
import com.devprogram.aliaspro.DAL.Implementations.ERoundService;
import com.devprogram.aliaspro.DAL.Implementations.ETaskService;
import com.devprogram.aliaspro.DAL.Implementations.ETeamService;
import com.devprogram.aliaspro.DAL.Implementations.EWordService;
import com.devprogram.aliaspro.DAL.Implementations.EWordStatusService;

import io.realm.Realm;

public interface IDbService {
   void CloseDb();
    EDictionaryService getEDictionaryService();
     EDifficultyService getEDifficultyService();
     EGameService getEGameService();
     ELanguageService getELanguageService();
     ERoundService getERoundService();
     ETaskService getETaskService();
     ETeamService getETeamService();
     EWordService getEWordService();
     EWordStatusService getEWordStatusService();
     EPlayingTeamsService getEPlayingTeamsService();
}
