 package com.eyesbet.business;
 
 import com.eyesbet.business.domain.Game;
import com.eyesbet.business.domain.TrackerGames;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
 
 public class Tracker
 {
   private static Logger logger = Logger.getLogger(Tracker.class);
   private static Tracker me = new Tracker();
 
   private TrackerGames games = new TrackerGames();
   private ScheduledExecutorService executor;
   private Random random = new Random();
   private Timestamp timestamp = new Timestamp();
   
   public static Tracker getInstance()
   {
     return me;
   }
 
   public void addGames(Set<Game> set, int userId)
   {
    /* for (Game game : set) {
       LiveGame livegame = game.toLiveGame();
       livegame.addUser(userId);
     }  */
     this.games.addGames(set, userId);
 
     if ((this.executor == null) || (this.executor.isShutdown()))
       start();
   }
 
   public void updateGame(Game game)
   {
     game.getAway().setScore(this.random.nextInt(game.getAway().getScore() + 10));
     game.getHome().setScore(this.random.nextInt(game.getHome().getScore() + 10));
   }
 
   private void start() {
     logger.info("Starting task...");
     this.executor = Executors.newSingleThreadScheduledExecutor();
     
     this.executor.scheduleAtFixedRate(new TrackerTask(), -1L, 10L, TimeUnit.SECONDS);
     logger.info("Task started.");
   }
 
   public void stop()
   {
     executor.shutdown();
     executor = null;
     logger.info("Task Stoped");
   }
 
   public void removeUser(int userId)
   {
     this.games.removeUser(userId);
   }
   
   
   public Timestamp getTimeStamps() {
	   
	   return timestamp;
	   
   }
 
   public class TrackerTask implements Runnable {
	   
     public TrackerTask() {
    	 
     }
 
     public void run() {
       logger.info("Tracking liveGames: ");
 
       if (games.isEmpty()) {
         logger.info("No liveGames found to track. shuting down TrackerTask");
         Tracker.this.stop();
       }
       else {
         ScoreFeed feed = new ScoreFeed(games);
         feed.setTimestamp(timestamp);
         feed.updateLiveScores();
       }
     }
   }
 }

