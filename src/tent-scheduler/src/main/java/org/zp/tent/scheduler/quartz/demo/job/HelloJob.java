package org.zp.tent.scheduler.quartz.demo.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * @Title HelloJob
 * @Description 实现Quartz的Job接口，成为一个作业类。打印Hello World
 * @Author zhangpeng0913
 * @Date 2016年7月6日
 */
public class HelloJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		TriggerKey triggerKey = context.getTrigger().getKey();
		String result = String.format("[JobKey:%s][TriggerKey:%s]Hello World! Time:%s", jobKey, triggerKey, new Date());
		System.out.println(result);
	}
}