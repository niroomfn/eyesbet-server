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
     Set<Leagues> keys = this.games.keySet();
     Set<Game> set = null;
     Params params = new Params(null);
     params.setGames("all");
     String url = null;
     Map<String,Set<Game>> map = null;
     for (Leagues league: keys) {
    	 
       set = games.get(league);
       map = groupGamesByDate(set);
       Set<String> mapKeys = map.keySet();
	       for (String k: mapKeys) {

		       params.setDate(k);
		       url = RequestBuilder.buildRequest(league, params);
		       try
		       {
		         new FeedSaxParser(url, new LiveScoreFeedHandler(league,set));
		       }
		       catch (Exception e)
		       {
		         logger.error("", e);
		       }
       }
     }
   }
 
   private Map<String, Set<Game>> groupGamesByDate(Set<Game> games)
   {
     Map<String,Set<Game>> map = new HashMap<String, Set<Game>>();
    // Date date = null;
     Set<Game> set = null;
     String date =  null; 
     for (Game game : games)
     {
    	 
         date = DateTime.convertDateToFeedDate(game.getScheduleDate());
       if (map.get(date) == null) {
         set = new HashSet<Game>();
         set.add(game);
         map.put(date, set);
       }
       else
       {
         (map.get(date)).add(game);
       }
 
     }
 
     return map;
   }
 
   public void saveFinishedGames()
   {
     Set<Leagues> set = this.games.keySet();
     Set<Game> finishedGames = new HashSet<Game>();
     Set<Game> gameList = null;
     for (Leagues league: set)
     {
 
       gameList = this.games.get(league);
       
       for (Game g: gameList) {
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
   
   
   
   public class DateKey {
	   
	   
	   private Date date;
	   private String dateText;
	   
	   public DateKey(Date date, String dateText) {
		   
		   this.date = date;
		   this.dateText = dateText;
		   
	   }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateText() {
		return dateText;
	}

	public void setDateText(String dateText) {
		this.dateText = dateText;
	}

	@Override
	public boolean equals(Object obj) {
		
		String text = ((DateKey)obj).getDateText();
		
		return this.dateText.equals(text);
		
	}

	@Override
	public int hashCode() {
		
		return dateText.hashCode();
		
		
	}
	   
	   
	   
	   
   }
   
  
   
 }

