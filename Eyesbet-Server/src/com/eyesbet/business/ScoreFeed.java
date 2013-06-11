 package com.eyesbet.business;
 
 import com.eyesbet.app.xml.FeedSaxParser;
 import com.eyesbet.app.xml.LiveScoreFeedHandler;
 import com.eyesbet.business.domain.Fixtures.Leagues;
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.business.domain.Params;
 import com.eyesbet.business.domain.RequestBuilder;
 import com.eyesbet.business.domain.TrackerGames;
 import com.eyesbet.util.DateTime;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.Map;
 import java.util.Set;
 import org.apache.log4j.Logger;
 
 public class ScoreFeed
 {
   private static Logger logger = Logger.getLogger(ScoreFeed.class);
   private TrackerGames games;
 
   public ScoreFeed(TrackerGames games)
   {
     this.games = games;
   }
 
   public void updateLiveScores()
   {
     Set<Leagues> keys = this.games.keySet();
     Set<Game> set = null;
     Params params = new Params(null);
     params.setDate(DateTime.convertDateToFeedDate(new Date()));
     params.setGames("livescores");
     String url = null;
     for (Leagues key : keys)
     {
       set = this.games.get(key);
       if (!set.isEmpty()) {
         url = RequestBuilder.buildRequest(key, params);
         try
         {
           LiveScoreFeedHandler handler = new LiveScoreFeedHandler(key, set);
           new FeedSaxParser(url, handler);
         }
         catch (Exception e) {
           logger.error("", e);
         }
       }
     }
   }
 
   public void updateAllGamesScore()
   {
     Set keys = this.games.keySet();
     Set set = null;
     Params params = new Params(null);
     params.setGames("all");
     String url = null;
 
     Map map = null;
     Iterator localIterator2;
     for (Iterator<Leagues> localIterator1 = keys.iterator(); localIterator1.hasNext(); 
       localIterator2.hasNext())
     {
       Leagues key = localIterator1.next();
 
       set = (Set)this.games.get(key);
       map = groupGamesByDate(set);
       Set<String> mapKeys = map.keySet();
       localIterator2 = mapKeys.iterator();
				String k = (String)localIterator2.next();
 
       params.setDate(DateTime.convertDateToFeedDate(k));
       url = RequestBuilder.buildRequest(key, params);
       try
       {
         LiveScoreFeedHandler handler = new LiveScoreFeedHandler(key, set);
         new FeedSaxParser(url, handler);
       }
       catch (Exception e)
       {
         logger.error("", e);
       }
     }
   }
 
   private Map<String, Set<Game>> groupGamesByDate(Set<Game> games)
   {
     Map map = new HashMap();
     String date = null;
     Set set = null;
     for (Game game : games)
     {
       date = game.getSchedule();
       if (map.get(date) == null) {
         set = new HashSet();
         set.add(game);
         map.put(date, set);
       }
       else
       {
         ((Set)map.get(date)).add(game);
       }
 
     }
 
     return map;
   }
 
   public void saveFinishedGames()
   {
     Set<Leagues> set = this.games.keySet();
     Set<Game> finishedGames = new HashSet<Game>();
     Set<Game> gameList = null;
     Iterator<Game> localIterator2;
     for (Iterator localIterator1 = set.iterator(); localIterator1.hasNext(); 
       localIterator2.hasNext())
     {
       Leagues league = (Leagues)localIterator1.next();
 
       gameList = this.games.get(league);
       localIterator2 = gameList.iterator(); 
				Game g = (Game)localIterator2.next();
       if (g.isFinished()) {
         finishedGames.add(g);
       }
 
     }
 
     if (finishedGames.size() > 0) {
       Service service = new Service();
       try {
         service.saveGameResult(finishedGames);
       } catch (Exception e) {
         logger.error("Error saving finished games", e);
       }
     }
   }
 }

