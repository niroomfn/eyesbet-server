/*     */ package com.eyesbet.business.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Fixtures
/*     */ {
/*  13 */   private static Fixtures me = new Fixtures();
/*  14 */   private FixtureMap map = new FixtureMap();
/*     */   private Date dateLoaded;
/*     */ 
/*     */   public String[] getLeagues()
/*     */   {
/*  48 */     Leagues[] leagues = Leagues.values();
/*  49 */     String[] array = new String[leagues.length];
/*  50 */     for (int i = 0; i < leagues.length; i++) {
/*  51 */       array[i] = leagues[i].toString();
/*     */     }
/*     */ 
/*  54 */     return array;
/*     */   }
/*     */ 
/*     */   public static Fixtures getInstance()
/*     */   {
/*  59 */     return me;
/*     */   }
/*     */ 
/*     */   public Set<Fixture> getLeagueFixtures(Leagues league)
/*     */   {
/*  66 */     return this.map.get(league.toString());
/*     */   }
/*     */ 
/*     */   public void setFixtures(Set<Fixture> list, Leagues league)
/*     */   {
/*  71 */     this.map.put(league.toString(), list);
/*     */   }
/*     */ 
/*     */   public void setNBAFixture(Set<Fixture> list)
/*     */   {
/*  76 */     this.map.put(Leagues.NBA.toString(), list);
/*     */   }
/*     */ 
/*     */   public void setNFLFixtures(Set<Fixture> list) {
/*  80 */     this.map.put(Leagues.NFL.toString(), list);
/*     */   }
/*     */ 
/*     */   public Set<Fixture> getNFLFixtures()
/*     */   {
/*  85 */     return this.map.get("NFL");
/*     */   }
/*     */ 
/*     */   public Set<Fixture> getNBAFixtures()
/*     */   {
/*  90 */     return this.map.get("NBA");
/*     */   }
/*     */ 
/*     */   public FixtureMap getAllFixtures()
/*     */   {
/*  96 */     return this.map;
/*     */   }
/*     */ 
/*     */   public Date getDateLoaded()
/*     */   {
/* 101 */     return this.dateLoaded;
/*     */   }
/*     */ 
/*     */   public void setDateLoaded(Date dateLoaded)
/*     */   {
/* 106 */     this.dateLoaded = dateLoaded;
/*     */   }
/*     */ 
/*     */   public static enum Leagues
/*     */   {
/*  18 */     NFL("3101004"), NBA("3100973"), MLB(""), NHL("");
/*     */ 
/*     */     private String leagueId;
/*  21 */     private static String[] order = { "NBA", "NFL", "MLB", "NHL" };
/*     */ 
/*  23 */     private Leagues(String leagueId) { this.leagueId = leagueId; }
/*     */ 
/*     */     public String getLeagueId() {
/*  26 */       return this.leagueId;
/*     */     }
/*     */ 
/*     */     public static String[] getOrder()
/*     */     {
/*  31 */       return order;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.Fixtures
 * JD-Core Version:    0.6.2
 */