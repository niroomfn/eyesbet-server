/*    */ package com.eyesbet.web.command;
/*    */ 
/*    */ import com.eyesbet.business.Service;
/*    */ import com.eyesbet.business.domain.User;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class RegisterCommand extends Command
/*    */ {
/*    */   public RegisterCommand(HttpServletRequest request)
/*    */   {
/* 12 */     super(request);
/* 13 */     this.view = "displayLeagues";
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 20 */     User user = new User(0);
/* 21 */     user.setCity(this.request.getParameter("city"));
/* 22 */     user.setEmail(this.request.getParameter("email"));
/* 23 */     user.setFirstName(this.request.getParameter("firstName"));
/* 24 */     user.setLastName(this.request.getParameter("lastName"));
/* 25 */     user.setUsername(this.request.getParameter("username"));
/* 26 */     user.setPassword(this.request.getParameter("password"));
/* 27 */     Service service = new Service();
/* 28 */     service.register(user);
/* 29 */     this.request.setAttribute("login", "Please login with your username and password");
/*    */ 
/* 32 */     return this.view;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.RegisterCommand
 * JD-Core Version:    0.6.2
 */