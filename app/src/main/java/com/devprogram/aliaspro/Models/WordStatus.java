package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WordStatus  extends RealmObject {
    @PrimaryKey
    String idwordstatus;
    String status;
//
//       dbService.getEWordStatusService().createWordStatus("отгадано");
//    String idwordStatusDefault = dbService.getEWordStatusService().createWordStatus("не отгадано");
//            dbService.getEWordStatusService().createWordStatus("удалить");
//
    public String getIdwordstatus() {
        return idwordstatus;
    }

    public void setIdwordstatus(String idwordstatus) {
        this.idwordstatus = idwordstatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
