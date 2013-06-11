 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.domain.Bet;
 import com.eyesbet.business.domain.BetType;
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.business.domain.GameBet;
 import com.eyesbet.dao.BetDao;
 import java.util.Iterator;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 
 public class SaveBetCommand extends MobileCommand
 {
   public SaveBetCommand(HttpServletRequest request)
   {
     super(request);
   }
 
   public String execute()
     throws Exception
   {
     String betType = this.request.getParameter("betType");
 
     Bet bet = (Bet)this.request.getSession().getAttribute("bet");
     String name = this.request.getParameter("betName");
     if (name == null) name = betType;
     bet.setName(name);
     List<Game> list = bet.getGames();
     Iterator<Game> localIterator;
    
			if (bet.getBetType() == BetType.straightWages) {
       GameBet gamebet = null;
       Game game = null;
       if (betType.equals(BetType.moneyline.toString())) {
         String userInput = null;
         for (localIterator = list.iterator(); localIterator.hasNext(); ) { 
					game = (Game)localIterator.next();
 
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
     else if (bet.getBetType() == BetType.parlay)
     {
       GameBet gamebet = null;
       Game game = null;
       if (betType.equals(BetType.moneyline.toString())) {
         String userInput = null;
         for (localIterator = list.iterator(); localIterator.hasNext(); ) { 
				game = (Game)localIterator.next();
 
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
     dao.saveBet(getUserId(), bet);
     this.xmlResponse.append("<success />");
     return this.xmlResponse.toString();
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
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.SaveBetCommand
 * JD-Core Version:    0.6.2
 */