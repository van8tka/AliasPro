package com.devprogram.aliaspro.DAL.Emplementations;

import io.realm.Realm;


public class DbService {

    private Realm realm;
    private EDictionaryService eDictionaryService;
    public EDictionaryService getEDictionaryService() {
        return this.eDictionaryService;
    }
    private EDifficultyService eDifficultyService;
    public EDifficultyService getEDifficultyService() {
        return this.eDifficultyService;
    }
    private EGameService eGameService;
    public EGameService getEGameService(){
        return eGameService;
    }
    private ELanguageService eLanguageService;
    public ELanguageService getELanguageService() {
        return eLanguageService;
    }
    private ERoundService eRoundService;
    public ERoundService getERoundService() {
        return eRoundService;
    }
    private ETaskService eTaskService;
    public ETaskService getETaskService()
    {
        return eTaskService;
    }

    private ETeamService eTeamService;
    public ETeamService getETeamService()
    {
        return eTeamService;
    }

    private EWordService eWordService;
    public EWordService getEWordService()
    {
        return eWordService;
    }
    private EWordStatusService eWordStatusService;
    public EWordStatusService getEWordStatusService()
    {
        return eWordStatusService;
    }


    //ctor
    public DbService()
    {
        realm = Realm.getDefaultInstance();
        this.eDictionaryService = new EDictionaryService(realm);
        this.eDifficultyService = new EDifficultyService(realm);
        this.eGameService = new EGameService(realm);
        this.eLanguageService = new ELanguageService(realm);
        this.eRoundService = new ERoundService(realm);
        this.eTaskService = new ETaskService(realm);
        this.eTeamService = new ETeamService(realm);
        this.eWordService = new EWordService(realm);
        this.eWordStatusService = new EWordStatusService(realm);
    }

    public void Close()
    {
        realm.close();
    }
}
