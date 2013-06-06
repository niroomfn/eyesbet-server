/*    */ package com.eyesbet.web.command;
/*    */ 
/*    */ import com.eyesbet.business.domain.Bets;
/*    */ import com.eyesbet.dao.BetDao;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class RemoveBetCommand extends Command
/*    */ {
/*    */   public RemoveBetCommand(HttpServletRequest request)
/*    */   {
/* 12 */     super(request);
/* 13 */     this.view = "displayBets.jsp";
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 19 */     int betId = Integer.parseInt(this.request.getParameter("betId"));
/* 20 */     BetDao dao = new BetDao();
/* 21 */     dao.removeBet(getUserId(), betId);
/* 22 */     Bets bets = (Bets)this.request.getSession().getAttribute("bets");
/* 23 */     bets.removeBet(betId);
/*    */ 
/* 25 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.RemoveBetCommand
 * JD-Core Version:    0.6.2
 */