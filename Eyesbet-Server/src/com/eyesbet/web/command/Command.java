/*    */ package com.eyesbet.web.command;
/*    */ 
/*    */ import com.eyesbet.business.domain.BetType;
/*    */ import com.eyesbet.business.domain.User;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public abstract class Command
/*    */ {
/*    */   protected HttpServletRequest request;
/*    */   protected String view;
/*    */ 
/*    */   public Command(HttpServletRequest request)
/*    */   {
/* 15 */     this.request = request;
/*    */   }
/*    */ 
/*    */   public abstract String execute()
/*    */     throws Exception;
/*    */ 
/*    */   protected String getGameMoneylineParameter(String game)
/*    */   {
/* 24 */     return this.request.getParameter(game + "_" + BetType.moneyline);
/*    */   }
/*    */ 
/*    */   protected int getUserId()
/*    */   {
/* 30 */     User user = (User)this.request.getSession().getAttribute("user");
/* 31 */     if (user == null) {
/* 32 */       return 0;
/*    */     }
/* 34 */     return user.getId();
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.Command
 * JD-Core Version:    0.6.2
 */