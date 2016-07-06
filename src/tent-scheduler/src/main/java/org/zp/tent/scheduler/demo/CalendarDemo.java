package org.zp.tent.scheduler.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;
import org.zp.tent.scheduler.demo.job.HelloJob;

public class CalendarDemo {
	public static void main(String[] args) {
		try {
			// 通过schedulerFactory获取一个调度器
			SchedulerFactory schedulerfactory = new StdSchedulerFactory();

			// 通过schedulerFactory获取一个调度器
			Scheduler scheduler = schedulerfactory.getScheduler();

			String dateString = "2016-07-06 16:40:30";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(dateString);

			HolidayCalendar cal = new HolidayCalendar();
			cal.addExcludedDate(date);

			scheduler.addCalendar("myHolidays", cal, false, true);

			// 创建jobDetail实例，绑定Job实现类
			JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("helloJob", "jobGroup1").build();

			// 定义调度触发规则，本例中使用SimpleScheduleBuilder创建了一个5s执行一次的触发器
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "triggerGroup1").startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
					.modifiedByCalendar("myHolidays").build();

			// 把作业和触发器注册到任务调度中
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动调度
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
