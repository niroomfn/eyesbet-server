/*    */ package com.eyesbet.business;
/*    */ 
/*    */ import com.eyesbet.business.domain.Game;
/*    */ import com.eyesbet.business.domain.GameBet;
/*    */ 
/*    */ public class OverunderComputer extends BetComputer
/*    */ {
/*    */   public int computeLiveGameStatus(Game game)
/*    */     throws Exception
/*    */   {
/* 11 */     int percentage = 0;
/* 12 */     GameBet gamebet = game.getBet();
/*    */ 
/* 15 */     int opoints = validatePoints(gamebet.getOverPoints());
/* 16 */     int upoints = validatePoints(gamebet.getUnderPoints());
/* 17 */     int total = game.getHome().getScore() + game.getAway().getScore();
/*    */ 
/* 21 */     if (opoints > 0) {
/* 22 */       if (total == opoints) return 1;
/* 23 */       if (total > opoints) {
/* 24 */         percentage = 100;
/*    */       }
/*    */       else
/* 27 */         return total * 100 / opoints;
/*    */     }
/* 29 */     else if (upoints > 0) {
/* 30 */       if (upoints == total) return 1;
/* 31 */       if (total < upoints)
/* 32 */         percentage = 100;
/*    */       else {
/* 34 */         return 0;
/*    */       }
/*    */     }
/*    */ 
/* 38 */     game.setOverUnderStatus(percentage);
/* 39 */     return percentage;
/*    */   }
/*    */ 
/*    */   private int validatePoints(String points)
/*    */   {
/*    */     try
/*    */     {
/* 47 */       return Integer.parseInt(points); } catch (NumberFormatException e) {
/*    */     }
/* 49 */     return 0;
/*    */   }
/*    */ 
/*    */   public int computeGameStatus(Game game)
/*    */     throws Exception
/*    */   {
/* 59 */     if (game.notStarted()) {
/* 60 */       return 0;
/*    */     }
/*    */ 
/* 63 */     int sum = game.getHome().getScore() + game.getAway().getScore();
/*    */ 
/* 65 */     if (game.getBet().isOver()) {
/* 66 */       int points = Integer.parseInt(game.getBet().getOverPoints());
/* 67 */       if (sum > points)
/*    */       {
/* 69 */         return 100;
/*    */       }
/* 71 */       return sum * 100 / points;
/*    */     }
/*    */ 
/* 74 */     int points = Integer.parseInt(game.getBet().getUnderPoints());
/* 75 */     if (sum < points) {
/* 76 */       return 100;
/*    */     }
/* 78 */     return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.OverunderComputer
 * JD-Core Version:    0.6.2
 */