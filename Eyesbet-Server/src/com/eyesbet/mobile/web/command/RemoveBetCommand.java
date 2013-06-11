 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.Service;
 import javax.servlet.http.HttpServletRequest;
 
 public class RemoveBetCommand extends MobileCommand
 {
   public RemoveBetCommand(HttpServletRequest request)
   {
     super(request);
   }
 
   public String execute()
     throws Exception
   {
     int betId = Integer.parseInt(this.request.getParameter("betId"));
 
     Service service = new Service();
     service.removeBet(getUserId(), betId);
 
     return "";
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.RemoveBetCommand
 * JD-Core Version:    0.6.2
 */