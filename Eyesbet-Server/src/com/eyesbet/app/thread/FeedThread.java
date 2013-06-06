/*    */ package com.eyesbet.app.thread;
/*    */ 
/*    */ import com.eyesbet.business.domain.Fixture;
import com.eyesbet.business.domain.Fixtures;
import com.eyesbet.business.domain.Fixtures.Leagues;

/*    */ import java.io.InputStream;
/*    */ import java.io.StringWriter;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class FeedThread
/*    */   implements Runnable
/*    */ {
/* 20 */   private static Logger logger = Logger.getLogger(FeedThread.class);
/*    */   protected String url;
/*    */   protected Leagues league;
/*    */   protected Set<Fixture> list;
/*    */ 
/*    */   public FeedThread(Set<Fixture> list, String url, Fixtures.Leagues league)
/*    */   {
/* 25 */     this.url = url;
/* 26 */     this.league = league;
/* 27 */     this.list = list;
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 33 */     HttpURLConnection connection = null;
/* 34 */     StringWriter sw = new StringWriter();
/* 35 */     JSONObject json = null;
/*    */ 
/* 37 */     logger.info("Calling feed url: " + this.url);
/*    */     try {
/* 39 */       URL http = new URL(this.url);
/* 40 */       connection = (HttpURLConnection)http.openConnection();
/* 41 */       connection.connect();
/* 42 */       InputStream is = connection.getInputStream();
/*    */ 
/* 44 */       IOUtils.copy(is, sw);
/*    */ 
/* 47 */       json = new JSONObject(sw.toString());
/* 48 */       if (this.league == Fixtures.Leagues.NFL)
/* 49 */         logger.debug(sw.toString());
/* 50 */       buildFixtures(this.league, json);
/*    */     }
/*    */     catch (Exception e) {
/* 53 */       logger.error("", e);
/*    */     }
/*    */     finally
/*    */     {
/* 57 */       if (connection != null) connection.disconnect();
/*    */     }
/*    */   }
/*    */ 
/*    */   private void buildFixtures(Fixtures.Leagues league, JSONObject json)
/*    */   {
/* 67 */     if (json == null) {
/* 68 */       logger.info("Fixtures returned empty for league: " + league.toString());
/*    */     }
/*    */ 
/*    */     try
/*    */     {
/* 75 */       JSONObject tournament = json.getJSONObject("maininfo").getJSONObject(league.getLeagueId());
/* 76 */       JSONArray array = tournament.getJSONArray("games");
/*    */ 
/* 78 */       Fixture fixture = null;
/* 79 */       for (int i = 0; i < array.length(); i++)
/*    */       {
/* 81 */         fixture = new Fixture();
/* 82 */         fixture.setHome(array.getJSONObject(i).getString("host"));
/* 83 */         fixture.setAway(array.getJSONObject(i).getString("guest"));
/*    */ 
/* 86 */         this.list.add(fixture);
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 90 */       logger.error("Returned empty fixtures: " + league.toString());
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.app.thread.FeedThread
 * JD-Core Version:    0.6.2
 */