/*    */ package com.eyesbet.business.domain;
/*    */ 
/*    */ public class UniqBet
/*    */ {
/*    */   private Game game;
/*    */   private GameBet gamebet;
/*    */ 
/*    */   public UniqBet(Game game)
/*    */   {
/* 11 */     GameBet gb = game.getBet();
/* 12 */     if (gb.isMoneyline())
/*    */     {
/* 14 */       this.gamebet = gb;
/* 15 */       this.game = game;
/*    */     }
/*    */     else {
/* 18 */       gb.isSpreadPoint();
/*    */     }
/*    */   }
/*    */ 
/*    */   public Game getGame()
/*    */   {
/* 30 */     return this.game;
/*    */   }
/*    */ 
/*    */   public void setGame(Game game) {
/* 34 */     GameBet gb = game.getBet();
/* 35 */     if (gb.isMoneyline())
/*    */     {
/* 37 */       this.gamebet = gb;
/*    */     }
/*    */ 
/* 45 */     this.game = game;
/*    */   }
/*    */ 
/*    */   public GameBet getGamebet()
/*    */   {
/* 53 */     return this.gamebet;
/*    */   }
/*    */   public void setGamebet(GameBet gamebet) {
/* 56 */     this.gamebet = gamebet;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.UniqBet
 * JD-Core Version:    0.6.2
 */