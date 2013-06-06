/*    */ package com.eyesbet.business;
/*    */ 
/*    */ import com.eyesbet.business.domain.Bet;
/*    */ import com.eyesbet.business.domain.Bets;
/*    */ import com.eyesbet.business.domain.Game;
/*    */ import com.eyesbet.business.domain.SortedGames;
/*    */ import com.eyesbet.business.domain.User;
/*    */ import com.eyesbet.dao.BetDao;
/*    */ import com.eyesbet.dao.UserDao;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class Service
/*    */ {
/* 17 */   private static UserDao dao = new UserDao();
/* 18 */   private static BetDao betDao = new BetDao();
/*    */ 
/*    */   public static User login(String username, String password) throws Exception {
/* 21 */     return dao.login(username, password);
/*    */   }
/*    */ 
/*    */   public void register(User user)
/*    */     throws Exception
/*    */   {
/* 29 */     dao.register(user);
/*    */   }
/*    */ 
/*    */   public boolean updateGameScores(Game game)
/*    */     throws Exception
/*    */   {
/* 35 */     return betDao.updateGameScores(game);
/*    */   }
/*    */ 
/*    */   public void saveGameResult(Set<Game> games)
/*    */     throws Exception
/*    */   {
/* 42 */     betDao.saveGameResult(games);
/*    */   }
/*    */ 
/*    */   public void removeBet(int userId, int betId)
/*    */     throws Exception
/*    */   {
/* 49 */     betDao.removeBet(userId, betId);
/*    */   }
/*    */ 
/*    */   public SortedGames sortByGameStartTime(Bets bets)
/*    */   {
/* 58 */     List<Bet> list = bets.getBets();
/*    */ 
/* 60 */     SortedGames sgames = new SortedGames();
/* 61 */     List<Game> gamelist = null;
/*    */     Iterator<Game> localIterator2;
			Iterator<Bet> localIterator1 = list.iterator();
/* 62 */     while (localIterator1.hasNext())
/*    */     {
/* 62 */       Bet bet = (Bet)localIterator1.next();
/* 63 */       gamelist = bet.getGames();
/* 64 */       localIterator2 = gamelist.iterator();
			   while (localIterator2.hasNext()) {
				Game g = (Game)localIterator2.next();
/*    */ 
/* 66 */       sgames.addGame(g, bet.getName());
/*    */     }
			}
/*    */ 
/* 72 */     sgames.sortByUSDate();
/*    */ 
/* 74 */     return sgames;
/*    */   }
/*    */ }


