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
	 boolean valid = false;
     User user = Service.login(this.request.getParameter("username"), this.request.getParameter("password"));
 
     if (user != null) {
    	 valid = true;
         this.request.getSession().setAttribute("user", user);

     }
     
     this.xmlResponse.append("<user valid='"+valid+"' />");
 
     return this.xmlResponse.toString();
   }
 }

