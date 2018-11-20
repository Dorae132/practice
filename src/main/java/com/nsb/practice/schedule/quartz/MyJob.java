package com.nsb.practice.schedule.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public	class MyJob implements Job {

	private static Integer i = 0;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(i + "_" +new Date());
		i++;
	}
	
}
