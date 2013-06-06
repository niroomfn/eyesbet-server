/*    */ package com.eyesbet.mobile.web.command;
/*    */ 
/*    */ import com.eyesbet.business.domain.Bet;
/*    */ import com.eyesbet.business.domain.BetType;
/*    */ import com.eyesbet.business.domain.Fixtures.Leagues;
/*    */ import com.eyesbet.business.domain.Game;
/*    */ import com.eyesbet.business.domain.Team;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class CreateBetCommand extends MobileCommand
/*    */ {
/*    */   public CreateBetCommand(HttpServletRequest request)
/*    */   {
/* 16 */     super(request);
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 23 */     String cmd = this.request.getParameter("cmd");
/*    */ 
/* 25 */     if (cmd == null)
/*    */     {
/* 28 */       String[] games = this.request.getParameter("games").split(",");
/* 29 */       String betType = null;
/* 30 */       if (games.length > 1)
/* 31 */         betType = BetType.parlay.toString();
/* 32 */       else if (games.length == 1) {
/* 33 */         betType = BetType.straightWages.toString();
/*    */       }
/* 35 */       Game game = null;
/* 36 */       Bet bet = new Bet(BetType.valueOf(betType), 0);
/* 37 */       String[] teams = null;
/* 38 */       for (String g : games)
/*    */       {
/* 40 */         teams = g.split("@");
/*    */ 
/* 42 */         game = new Game(new Team(0, teams[1]), new Team(0, teams[0]), Leagues.valueOf(teams[4]));
/* 43 */         game.setId(Integer.parseInt(teams[3]));
/* 44 */         game.setSchedule(teams[2]);
/* 45 */         bet.addGame(game);
/*    */       }
/*    */ 
/* 50 */       buildXml(bet);
/*    */ 
/* 52 */       this.request.getSession().setAttribute("bet", bet);
/*    */ 
/* 57 */       return "";
/*    */     }
/* 59 */     if ("getBet".equals(cmd))
/*    */     {
/* 61 */       return ((Bet)this.request.getSession().getAttribute("bet")).getXml();
/*    */     }
/*    */ 
/* 66 */     return "Invalid command";
/*    */   }
/*    */ 
/*    */   private void buildXml(Bet bet)
/*    */   {
/* 73 */     this.xmlResponse.append("<bet type='").append(bet.getBetType()).append("' >");
/* 74 */     List<Game> list = bet.getGames();
/* 75 */     for (Game game : list)
/*    */     {
/* 77 */       this.xmlResponse.append("<game a='").append(game.getAway().getName()).append("'");
/* 78 */       this.xmlResponse.append(" h='").append(game.getHome().getName()).append("'");
/* 79 */       this.xmlResponse.append(" id='").append(game.getGameId()).append("' />");
/*    */     }
/*    */ 
/* 82 */     this.xmlResponse.append("</bet>");
/* 83 */     bet.setXml(this.xmlResponse.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.CreateBetCommand
 * JD-Core Version:    0.6.2
 */