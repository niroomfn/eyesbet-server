/*    */ package com.eyesbet.business;
/*    */ 
/*    */ import com.eyesbet.business.domain.Game;
/*    */ import com.eyesbet.business.domain.LiveGame;
/*    */ import com.eyesbet.business.domain.TrackerGames;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class Tracker
/*    */ {
/* 16 */   private static Logger logger = Logger.getLogger(Tracker.class);
/* 17 */   private static Tracker me = new Tracker();
/*    */ 
/* 19 */   private TrackerGames games = new TrackerGames();
/*    */   private ScheduledExecutorService executor;
/* 21 */   private Random random = new Random();
/*    */ 
/*    */   public static Tracker getInstance()
/*    */   {
/* 25 */     return me;
/*    */   }
/*    */ 
/*    */   public void addGames(Set<Game> set, int userId)
/*    */   {
/* 35 */     for (Game game : set) {
/* 36 */       LiveGame livegame = game.toLiveGame();
/* 37 */       livegame.addUser(userId);
/*    */     }
/* 39 */     this.games.addGames(set, userId);
/*    */ 
/* 41 */     if ((this.executor == null) || (this.executor.isShutdown()))
/* 42 */       start();
/*    */   }
/*    */ 
/*    */   public void updateGame(Game game)
/*    */   {
/* 57 */     game.getAway().setScore(this.random.nextInt(game.getAway().getScore() + 10));
/* 58 */     game.getHome().setScore(this.random.nextInt(game.getHome().getScore() + 10));
/*    */   }
/*    */ 
/*    */   private void start() {
/* 62 */     logger.info("Starting task...");
/* 63 */     this.executor = Executors.newSingleThreadScheduledExecutor();
/* 64 */     this.executor.scheduleAtFixedRate(new TrackerTask(), -1L, 10L, TimeUnit.SECONDS);
/* 65 */     logger.info("Task started.");
/*    */   }
/*    */ 
/*    */   public void stop()
/*    */   {
/* 71 */     this.executor.shutdown();
/* 72 */     logger.info("Task Stoped");
/*    */   }
/*    */ 
/*    */   public void removeUser(int userId)
/*    */   {
/* 78 */     this.games.removeUser(userId);
/*    */   }
/*    */ 
/*    */   public class TrackerTask implements Runnable {
/*    */     public TrackerTask() {
/*    */     }
/*    */ 
/*    */     public void run() {
/* 86 */       Tracker.logger.info("Tracking liveGames: ");
/*    */ 
/* 89 */       if (Tracker.this.games.isEmpty()) {
/* 90 */         Tracker.logger.info("No liveGames found to track. shuting down TrackerTask");
/* 91 */         Tracker.this.executor.shutdown();
/* 92 */         Tracker.this.executor = null;
/*    */       }
/*    */       else {
/* 95 */         ScoreFeed feed = new ScoreFeed(Tracker.this.games);
/* 96 */         feed.updateLiveScores();
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.Tracker
 * JD-Core Version:    0.6.2
 */