 package com.eyesbet.business;
 
 import com.eyesbet.app.thread.SaxFeedThread;
 import com.eyesbet.app.thread.ThreadPoolManager;
		  import com.eyesbet.business.domain.Fixture;
 import com.eyesbet.business.domain.Fixtures;
 import com.eyesbet.business.domain.Params;
 import com.eyesbet.business.domain.RequestBuilder;
 import com.eyesbet.util.DateTime;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Collections;
 import java.util.Date;
 import java.util.List;
 import java.util.Set;
 import java.util.TreeSet;
 import java.util.concurrent.Callable;
 import java.util.concurrent.Executors;
 
 public class FixturesLoader
 {
   private List<Callable<Object>> feedTasks = new ArrayList<Callable<Object>>();
   private String timezoneId;
 
   public FixturesLoader(String timezoneId)
   {
     this.timezoneId = timezoneId;
   }
 
   public Fixtures load() throws Exception {
     Fixtures.Leagues[] leagues = Fixtures.Leagues.values();
     Fixtures fixtures = Fixtures.getInstance();
     Calendar cal = null;
 
     Params params = new Params(this.timezoneId);
     params.setGames("fixtures");
     String url = null;
 
     Set<Fixture> list = null;
     SaxFeedThread thread = null;
     for (Fixtures.Leagues league : leagues) {
       list = Collections.synchronizedSet(new TreeSet<Fixture>());
       cal = Calendar.getInstance();
 
       params.setDate(DateTime.convertDateToFeedDate(cal.getTime()));
 
       url = new String(RequestBuilder.buildRequest(league, params));
       thread = new SaxFeedThread(list, url, league);
       this.feedTasks.add(Executors.callable(thread));
       for (int i = 1; i <= 3; i++)
       {
         cal.add(5, 1);
         params.setDate(DateTime.convertDateToFeedDate(cal.getTime()));
         thread = new SaxFeedThread(list, new String(RequestBuilder.buildRequest(league, params)), league);
         this.feedTasks.add(Executors.callable(thread));
       }
 
       ThreadPoolManager pool = ThreadPoolManager.getInstance();
       pool.executePDFConverterTasks(this.feedTasks);
 
       fixtures.setFixtures(list, league);
     }
 
     fixtures.setDateLoaded(new Date());
 
     return fixtures;
   }
 
  
 }
