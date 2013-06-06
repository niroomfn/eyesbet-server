/*     */ package com.eyesbet.business.domain;
/*     */ 
/*     */ public class Game
/*     */ {
/*  10 */   protected int id = 0;
/*     */   private GameBet bet;
/*     */   private int betId;
/*     */   protected String schedule;
/*     */   protected Team home;
/*     */   protected Team away;
/*     */   protected Fixtures.Leagues league;
/*  19 */   protected int status = 0;
/*     */   protected int overUnderStatus;
/*     */   protected int spreadPointStatus;
/*     */   protected StatusType statusType;
/*     */   protected String statusDesc;
/*     */ 
/*     */   public Game()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Game(Team home, Team away, Fixtures.Leagues league)
/*     */   {
/*  44 */     this.home = home;
/*  45 */     this.away = away;
/*  46 */     this.league = league;
/*     */   }
/*     */ 
/*     */   public GameBet getBet()
/*     */   {
/*  51 */     return this.bet;
/*     */   }
/*     */ 
/*     */  
/*     */ 
/*     */   public void setId(int id)
/*     */   {
/*  63 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public void updateScores(String home, String away)
/*     */   {
/*  68 */     this.home.setScore(Integer.parseInt(home));
/*  69 */     this.away.setScore(Integer.parseInt(away));
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  78 */     if ((obj == null) || (!(obj instanceof Game))) {
/*  79 */       return false;
/*     */     }
/*  81 */     Game game = (Game)obj;
/*  82 */     if (game.getGameId() == getGameId())
/*     */     {
/*  84 */       return true;
/*     */     }
/*     */ 
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  96 */     return new Integer(this.id).hashCode();
/*     */   }
/*     */ 
/*     */   public String getGameBetName()
/*     */   {
/* 103 */     return this.betId + "_" + this.home.getName() + "_" + this.away.getName();
/*     */   }
/*     */ 
/*     */   public void setBet(GameBet bet)
/*     */   {
/* 108 */     this.bet = bet;
/*     */   }
/*     */ 
/*     */   public String getSchedule()
/*     */   {
/* 114 */     return this.schedule;
/*     */   }
/*     */ 
/*     */   public void setSchedule(String schedule)
/*     */   {
/* 120 */     this.schedule = schedule;
/*     */   }
/*     */ 
/*     */   public boolean isLive()
/*     */   {
/* 127 */     if (this.statusType == StatusType.inprogress) {
/* 128 */       return true;
/*     */     }
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean notStarted()
/*     */   {
/* 137 */     if (this.statusType == StatusType.notstarted) {
/* 138 */       return true;
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isTightScore() {
/* 144 */     if ((this.home.getScore() == this.away.getScore()) && (!notStarted())) {
/* 145 */       return true;
/*     */     }
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   public int getStatus()
/*     */   {
/* 153 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(int status)
/*     */   {
/* 159 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public boolean isFinished()
/*     */   {
/* 164 */     if (this.statusType == StatusType.finished) {
/* 165 */       return true;
/*     */     }
/*     */ 
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   public Team getHome()
/*     */   {
/* 177 */     return this.home;
/*     */   }
/*     */ 
/*     */   public void setHome(Team home)
/*     */   {
/* 183 */     this.home = home;
/*     */   }
/*     */ 
/*     */   public Team getAway()
/*     */   {
/* 189 */     return this.away;
/*     */   }
/*     */ 
/*     */   public void setAway(Team away)
/*     */   {
/* 195 */     this.away = away;
/*     */   }
/*     */ 
/*     */   public Team getWinner()
/*     */   {
/* 200 */     if (this.home.getScore() > this.away.getScore())
/* 201 */       return this.home;
/* 202 */     if (this.home.getScore() < this.away.getScore()) {
/* 203 */       return this.away;
/*     */     }
/*     */ 
/* 206 */     return null;
/*     */   }
/*     */ 
/*     */   public LiveGame toLiveGame()
/*     */   {
/* 211 */     LiveGame game = new LiveGame(this.home, this.away, this.league);
/* 212 */     game.setId(this.id);
/* 213 */     game.setSchedule(this.schedule);
/* 214 */     game.setStatus(this.status);
/* 215 */     return game;
/*     */   }
/*     */ 
/*     */   public Fixtures.Leagues getLeage()
/*     */   {
/* 220 */     return this.league;
/*     */   }
/*     */ 
/*     */   public String getStatusType()
/*     */   {
/* 225 */     if (this.statusType == null) {
/* 226 */       return "";
/*     */     }
/* 228 */     return this.statusType.toString();
/*     */   }
/*     */ 
/*     */   public String getStatusTypeText()
/*     */   {
/* 233 */     if (this.statusType == null) {
/* 234 */       return "unkown";
/*     */     }
/*     */ 
/* 237 */     return this.statusType.getText();
/*     */   }
/*     */ 
/*     */   public void setStatusType(String statusType)
/*     */   {
/*     */     try {
/* 243 */       this.statusType = StatusType.valueOf(statusType);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getStatusDesc()
/*     */   {
/* 252 */     return this.statusDesc;
/*     */   }
/*     */ 
/*     */   public void setStatusDesc(String statusDesc)
/*     */   {
/* 257 */     this.statusDesc = statusDesc.toUpperCase();
/*     */   }
/*     */ 
/*     */   public String getDate()
/*     */   {
/* 262 */     if (this.schedule == null) return "";
/* 263 */     return this.schedule.substring(0, this.schedule.indexOf(" "));
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 268 */     return this.away.getName() + ": " + this.away.getScore() + " @ " + this.home.getName() + ": " + this.home.getScore() + " STATUS: " + this.statusType;
/*     */   }
/*     */ 
/*     */   public int getBetId() {
/* 272 */     return this.betId;
/*     */   }
/*     */ 
/*     */   public void setBetId(int betId) {
/* 276 */     this.betId = betId;
/*     */   }
/*     */ 
/*     */   public int getOverUnderStatus()
/*     */   {
/* 283 */     return this.overUnderStatus;
/*     */   }
/*     */ 
/*     */   public void setOverUnderStatus(int overUnderStatus) {
/* 287 */     this.overUnderStatus = overUnderStatus;
/*     */   }
/*     */ 
/*     */   public int getSpreadPointStatus() {
/* 291 */     return this.spreadPointStatus;
/*     */   }
/*     */ 
/*     */   public void setSpreadPointStatus(int spreadPointStatus)
/*     */   {
/* 300 */     this.spreadPointStatus = spreadPointStatus;
/*     */   }
/*     */   public String getStatusText() {
/* 303 */     return getStatusText(this.status);
/*     */   }
/*     */ 
/*     */   public String getOnverUnderStatusText() {
/* 307 */     return getStatusText(this.overUnderStatus);
/*     */   }
/*     */ 
/*     */   public String getSpreadPointStatusText()
/*     */   {
/* 312 */     return getStatusText(this.spreadPointStatus);
/*     */   }
/*     */ 
/*     */   private String getStatusText(int status) {
/* 316 */     String text = "";
/* 317 */     if (isFinished())
/*     */     {
/* 319 */       if (status == 1) text = "Tight";
/* 320 */       else if (status == 100) text = "WON";
/* 321 */       else if ((status < 100) && (status != 1)) text = "LOST";
/*     */ 
/*     */     }
/* 324 */     else if (isLive())
/*     */     {
/* 326 */       text = "Pending...";
/*     */     }
/* 328 */     else text = "Unknown";
/*     */ 
/* 332 */     return text;
/*     */   }
/*     */ 
/*     */   public int getGameId() {
/* 336 */     return this.id;
/*     */   }
/*     */ 
/*     */   public int getMoneylineBetScore()
/*     */   {
/* 345 */     if (this.home.getName().equals(this.bet.getMoneyline())) {
/* 346 */       return this.home.getScore();
/*     */     }
/*     */ 
/* 349 */     return this.away.getScore();
/*     */   }
/*     */ 
/*     */   public double getSpreadPointBetScore()
/*     */   {
/* 358 */     if (this.home.getName().equals(this.bet.getSpreadPointTeam()))
/*     */     {
/* 360 */       return this.home.getBetScore();
/*     */     }
/*     */ 
/* 364 */     return this.away.getBetScore();
/*     */   }
/*     */ 
/*     */   public int getOverUnderBetScore()
/*     */   {
/* 373 */     return this.away.getScore() + this.home.getScore();
/*     */   }
/*     */ 
/*     */   public static enum StatusType
/*     */   {
/*  28 */     inprogress("In Progress"), finished("Finished"), notstarted("Not Started");
/*     */ 
/*     */     private String text;
/*     */ 
/*  31 */     private StatusType(String text) { this.text = text; }
/*     */ 
/*     */     public String getText()
/*     */     {
/*  35 */       return this.text;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.Game
 * JD-Core Version:    0.6.2
 */