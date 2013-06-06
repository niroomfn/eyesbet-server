/*    */ package com.eyesbet.web.command;
/*    */ 
/*    */ import com.eyesbet.business.domain.Fixtures;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class DisplayLeaguesCommand extends Command
/*    */ {
/*    */   public DisplayLeaguesCommand(HttpServletRequest request)
/*    */   {
/* 11 */     super(request);
/* 12 */     this.view = "displayLeagues.jsp";
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 20 */     Fixtures fixtures = Fixtures.getInstance();
/*    */ 
/* 22 */     this.request.setAttribute("fixtures", fixtures.getAllFixtures());
/* 23 */     return this.view;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.DisplayLeaguesCommand
 * JD-Core Version:    0.6.2
 */