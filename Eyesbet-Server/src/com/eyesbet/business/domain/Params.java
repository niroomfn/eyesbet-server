 package com.eyesbet.business.domain;
 
 import com.eyesbet.util.DateTime;
 import java.util.HashMap;
 
 @SuppressWarnings("serial")
public class Params extends HashMap<String, String>
 {
   public static final String key = "2c9c8d0eb13013b0219ef54f79900fa66149edc6";
   private String timezone = "21";
   private String counteryId = "20";
   private String date;
   private String games;
   private String format = "json";
 
   public Params(String timezone)
   {
     if (timezone == null)
       this.timezone = DateTime.getDefaultTimeZone();
     else
       this.timezone = timezone;
   }
 
   public String getTimezone()
   {
     return this.timezone;
   }
   public void setTimezone(String timezone) {
     this.timezone = timezone;
   }
   public String getCounteryId() {
     return this.counteryId;
   }
   public void setCounteryId(String counteryId) {
     this.counteryId = counteryId;
   }
   public String getDate() {
     return this.date;
   }
   public void setDate(String date) {
     this.date = date;
   }
   public String getGames() {
     return this.games;
   }
   public void setGames(String games) {
     this.games = games;
   }
   public String getFormat() {
     return this.format;
   }
   public void setFormat(String format) {
     this.format = format;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.Params
 * JD-Core Version:    0.6.2
 */