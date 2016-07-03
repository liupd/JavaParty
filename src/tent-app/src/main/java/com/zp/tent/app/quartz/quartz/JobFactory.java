package com.zp.tent.app.quartz.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zp.tent.app.quartz.model.ScheduleJob;
import com.zp.tent.app.quartz.vo.ScheduleJobVo;

@DisallowConcurrentExecution
public class JobFactory implements Job {

	/* 日志对象 */
	private static final Logger LOG = LoggerFactory.getLogger(JobFactory.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {

		LOG.info("JobFactory execute");

		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJobVo.JOB_PARAM_KEY);

		System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
