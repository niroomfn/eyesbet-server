 package com.eyesbet.web.command;
 
 import com.eyesbet.business.domain.Bets;
 import com.eyesbet.dao.BetDao;
 import javax.servlet.http.HttpServletRequest;
 
 public class RemoveBetCommand extends Command
 {
   public RemoveBetCommand(HttpServletRequest request)
   {
     super(request);
     this.view = "displayBets.jsp";
   }
 
   public String execute()
     throws Exception
   {
     int betId = Integer.parseInt(this.request.getParameter("betId"));
     BetDao dao = new BetDao();
     dao.removeBet(getUserId(), betId);
     Bets bets = (Bets)this.request.getSession().getAttribute("bets");
     bets.removeBet(betId);
 
     return null;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.RemoveBetCommand
 * JD-Core Version:    0.6.2
 */