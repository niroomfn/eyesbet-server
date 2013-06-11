 package com.eyesbet.business;
 
 import com.eyesbet.business.domain.Bet;
 import com.eyesbet.business.domain.Bets;
 import com.eyesbet.business.domain.Game;
 import java.util.List;
 
 public abstract class BetComputer
 {
   private static BetComputer moneylineComputer = new MoneylineComputer();
   private static BetComputer overunderComputer = new OverunderComputer();
   private static BetComputer spreadpointComputer = new SpreadpointComputer();
 
   public abstract int computeLiveGameStatus(Game paramGame)
     throws Exception;
 
   public abstract int computeGameStatus(Game paramGame)
     throws Exception;
 
   public static int computeLiveGameBet(Game game)
   {
     int counter = 0;
     int overunderStatus = 0;
     int spreadpointStatus = 0;
     if (game.getBet().isMoneyline())
       try
       {
         game.setStatus(moneylineComputer.computeLiveGameStatus(game));
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     if (game.getBet().isOverUnder()) {
       counter++;
       try {
         overunderStatus = overunderComputer.computeLiveGameStatus(game);
         game.setOverUnderStatus(overunderStatus);
         game.setStatus(overunderStatus);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     if (game.getBet().isSpreadPoint()) {
       counter++;
       try {
         spreadpointStatus = spreadpointComputer.computeLiveGameStatus(game);
         game.setSpreadPointStatus(spreadpointStatus);
         game.setStatus(spreadpointStatus);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
 
     if (counter == 2)
     {
       game.setStatus((spreadpointStatus + overunderStatus) / 2);
     }
 
     return game.getStatus();
   }
 
   public static int computGameBet(Game game)
   {
     try
     {
       if (game.getBet().isMoneyline())
       {
         game.setStatus(moneylineComputer.computeGameStatus(game));
       }
       else {
         int count = 0;
         int spreadStatus = 0;
         int overunderStatus = 0;
         if (game.getBet().isOverUnder()) {
           count++;
           overunderStatus = overunderComputer.computeGameStatus(game);
           game.setOverUnderStatus(overunderStatus);
           game.setStatus(overunderStatus);
         }
         if (game.getBet().isSpreadPoint()) {
           count++;
           spreadStatus = spreadpointComputer.computeGameStatus(game);
           game.setSpreadPointStatus(spreadStatus);
           game.setStatus(spreadStatus);
         }
 
         if (count == 2)
         {
           game.setStatus((spreadStatus + overunderStatus) / 2);
         }
       }
 
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
 
     return game.getStatus();
   }
 
   public static String computeLiveBetStatus(Bets bets) {
     StringBuilder sb = new StringBuilder("<bets>");
     List<Game> games = null;
     int sum = 0;
     int count = 0;
     for (Bet bet : bets.getBets()) {
       sum = 0;
       games = bet.getGames();
       count = 0;
       for (Game game : games) {
         if (game.getStatus() != 1) {
           count++;
           sum += game.getStatus();
         }
       }
       if (count == 0) bet.setStatus(1);
       else if (count > 0) {
         bet.setStatus(sum / count);
       }
       sb.append("<bet id='" + bet.getId() + "' s='" + bet.getStatus() + "'/>");
     }
     sb.append("</bets></data>");
     return sb.toString();
   }
 
   public static Bet computeBetStatus(Bet bet)
   {
     int sum = 0;
     List<Game> games = bet.getGames();
     int count = 0;
     for (Game game : games) {
       if (game.getStatus() != 1) {
         count++;
         sum += game.getStatus();
       }
     }
 
     if (count == 0) bet.setStatus(1);
     else if (count > 0) {
       bet.setStatus(sum / count);
     }
     return bet;
   }
 
   public static enum TightStatus
   {
     tight;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.BetComputer
 * JD-Core Version:    0.6.2
 */