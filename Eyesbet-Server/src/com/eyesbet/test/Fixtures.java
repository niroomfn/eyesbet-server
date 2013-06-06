/*    */ package com.eyesbet.test;
/*    */ 
/*    */ import java.util.TimeZone;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import org.junit.Test;
/*    */ 
/*    */ public class Fixtures
/*    */ {
/*    */   @Test
/*    */   public void test()
/*    */   {
/* 22 */     TimeZone zone = TimeZone.getTimeZone("America/Los_Angeles");
/*    */ 
/* 26 */     System.out.println(TimeUnit.MILLISECONDS.toHours(zone.getDSTSavings()));
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.test.Fixtures
 * JD-Core Version:    0.6.2
 */