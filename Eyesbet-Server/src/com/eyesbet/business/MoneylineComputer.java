/*    */ package com.eyesbet.business;
/*    */ 
/*    */ import com.eyesbet.business.domain.Game;
/*    */ import com.eyesbet.business.domain.GameBet;
/*    */ import com.eyesbet.business.domain.Team;
/*    */ 
/*    */ public class MoneylineComputer extends BetComputer
/*    */ {
/*    */   public int computeLiveGameStatus(Game game)
/*    */     throws Exception
/*    */   {
/* 13 */     int percentage = 0;
/*    */ 
/* 15 */     GameBet gamebet = game.getBet();
/*    */ 
/* 17 */     if (!gamebet.isMoneyline()) {
/* 18 */       throw new Exception("This bet is not a money line bet");
/*    */     }
/*    */ 
/* 22 */     if (game.isTightScore()) {
/* 23 */       percentage = 50;
/*    */     }
/* 26 */     else if (gamebet.getMoneyline().equals(game.getHome().getName()))
/*    */     {
/* 28 */       int diff = game.getHome().getScore() - game.getAway().getScore();
/*    */ 
/* 30 */       if (diff > 0)
/* 31 */         percentage = 100;
/* 32 */       else if (diff < 0)
/*    */       {
/* 34 */         percentage = game.getHome().getScore() * 100 / game.getAway().getScore();
/*    */       }
/*    */     }
/* 37 */     else if (gamebet.getMoneyline().equals(game.getAway().getName())) {
/* 38 */       int diff = game.getAway().getScore() - game.getHome().getScore();
/*    */ 
/* 40 */       if (diff > 0) {
/* 41 */         percentage = 100;
/*    */       }
/*    */       else {
/* 44 */         percentage = game.getAway().getScore() * 100 / game.getHome().getScore();
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 50 */     return percentage;
/*    */   }
/*    */ 
/*    */   public int computeGameStatus(Game game)
/*    */     throws Exception
/*    */   {
/* 56 */     if (game.notStarted()) {
/* 57 */       return 0;
/*    */     }
/* 59 */     Team winner = game.getWinner();
/* 60 */     if (winner == null) {
/* 61 */       return 1;
/*    */     }
/* 63 */     if (winner.getName().equals(game.getBet().getMoneyline())) {
/* 64 */       return 100;
/*    */     }
/* 66 */     return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.MoneylineComputer
 * JD-Core Version:    0.6.2
 */