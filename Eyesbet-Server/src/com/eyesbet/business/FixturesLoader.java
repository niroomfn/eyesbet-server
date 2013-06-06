/*     */ package com.eyesbet.business;
/*     */ 
/*     */ import com.eyesbet.app.thread.SaxFeedThread;
/*     */ import com.eyesbet.app.thread.ThreadPoolManager;
		  import com.eyesbet.business.domain.Fixture;
/*     */ import com.eyesbet.business.domain.Fixtures;
/*     */ import com.eyesbet.business.domain.Params;
/*     */ import com.eyesbet.business.domain.RequestBuilder;
/*     */ import com.eyesbet.util.DateTime;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.Executors;
/*     */ 
/*     */ public class FixturesLoader
/*     */ {
/*  27 */   private List<Callable<Object>> feedTasks = new ArrayList<Callable<Object>>();
/*     */   private String timezoneId;
/*     */ 
/*     */   public FixturesLoader(String timezoneId)
/*     */   {
/*  32 */     this.timezoneId = timezoneId;
/*     */   }
/*     */ 
/*     */   public Fixtures load() throws Exception {
/*  36 */     Fixtures.Leagues[] leagues = Fixtures.Leagues.values();
/*  37 */     Fixtures fixtures = Fixtures.getInstance();
/*  38 */     Calendar cal = null;
/*     */ 
/*  40 */     Params params = new Params(this.timezoneId);
/*  41 */     params.setGames("fixtures");
/*  42 */     String url = null;
/*     */ 
/*  44 */     Set<Fixture> list = null;
/*  45 */     SaxFeedThread thread = null;
/*  46 */     for (Fixtures.Leagues league : leagues) {
/*  47 */       list = Collections.synchronizedSet(new TreeSet<Fixture>());
/*  48 */       cal = Calendar.getInstance();
/*     */ 
/*  50 */       params.setDate(DateTime.convertDateToFeedDate(cal.getTime()));
/*     */ 
/*  52 */       url = new String(RequestBuilder.buildRequest(league, params));
/*  53 */       thread = new SaxFeedThread(list, url, league);
/*  54 */       this.feedTasks.add(Executors.callable(thread));
/*  55 */       for (int i = 1; i <= 3; i++)
/*     */       {
/*  57 */         cal.add(5, 1);
/*  58 */         params.setDate(DateTime.convertDateToFeedDate(cal.getTime()));
/*  59 */         thread = new SaxFeedThread(list, new String(RequestBuilder.buildRequest(league, params)), league);
/*  60 */         this.feedTasks.add(Executors.callable(thread));
/*     */       }
/*     */ 
/*  75 */       ThreadPoolManager pool = ThreadPoolManager.getInstance();
/*  76 */       pool.executePDFConverterTasks(this.feedTasks);
/*     */ 
/*  78 */       fixtures.setFixtures(list, league);
/*     */     }
/*     */ 
/*  84 */     fixtures.setDateLoaded(new Date());
/*     */ 
/*  88 */     return fixtures;
/*     */   }
/*     */ 
/*     */  
/*     */ }
