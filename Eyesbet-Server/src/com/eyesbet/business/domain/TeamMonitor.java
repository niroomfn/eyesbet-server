/*     */ package com.eyesbet.business.domain;
/*     */ 
/*     */ import com.eyesbet.util.DateTime;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TeamMonitor
/*     */ {
/*  12 */   private int id = 0;
/*     */   private boolean home;
/*     */   private int score;
/*     */   private String sportName;
/*     */   private String quater;
/*     */   private Date schedule;
/*     */   private String name;
/*  19 */   private Set<String> sessionIds = new HashSet<String>();
/*     */ 
/*     */   public TeamMonitor(int id, String name, String schedule, String sessionId) {
/*  22 */     this.id = id;
/*  23 */     this.schedule = DateTime.convertToUSOnlyDate(schedule);
/*  24 */     this.name = name;
/*  25 */     addSessionId(sessionId);
/*     */   }
/*     */ 
/*     */   public boolean isHome()
/*     */   {
/*  30 */     return this.home;
/*     */   }
/*     */ 
/*     */   public void addSessionId(String sessionId) {
/*  34 */     System.out.println("Adding session Id: " + sessionId + "for team: " + getName());
/*  35 */     if (!this.sessionIds.add(sessionId))
/*     */     {
/*  38 */       System.out.println(sessionId + " arleady exist not added");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setHome(boolean home)
/*     */   {
/*  44 */     this.home = home;
/*     */   }
/*     */ 
/*     */   public int getScore()
/*     */   {
/*  49 */     return this.score;
/*     */   }
/*     */ 
/*     */   public void setScore(int score)
/*     */   {
/*  54 */     this.score = score;
/*     */   }
/*     */ 
/*     */   public String getSportName()
/*     */   {
/*  59 */     return this.sportName;
/*     */   }
/*     */ 
/*     */   public void setSportName(String sportName)
/*     */   {
/*  64 */     this.sportName = sportName;
/*     */   }
/*     */ 
/*     */   public String getQuater()
/*     */   {
/*  69 */     return this.quater;
/*     */   }
/*     */ 
/*     */   public void setQuater(String quater)
/*     */   {
/*  74 */     this.quater = quater;
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  79 */     return this.id;
/*     */   }
/*     */ 
/*     */   public Date getSchedule()
/*     */   {
/*  84 */     return this.schedule;
/*     */   }
/*     */ 
/*     */   public void setSchedule(Date schedule)
/*     */   {
/*  89 */     this.schedule = schedule;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  94 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  99 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 105 */     if ((obj == null) || (!(obj instanceof TeamMonitor))) {
/* 106 */       return false;
/*     */     }
/* 108 */     TeamMonitor tm = (TeamMonitor)obj;
/* 109 */     if ((getId() == tm.getId()) && (this.name.equals(tm.getName()))) {
/* 110 */       return true;
/*     */     }
/*     */ 
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 121 */     return (this.id + this.name).hashCode();
/*     */   }
/*     */ 
/*     */   public boolean removeSessionId(String sessionId)
/*     */   {
/* 128 */     return this.sessionIds.remove(sessionId);
/*     */   }
/*     */ 
/*     */   public boolean remove()
/*     */   {
/* 133 */     if (this.sessionIds.size() == 0) {
/* 134 */       return true;
/*     */     }
/*     */ 
/* 137 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.TeamMonitor
 * JD-Core Version:    0.6.2
 */