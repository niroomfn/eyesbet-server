 package com.eyesbet.business.domain;
 
 import com.eyesbet.util.DateTime;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 public class SortedGames
 {
   private Map<Integer, String> betNames = new HashMap<Integer,String>();
 
   private List<Game> games = new ArrayList<Game>();
 
   public void addGame(Game game, String betName, int betId)
   {
	   
	  String name = betName + "="+betId;
     if (game.isFinished())
       if (!this.games.contains(game))
       {
         this.games.add(game);
         this.betNames.put(Integer.valueOf(game.getGameId()), name);
       }
       else
       {
         Game g = (Game)this.games.get(this.games.indexOf(game));
          name = (String)this.betNames.get(Integer.valueOf(g.getGameId())) + ";" + name;
         this.betNames.put(Integer.valueOf(g.getGameId()), name);
       }
   }
 
   public void sortByUSDate()
   {
     Collections.sort(this.games, new SortByUSDate());
   }
 
   public List<Game> getGames()
   {
     return this.games;
   }
 
   public String getBetNames(int gameId)
   {
     return (String)this.betNames.get(Integer.valueOf(gameId));
   }
 
   public static String getStartTime(Game game)
   {
     if (game.isLive())
     {
       return game.getStatusDesc();
     }
 
     return game.getSchedule();
   }
 
   public class SortByUSDate
     implements Comparator<Game>
   {
     public SortByUSDate()
     {
     }
 
     public int compare(Game g1, Game g2)
     {
       Date date1 = DateTime.convertToUSDate(g1.getSchedule());
       Date date2 = DateTime.convertToUSDate(g2.getSchedule());
 
       return date1.compareTo(date2);
     }
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.SortedGames
 * JD-Core Version:    0.6.2
 */