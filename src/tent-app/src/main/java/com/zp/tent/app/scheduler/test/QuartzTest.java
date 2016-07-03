package com.zp.tent.app.scheduler.test;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.zp.tent.app.scheduler.job.HelloJob;

public class QuartzTest {
	public static void main(String[] args) {
		// 通过schedulerFactory获取一个调度器
		SchedulerFactory schedulerfactory = new StdSchedulerFactory();
		
		try {
			// 通过schedulerFactory获取一个调度器
			Scheduler scheduler = schedulerfactory.getScheduler();

			// 创建jobDetail实例，绑定Job实现类
			JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("helloJob", "jobGroup1").build();

			// 定义调度触发规则
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "triggerGroup1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).startNow().build();

			// 把作业和触发器注册到任务调度中
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动调度
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
