/*    */ package com.eyesbet.app.thread;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.concurrent.Callable;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.Future;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class ThreadPoolManager
/*    */ {
/* 14 */   private Logger logger = Logger.getLogger(ThreadPoolManager.class);
/* 15 */   private ExecutorService pool = Executors.newCachedThreadPool();
/* 16 */   private static ThreadPoolManager me = new ThreadPoolManager();
/*    */ 
/*    */   public static ThreadPoolManager getInstance()
/*    */   {
/* 25 */     return me;
/*    */   }
/*    */ 
/*    */   public List<Future<Object>> executePDFConverterTasks(List<Callable<Object>> tasks) throws Exception {
/* 29 */     return this.pool.invokeAll(tasks, 2L, TimeUnit.SECONDS);
/*    */   }
/*    */ 
/*    */   public void shutDownAll()
/*    */   {
/* 36 */     this.pool.shutdown();
/* 37 */     this.pool = null;
/* 38 */     this.logger.info("Thread pools shut down successfully");
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.app.thread.ThreadPoolManager
 * JD-Core Version:    0.6.2
 */