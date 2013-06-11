 package com.eyesbet.web.command;
 
 import com.eyesbet.business.Service;
 import com.eyesbet.business.domain.User;
 import javax.servlet.http.HttpServletRequest;
 
 public class LoginCommand extends Command
 {
   public LoginCommand(HttpServletRequest request)
   {
     super(request);
   }
 
   public String execute()
     throws Exception
   {
     User user = Service.login(this.request.getParameter("username"), this.request.getParameter("password"));
     if (user != null)
     {
       this.request.getSession().setAttribute("user", user);
       DisplayBetsCommand command = new DisplayBetsCommand(this.request);
       this.view = command.execute();
     }
     else {
       this.request.setAttribute("error", "");
       this.view = "createBetGame.jsp";
     }
 
     return this.view;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.LoginCommand
 * JD-Core Version:    0.6.2
 */