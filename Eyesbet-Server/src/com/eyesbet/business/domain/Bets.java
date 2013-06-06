/*     */ package com.eyesbet.business.domain;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ @SuppressWarnings("serial")
public class Bets extends HashMap<String, List<Bet>>
/*     */ {
/*     */   private int status;
/*     */   private String xml;
/*     */ 
/*     */   public void addBet(Bet bet)
/*     */   {
/*  22 */     String type = bet.getBetType().toString();
/*  23 */     if (get(type) == null) {
/*  24 */       List<Bet> list = new ArrayList<Bet>();
/*  25 */       list.add(bet);
/*  26 */       put(type, list);
/*     */     }
/*     */     else {
/*  29 */       List<Bet> list = get(type);
/*  30 */       if (!list.contains(bet))
/*  31 */         list.add(bet);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Bet getBet(int betId)
/*     */   {
/*  41 */     Set<String> set = keySet();
/*  42 */     List<Bet> list = null;
/*     */     Iterator<Bet> localIterator2;
			  Bet bet = null;
			  Iterator<String> localIterator1 = set.iterator();
/*  43 */     while (localIterator1.hasNext())
/*     */     {
/*  43 */       String type = (String)localIterator1.next();
/*     */ 
/*  45 */       list = get(type);
/*  46 */       localIterator2 = list.iterator(); 
		        while (localIterator2.hasNext()) {
				 bet = localIterator2.next();
/*     */ 
/*  48 */       if (bet.getId() == betId) {
/*  49 */         return bet;
/*     */       }
/*     */ 
/*     */     }
		        
			}
/*     */ 
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */   public void removeBet(int betId)
/*     */   {
/*  60 */     Set<String> set = keySet();
/*  61 */     Iterator<Bet> iter = null;
/*  62 */     for (String type : set)
/*     */     {
/*  64 */       iter = (get(type)).iterator();
/*  65 */       while (iter.hasNext())
/*     */       {
/*  67 */         if (((Bet)iter.next()).getId() == betId) {
/*  68 */           iter.remove();
/*  69 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Set<Game> getLiveGames()
/*     */   {
/*  79 */     Set<Game> set = new HashSet<Game>();
/*  80 */     Set<String> keyset = keySet();
/*  81 */     List<Bet> list = null;
/*  82 */     for ( String key: keyset)
/*     */     {
/*  82 */       
/*  83 */       list = get(key);
/*  84 */       
				for (Bet bet: list) {
/*     */ 
/*  86 */       set.addAll(bet.getLiveGames());
/*     */     }
			}
/*     */ 
/*  91 */     return set;
/*     */   }
/*     */ 
/*     */   public List<Game> getFinishedGames()
/*     */   {
/*  96 */     List<Game> games = new ArrayList<Game>();
/*  97 */     Set<String> keyset = keySet();
/*  98 */     List<Bet> list = null;
/*  99 */     for (String key: keyset)
/*     */     {
/*  99 */       
/* 100 */       list = get(key);
				for (Bet bet: list) {
/*     */ 
/* 103 */       games.addAll(bet.getFinishedGames());
/*     */     }
			}
/*     */ 
/* 107 */     return games;
/*     */   }
/*     */ 
/*     */   public List<Bet> getBets()
/*     */   {
/* 113 */     Set<String> keyset = keySet();
/* 114 */     List<Bet> list = new ArrayList<Bet>();
/* 115 */     for (String type : keyset) {
/* 116 */       list.addAll(get(type));
/*     */     }
/* 118 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Game> getGames(String favorite, String underdog)
/*     */   {
/* 124 */     List<Bet> list = getBets();
/* 125 */     List<Game> gameList = new ArrayList<Game>();
/* 126 */     for (Bet bet: list)
/*     */     {
/* 126 */      
/*     */ 
/* 128 */       List<Game> games = bet.getGames();
				for (Game game: games) {
/* 130 */       if ((game.getHome().getName().equals(favorite)) && (game.getAway().getName().equals(underdog))) {
/* 131 */         gameList.add(game);
/*     */       }
				}
/*     */ 
/*     */     }
/*     */ 
/* 136 */     return gameList;
/*     */   }
/*     */ 
/*     */   public int getStatus()
/*     */   {
/* 141 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(int status)
/*     */   {
/* 146 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Map<String, Set<Game>> getGamesByDate()
/*     */   {
/* 152 */     Map<String,Set<Game>> map = new HashMap<String,Set<Game>>();
/* 153 */     Set<String> set = keySet();
/*     */ 
/* 155 */     List<Game> games = null;
/* 156 */     for (String key: set)
/*     */     {
/*     */ 
/* 158 */       List<Bet> list = get(key);
/*     */ 		for (Bet bet: list) {
/*     */ 
	/* 162 */       games = bet.getGames();
	/*     */ 
	/* 164 */       for (Game game : games)
	/*     */       {
	/* 166 */         if (map.get(game.getSchedule()) == null) {
	/* 167 */           Set<Game> s = new HashSet<Game>();
	/* 168 */           s.add(game);
	/* 169 */           map.put(game.getSchedule(), s);
	/*     */         }
	/*     */         else {
	/* 172 */           (map.get(game.getSchedule())).add(game);
	/*     */         }
	/*     */ 
	/*     */       }
			  }
/*     */ 
/*     */     }
/*     */ 
/* 181 */     return map;
/*     */   }
/*     */ 
/*     */   public String getXml()
/*     */   {
/* 189 */     return this.xml;
/*     */   }
/*     */ 
/*     */   public void setXml(String xml)
/*     */   {
/* 194 */     this.xml = xml;
/*     */   }
/*     */ 
/*     */   public boolean hasLiveGames()
/*     */   {
/* 200 */     for (Bet bet : getBets())
/*     */     {
/* 202 */       if (bet.hasLiveGame()) return true;
/*     */ 
/*     */     }
/*     */ 
/* 206 */     return false;
/*     */   }
/*     */ }

