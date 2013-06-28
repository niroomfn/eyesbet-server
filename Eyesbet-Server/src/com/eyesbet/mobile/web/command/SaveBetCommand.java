 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.Service;
import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.BetType;
import com.eyesbet.business.domain.Game;
import com.eyesbet.business.domain.GameBet;
import com.eyesbet.dao.BetDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
 
 public class SaveBetCommand extends MobileCommand
 {
  
	 private boolean editMode = false;
	 public SaveBetCommand(HttpServletRequest request)
   {
     super(request);
     
     if (request.getParameter("editBet") != null) {
    	 editMode = true;
     }
   }
 
   public String execute() throws Exception
   {
     String betType = this.request.getParameter("betType");
 
     Bet bet = (Bet)this.request.getSession().getAttribute("bet");
     String name = this.request.getParameter("betName");
     bet.setName(name);
     List<Game> list = bet.getGames();
    
   if (bet.getBetType() == BetType.straightWages) {
       GameBet gamebet = null;
       if (betType.equals(BetType.moneyline.toString())) {
         String userInput = null;
         for (Game game: list ) { 
					
           userInput = getGameMoneylineParameter(""+game.getGameId());
           gamebet = new GameBet(0);
           gamebet.setMoneyline(userInput);
           game.setBet(gamebet);
           
         }
       }
       else if (betType.equals(BetType.points.toString()))
       {
         for (Game g : list) {
           gamebet = setupPointsBet(g);
           g.setBet(gamebet);
           
           if (gamebet.isOverUnder() && gamebet.isSpreadPoint()) {
        	   
        	   bet.setBetType(BetType.parlay);
           }
         }
       }
     }
     else if (bet.getBetType() == BetType.parlay)
     {
       GameBet gamebet = null;
       if (betType.equals(BetType.moneyline.toString())) {
         String userInput = null;
         for (Game game: list ) { 
				
           userInput = getGameMoneylineParameter(""+game.getGameId());
           gamebet = new GameBet(0);
           gamebet.setMoneyline(userInput);
           game.setBet(gamebet); }
       }
       else if (betType.equals(BetType.points.toString()))
       {
         for (Game g : list) {
           gamebet = setupPointsBet(g);
           g.setBet(gamebet);
         }
 
       }
 
     }
   	 
   	BetDao dao = new BetDao();
     
     if (editMode == false) {
	     dao.saveBet(getUserId(), bet);
	     return "<result value='success' edit='false' />";
     } else {
    	 Bet obet = getBet(Integer.parseInt(request.getParameter("betId")));
    	Service service = new Service();
    	boolean result = service.mergeBets(obet, bet);
    	
    	if (result == false) {
    		
    		return "<result value='invalid' edit='true' />";
    	}
    	refreshBet(obet);
	     dao.updateBet(obet);
    	 
    	 return "<result value='success' edit='true' />";
     }
     
   }
 
   private GameBet setupPointsBet(Game game)
   {
     GameBet gamebet = new GameBet(0);
     try {
       double p1 = Double.parseDouble(this.request.getParameter(game.getHome().getName() + "_spreadPoint"));
       gamebet.setSpreadPointTeam(game.getHome().getName());
       gamebet.setSpreadPoint(Double.toString(p1));
     }
     catch (NumberFormatException localNumberFormatException)
     {
     }
     try {
       double p2 = Double.parseDouble(this.request.getParameter(game.getAway().getName() + "_spreadPoint"));
       gamebet.setSpreadPointTeam(game.getAway().getName());
       gamebet.setSpreadPoint(Double.toString(p2));
     }
     catch (NumberFormatException localNumberFormatException1)
     {
     }
 
     if (gamebet.isSpreadPoint()) {
       System.out.println("spreadpointTeam: " + gamebet.getSpreadPointTeam());
       String value = this.request.getParameter(gamebet.getSpreadPointTeam() + "_fu");
 
       if ("-".equals(value))
       {
         gamebet.setSpreadPointFavorite(gamebet.getSpreadPointTeam());
       }
       else if (!gamebet.getSpreadPointTeam().equals(game.getHome().getName()))
         gamebet.setSpreadPointFavorite(game.getHome().getName());
       else {
         gamebet.setSpreadPointFavorite(game.getAway().getName());
       }
 
     }
 
     gamebet.setOverPoints(this.request.getParameter(game.getGameId() + "_over"));
     gamebet.setUnderPoints(this.request.getParameter(game.getGameId() + "_under"));
 
     return gamebet;
   }


	private void refreshBet(Bet bet) {
		
		List<Game> list = bet.getGames();
		if (list.size() == 1 ) {
			
			Game game = list.get(0);
			if (game.getBet().isMoneyline()) {
				bet.setBetType(BetType.straightWages);
			} else {
				
				if (game.getBet().isOverUnder() && game.getBet().isSpreadPoint()) {
					
					bet.setBetType(BetType.parlay);
				}
				
			}
			
			
		} else {
			
			bet.setBetType(BetType.parlay);
			
			
		}
		
	}
   
   
 }

