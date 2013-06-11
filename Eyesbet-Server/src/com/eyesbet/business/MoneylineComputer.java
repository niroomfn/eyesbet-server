 package com.eyesbet.business;
 
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.business.domain.GameBet;
 import com.eyesbet.business.domain.Team;
 
 public class MoneylineComputer extends BetComputer
 {
   public int computeLiveGameStatus(Game game)
     throws Exception
   {
     int percentage = 0;
 
     GameBet gamebet = game.getBet();
 
     if (!gamebet.isMoneyline()) {
       throw new Exception("This bet is not a money line bet");
     }
 
     if (game.isTightScore()) {
       percentage = 50;
     }
     else if (gamebet.getMoneyline().equals(game.getHome().getName()))
     {
       int diff = game.getHome().getScore() - game.getAway().getScore();
 
       if (diff > 0)
         percentage = 100;
       else if (diff < 0)
       {
         percentage = game.getHome().getScore() * 100 / game.getAway().getScore();
       }
     }
     else if (gamebet.getMoneyline().equals(game.getAway().getName())) {
       int diff = game.getAway().getScore() - game.getHome().getScore();
 
       if (diff > 0) {
         percentage = 100;
       }
       else {
         percentage = game.getAway().getScore() * 100 / game.getHome().getScore();
       }
 
     }
 
     return percentage;
   }
 
   public int computeGameStatus(Game game)
     throws Exception
   {
     if (game.notStarted()) {
       return 0;
     }
     Team winner = game.getWinner();
     if (winner == null) {
       return 1;
     }
     if (winner.getName().equals(game.getBet().getMoneyline())) {
       return 100;
     }
     return 0;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.MoneylineComputer
 * JD-Core Version:    0.6.2
 */