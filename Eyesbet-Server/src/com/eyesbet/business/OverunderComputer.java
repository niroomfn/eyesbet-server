 package com.eyesbet.business;
 
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.business.domain.GameBet;
 
 public class OverunderComputer extends BetComputer
 {
   public int computeLiveGameStatus(Game game)
     throws Exception
   {
     int percentage = 0;
     GameBet gamebet = game.getBet();
 
     int opoints = validatePoints(gamebet.getOverPoints());
     int upoints = validatePoints(gamebet.getUnderPoints());
     int total = game.getHome().getScore() + game.getAway().getScore();
 
     if (opoints > 0) {
       if (total == opoints) return 1;
       if (total > opoints) {
         percentage = 100;
       }
       else
         return total * 100 / opoints;
     }
     else if (upoints > 0) {
       if (upoints == total) return 1;
       if (total < upoints)
         percentage = 100;
       else {
         return 0;
       }
     }
 
     game.setOverUnderStatus(percentage);
     return percentage;
   }
 
   private int validatePoints(String points)
   {
     try
     {
       return Integer.parseInt(points); } catch (NumberFormatException e) {
     }
     return 0;
   }
 
   public int computeGameStatus(Game game)
     throws Exception
   {
     if (game.notStarted()) {
       return 0;
     }
 
     int sum = game.getHome().getScore() + game.getAway().getScore();
 
     if (game.getBet().isOver()) {
       int points = Integer.parseInt(game.getBet().getOverPoints());
       if (sum > points)
       {
         return 100;
       }
       return sum * 100 / points;
     }
 
     int points = Integer.parseInt(game.getBet().getUnderPoints());
     if (sum < points) {
       return 100;
     }
     return 0;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.OverunderComputer
 * JD-Core Version:    0.6.2
 */