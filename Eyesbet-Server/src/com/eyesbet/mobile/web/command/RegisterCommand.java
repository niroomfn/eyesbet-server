/*    */ package com.eyesbet.mobile.web.command;
/*    */ 
/*    */ import com.eyesbet.business.Service;
/*    */ import com.eyesbet.business.domain.User;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class RegisterCommand extends MobileCommand
/*    */ {
/*    */   public RegisterCommand(HttpServletRequest request)
/*    */   {
/* 11 */     super(request);
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 19 */     User user = new User(0);
/* 20 */     user.setCity("");
/* 21 */     user.setEmail(this.request.getParameter("email"));
/* 22 */     user.setFirstName(this.request.getParameter("firstName"));
/* 23 */     user.setLastName(this.request.getParameter("lastName"));
/* 24 */     user.setUsername(this.request.getParameter("username"));
/* 25 */     user.setPassword(this.request.getParameter("password"));
/* 26 */     Service service = new Service();
/* 27 */     service.register(user);
/*    */ 
/* 29 */     this.xmlResponse.append("success");
/*    */ 
/* 31 */     return this.xmlResponse.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.RegisterCommand
 * JD-Core Version:    0.6.2
 */