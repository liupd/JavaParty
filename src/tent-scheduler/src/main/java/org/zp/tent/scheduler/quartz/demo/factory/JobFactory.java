package org.zp.tent.scheduler.quartz.demo.factory;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zp.tent.entity.mybatis.domain.SchedulerJob;

@DisallowConcurrentExecution
public class JobFactory implements Job {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("JobFactory execute");
		SchedulerJob scheduleJob = (SchedulerJob) context.getMergedJobDataMap().get("jobParams");
		System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
