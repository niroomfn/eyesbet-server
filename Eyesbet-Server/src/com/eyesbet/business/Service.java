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
 }


