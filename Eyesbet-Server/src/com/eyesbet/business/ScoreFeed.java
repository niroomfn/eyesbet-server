/*     */ package com.eyesbet.business;
/*     */ 
/*     */ import com.eyesbet.app.xml.FeedSaxParser;
/*     */ import com.eyesbet.app.xml.LiveScoreFeedHandler;
/*     */ import com.eyesbet.business.domain.Fixtures.Leagues;
/*     */ import com.eyesbet.business.domain.Game;
/*     */ import com.eyesbet.business.domain.Params;
/*     */ import com.eyesbet.business.domain.RequestBuilder;
/*     */ import com.eyesbet.business.domain.TrackerGames;
/*     */ import com.eyesbet.util.DateTime;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class ScoreFeed
/*     */ {
/*  24 */   private static Logger logger = Logger.getLogger(ScoreFeed.class);
/*     */   private TrackerGames games;
/*     */ 
/*     */   public ScoreFeed(TrackerGames games)
/*     */   {
/*  28 */     this.games = games;
/*     */   }
/*     */ 
/*     */   public void updateLiveScores()
/*     */   {
/*  33 */     Set<Leagues> keys = this.games.keySet();
/*  34 */     Set<Game> set = null;
/*  35 */     Params params = new Params(null);
/*  36 */     params.setDate(DateTime.convertDateToFeedDate(new Date()));
/*  37 */     params.setGames("livescores");
/*  38 */     String url = null;
/*  39 */     for (Leagues key : keys)
/*     */     {
/*  41 */       set = this.games.get(key);
/*  42 */       if (!set.isEmpty()) {
/*  43 */         url = RequestBuilder.buildRequest(key, params);
/*     */         try
/*     */         {
/*  46 */           LiveScoreFeedHandler handler = new LiveScoreFeedHandler(key, set);
/*  47 */           new FeedSaxParser(url, handler);
/*     */         }
/*     */         catch (Exception e) {
/*  50 */           logger.error("", e);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateAllGamesScore()
/*     */   {
/*  64 */     Set keys = this.games.keySet();
/*  65 */     Set set = null;
/*  66 */     Params params = new Params(null);
/*  67 */     params.setGames("all");
/*  68 */     String url = null;
/*     */ 
/*  71 */     Map map = null;
/*     */     Iterator localIterator2;
/*  72 */     for (Iterator<Leagues> localIterator1 = keys.iterator(); localIterator1.hasNext(); 
/*  77 */       localIterator2.hasNext())
/*     */     {
/*  72 */       Leagues key = localIterator1.next();
/*     */ 
/*  74 */       set = (Set)this.games.get(key);
/*  75 */       map = groupGamesByDate(set);
/*  76 */       Set<String> mapKeys = map.keySet();
/*  77 */       localIterator2 = mapKeys.iterator();
				String k = (String)localIterator2.next();
/*     */ 
/*  79 */       params.setDate(DateTime.convertDateToFeedDate(k));
/*  80 */       url = RequestBuilder.buildRequest(key, params);
/*     */       try
/*     */       {
/*  83 */         LiveScoreFeedHandler handler = new LiveScoreFeedHandler(key, set);
/*  84 */         new FeedSaxParser(url, handler);
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*  89 */         logger.error("", e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private Map<String, Set<Game>> groupGamesByDate(Set<Game> games)
/*     */   {
/* 102 */     Map map = new HashMap();
/* 103 */     String date = null;
/* 104 */     Set set = null;
/* 105 */     for (Game game : games)
/*     */     {
/* 107 */       date = game.getSchedule();
/* 108 */       if (map.get(date) == null) {
/* 109 */         set = new HashSet();
/* 110 */         set.add(game);
/* 111 */         map.put(date, set);
/*     */       }
/*     */       else
/*     */       {
/* 115 */         ((Set)map.get(date)).add(game);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 123 */     return map;
/*     */   }
/*     */ 
/*     */   public void saveFinishedGames()
/*     */   {
/* 131 */     Set<Leagues> set = this.games.keySet();
/* 132 */     Set<Game> finishedGames = new HashSet<Game>();
/* 133 */     Set<Game> gameList = null;
/*     */     Iterator<Game> localIterator2;
/* 134 */     for (Iterator localIterator1 = set.iterator(); localIterator1.hasNext(); 
/* 137 */       localIterator2.hasNext())
/*     */     {
/* 134 */       Leagues league = (Leagues)localIterator1.next();
/*     */ 
/* 136 */       gameList = this.games.get(league);
/* 137 */       localIterator2 = gameList.iterator(); 
				Game g = (Game)localIterator2.next();
/* 138 */       if (g.isFinished()) {
/* 139 */         finishedGames.add(g);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 146 */     if (finishedGames.size() > 0) {
/* 147 */       Service service = new Service();
/*     */       try {
/* 149 */         service.saveGameResult(finishedGames);
/*     */       } catch (Exception e) {
/* 151 */         logger.error("Error saving finished games", e);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

