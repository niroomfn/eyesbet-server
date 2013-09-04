 package com.eyesbet.business.domain;
 
 import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.eyesbet.business.Timestamp;
 
 public class MonitorBet
 {
   private Bet bet;
   private Set<Game> liveGames;
   private boolean hasNotStartedGame;
   private Timestamp timestamp;
   
   public MonitorBet(Bet bet)
   {
     this.bet = bet;
     this.hasNotStartedGame = bet.hasNotStartedGame();
   }
 
   public Set<Game> getLiveGames()
   {
     return this.liveGames;
   }
 
   public void setLiveGames(Set<Game> liveGames) {
     this.liveGames = liveGames;
     
   }
 
   public Bet getBet() {
     return this.bet;
   }
 
   public boolean isHasNotStartedGame() {
     return this.hasNotStartedGame;
   }
 
   public void setHasNotStatedGame(boolean hasNotStatedGame) {
     this.hasNotStartedGame = hasNotStatedGame;
   }
   
   
   public List<Game> synchronizeTimestamps(Timestamp timestamp) {
	   
	   List<Game> updatedGames = new ArrayList<Game>(liveGames.size());
	   String time = null;
	   for (Game game: liveGames) {
		   time = this.timestamp.getTimestamp(game.getGameId());
		   if (time == null || time.equals(timestamp.getTimestamp(game.getGameId())) == false) {
			   this.timestamp.update(game.getGameId(), timestamp.getTimestamp(game.getGameId()));
			   updatedGames.add(game);
		   
		   }
		   
		   
	   }
	   
	   return updatedGames;
   }
   
 }

