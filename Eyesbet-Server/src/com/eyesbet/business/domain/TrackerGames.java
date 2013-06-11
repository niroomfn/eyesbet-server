 package com.eyesbet.business.domain;
 
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.Set;
 import org.apache.log4j.Logger;

import com.eyesbet.business.domain.Fixtures.Leagues;
 
 @SuppressWarnings("serial")
public class TrackerGames extends HashMap<Leagues, Set<Game>>
 {
   private boolean hasFinishedGames;
   private static Logger logger = Logger.getLogger(TrackerGames.class);
 
   public TrackerGames() {
     put(Leagues.NBA, new HashSet<Game>());
     put(Leagues.NFL, new HashSet<Game>());
     put(Leagues.MLB, new HashSet<Game>());
   }
 
   public void removeBet(int userId)
   {
   }
 
   public void addGames(Set<Game> set, int userId)
   {
     Set<Game> games = getAllGames();
     LiveGame livegame = null;
 
     for (Game g : games)
     {
       livegame = (LiveGame)g;
       if ((livegame.userGame(userId)) && (!set.contains(g)) && 
         (!livegame.hasOtherUsers(userId))) {
         logger.debug("User bet removed from the monitor");
         livegame.remove();
       }
 
     }
 
     for (Game g : set)
       if (!addGame(g.toLiveGame()))
         addUser(g, userId);
   }
 
   public boolean addGame(Game game)
   {
     return (get(game.getLeage())).add(game);
   }
 
   private void addUser(Game game, int userId)
   {
     Set<Game> set = get(game.league);
 
     for (Game g : set)
     {
       if (g.equals(game))
       {
         ((LiveGame)g).addUser(userId);
       }
     }
   }
 
   public Game getGame(Game game)
   {
     Set<Game> set = get(game.getLeage());
 
     for (Game g : set) {
       if (g.equals(game))
       {
         return g;
       }
     }
 
     return null;
   }
 
   public void removeUser(int userId)
   {
     Iterator<Leagues> iter = keySet().iterator();
     Iterator<Game> games = null;
     Game game = null;
     while ( iter.hasNext())
     {
       games = (get(iter.next())).iterator();
				
				while (games.hasNext()) {
       game = games.next();
       ((LiveGame)game).removeUser(userId);
       if (!((LiveGame)game).hasUsers()) {
         games.remove();
         logger.debug("User game reomved from tracker");
       }
     }
			 }
   }
 
   public Set<Game> getAllGames()
   {
     Set<Leagues> keys = keySet();
     Set<Game> games = new HashSet<Game>();
     for (Fixtures.Leagues l : keys)
     {
       games.addAll(get(l));
     }
 
     return games;
   }
 
   public boolean isEmpty()
   {
     Set<Leagues> keys = keySet();
     for (Leagues l : keys)
     {
       if ((get(l)).size() > 0) {
         return false;
       }
     }
 
     return true;
   }
 
   public boolean hasFinishedGames()
   {
     return this.hasFinishedGames;
   }
 
   public void setHasFinishedGames(boolean hasFinishedGames)
   {
     this.hasFinishedGames = hasFinishedGames;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.TrackerGames
 * JD-Core Version:    0.6.2
 */