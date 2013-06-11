 package com.eyesbet.web.command;
 
 import com.eyesbet.business.Service;
 import com.eyesbet.business.domain.User;
 import javax.servlet.http.HttpServletRequest;
 
 public class RegisterCommand extends Command
 {
   public RegisterCommand(HttpServletRequest request)
   {
     super(request);
     this.view = "displayLeagues";
   }
 
   public String execute()
     throws Exception
   {
     User user = new User(0);
     user.setCity(this.request.getParameter("city"));
     user.setEmail(this.request.getParameter("email"));
     user.setFirstName(this.request.getParameter("firstName"));
     user.setLastName(this.request.getParameter("lastName"));
     user.setUsername(this.request.getParameter("username"));
     user.setPassword(this.request.getParameter("password"));
     Service service = new Service();
     service.register(user);
     this.request.setAttribute("login", "Please login with your username and password");
 
     return this.view;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.RegisterCommand
 * JD-Core Version:    0.6.2
 */