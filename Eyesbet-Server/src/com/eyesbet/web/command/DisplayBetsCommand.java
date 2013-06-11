 package com.eyesbet.web.command;
 
 import com.eyesbet.business.BetComputer;
 import com.eyesbet.business.ScoreFeed;
 import com.eyesbet.business.Service;
 import com.eyesbet.business.domain.Bet;
 import com.eyesbet.business.domain.Bets;
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.business.domain.TrackerGames;
 import com.eyesbet.dao.UserDao;
 import java.util.Iterator;
 import javax.servlet.http.HttpServletRequest;
 
 public class DisplayBetsCommand extends Command
 {
   private boolean updateBetStatus = true;
 
   public DisplayBetsCommand(HttpServletRequest request) {
     super(request);
     this.view = "displayBets.jsp";
   }
 
   public String execute()
     throws Exception
   {
     Bets bets = null;
     if (this.updateBetStatus) {
       UserDao dao = new UserDao();
       bets = dao.getUserBets(getUserId());
       TrackerGames trackGames = new TrackerGames();
       Service service = new Service();
       Iterator<Game> localIterator2;
       for (Iterator<Bet> localIterator1 = bets.getBets().iterator(); localIterator1.hasNext();)
       {
         Bet bet = localIterator1.next();
 
         localIterator2 = bet.getGames().iterator();
				  while (localIterator2.hasNext()) {
					  Game game = localIterator2.next();
         if (!service.updateGameScores(game))
           trackGames.addGame(game);
         else {
           trackGames.setHasFinishedGames(true);
         }
       }
			  }
 
       if (!trackGames.isEmpty()) {
         ScoreFeed feed = new ScoreFeed(trackGames);
         feed.updateAllGamesScore();
         feed.saveFinishedGames();
       }
 
       for (Bet bet : bets.getBets())
       {
         for (Game game : bet.getGames())
         {
           if (game.getStatusType().equals("")) {
             Game g = trackGames.getGame(game);
             game.getHome().setScore(g.getHome().getScore());
             game.getAway().setScore(g.getAway().getScore());
             game.setStatusType(g.getStatusType());
           }
           if (!game.isLive())
           {
             BetComputer.computGameBet(game);
             if ((game.isFinished()) && (bet.isParlay()) && (game.getStatus() < 100)) {
               bet.setParlayLost(true);
               bet.setStatus(0);
             }
           }
           else
           {
             BetComputer.computeLiveGameBet(game);
           }
         }
 
         if (!bet.isParlayLost()) {
           BetComputer.computeBetStatus(bet);
         }
 
       }
 
       this.request.getSession().setAttribute("bets", bets);
     }
     else
     {
       bets = (Bets)this.request.getSession().getAttribute("bets");
     }
 
     return this.view;
   }
 
   public boolean isUpdateBetStatus() {
     return this.updateBetStatus;
   }
 
   public void setUpdateBetStatus(boolean updateBetStatus) {
     this.updateBetStatus = updateBetStatus;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.DisplayBetsCommand
 * JD-Core Version:    0.6.2
 */