/*     */ package com.eyesbet.business.domain;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.log4j.Logger;

import com.eyesbet.business.domain.Fixtures.Leagues;
/*     */ 
/*     */ @SuppressWarnings("serial")
public class TrackerGames extends HashMap<Leagues, Set<Game>>
/*     */ {
/*     */   private boolean hasFinishedGames;
/*  18 */   private static Logger logger = Logger.getLogger(TrackerGames.class);
/*     */ 
/*     */   public TrackerGames() {
/*  21 */     put(Leagues.NBA, new HashSet<Game>());
/*  22 */     put(Leagues.NFL, new HashSet<Game>());
/*  23 */     put(Leagues.MLB, new HashSet<Game>());
/*     */   }
/*     */ 
/*     */   public void removeBet(int userId)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void addGames(Set<Game> set, int userId)
/*     */   {
/*  41 */     Set<Game> games = getAllGames();
/*  42 */     LiveGame livegame = null;
/*     */ 
/*  44 */     for (Game g : games)
/*     */     {
/*  46 */       livegame = (LiveGame)g;
/*  47 */       if ((livegame.userGame(userId)) && (!set.contains(g)) && 
/*  48 */         (!livegame.hasOtherUsers(userId))) {
/*  49 */         logger.debug("User bet removed from the monitor");
/*  50 */         livegame.remove();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  56 */     for (Game g : set)
/*  57 */       if (!addGame(g.toLiveGame()))
/*  58 */         addUser(g, userId);
/*     */   }
/*     */ 
/*     */   public boolean addGame(Game game)
/*     */   {
/*  67 */     return (get(game.getLeage())).add(game);
/*     */   }
/*     */ 
/*     */   private void addUser(Game game, int userId)
/*     */   {
/*  73 */     Set<Game> set = get(game.league);
/*     */ 
/*  75 */     for (Game g : set)
/*     */     {
/*  77 */       if (g.equals(game))
/*     */       {
/*  79 */         ((LiveGame)g).addUser(userId);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Game getGame(Game game)
/*     */   {
/*  89 */     Set<Game> set = get(game.getLeage());
/*     */ 
/*  91 */     for (Game g : set) {
/*  92 */       if (g.equals(game))
/*     */       {
/*  94 */         return g;
/*     */       }
/*     */     }
/*     */ 
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */   public void removeUser(int userId)
/*     */   {
/* 105 */     Iterator<Leagues> iter = keySet().iterator();
/* 106 */     Iterator<Game> games = null;
/* 107 */     Game game = null;
/* 108 */     while ( iter.hasNext())
/*     */     {
/* 110 */       games = (get(iter.next())).iterator();
				
				while (games.hasNext()) {
/* 112 */       game = games.next();
/* 113 */       ((LiveGame)game).removeUser(userId);
/* 114 */       if (!((LiveGame)game).hasUsers()) {
/* 115 */         games.remove();
/* 116 */         logger.debug("User game reomved from tracker");
/*     */       }
/*     */     }
			 }
/*     */   }
/*     */ 
/*     */   public Set<Game> getAllGames()
/*     */   {
/* 128 */     Set<Leagues> keys = keySet();
/* 129 */     Set<Game> games = new HashSet<Game>();
/* 130 */     for (Fixtures.Leagues l : keys)
/*     */     {
/* 132 */       games.addAll(get(l));
/*     */     }
/*     */ 
/* 135 */     return games;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 140 */     Set<Leagues> keys = keySet();
/* 141 */     for (Leagues l : keys)
/*     */     {
/* 143 */       if ((get(l)).size() > 0) {
/* 144 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean hasFinishedGames()
/*     */   {
/* 153 */     return this.hasFinishedGames;
/*     */   }
/*     */ 
/*     */   public void setHasFinishedGames(boolean hasFinishedGames)
/*     */   {
/* 158 */     this.hasFinishedGames = hasFinishedGames;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.TrackerGames
 * JD-Core Version:    0.6.2
 */