 package com.eyesbet.web.command;
 
 import com.eyesbet.business.BetComputer;
import com.eyesbet.business.ScoreFeed;
import com.eyesbet.business.Service;
import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.Bets;
import com.eyesbet.business.domain.Fixtures.TimeZones;
import com.eyesbet.business.domain.Game;
import com.eyesbet.business.domain.Game.StatusType;
import com.eyesbet.business.domain.TrackerGames;
import com.eyesbet.dao.UserDao;
import com.eyesbet.util.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
 
 public class DisplayBetsCommand extends Command
 {
   private boolean updateBetStatus = true;
   private static Logger logger = Logger.getLogger(DisplayBetsCommand.class);
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
       List<Game> games = null;
       String pattern = DateTime.dateFormat;
       pattern = pattern.substring(0, pattern.indexOf(" hh"));
     
       SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
       String today = dateFormat.format(new Date());
       Date todayDate = dateFormat.parse(today);
       
       for (Bet bet: bets.getBets()){
    	   
    	   // compare todays date (exclude time) with each bets game schedule
    	   pattern = bet.getDateFormat();
    	   pattern = pattern.substring(0, pattern.indexOf(" hh"));
    	   dateFormat.applyPattern(pattern);
           dateFormat.setTimeZone(TimeZones.valueOf(bet.getTimezone()).getTimeZone());
            
    	   	games = bet.getGames();
		    int number = -1;
    	   	for (Game game: games) {
    	   		number = compareGameDate(game,todayDate,dateFormat);
    	   		
		    	if ( number == 0 ) { // todays game
		    		 trackGames.addGame(game);
		    	} else if (number > 0) {
		    		    // future game
		    		
		    		game.setStatusType(StatusType.notstarted.toString());
		    		
		    	} else { // finished game
		    		 if (!service.updateGameScores(game))
			        	 trackGames.addGame(game);
			         else {
			        	 trackGames.setHasFinishedGames(true);
			         }
		    	}
			        
		    	
		    }
		}
       
       logger.info("User live games: "+trackGames.getAllGames().size());
      
       if (!trackGames.isEmpty()) {
	         ScoreFeed feed = new ScoreFeed(trackGames);
	         feed.updateAllGamesScore();
	         feed.saveFinishedGames();
	       
       }
	       for (Bet bet : bets.getBets())
	       {
	         for (Game game : bet.getGames())
	         {
	          /* if (game.getStatusType().equals("")) {
	             Game g = trackGames.getGame(game);
	             game.getHome().setScore(g.getHome().getScore());
	             game.getAway().setScore(g.getAway().getScore());
	             game.setStatusType(g.getStatusType());
	           } */
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
   
   
   private int compareGameDate(Game game, Date date, SimpleDateFormat dateFormat) throws Exception {
	   
	  Date d = dateFormat.parse(game.getSchedule());
	  
	  return d.compareTo(date);
	   
   }
   
 }

