 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.BetType;
import com.eyesbet.business.domain.Bets;
import com.eyesbet.business.domain.Fixtures.Leagues;
import com.eyesbet.business.domain.Game;
import com.eyesbet.business.domain.GameBet;
import com.eyesbet.business.domain.Team;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
 
 public class CreateBetCommand extends MobileCommand
 {
	 
	 private boolean editBet = false;
	 
   public CreateBetCommand(HttpServletRequest request)
   {
     super(request);
   }
 
   public String execute() throws Exception
   {
     String cmd = this.request.getParameter("cmd");
 	 
     if (cmd == null)
     {
       String[] games = this.request.getParameter("games").split(",");
       
       
       Bet bet = null;

       if (games.length > 1)
         bet = new Bet(BetType.parlay, 0);
       else if (games.length == 1) {
         bet = new Bet(BetType.straightWages, 0);
       }
       
       bet.setTimezone(request.getParameter("timezone"));
       Game game = null;
       String[] teams = null;
       for (String g : games)
       {
         teams = g.split("@");
         game = new Game(new Team(0, teams[1]), new Team(0, teams[0]), Leagues.valueOf(teams[4]));
         game.setId(Integer.parseInt(teams[3]));
         game.setSchedule(teams[2]);
         bet.addGame(game);
         
       
       }
       
       boolean lockbetType = false;
       String lockTypeTo = "";
       StringBuilder entry = new StringBuilder();
       
       if (request.getParameter("editBet") != null	) {
    	  Bets bets = (Bets) request.getSession().getAttribute("bets");
    	  
    	  int betId = Integer.parseInt(request.getParameter("betId"));
    	  
    	  Bet obet = bets.getBet(betId);
    	  List<Game> olist = obet.getGames();
    	  int size = olist.size();
    	  List<Game> list = bet.getGames();
    	  int count = 0;
    	  int index = -1;
    	  for (Game g: list) {
    		  
    		  if ((index = olist.indexOf(g)) >= 0) {
    			  count++;
    			  createGamebetEntry(entry, olist.get(index).getBet(), g.getGameId());
    		  }
    		  
    	  }
    	  
    	  if (count !=  size) {
    		  lockbetType = true;
    	  }
    	  
    	  if (obet.isMoneyline()) {
			  
			  lockTypeTo = BetType.moneyline.toString();
		  } else {
			  
			  lockTypeTo = BetType.points.toString();
		  }
    	   
       }
 
       saveBet(bet, lockbetType, lockTypeTo, entry );
       
       return "";
     } 
    

     else if ("getBet".equals(cmd))
     {
       return ((Bet)this.request.getSession().getAttribute("bet")).getXml();
     }
 
     return "Invalid command";
   }


   public void saveBet(Bet bet, boolean lockbetType, String lockTypeTo, StringBuilder entry) {
	   
	   buildXml(bet,  lockbetType, lockTypeTo, entry);
	   
       this.request.getSession().setAttribute("bet", bet);
 
   }
 
   private void buildXml(Bet bet, boolean lockbetType, 
		   String lockTypeTo, StringBuilder entry)
   {
     
	 this.xmlResponse.append("<bet type='").append(bet.getBetType())
	 .append("' lockType='"+lockbetType+"' lockTypeTo='"+lockTypeTo+"' >");
     List<Game> list = bet.getGames();
     for (Game game : list)
     {
      
       this.xmlResponse.append("<game a='").append(game.getAway().getName()).append("'");
       this.xmlResponse.append(" h='").append(game.getHome().getName()).append("'");
       this.xmlResponse.append(" id='").append(game.getGameId()).append("'  />");
       
     }
     if (entry.length() > 0) {
    	 
    	 xmlResponse.append("<entries>");
    	 xmlResponse.append(entry);
    	 xmlResponse.append("</entries>");
     }
     this.xmlResponse.append("</bet>");
     bet.setXml(this.xmlResponse.toString());
   }

public boolean isEditBet() {
	return editBet;
}

public void setEditBet(boolean editBet) {
	this.editBet = editBet;
}

 /**
  * 
  * @param sb
  * @param gamebet
  * @param gameId
  */
 private void createGamebetEntry(StringBuilder sb, GameBet gamebet, int gameId) {
	 
	 
	 
	 if (gamebet.isMoneyline()) {
	 
		 sb.append("<entry gameid='"+gameId+"' mn='"+gamebet.getMoneyline()+ "' /> ");
		
		 
	 } else {
		 
		 sb.append("<entry gameid='"+gameId+"' sp='"+gamebet.getSpreadPointAndSign()+"' spteam='"+gamebet.getSpreadPointTeam()+"' ");
		 
		 
		 if (gamebet.isOverUnder()) {
			 
			 if (gamebet.isOver()) {
				 
				 sb.append("oupoints='"+gamebet.getOverPoints()+"' ou='o' />");
			 } else {
				 
				 sb.append("oupoints='"+gamebet.getUnderPoints()+"' ou='u' />");
			 }
		 } else {
			 
			 sb.append("ou='' />");
		 }
		 
	 }
	 
	 
 }
   
   
   
 }

