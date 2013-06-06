/*    */ package com.eyesbet.mobile.web.command;
/*    */ 
/*    */ import com.eyesbet.business.Service;
/*    */ import com.eyesbet.business.domain.User;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class LoginCommand extends MobileCommand
/*    */ {
/*    */   public LoginCommand(HttpServletRequest request)
/*    */   {
/* 11 */     super(request);
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 17 */     User user = Service.login(this.request.getParameter("username"), this.request.getParameter("password"));
/*    */ 
/* 19 */     if (user != null) {
/* 20 */       this.request.getSession().setAttribute("user", user);
/* 21 */       this.xmlResponse.append("<user f='").append(user.getFirstName()).append("' l='")
/* 22 */         .append(user.getLastName()).append("' />");
/* 23 */       return this.xmlResponse.toString();
/*    */     }
/*    */ 
/* 26 */     return this.xmlResponse.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.LoginCommand
 * JD-Core Version:    0.6.2
 */