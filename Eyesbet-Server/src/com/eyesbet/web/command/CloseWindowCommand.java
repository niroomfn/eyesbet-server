/*    */ package com.eyesbet.web.command;
/*    */ 
/*    */ import com.eyesbet.business.Tracker;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class CloseWindowCommand extends Command
/*    */ {
/*    */   public CloseWindowCommand(HttpServletRequest request)
/*    */   {
/* 11 */     super(request);
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 17 */     this.view = "betTrackerWindow.jsp";
/* 18 */     Tracker monitor = Tracker.getInstance();
/* 19 */     monitor.removeUser(getUserId());
/* 20 */     this.request.setAttribute("close", "close");
/* 21 */     return this.view;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.CloseWindowCommand
 * JD-Core Version:    0.6.2
 */