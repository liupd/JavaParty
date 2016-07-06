package org.zp.tent.scheduler.demo;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.zp.tent.scheduler.demo.job.HelloJob;

/**
 * @Title HelloQuartz
 * @Description Quartz的Hello World实例
 * @Author zhangpeng0913
 * @Date 2016年7月6日
 */
public class HelloWorldDemo {
	public static void main(String[] args) {
		try {
			// 通过schedulerFactory获取一个调度器
			SchedulerFactory schedulerfactory = new StdSchedulerFactory();

			// 通过schedulerFactory获取一个调度器
			Scheduler scheduler = schedulerfactory.getScheduler();

			// 创建jobDetail实例，绑定Job实现类
			JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("helloJob", "jobGroup1").build();

			// 定义调度触发规则，本例中使用SimpleScheduleBuilder创建了一个5s执行一次的触发器
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "triggerGroup1").startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
					.build();

			// 把作业和触发器注册到任务调度中
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动调度
			scheduler.start();

			// 60s后关闭
			Thread.sleep(1000 * 30);
			scheduler.shutdown();
			System.out.println("调度任务结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
