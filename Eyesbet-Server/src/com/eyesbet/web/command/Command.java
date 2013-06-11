 package com.eyesbet.web.command;
 
 import com.eyesbet.business.domain.BetType;
 import com.eyesbet.business.domain.User;
 import javax.servlet.http.HttpServletRequest;
 
 public abstract class Command
 {
   protected HttpServletRequest request;
   protected String view;
 
   public Command(HttpServletRequest request)
   {
     this.request = request;
   }
 
   public abstract String execute()
     throws Exception;
 
   protected String getGameMoneylineParameter(String game)
   {
     return this.request.getParameter(game + "_" + BetType.moneyline);
   }
 
   protected int getUserId()
   {
     User user = (User)this.request.getSession().getAttribute("user");
     if (user == null) {
       return 0;
     }
     return user.getId();
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.Command
 * JD-Core Version:    0.6.2
 */