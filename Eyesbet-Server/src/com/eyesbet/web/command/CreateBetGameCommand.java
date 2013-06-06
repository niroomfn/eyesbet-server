/*    */ package com.eyesbet.web.command;
/*    */ 
/*    */ import com.eyesbet.business.domain.Bet;
/*    */ import com.eyesbet.business.domain.BetType;
/*    */ import com.eyesbet.business.domain.Fixtures.Leagues;
/*    */ import com.eyesbet.business.domain.Game;
/*    */ import com.eyesbet.business.domain.Team;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class CreateBetGameCommand extends Command
/*    */ {
/*    */   public CreateBetGameCommand(HttpServletRequest request)
/*    */   {
/* 16 */     super(request);
/* 17 */     this.view = "enterBet.jsp";
/*    */   }
/*    */ 
/*    */   public String execute() throws Exception
/*    */   {
/* 22 */     String betType = this.request.getParameter("betType");
/* 23 */     String[] games = this.request.getParameterValues("games");
/* 24 */     Game game = null;
/* 25 */     Bet bet = new Bet(BetType.valueOf(betType), 0);
/*    */ 
/* 27 */     for (String g : games)
/*    */     {
/* 29 */       String[] teams = g.split("@");
/*    */ 
/* 31 */       game = new Game(new Team(0, teams[1]), new Team(0, teams[0]), Leagues.valueOf(teams[3]));
/* 32 */       game.setId(Integer.parseInt(teams[2]));
/* 33 */       game.setSchedule(this.request.getParameter(game.getGameId() + "_schedule"));
/* 34 */       bet.addGame(game);
/*    */     }
/*    */ 
/* 41 */     this.request.getSession().setAttribute("bet", bet);
/*    */ 
/* 43 */     return this.view;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.CreateBetGameCommand
 * JD-Core Version:    0.6.2
 */