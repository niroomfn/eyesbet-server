 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.Service;
 import com.eyesbet.business.domain.User;
 import javax.servlet.http.HttpServletRequest;
 
 public class LoginCommand extends MobileCommand
 {
   public LoginCommand(HttpServletRequest request)
   {
     super(request);
   }
 
   public String execute()
     throws Exception
   {
     User user = Service.login(this.request.getParameter("username"), this.request.getParameter("password"));
 
     if (user != null) {
       this.request.getSession().setAttribute("user", user);
       this.xmlResponse.append("<user f='").append(user.getFirstName()).append("' l='")
         .append(user.getLastName()).append("' />");
       return this.xmlResponse.toString();
     }
 
     return this.xmlResponse.toString();
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.LoginCommand
 * JD-Core Version:    0.6.2
 */