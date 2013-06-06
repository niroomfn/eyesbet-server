/*    */ package com.eyesbet.business.domain;
/*    */ 
/*    */ import com.eyesbet.util.DateTime;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SortedGames
/*    */ {
/* 16 */   private Map<Integer, String> betNames = new HashMap<Integer,String>();
/*    */ 
/* 19 */   private List<Game> games = new ArrayList<Game>();
/*    */ 
/*    */   public void addGame(Game game, String betName)
/*    */   {
/* 27 */     if (game.isFinished())
/* 28 */       if (!this.games.contains(game))
/*    */       {
/* 30 */         this.games.add(game);
/* 31 */         this.betNames.put(Integer.valueOf(game.getGameId()), betName);
/*    */       }
/*    */       else
/*    */       {
/* 35 */         Game g = (Game)this.games.get(this.games.indexOf(game));
/* 36 */         String name = (String)this.betNames.get(Integer.valueOf(g.getGameId())) + ";" + betName;
/* 37 */         this.betNames.put(Integer.valueOf(g.getGameId()), name);
/*    */       }
/*    */   }
/*    */ 
/*    */   public void sortByUSDate()
/*    */   {
/* 48 */     Collections.sort(this.games, new SortByUSDate());
/*    */   }
/*    */ 
/*    */   public List<Game> getGames()
/*    */   {
/* 72 */     return this.games;
/*    */   }
/*    */ 
/*    */   public String getBetNames(int gameId)
/*    */   {
/* 79 */     return (String)this.betNames.get(Integer.valueOf(gameId));
/*    */   }
/*    */ 
/*    */   public static String getStartTime(Game game)
/*    */   {
/* 86 */     if (game.isLive())
/*    */     {
/* 88 */       return game.getStatusDesc();
/*    */     }
/*    */ 
/* 92 */     return game.getSchedule();
/*    */   }
/*    */ 
/*    */   public class SortByUSDate
/*    */     implements Comparator<Game>
/*    */   {
/*    */     public SortByUSDate()
/*    */     {
/*    */     }
/*    */ 
/*    */     public int compare(Game g1, Game g2)
/*    */     {
/* 58 */       Date date1 = DateTime.convertToUSDate(g1.getSchedule());
/* 59 */       Date date2 = DateTime.convertToUSDate(g2.getSchedule());
/*    */ 
/* 61 */       return date1.compareTo(date2);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.SortedGames
 * JD-Core Version:    0.6.2
 */