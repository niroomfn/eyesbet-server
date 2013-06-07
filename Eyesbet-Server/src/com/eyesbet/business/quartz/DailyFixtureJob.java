package com.eyesbet.business.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DailyFixtureJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		
		System.out.println("testing....");
		
		
	}

}
