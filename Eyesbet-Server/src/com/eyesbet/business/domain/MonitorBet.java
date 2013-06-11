 package com.eyesbet.business.domain;
 
 import java.util.Set;
 
 public class MonitorBet
 {
   private Bet bet;
   private Set<Game> liveGames;
   private boolean hasNotStartedGame;
 
   public MonitorBet(Bet bet)
   {
     this.bet = bet;
     this.hasNotStartedGame = bet.hasNotStartedGame();
   }
 
   public Set<Game> getLiveGames()
   {
     return this.liveGames;
   }
 
   public void setLiveGames(Set<Game> liveGames) {
     this.liveGames = liveGames;
   }
 
   public Bet getBet() {
     return this.bet;
   }
 
   public boolean isHasNotStartedGame() {
     return this.hasNotStartedGame;
   }
 
   public void setHasNotStatedGame(boolean hasNotStatedGame) {
     this.hasNotStartedGame = hasNotStatedGame;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.MonitorBet
 * JD-Core Version:    0.6.2
 */