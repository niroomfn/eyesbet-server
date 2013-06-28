 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.BetType;
import com.eyesbet.business.domain.Bets;
import com.eyesbet.business.domain.Fixtures.Leagues;
import com.eyesbet.business.domain.Game;
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
      // BetType betType = null;
       Bet bet = null;

       if (games.length > 1)
         bet = new Bet(BetType.parlay, 0);
       else if (games.length == 1) {
         bet = new Bet(BetType.straightWages, 0);
       }
       
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
       if (request.getParameter("editBet") != null	) {
    	  Bets bets = (Bets) request.getSession().getAttribute("bets");
    	  
    	  int betId = Integer.parseInt(request.getParameter("betId"));
    	  
    	  Bet obet = bets.getBet(betId);
    	  List<Game> olist = obet.getGames();
    	  int size = olist.size();
    	  List<Game> list = bet.getGames();
    	  int count = 0;
    	  for (Game g: list) {
    		  
    		  if (olist.indexOf(g) >= 0) {
    			  count++;
    			
    			  
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
 
       saveBet(bet, lockbetType, lockTypeTo );
 
       return "";
     } 
    

     else if ("getBet".equals(cmd))
     {
       return ((Bet)this.request.getSession().getAttribute("bet")).getXml();
     }
 
     return "Invalid command";
   }


   public void saveBet(Bet bet, boolean lockbetType, String lockTypeTo) {
	   
	   buildXml(bet,  lockbetType, lockTypeTo);
	   
       this.request.getSession().setAttribute("bet", bet);
 
   }
 
   private void buildXml(Bet bet, boolean lockbetType, String lockTypeTo)
   {
     
	 this.xmlResponse.append("<bet type='").append(bet.getBetType())
	 .append("' lockType='"+lockbetType+"' lockTypeTo='"+lockTypeTo+"' >");
     List<Game> list = bet.getGames();
     for (Game game : list)
     {
       this.xmlResponse.append("<game a='").append(game.getAway().getName()).append("'");
       this.xmlResponse.append(" h='").append(game.getHome().getName()).append("'");
       this.xmlResponse.append(" id='").append(game.getGameId()).append("' />");
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
   
   
   
 }

