package com.devprogram.aliaspro.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Task extends RealmObject {
   @Required
   @PrimaryKey
   String idtask;
   String name;
   String description;
   String avatar;
   boolean complete;
   int addscore;
   Language language;

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

   public void setLanguage(Language language) {
      this.language = language;
   }

   public Language getLanguage() {
      return language;
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
