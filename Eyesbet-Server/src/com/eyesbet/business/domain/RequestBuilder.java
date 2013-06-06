/*    */ package com.eyesbet.business.domain;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class RequestBuilder
/*    */ {
/*  8 */   private static Logger logger = Logger.getLogger(RequestBuilder.class);
/*  9 */   private static String basketball = "http://livescorefeed.net/data/basketball.php?";
/* 10 */   private static String amfootball = "http://livescorefeed.net/data/amfootball.php?";
/* 11 */   private static String hockey = "http://livescorefeed.net/data/hockey.php?";
/* 12 */   private static String baseball = "http://livescorefeed.net/data/baseball.php?";
/*    */ 
/*    */   public static String buildRequest(Fixtures.Leagues league, Params params)
/*    */   {
/* 17 */     StringBuilder sb = new StringBuilder();
/*    */ 
/* 19 */     if (league == Fixtures.Leagues.NBA)
/*    */     {
/* 21 */       sb.append(basketball);
/*    */     }
/* 24 */     else if (league == Fixtures.Leagues.NFL)
/*    */     {
/* 26 */       sb.append(amfootball);
/* 27 */     } else if (league == Fixtures.Leagues.NHL)
/*    */     {
/* 29 */       sb.append(hockey);
/* 30 */     } else if (league == Fixtures.Leagues.MLB) {
/* 31 */       sb.append(baseball);
/*    */     }
/* 33 */     sb.append("key=").append("2c9c8d0eb13013b0219ef54f79900fa66149edc6");
/* 34 */     sb.append("&").append("timezone_id=").append(params.getTimezone()).append("&");
/* 35 */     sb.append("country_id=").append(params.getCounteryId());
/* 36 */     sb.append("&date=").append(params.getDate()).append("&games=").append(params.getGames());
/*    */ 
/* 38 */     logger.info("Bulding request for games: " + params.getGames() + " URL: " + sb);
/*    */ 
/* 41 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.RequestBuilder
 * JD-Core Version:    0.6.2
 */