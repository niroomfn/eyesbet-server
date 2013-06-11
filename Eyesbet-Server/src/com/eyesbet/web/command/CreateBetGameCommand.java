 package com.eyesbet.web.command;
 
 import com.eyesbet.business.domain.Bet;
 import com.eyesbet.business.domain.BetType;
 import com.eyesbet.business.domain.Fixtures.Leagues;
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.business.domain.Team;
 import javax.servlet.http.HttpServletRequest;
 
 public class CreateBetGameCommand extends Command
 {
   public CreateBetGameCommand(HttpServletRequest request)
   {
     super(request);
     this.view = "enterBet.jsp";
   }
 
   public String execute() throws Exception
   {
     String betType = this.request.getParameter("betType");
     String[] games = this.request.getParameterValues("games");
     Game game = null;
     Bet bet = new Bet(BetType.valueOf(betType), 0);
 
     for (String g : games)
     {
       String[] teams = g.split("@");
 
       game = new Game(new Team(0, teams[1]), new Team(0, teams[0]), Leagues.valueOf(teams[3]));
       game.setId(Integer.parseInt(teams[2]));
       game.setSchedule(this.request.getParameter(game.getGameId() + "_schedule"));
       bet.addGame(game);
     }
 
     this.request.getSession().setAttribute("bet", bet);
 
     return this.view;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.CreateBetGameCommand
 * JD-Core Version:    0.6.2
 */