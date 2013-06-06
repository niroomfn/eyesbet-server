/*    */ package com.eyesbet.business.domain;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ public class MonitorBet
/*    */ {
/*    */   private Bet bet;
/*    */   private Set<Game> liveGames;
/*    */   private boolean hasNotStartedGame;
/*    */ 
/*    */   public MonitorBet(Bet bet)
/*    */   {
/* 14 */     this.bet = bet;
/* 15 */     this.hasNotStartedGame = bet.hasNotStartedGame();
/*    */   }
/*    */ 
/*    */   public Set<Game> getLiveGames()
/*    */   {
/* 23 */     return this.liveGames;
/*    */   }
/*    */ 
/*    */   public void setLiveGames(Set<Game> liveGames) {
/* 27 */     this.liveGames = liveGames;
/*    */   }
/*    */ 
/*    */   public Bet getBet() {
/* 31 */     return this.bet;
/*    */   }
/*    */ 
/*    */   public boolean isHasNotStartedGame() {
/* 35 */     return this.hasNotStartedGame;
/*    */   }
/*    */ 
/*    */   public void setHasNotStatedGame(boolean hasNotStatedGame) {
/* 39 */     this.hasNotStartedGame = hasNotStatedGame;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.MonitorBet
 * JD-Core Version:    0.6.2
 */