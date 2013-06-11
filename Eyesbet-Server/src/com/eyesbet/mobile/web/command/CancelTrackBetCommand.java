 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.Tracker;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.log4j.Logger;
 
 public class CancelTrackBetCommand extends MobileCommand
 {
   private static Logger logger = Logger.getLogger(CancelTrackBetCommand.class);
 
   public CancelTrackBetCommand(HttpServletRequest request) {
     super(request);
   }
 
   public String execute()
     throws Exception
   {
     logger.info("User cancled track bet");
 
     Tracker monitor = Tracker.getInstance();
     monitor.removeUser(getUserId());
 
     return "";
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.CancelTrackBetCommand
 * JD-Core Version:    0.6.2
 */