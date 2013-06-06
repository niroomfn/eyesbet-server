/*    */ package com.eyesbet.business.domain;
/*    */ 
/*    */ import com.eyesbet.util.DateTime;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ @SuppressWarnings("serial")
public class Params extends HashMap<String, String>
/*    */ {
/*    */   public static final String key = "2c9c8d0eb13013b0219ef54f79900fa66149edc6";
/* 13 */   private String timezone = "21";
/* 14 */   private String counteryId = "20";
/*    */   private String date;
/*    */   private String games;
/* 17 */   private String format = "json";
/*    */ 
/*    */   public Params(String timezone)
/*    */   {
/* 22 */     if (timezone == null)
/* 23 */       this.timezone = DateTime.getDefaultTimeZone();
/*    */     else
/* 25 */       this.timezone = timezone;
/*    */   }
/*    */ 
/*    */   public String getTimezone()
/*    */   {
/* 31 */     return this.timezone;
/*    */   }
/*    */   public void setTimezone(String timezone) {
/* 34 */     this.timezone = timezone;
/*    */   }
/*    */   public String getCounteryId() {
/* 37 */     return this.counteryId;
/*    */   }
/*    */   public void setCounteryId(String counteryId) {
/* 40 */     this.counteryId = counteryId;
/*    */   }
/*    */   public String getDate() {
/* 43 */     return this.date;
/*    */   }
/*    */   public void setDate(String date) {
/* 46 */     this.date = date;
/*    */   }
/*    */   public String getGames() {
/* 49 */     return this.games;
/*    */   }
/*    */   public void setGames(String games) {
/* 52 */     this.games = games;
/*    */   }
/*    */   public String getFormat() {
/* 55 */     return this.format;
/*    */   }
/*    */   public void setFormat(String format) {
/* 58 */     this.format = format;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.Params
 * JD-Core Version:    0.6.2
 */