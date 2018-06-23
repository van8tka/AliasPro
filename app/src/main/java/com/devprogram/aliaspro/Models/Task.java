package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {
    @PrimaryKey
   String idtask;
   String name;
   String description;
   String avatar;
   boolean complete;
   int addscore;
   String idlanguage;

   public void setIdtask(String idtask) {
      this.idtask = idtask;
   }

   public String getIdtask() {
      return idtask;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   public void setIdlanguage(String idlanguage) {
      this.idlanguage = idlanguage;
   }

   public String getIdlanguage() {
      return idlanguage;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   public int getAddscore() {
      return addscore;
   }

   public void setAddscore(int addscore) {
      this.addscore = addscore;
   }

   public void setComplete(boolean complete) {
      this.complete = complete;
   }

   public boolean getIscomplete() {
      return complete;
   }
}
