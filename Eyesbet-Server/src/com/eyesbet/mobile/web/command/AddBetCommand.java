package com.eyesbet.mobile.web.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.BetType;
import com.eyesbet.business.domain.Game;
import com.eyesbet.business.domain.Team;
import com.eyesbet.business.domain.Fixtures.Leagues;

public class AddBetCommand extends MobileCommand {

	public AddBetCommand(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		
		
		
		int betId = Integer.parseInt(request.getParameter("betId"));
		
		
		Bet bet = getBet(betId);
		
		 String[] games = this.request.getParameter("games").split(",");
	      
	       Game game = null;
	       String[] teams = null;
	       boolean added = false;
	       String type = null;
	       String duplicateGame = null;
	       if (bet.isMoneyline()) {
	    	   type = BetType.moneyline.getShortName();
		       for (String g : games)
		       {
		         teams = g.split("@");
		 
		         game = new Game(new Team(0, teams[1]), new Team(0, teams[0]), Leagues.valueOf(teams[4]));
		         game.setId(Integer.parseInt(teams[3]));
		         game.setSchedule(teams[2]);

		        	 List<Game> list = bet.getGames();
		        	 if (list.contains(game)==false) { 
		        		 
		        		 bet.addGame(game);
		        		 bet.setBetType(BetType.parlay);
		        		 added = true;
		        	 } else {
		        		 duplicateGame = game.getAway().getName() + " @ " + game.getHome().getName();
		        		 
		        	 }
		         }
	       
	       } else {
	    	   type = BetType.points.getShortName();
	    	   for (String g : games)
		       {
		         teams = g.split("@");
		 
		         game = new Game(new Team(0, teams[1]), new Team(0, teams[0]), Leagues.valueOf(teams[4]));
		         game.setId(Integer.parseInt(teams[3]));
		         game.setSchedule(teams[2]);

		        	 List<Game> list = bet.getGames();
		        	 if (list.contains(game)==false) { 
		        		 
		        		 bet.addGame(game);
		        		 bet.setBetType(BetType.parlay);
		        		 added = true;
		        	 }  else {
		        		 
		        		 
		        		 
		        	 }
		         }
	    	   
	    	   
	    	   
	       }
	       
	         
	    
	     if (added == true) {
	    	
	    	CreateBetCommand command = new CreateBetCommand(request);
	    	command.saveBet(bet);
	    	return "<success />";
	    } else {
	    	
	    	return "<error type='' game='' bet='' />";
	    	
	    }
		
		
	}
	
	
	

}
