 package com.eyesbet.business.domain;
 
 import org.apache.log4j.Logger;
 
 public class RequestBuilder
 {
   private static Logger logger = Logger.getLogger(RequestBuilder.class);
   private static String basketball = "http://livescorefeed.net/data/basketball.php?";
   private static String amfootball = "http://livescorefeed.net/data/amfootball.php?";
   private static String hockey = "http://livescorefeed.net/data/hockey.php?";
   private static String baseball = "http://livescorefeed.net/data/baseball.php?";
 
   public static String buildRequest(Fixtures.Leagues league, Params params)
   {
     StringBuilder sb = new StringBuilder();
 
     if (league == Fixtures.Leagues.NBA)
     {
       sb.append(basketball);
     }
     else if (league == Fixtures.Leagues.NFL)
     {
       sb.append(amfootball);
     } else if (league == Fixtures.Leagues.NHL)
     {
       sb.append(hockey);
     } else if (league == Fixtures.Leagues.MLB) {
       sb.append(baseball);
     }
     sb.append("key=").append("2c9c8d0eb13013b0219ef54f79900fa66149edc6");
     sb.append("&").append("timezone_id=").append(params.getTimezone()).append("&");
     sb.append("country_id=").append(params.getCounteryId());
     sb.append("&date=").append(params.getDate()).append("&games=").append(params.getGames());
 
     logger.info("Bulding request for games: " + params.getGames() + " URL: " + sb);
 
     return sb.toString();
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.RequestBuilder
 * JD-Core Version:    0.6.2
 */