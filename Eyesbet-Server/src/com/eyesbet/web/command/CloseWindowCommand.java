 package com.eyesbet.web.command;
 
 import com.eyesbet.business.Tracker;
 import javax.servlet.http.HttpServletRequest;
 
 public class CloseWindowCommand extends Command
 {
   public CloseWindowCommand(HttpServletRequest request)
   {
     super(request);
   }
 
   public String execute()
     throws Exception
   {
     this.view = "betTrackerWindow.jsp";
     Tracker monitor = Tracker.getInstance();
     monitor.removeUser(getUserId());
     this.request.setAttribute("close", "close");
     return this.view;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.CloseWindowCommand
 * JD-Core Version:    0.6.2
 */