/*    */ package com.eyesbet.web.command;
/*    */ 
/*    */ import com.eyesbet.business.Service;
/*    */ import com.eyesbet.business.domain.User;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class LoginCommand extends Command
/*    */ {
/*    */   public LoginCommand(HttpServletRequest request)
/*    */   {
/* 13 */     super(request);
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 20 */     User user = Service.login(this.request.getParameter("username"), this.request.getParameter("password"));
/* 21 */     if (user != null)
/*    */     {
/* 23 */       this.request.getSession().setAttribute("user", user);
/* 24 */       DisplayBetsCommand command = new DisplayBetsCommand(this.request);
/* 25 */       this.view = command.execute();
/*    */     }
/*    */     else {
/* 28 */       this.request.setAttribute("error", "");
/* 29 */       this.view = "createBetGame.jsp";
/*    */     }
/*    */ 
/* 34 */     return this.view;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.LoginCommand
 * JD-Core Version:    0.6.2
 */