package org.zp.tent.scheduler.demo;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.zp.tent.scheduler.demo.job.HelloJob;

public class SimpleTriggerDemo {

	public static void main(String[] args) {
		runAtSomeDate();
		runAtInterval();
	}

	static void runAtSomeDate() {
		try {
			// 通过schedulerFactory获取一个调度器
			SchedulerFactory schedulerfactory = new StdSchedulerFactory();

			// 通过schedulerFactory获取一个调度器
			Scheduler scheduler = schedulerfactory.getScheduler();

			// 创建jobDetail实例，绑定Job实现类
			JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("helloJob", "jobGroup1").build();

			// 定义调度触发规则，计划在2016-07-06 15:13:50执行任务
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "triggerGroup1")
					.startAt(DateBuilder.dateOf(15, 13, 50, 6, 7, 2016)).build();

			// 把作业和触发器注册到任务调度中
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动调度
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void runAtInterval() {
		try {
			// 通过schedulerFactory获取一个调度器
			SchedulerFactory schedulerfactory = new StdSchedulerFactory();

			// 通过schedulerFactory获取一个调度器
			Scheduler scheduler = schedulerfactory.getScheduler();

			// 创建jobDetail实例，绑定Job实现类
			JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("helloJob", "jobGroup1").build();

			// 定义调度触发规则，每秒执行1次，重复10次
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "triggerGroup1").startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(10))
					.build();

			// 把作业和触发器注册到任务调度中
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动调度
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
