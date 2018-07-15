package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Language;
import com.devprogram.aliaspro.Models.Task;

import java.util.List;

public interface ITaskService {
    Task getTask(String idtask);
    Task getTaskRandom();
    List<Task> getTasks();
    String createTask(String name, String description, String avatar, boolean complete,int addscore, Language language);
    String updateTask(String idtask,String name, String description, String avatar, boolean complete,int addscore, Language language);
    String deleteTask(String idtask);
}
