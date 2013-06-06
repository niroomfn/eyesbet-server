/*     */ package com.eyesbet.business;
/*     */ 
/*     */ import com.eyesbet.business.domain.Game;
/*     */ import com.eyesbet.business.domain.GameBet;
/*     */ 
/*     */ public class SpreadpointComputer extends BetComputer
/*     */ {
/*     */   public int computeLiveGameStatus(Game game)
/*     */     throws Exception
/*     */   {
/*  11 */     GameBet gamebet = game.getBet();
/*     */ 
/*  13 */     String team = gamebet.getSpreadPointTeam();
/*  14 */     String favorite = gamebet.getSpreadPointFavorite();
/*  15 */     String underdog = null;
/*  16 */     double points = Double.parseDouble(gamebet.getSpreadPoint());
/*  17 */     double fdiff = 0.0D;
/*  18 */     double udiff = 0.0D;
/*     */ 
/*  21 */     if (favorite.equals(game.getHome().getName())) {
/*  22 */       fdiff = Math.abs(game.getHome().getScore() - points);
/*  23 */       udiff = game.getAway().getScore() + points;
/*  24 */       underdog = game.getAway().getName();
/*  25 */       game.getHome().setBetScore(fdiff);
/*  26 */       game.getAway().setBetScore(udiff);
/*     */     }
/*  28 */     else if (favorite.equals(game.getAway().getName())) {
/*  29 */       fdiff = Math.abs(game.getAway().getScore() - points);
/*  30 */       udiff = game.getHome().getScore() + points;
/*  31 */       underdog = game.getHome().getName();
/*     */ 
/*  33 */       game.getAway().setBetScore(fdiff);
/*  34 */       game.getHome().setBetScore(udiff);
/*     */     }
/*     */ 
/*  39 */     if (fdiff == udiff) return 1;
/*     */ 
/*  41 */     if (team.equals(favorite))
/*     */     {
/*  43 */       if (fdiff > udiff) {
/*  44 */         return 100;
/*     */       }
/*     */ 
/*  47 */       return new Double(fdiff * 100.0D / udiff).intValue();
/*     */     }
/*     */ 
/*  50 */     if (team.equals(underdog))
/*     */     {
/*  52 */       if (udiff > fdiff) {
/*  53 */         return 100;
/*     */       }
/*     */ 
/*  56 */       return new Double(udiff * 100.0D / fdiff).intValue();
/*     */     }
/*     */ 
/*  59 */     return 0;
/*     */   }
/*     */ 
/*     */   public int computeGameStatus(Game game)
/*     */     throws Exception
/*     */   {
/*  67 */     if (game.notStarted()) {
/*  68 */       return 0;
/*     */     }
/*  70 */     GameBet gamebet = game.getBet();
/*     */ 
/*  72 */     String team = gamebet.getSpreadPointTeam();
/*  73 */     String favorite = gamebet.getSpreadPointFavorite();
/*  74 */     String underdog = null;
/*  75 */     double points = Double.parseDouble(gamebet.getSpreadPoint());
/*  76 */     double fdiff = 0.0D;
/*  77 */     double udiff = 0.0D;
/*     */ 
/*  79 */     if (favorite.equals(game.getHome().getName())) {
/*  80 */       fdiff = Math.abs(game.getHome().getScore() - points);
/*  81 */       udiff = game.getAway().getScore() + points;
/*  82 */       underdog = game.getAway().getName();
/*  83 */       game.getHome().setBetScore(fdiff);
/*  84 */       game.getAway().setBetScore(udiff);
/*     */     }
/*  86 */     else if (favorite.equals(game.getAway().getName())) {
/*  87 */       fdiff = Math.abs(game.getAway().getScore() - points);
/*  88 */       udiff = game.getHome().getScore() + points;
/*  89 */       underdog = game.getHome().getName();
/*  90 */       game.getAway().setBetScore(fdiff);
/*  91 */       game.getHome().setBetScore(udiff);
/*     */     }
/*     */ 
/*  95 */     if (fdiff == udiff) return 1;
/*     */ 
/*  97 */     if (team.equals(favorite))
/*     */     {
/*  99 */       if (fdiff > udiff) {
/* 100 */         return 100;
/*     */       }
/* 102 */       return 0;
/*     */     }
/* 104 */     if (team.equals(underdog))
/*     */     {
/* 106 */       if (udiff > fdiff) return 100;
/*     */ 
/* 109 */       return 0;
/*     */     }
/*     */ 
/* 112 */     return 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.SpreadpointComputer
 * JD-Core Version:    0.6.2
 */