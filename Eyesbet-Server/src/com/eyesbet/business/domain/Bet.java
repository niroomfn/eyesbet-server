/*     */ package com.eyesbet.business.domain;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Bet
/*     */ {
/*     */   private BetType betType;
/*  18 */   private List<Game> games = new ArrayList<Game>();
/*     */   private String createdDate;
/*     */   private String modifiedDate;
/*     */   private int id;
/*  22 */   private boolean parlayLost = false;
/*     */   private int status;
/*     */   private String xml;
/*     */   private String name;
/*     */ 
/*     */   public Bet(BetType betType, int id)
/*     */   {
/*  28 */     this.betType = betType;
/*  29 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BetType getBetType() {
/*  33 */     return this.betType;
/*     */   }
/*     */ 
/*     */   public boolean isMoneyline()
/*     */   {
/*  39 */     for (Game game : this.games)
/*     */     {
/*  41 */       if (game.getBet().isMoneyline())
/*     */       {
/*  43 */         return true;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isParlay()
/*     */   {
/*  55 */     if (this.betType == BetType.parlay) {
/*  56 */       return true;
/*     */     }
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   public void addGame(Game game) {
/*  62 */     game.setBetId(this.id);
/*  63 */     this.games.add(game);
/*     */   }
/*     */ 
/*     */   public List<Game> getGames() {
/*  67 */     return this.games;
/*     */   }
/*     */ 
/*     */   public String getCreatedDate() {
/*  71 */     return this.createdDate;
/*     */   }
/*     */ 
/*     */   public void setCreatedDate(String createdDate) {
/*  75 */     this.createdDate = createdDate;
/*     */   }
/*     */ 
/*     */   public String getModifiedDate() {
/*  79 */     return this.modifiedDate;
/*     */   }
/*     */ 
/*     */   public void setModifiedDate(String modifiedDate) {
/*  83 */     this.modifiedDate = modifiedDate;
/*     */   }
/*     */ 
/*     */   public int getId() {
/*  87 */     return this.id;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  93 */     if ((obj == null) || (!(obj instanceof Bet))) {
/*  94 */       return false;
/*     */     }
/*     */ 
/*  97 */     Bet bet = (Bet)obj;
/*  98 */     if (bet.getId() == getId()) {
/*  99 */       return true;
/*     */     }
/*     */ 
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */  
/*     */ 
/*     */   public boolean hasLiveGame()
/*     */   {
/* 117 */     for (Game game : this.games) {
/* 118 */       if (game.isLive()) {
/* 119 */         return true;
/*     */       }
/*     */     }
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   public Set<Game> getLiveGames() {
/* 126 */     Set<Game> set = new HashSet<Game>();
/* 127 */     for (Game game : this.games)
/*     */     {
/* 129 */       if ((game.isLive()) || (game.getBet().getId() == 193) || (game.getBet().getId() == 190) || 
/* 130 */         (game.getBet().getId() == 195) || (game.getBet().getId() == 206))
/*     */       {
/* 132 */         set.add(game);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 138 */     return set;
/*     */   }
/*     */ 
/*     */   public Game getGame(String home, String away)
/*     */   {
/* 143 */     for (Game game : this.games) {
/* 144 */       if ((home.equals(game.getHome().getName())) && (away.equals(game.getAway().getName()))) {
/* 145 */         return game;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */   public int getStatus() {
/* 154 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(int status) {
/* 158 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public List<Game> getFinishedGames()
/*     */   {
/* 163 */     List<Game> list = new ArrayList<Game>();
/* 164 */     for (Game g : this.games)
/*     */     {
/* 166 */       if (g.isFinished()) {
/* 167 */         list.add(g);
/*     */       }
/*     */     }
/*     */ 
/* 171 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Game> getNonLiveGames()
/*     */   {
/* 179 */     List<Game> list = new ArrayList<Game>();
/* 180 */     for (Game g : this.games)
/*     */     {
/* 182 */       if (!g.isLive()) {
/* 183 */         list.add(g);
/*     */       }
/*     */     }
/*     */ 
/* 187 */     return list;
/*     */   }
/*     */ 
/*     */   public String getStatusText()
/*     */   {
/* 193 */     if (this.parlayLost) {
/* 194 */       return "LOST";
/*     */     }
/* 196 */     List<Game> list = getGames();
/* 197 */     for (Game game : list)
/*     */     {
/* 199 */       if ((game.isLive()) || (game.notStarted())) {
/* 200 */         return "Pending...";
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 205 */     if (this.status == 100)
/*     */     {
/* 207 */       return "WON";
/* 208 */     }if ((this.status < 100) && (this.status != 1))
/* 209 */       return "LOST";
/* 210 */     if (this.status == 1)
/*     */     {
/* 212 */       return "Tight Game";
/*     */     }
/* 214 */     return "Unkown";
/*     */   }
/*     */ 
/*     */   public boolean isParlayLost()
/*     */   {
/* 221 */     return this.parlayLost;
/*     */   }
/*     */ 
/*     */   public void setParlayLost(boolean parlayLost) {
/* 225 */     this.parlayLost = parlayLost;
/*     */   }
/*     */ 
/*     */   public String getXml() {
/* 229 */     return this.xml;
/*     */   }
/*     */ 
/*     */   public void setXml(String xml) {
/* 233 */     this.xml = xml;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 237 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 241 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public boolean hasNotStartedGame()
/*     */   {
/* 247 */     for (Game game : this.games)
/*     */     {
/* 249 */       if (game.notStarted()) {
/* 250 */         return true;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 255 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.Bet
 * JD-Core Version:    0.6.2
 */