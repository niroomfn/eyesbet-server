 package com.eyesbet.app.thread;
 
 import com.eyesbet.business.domain.Fixture;
import com.eyesbet.business.domain.Fixtures;
import com.eyesbet.business.domain.Fixtures.Leagues;

 import java.io.InputStream;
 import java.io.StringWriter;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.util.Set;
 import org.apache.commons.io.IOUtils;
 import org.apache.log4j.Logger;
 import org.json.JSONArray;
 import org.json.JSONObject;
 
 public class FeedThread
   implements Runnable
 {
   private static Logger logger = Logger.getLogger(FeedThread.class);
   protected String url;
   protected Leagues league;
   protected Set<Fixture> list;
 
   public FeedThread(Set<Fixture> list, String url, Fixtures.Leagues league)
   {
     this.url = url;
     this.league = league;
     this.list = list;
   }
 
   public void run()
   {
     HttpURLConnection connection = null;
     StringWriter sw = new StringWriter();
     JSONObject json = null;
 
     logger.info("Calling feed url: " + this.url);
     try {
       URL http = new URL(this.url);
       connection = (HttpURLConnection)http.openConnection();
       connection.connect();
       InputStream is = connection.getInputStream();
 
       IOUtils.copy(is, sw);
 
       json = new JSONObject(sw.toString());
       if (this.league == Fixtures.Leagues.NFL)
         logger.debug(sw.toString());
       buildFixtures(this.league, json);
     }
     catch (Exception e) {
       logger.error("", e);
     }
     finally
     {
       if (connection != null) connection.disconnect();
     }
   }
 
   private void buildFixtures(Fixtures.Leagues league, JSONObject json)
   {
     if (json == null) {
       logger.info("Fixtures returned empty for league: " + league.toString());
     }
 
     try
     {
       JSONObject tournament = json.getJSONObject("maininfo").getJSONObject(league.getLeagueId());
       JSONArray array = tournament.getJSONArray("games");
 
       Fixture fixture = null;
       for (int i = 0; i < array.length(); i++)
       {
         fixture = new Fixture();
         fixture.setHome(array.getJSONObject(i).getString("host"));
         fixture.setAway(array.getJSONObject(i).getString("guest"));
 
         this.list.add(fixture);
       }
     }
     catch (Exception e) {
       logger.error("Returned empty fixtures: " + league.toString());
     }
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.app.thread.FeedThread
 * JD-Core Version:    0.6.2
 */