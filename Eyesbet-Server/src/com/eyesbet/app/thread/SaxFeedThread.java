/*    */ package com.eyesbet.app.thread;
/*    */ 
/*    */ import com.eyesbet.app.xml.FeedSaxParser;
/*    */ import com.eyesbet.app.xml.FixtureHandler;
/*    */ import com.eyesbet.business.domain.Fixture;
		import com.eyesbet.business.domain.Fixtures;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class SaxFeedThread extends FeedThread
/*    */ {
/*    */   public SaxFeedThread(Set<Fixture> list, String url, Fixtures.Leagues league)
/*    */   {
/* 13 */     super(list, url, league);
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 22 */     FixtureHandler handler = new FixtureHandler(league);
/*    */     try
/*    */     {
/* 25 */       new FeedSaxParser(this.url, handler);
/*    */ 
/* 27 */       this.list.addAll(handler.getFixtures());
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 32 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.app.thread.SaxFeedThread
 * JD-Core Version:    0.6.2
 */