 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.domain.Bet;
 import com.eyesbet.business.domain.BetType;
 import com.eyesbet.business.domain.Fixtures.Leagues;
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.business.domain.Team;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 
 public class CreateBetCommand extends MobileCommand
 {
   public CreateBetCommand(HttpServletRequest request)
   {
     super(request);
   }
 
   public String execute()
     throws Exception
   {
     String cmd = this.request.getParameter("cmd");
 	 
     if (cmd == null)
     {
       String[] games = this.request.getParameter("games").split(",");
       String betType = null;
       if (games.length > 1)
         betType = BetType.parlay.toString();
       else if (games.length == 1) {
         betType = BetType.straightWages.toString();
       }
       Game game = null;
       Bet bet = new Bet(BetType.valueOf(betType), 0);
       String[] teams = null;
       for (String g : games)
       {
         teams = g.split("@");
         game = new Game(new Team(0, teams[1]), new Team(0, teams[0]), Leagues.valueOf(teams[4]));
         game.setId(Integer.parseInt(teams[3]));
         game.setSchedule(teams[2]);
         bet.addGame(game);
       }
 
      saveBet(bet);
 
       return "";
     } 
    

     else if ("getBet".equals(cmd))
     {
       return ((Bet)this.request.getSession().getAttribute("bet")).getXml();
     }
 
     return "Invalid command";
   }


   public void saveBet(Bet bet) {
	   
	   buildXml(bet,"");
	   
       this.request.getSession().setAttribute("bet", bet);
 
   }
 
   private void buildXml(Bet bet, String betType)
   {
     this.xmlResponse.append("<bet type='").append(bet.getBetType()).append("' >");
     List<Game> list = bet.getGames();
     for (Game game : list)
     {
       this.xmlResponse.append("<game a='").append(game.getAway().getName()).append("'");
       this.xmlResponse.append(" h='").append(game.getHome().getName()).append("'");
       this.xmlResponse.append(" id='").append(game.getGameId()).append("' bt='"+betType+"' />");
     }
 
     this.xmlResponse.append("</bet>");
     bet.setXml(this.xmlResponse.toString());
   }
 }

