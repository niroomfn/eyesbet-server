/*     */ package com.eyesbet.business.domain;
/*     */ 
/*     */ public class GameBet
/*     */ {
/*     */   private int id;
/*     */   private String name;
/*     */   private String teams;
/*  10 */   private String moneyline = "";
/*  11 */   private String overPoints = "";
/*  12 */   private String underPoints = "";
/*  13 */   private String spreadPoint = "";
/*  14 */   private String spreadPointTeam = "";
/*  15 */   private String spreadPointFavorite = "";
/*     */ 
/*     */   public GameBet(int id)
/*     */   {
/*  20 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  28 */     return this.id;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  33 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  38 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getTeams()
/*     */   {
/*  46 */     return this.teams;
/*     */   }
/*     */ 
/*     */   public void setTeams(String teams)
/*     */   {
/*  54 */     this.teams = teams;
/*     */   }
/*     */ 
/*     */   public String getMoneyline()
/*     */   {
/*  62 */     return this.moneyline;
/*     */   }
/*     */ 
/*     */   public void setMoneyline(String moneyline)
/*     */   {
/*  70 */     this.moneyline = treamTeamName(moneyline);
/*     */   }
/*     */ 
/*     */   public String getOverPoints()
/*     */   {
/*  77 */     return this.overPoints;
/*     */   }
/*     */ 
/*     */   public void setOverPoints(String overPoints)
/*     */   {
/*  85 */     this.overPoints = overPoints;
/*     */   }
/*     */ 
/*     */   public String getUnderPoints()
/*     */   {
/*  93 */     return this.underPoints;
/*     */   }
/*     */ 
/*     */   public void setUnderPoints(String underPoints)
/*     */   {
/* 101 */     this.underPoints = underPoints;
/*     */   }
/*     */ 
/*     */   public String getSpreadPoint()
/*     */   {
/* 109 */     return this.spreadPoint;
/*     */   }
/*     */ 
/*     */   public void setSpreadPoint(String spreadPoint)
/*     */   {
/* 117 */     this.spreadPoint = spreadPoint;
/*     */   }
/*     */ 
/*     */   public String getSpreadPointTeam()
/*     */   {
/* 125 */     return this.spreadPointTeam;
/*     */   }
/*     */ 
/*     */   public void setSpreadPointTeam(String spreadPointTeam)
/*     */   {
/* 133 */     this.spreadPointTeam = treamTeamName(spreadPointTeam);
/*     */   }
/*     */ 
/*     */   public boolean isMoneyline()
/*     */   {
/* 139 */     if ((this.moneyline == null) || ("".equals(this.moneyline))) {
/* 140 */       return false;
/*     */     }
/*     */ 
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isSpreadPoint()
/*     */   {
/* 148 */     if ((this.spreadPointTeam == null) || ("".equals(this.spreadPointTeam)) || ("".equals(this.spreadPoint))) {
/* 149 */       return false;
/*     */     }
/*     */ 
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isOverUnder()
/*     */   {
/* 158 */     if ((isOver()) || (isUnder())) {
/* 159 */       return true;
/*     */     }
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isUnder() {
/*     */     try {
/* 166 */       Integer.parseInt(this.underPoints);
/*     */     } catch (NumberFormatException e) {
/* 168 */       return false;
/*     */     }
/* 170 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isOver()
/*     */   {
/*     */     try
/*     */     {
/* 177 */       Integer.parseInt(this.overPoints);
/*     */     } catch (NumberFormatException e) {
/* 179 */       return false;
/*     */     }
/* 181 */     return true;
/*     */   }
/*     */ 
/*     */   public String getSpreadPointFavorite()
/*     */   {
/* 189 */     return this.spreadPointFavorite;
/*     */   }
/*     */ 
/*     */   public void setSpreadPointFavorite(String spreadPointFavorite)
/*     */   {
/* 194 */     this.spreadPointFavorite = treamTeamName(spreadPointFavorite);
/*     */   }
/*     */ 
/*     */   public String getSpreadPointAndSign()
/*     */   {
/* 201 */     if (this.spreadPointFavorite.equals(this.spreadPointTeam)) {
/* 202 */       return "-" + this.spreadPoint;
/*     */     }
/* 204 */     return "+" + this.spreadPoint;
/*     */   }
/*     */ 
/*     */   private String treamTeamName(String team)
/*     */   {
/* 213 */     return team.substring(team.lastIndexOf(" ") + 1, team.length());
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/* 220 */     if (isMoneyline()) {
/* 221 */       return BetType.moneyline.toString();
/*     */     }
/*     */ 
/* 227 */     return BetType.points.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.GameBet
 * JD-Core Version:    0.6.2
 */