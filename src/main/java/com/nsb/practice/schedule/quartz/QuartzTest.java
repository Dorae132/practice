package com.nsb.practice.schedule.quartz;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz
 * @author Dorae
 *
 */
public class QuartzTest {

	public static void main(String[] args) throws Exception {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		Properties properties = propertiesFactoryBean.getObject();
		schedulerFactoryBean.setQuartzProperties(properties);
		schedulerFactoryBean.afterPropertiesSet();
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withDescription("this is a ram job")
				.withIdentity("ramJob", "jobGroup").build();
		CronTrigger trigger = TriggerBuilder.newTrigger().withDescription("").withIdentity("ramTrigger", "ramTriggerGroup")
			.startAt(new Date(System.currentTimeMillis() + 3000)).withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?").withMisfireHandlingInstructionDoNothing())
			.build();
//		scheduler.deleteJob(jobDetail.getKey());
//		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
		Thread.currentThread().sleep(10000000);
	}
}

