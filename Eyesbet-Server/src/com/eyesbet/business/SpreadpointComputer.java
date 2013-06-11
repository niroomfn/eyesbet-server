 package com.eyesbet.business;
 
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.business.domain.GameBet;
 
 public class SpreadpointComputer extends BetComputer
 {
   public int computeLiveGameStatus(Game game)
     throws Exception
   {
     GameBet gamebet = game.getBet();
 
     String team = gamebet.getSpreadPointTeam();
     String favorite = gamebet.getSpreadPointFavorite();
     String underdog = null;
     double points = Double.parseDouble(gamebet.getSpreadPoint());
     double fdiff = 0.0D;
     double udiff = 0.0D;
 
     if (favorite.equals(game.getHome().getName())) {
       fdiff = Math.abs(game.getHome().getScore() - points);
       udiff = game.getAway().getScore() + points;
       underdog = game.getAway().getName();
       game.getHome().setBetScore(fdiff);
       game.getAway().setBetScore(udiff);
     }
     else if (favorite.equals(game.getAway().getName())) {
       fdiff = Math.abs(game.getAway().getScore() - points);
       udiff = game.getHome().getScore() + points;
       underdog = game.getHome().getName();
 
       game.getAway().setBetScore(fdiff);
       game.getHome().setBetScore(udiff);
     }
 
     if (fdiff == udiff) return 1;
 
     if (team.equals(favorite))
     {
       if (fdiff > udiff) {
         return 100;
       }
 
       return new Double(fdiff * 100.0D / udiff).intValue();
     }
 
     if (team.equals(underdog))
     {
       if (udiff > fdiff) {
         return 100;
       }
 
       return new Double(udiff * 100.0D / fdiff).intValue();
     }
 
     return 0;
   }
 
   public int computeGameStatus(Game game)
     throws Exception
   {
     if (game.notStarted()) {
       return 0;
     }
     GameBet gamebet = game.getBet();
 
     String team = gamebet.getSpreadPointTeam();
     String favorite = gamebet.getSpreadPointFavorite();
     String underdog = null;
     double points = Double.parseDouble(gamebet.getSpreadPoint());
     double fdiff = 0.0D;
     double udiff = 0.0D;
 
     if (favorite.equals(game.getHome().getName())) {
       fdiff = Math.abs(game.getHome().getScore() - points);
       udiff = game.getAway().getScore() + points;
       underdog = game.getAway().getName();
       game.getHome().setBetScore(fdiff);
       game.getAway().setBetScore(udiff);
     }
     else if (favorite.equals(game.getAway().getName())) {
       fdiff = Math.abs(game.getAway().getScore() - points);
       udiff = game.getHome().getScore() + points;
       underdog = game.getHome().getName();
       game.getAway().setBetScore(fdiff);
       game.getHome().setBetScore(udiff);
     }
 
     if (fdiff == udiff) return 1;
 
     if (team.equals(favorite))
     {
       if (fdiff > udiff) {
         return 100;
       }
       return 0;
     }
     if (team.equals(underdog))
     {
       if (udiff > fdiff) return 100;
 
       return 0;
     }
 
     return 0;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.SpreadpointComputer
 * JD-Core Version:    0.6.2
 */