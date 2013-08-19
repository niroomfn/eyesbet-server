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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
 
   public String execute() throws Exception {
     Bets bets = null;
     if (this.updateBetStatus) {
    	 
       UserDao dao = new UserDao();
       bets = dao.getUserBets(getUserId());
       TrackerGames trackGames = new TrackerGames();
       Service service = new Service();
       List<Game> games = null;
  
       Calendar cal = Calendar.getInstance();
       Date now = new Date();
       SimpleDateFormat dateFormat = new SimpleDateFormat();
             
       for (Bet bet: bets.getBets()){
    	   
    	   TimeZone timezone = TimeZones.valueOf(bet.getTimezone()).getTimeZone();
    	   cal.setTimeZone(timezone);
    	   trackGames.setTimezone(timezone);
    	   dateFormat.applyPattern(bet.getDateFormat());
           dateFormat.setTimeZone(timezone);
          
    	   	games = bet.getGames();
		    int number = -1;
    	   	for (Game game: games) {
   	   		 	game.setScheduleDate(dateFormat.parse(game.getSchedule()));
    	   		number = compareGameDate(game,now, cal);
		    	if ( number == 0 ) { 
		    		// could be a live game track the game to find out for sure and update it.

		    		 trackGames.addGame(game);
		    	} else if (number > 0) {
		    		    // future game
		    		
		    		game.setStatusType(StatusType.notstarted.toString());
		    		
		    	} else { // finished game
		    		 if (!service.updateGameScores(game)) {
			        	 trackGames.addGame(game);
		    		 }
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
       
       BetComputer.computeAllBetsStatus(bets);
	       
 
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
   
   
   /**
    * 
    * @param game
    * @param now
    * @param cal
    * @return
    * @throws Exception
    */
   private int compareGameDate(Game game, Date now, Calendar cal) throws Exception {
	   
	  Date gameDate = game.getScheduleDate();
	  if (gameDate.after(now)) {
		  
		  return 1;
	  } else {
		  
		  cal.setTime(gameDate);
		  cal.add(Calendar.MINUTE, game.getLeage().getEstimatedDurationMinutes());
		  if (now.after(cal.getTime())) {
			  return -1;
		  } else {
			  
			  return 0;
		  }
		
	  }
	
   }
   
   
  
   
 }

