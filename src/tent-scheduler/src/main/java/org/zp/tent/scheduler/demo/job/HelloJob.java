package org.zp.tent.scheduler.demo.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(String.format("Hello World! Time:%s", new Date()));
	}
}