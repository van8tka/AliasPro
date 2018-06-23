package com.devprogram.aliaspro.DAL.Interfaces;

import com.devprogram.aliaspro.Models.Task;

import java.util.List;

public interface ITaskService {
    Task getTask(String idtask);
    List<Task> getTasks();
    String createTask(String idtask,String name, String description, String avatar, boolean complete,int addscore, String idlanguage);
    String updateTask(String idtask,String name, String description, String avatar, boolean complete,int addscore, String idlanguage);
    String deleteTask(String idtask);
}
