 package com.eyesbet.business.domain;
 
 public class UniqBet
 {
   private Game game;
   private GameBet gamebet;
 
   public UniqBet(Game game)
   {
     GameBet gb = game.getBet();
     if (gb.isMoneyline())
     {
       this.gamebet = gb;
       this.game = game;
     }
     else {
       gb.isSpreadPoint();
     }
   }
 
   public Game getGame()
   {
     return this.game;
   }
 
   public void setGame(Game game) {
     GameBet gb = game.getBet();
     if (gb.isMoneyline())
     {
       this.gamebet = gb;
     }
 
     this.game = game;
   }
 
   public GameBet getGamebet()
   {
     return this.gamebet;
   }
   public void setGamebet(GameBet gamebet) {
     this.gamebet = gamebet;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.UniqBet
 * JD-Core Version:    0.6.2
 */