package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.DAL.Emplementations.EDictionaryService;
import com.devprogram.aliaspro.DAL.Emplementations.EDifficultyService;
import com.devprogram.aliaspro.DAL.Emplementations.EGameService;
import com.devprogram.aliaspro.DAL.Emplementations.ELanguageService;
import com.devprogram.aliaspro.DAL.Emplementations.EPlayingTeamsService;
import com.devprogram.aliaspro.DAL.Emplementations.ERoundService;
import com.devprogram.aliaspro.DAL.Emplementations.ETaskService;
import com.devprogram.aliaspro.DAL.Emplementations.ETeamService;
import com.devprogram.aliaspro.DAL.Emplementations.EWordService;
import com.devprogram.aliaspro.DAL.Emplementations.EWordStatusService;

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
