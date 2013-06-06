/*    */ package com.eyesbet.test;
/*    */ 
/*    */ import com.eyesbet.app.xml.FeedSaxParser;
/*    */ import com.eyesbet.app.xml.FixtureHandler;
/*    */ import com.eyesbet.business.domain.Fixtures.Leagues;
/*    */ import org.junit.Test;
/*    */ 
/*    */ public class JsonTest
/*    */ {
/*    */   @SuppressWarnings("unused")
@Test
/*    */   public void test()
/*    */   {
/* 24 */     String url = "http://xml1.livescorefeed.net/data/basketball.php?key=2c9c8d0eb13013b0219ef54f79900fa66149edc6&timezone_id=21&country_id=20&games=fixtures&date=2013-01-22";
/*    */     try
/*    */     {
/* 27 */       FeedSaxParser localFeedSaxParser = new FeedSaxParser(url, new FixtureHandler(Leagues.NBA));
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 32 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.test.JsonTest
 * JD-Core Version:    0.6.2
 */