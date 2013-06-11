 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.Service;
 import com.eyesbet.business.domain.User;
 import javax.servlet.http.HttpServletRequest;
 
 public class RegisterCommand extends MobileCommand
 {
   public RegisterCommand(HttpServletRequest request)
   {
     super(request);
   }
 
   public String execute()
     throws Exception
   {
     User user = new User(0);
     user.setCity("");
     user.setEmail(this.request.getParameter("email"));
     user.setFirstName(this.request.getParameter("firstName"));
     user.setLastName(this.request.getParameter("lastName"));
     user.setUsername(this.request.getParameter("username"));
     user.setPassword(this.request.getParameter("password"));
     Service service = new Service();
     service.register(user);
 
     this.xmlResponse.append("success");
 
     return this.xmlResponse.toString();
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.RegisterCommand
 * JD-Core Version:    0.6.2
 */