/*    */ package com.eyesbet.mobile.web.command;
/*    */ 
/*    */ import com.eyesbet.business.Service;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class RemoveBetCommand extends MobileCommand
/*    */ {
/*    */   public RemoveBetCommand(HttpServletRequest request)
/*    */   {
/* 10 */     super(request);
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 16 */     int betId = Integer.parseInt(this.request.getParameter("betId"));
/*    */ 
/* 20 */     Service service = new Service();
/* 21 */     service.removeBet(getUserId(), betId);
/*    */ 
/* 24 */     return "";
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.RemoveBetCommand
 * JD-Core Version:    0.6.2
 */