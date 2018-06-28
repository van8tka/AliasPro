package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.ITaskService;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Task;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;

public class ETaskService implements ITaskService {
    Realm realm;
    public ETaskService(Realm realm){
        this.realm = realm;
    }
    @Override
    public Task getTask(String idtask) {
        return realm.where(Task.class).equalTo("idtask",idtask).findFirst();
    }

    @Override
    public List<Task> getTasks() {
        return realm.where(Task.class).findAll();
    }

    @Override
    public String createTask(String name, String description, String avatar, boolean complete, int addscore, Language language) {
        String idtask = UUID.randomUUID().toString();
        realm.beginTransaction();
        Task task = realm.createObject(Task.class,idtask);
        task.setName(name);
        task.setDescription(description);
        task.setAvatar(avatar);
        task.setComplete(complete);
        task.setAddscore(addscore);
        task.setLanguage(language);
        realm.commitTransaction();
        return idtask;
    }

    @Override
    public String updateTask(String idtask, String name, String description, String avatar, boolean complete, int addscore, Language language) {

        realm.beginTransaction();
        Task task = realm.where(Task.class).equalTo("idtask",idtask).findFirst();
        task.setName(name);
        task.setDescription(description);
        task.setAvatar(avatar);
        task.setComplete(complete);
        task.setAddscore(addscore);
        task.setLanguage(language);
        realm.commitTransaction();
        return idtask;
    }

    @Override
    public String deleteTask(String idtask) {
        realm.where(Task.class).equalTo("idtask",idtask).findFirst().deleteFromRealm();
        return idtask;
    }
}
