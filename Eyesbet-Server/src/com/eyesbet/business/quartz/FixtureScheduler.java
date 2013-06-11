package com.eyesbet.business.quartz;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;

public class FixtureScheduler {
	
 private Logger logger = Logger.getLogger(FixtureScheduler.class);
	
  public void schedule() throws Exception {
	  
	  
	  logger.info("Start of scheduling fixtures....");
	  
	  
	  SchedulerFactory sf = new StdSchedulerFactory();
	  Scheduler sched = sf.getScheduler();
	  JobDetail job = newJob(DailyFixtureJob.class)
		      .withIdentity("DailyFixtureJob", "Fixtures")
		      .build();
	  
	  
	 CronTrigger trigger = newTrigger()
			    .withIdentity("fixture", "Fixtures")
			    .withSchedule( dailyAtHourAndMinute(17, 12))
			    .forJob(job.getKey())
			    .build();
	  
	 
	 sched.scheduleJob(job, trigger);
	 sched.start();
	 
	 
	 logger.info("End of scheduling fixtures: success");
	 
  }
	
	

}
