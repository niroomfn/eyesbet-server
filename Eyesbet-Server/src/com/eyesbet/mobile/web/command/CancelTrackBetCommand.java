/*    */ package com.eyesbet.mobile.web.command;
/*    */ 
/*    */ import com.eyesbet.business.Tracker;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class CancelTrackBetCommand extends MobileCommand
/*    */ {
/* 11 */   private static Logger logger = Logger.getLogger(CancelTrackBetCommand.class);
/*    */ 
/*    */   public CancelTrackBetCommand(HttpServletRequest request) {
/* 14 */     super(request);
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 20 */     logger.info("User cancled track bet");
/*    */ 
/* 22 */     Tracker monitor = Tracker.getInstance();
/* 23 */     monitor.removeUser(getUserId());
/*    */ 
/* 28 */     return "";
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.CancelTrackBetCommand
 * JD-Core Version:    0.6.2
 */