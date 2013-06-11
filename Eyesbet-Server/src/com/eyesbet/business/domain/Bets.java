 package com.eyesbet.business.domain;
 
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 
 @SuppressWarnings("serial")
public class Bets extends HashMap<String, List<Bet>>
 {
   private int status;
   private String xml;
 
   public void addBet(Bet bet)
   {
     String type = bet.getBetType().toString();
     if (get(type) == null) {
       List<Bet> list = new ArrayList<Bet>();
       list.add(bet);
       put(type, list);
     }
     else {
       List<Bet> list = get(type);
       if (!list.contains(bet))
         list.add(bet);
     }
   }
 
   public Bet getBet(int betId)
   {
     Set<String> set = keySet();
     List<Bet> list = null;
     Iterator<Bet> localIterator2;
			  Bet bet = null;
			  Iterator<String> localIterator1 = set.iterator();
     while (localIterator1.hasNext())
     {
       String type = (String)localIterator1.next();
 
       list = get(type);
       localIterator2 = list.iterator(); 
		        while (localIterator2.hasNext()) {
				 bet = localIterator2.next();
 
       if (bet.getId() == betId) {
         return bet;
       }
 
     }
		        
			}
 
     return null;
   }
 
   public void removeBet(int betId)
   {
     Set<String> set = keySet();
     Iterator<Bet> iter = null;
     for (String type : set)
     {
       iter = (get(type)).iterator();
       while (iter.hasNext())
       {
         if (((Bet)iter.next()).getId() == betId) {
           iter.remove();
           break;
         }
       }
     }
   }
 
   public Set<Game> getLiveGames()
   {
     Set<Game> set = new HashSet<Game>();
     Set<String> keyset = keySet();
     List<Bet> list = null;
     for ( String key: keyset)
     {
       
       list = get(key);
       
				for (Bet bet: list) {
 
       set.addAll(bet.getLiveGames());
     }
			}
 
     return set;
   }
 
   public List<Game> getFinishedGames()
   {
     List<Game> games = new ArrayList<Game>();
     Set<String> keyset = keySet();
     List<Bet> list = null;
     for (String key: keyset)
     {
       
       list = get(key);
				for (Bet bet: list) {
 
       games.addAll(bet.getFinishedGames());
     }
			}
 
     return games;
   }
 
   public List<Bet> getBets()
   {
     Set<String> keyset = keySet();
     List<Bet> list = new ArrayList<Bet>();
     for (String type : keyset) {
       list.addAll(get(type));
     }
     return list;
   }
 
   public List<Game> getGames(String favorite, String underdog)
   {
     List<Bet> list = getBets();
     List<Game> gameList = new ArrayList<Game>();
     for (Bet bet: list)
     {
      
 
       List<Game> games = bet.getGames();
				for (Game game: games) {
       if ((game.getHome().getName().equals(favorite)) && (game.getAway().getName().equals(underdog))) {
         gameList.add(game);
       }
				}
 
     }
 
     return gameList;
   }
 
   public int getStatus()
   {
     return this.status;
   }
 
   public void setStatus(int status)
   {
     this.status = status;
   }
 
   public Map<String, Set<Game>> getGamesByDate()
   {
     Map<String,Set<Game>> map = new HashMap<String,Set<Game>>();
     Set<String> set = keySet();
 
     List<Game> games = null;
     for (String key: set)
     {
 
       List<Bet> list = get(key);
 		for (Bet bet: list) {
 
	       games = bet.getGames();
	 
	       for (Game game : games)
	       {
	         if (map.get(game.getSchedule()) == null) {
	           Set<Game> s = new HashSet<Game>();
	           s.add(game);
	           map.put(game.getSchedule(), s);
	         }
	         else {
	           (map.get(game.getSchedule())).add(game);
	         }
	 
	       }
			  }
 
     }
 
     return map;
   }
 
   public String getXml()
   {
     return this.xml;
   }
 
   public void setXml(String xml)
   {
     this.xml = xml;
   }
 
   public boolean hasLiveGames()
   {
     for (Bet bet : getBets())
     {
       if (bet.hasLiveGame()) return true;
 
     }
 
     return false;
   }

			public void resetBet(Bet bet) {
				
				this.removeBet(bet.getId());
				
				
				this.addBet(bet);
				
				
			}
 }

