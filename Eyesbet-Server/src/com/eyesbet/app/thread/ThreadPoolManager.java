 package com.eyesbet.app.thread;
 
 import java.util.List;
 import java.util.concurrent.Callable;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.Future;
 import java.util.concurrent.TimeUnit;
 import org.apache.log4j.Logger;
 
 public class ThreadPoolManager
 {
   private Logger logger = Logger.getLogger(ThreadPoolManager.class);
   private ExecutorService pool = Executors.newCachedThreadPool();
   private static ThreadPoolManager me = new ThreadPoolManager();
 
   public static ThreadPoolManager getInstance()
   {
     return me;
   }
 
   public List<Future<Object>> executePDFConverterTasks(List<Callable<Object>> tasks) throws Exception {
     return this.pool.invokeAll(tasks, 2L, TimeUnit.SECONDS);
   }
 
   public void shutDownAll()
   {
     this.pool.shutdown();
     this.pool = null;
     this.logger.info("Thread pools shut down successfully");
   }
 }

