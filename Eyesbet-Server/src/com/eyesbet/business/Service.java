 package com.eyesbet.business;
 
 import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.Bets;
import com.eyesbet.business.domain.Game;
import com.eyesbet.business.domain.SortedGames;
import com.eyesbet.business.domain.User;
import com.eyesbet.dao.BetDao;
import com.eyesbet.dao.UserDao;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
 
 public class Service
 {
   private static UserDao dao = new UserDao();
   private static BetDao betDao = new BetDao();
 
   public static User login(String username, String password) throws Exception {
     return dao.login(username, password);
   }
 
   public void register(User user)
     throws Exception
   {
     dao.register(user);
   }
 
   public boolean updateGameScores(Game game)
     throws Exception
   {
     return betDao.updateGameScores(game);
   }
 
   public void saveGameResult(Set<Game> games)
     throws Exception
   {
     betDao.saveGameResult(games);
   }
 
   public void removeBet(int userId, int betId)
     throws Exception
   {
     betDao.removeBet(userId, betId);
   }
 
   public SortedGames sortByGameStartTime(Bets bets)
   {
     List<Bet> list = bets.getBets();
 
     SortedGames sgames = new SortedGames();
     List<Game> gamelist = null;
     Iterator<Game> localIterator2;
			Iterator<Bet> localIterator1 = list.iterator();
     while (localIterator1.hasNext())
     {
       Bet bet = (Bet)localIterator1.next();
       gamelist = bet.getGames();
       localIterator2 = gamelist.iterator();
			   while (localIterator2.hasNext()) {
				Game g = (Game)localIterator2.next();
 
       sgames.addGame(g, bet.getName());
     }
			}
 
     sgames.sortByUSDate();
 
     return sgames;
   }
   
   
   public void changeBetName(int betId, String name) throws Exception {
	   
	   betDao.changeBetName(betId, name);
	   
   }
   
   
   public boolean validateResetPassword(String username, String email) throws Exception {
	   
	   
	   return dao.validateResetPassword(username, email);
	   
   }
   
   public void resetUserPassword(String username, String email, String password	) throws Exception {
	   
	   dao.resetPassword(username, email, password);
   }
   
   
   public boolean mergeBets(Bet obet, Bet nbet) throws Exception {
	   
	   if (obet == null || nbet == null) {
		   
		   throw new Exception("Unable to merge bets");
	 
	   }
		   
		   List<Game> newList = nbet.getGames();
		   List<Game> olist = obet.getGames();
		   int index = 0;
		   for (Game game: newList) {
			   
			   if ((index = olist.indexOf(game)) >= 0) {				   
				   olist.get(index).updateBet(game.getBet());
				   
			   } else {
				   
				   olist.add(game);
			   }
			   
		   }
		   
		   
		   boolean moneyline = false;
		   boolean points = false;
		   for (Game game: olist) {
			   
			   if (game.getBet().isMoneyline()) {
				    moneyline = true;
				   
			   } else {
				   
				   points = true;
				   
			   }
			   
		   }
		   
		   
		   if (points == true && moneyline == true) {
			   
			   return false;
		   }
	
	   
	   return true;
   }
   
 }


