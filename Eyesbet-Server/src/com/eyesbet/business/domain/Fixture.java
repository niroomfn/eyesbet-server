/*     */ package com.eyesbet.business.domain;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ public class Fixture
/*     */   implements Comparable<Fixture>
/*     */ {
/*     */   private int id;
/*     */   private String home;
/*     */   private String away;
/*     */   private String schedule;
/*  16 */   private String timezone = "America/Los_Angeles";
/*  18 */   private static SimpleDateFormat usDateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm aaa");
/*  19 */   private static SimpleDateFormat noneUSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/*     */ 
/*     */   public String getHome()
/*     */   {
/*  25 */     return this.home;
/*     */   }
/*     */ 
/*     */   public void setId(int id)
/*     */   {
/*  31 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public void setHome(String home)
/*     */   {
/*  36 */     this.home = getTeamName(home.trim());
/*     */   }
/*     */ 
/*     */   public String getAway()
/*     */   {
/*  41 */     return this.away;
/*     */   }
/*     */ 
/*     */   public void setAway(String away)
/*     */   {
/*  46 */     this.away = getTeamName(away.trim());
/*     */   }
/*     */ 
/*     */   public void setSchedule(String date, String time)
/*     */   {
/*     */     try
/*     */     {
/*  56 */       Date d = noneUSFormat.parse(date + " " + time);
/*  57 */       this.schedule = usDateFormat.format(d);
/*     */     }
/*     */     catch (ParseException e) {
/*  60 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSchedule()
/*     */   {
/*  69 */     return this.schedule;
/*     */   }
/*     */ 
/*     */   public String displayFixture()
/*     */   {
/*  74 */     return this.away + " @ " + this.home;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  79 */     return this.away + "@" + this.home + "@" + this.id;
/*     */   }
/*     */ 
/*     */   public String getTimezone()
/*     */   {
/*  87 */     return this.timezone;
/*     */   }
/*     */ 
/*     */   public void setTimezone(String timezone)
/*     */   {
/*  92 */     this.timezone = timezone;
/*     */ 
/*  94 */     usDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 105 */     if (!(obj instanceof Fixture)) {
/* 106 */       return false;
/*     */     }
/*     */ 
/* 110 */     Fixture f = (Fixture)obj;
/* 111 */     if (this.id == f.getFixtureId())
/*     */     {
/* 113 */       return true;
/*     */     }
/*     */ 
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 123 */     return (""+id).hashCode();
/*     */   }
/*     */ 
/*     */   public int getFixtureId()
/*     */   {
/* 128 */     return this.id;
/*     */   }
/*     */ 
/*     */   public int compareTo(Fixture f)
/*     */   {
/*     */     try
/*     */     {
/* 136 */       Date date1 = usDateFormat.parse(getSchedule());
/* 137 */       Date date2 = usDateFormat.parse(f.getSchedule());
/*     */ 
/* 140 */       return date1.compareTo(date2);
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/* 146 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 150 */     return 0;
/*     */   }
/*     */ 
/*     */   private String getTeamName(String team)
/*     */   {
/* 157 */     return team.substring(team.lastIndexOf(" ") + 1, team.length());
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.Fixture
 * JD-Core Version:    0.6.2
 */