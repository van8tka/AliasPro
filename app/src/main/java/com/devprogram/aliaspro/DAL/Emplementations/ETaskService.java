package com.devprogram.aliaspro.DAL.Emplementations;

import com.devprogram.aliaspro.DAL.Interfaces.ITaskService;
import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Task;

import java.util.List;

import io.realm.Realm;

public class ETaskService implements ITaskService {
    Realm realm;
    public ETaskService(Realm realm){
        this.realm = realm;
    }
    @Override
    public Task getTask(String idtask) {
        return null;
    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public String createTask(String name, String description, String avatar, boolean complete, int addscore, Language language) {
        return null;
    }

    @Override
    public String updateTask(String idtask, String name, String description, String avatar, boolean complete, int addscore, Language language) {
        return null;
    }

    @Override
    public String deleteTask(String idtask) {
        return null;
    }
}
