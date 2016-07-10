package org.zp.tent.scheduler.quartz.demo.client;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.zp.tent.scheduler.quartz.demo.dto.StudentDTO;
import org.zp.tent.scheduler.quartz.demo.job.WithJobDataMapJob;

/**
 * @Title JobDataMapDemo
 * @Description 本类使用JobDataMap向作业传入指定参数
 * @Author zhangpeng0913
 * @Date 2016年7月6日
 */
public class JobDataMapDemo {
	public static void main(String[] args) {
		try {
			// 通过schedulerFactory获取一个调度器
			SchedulerFactory schedulerfactory = new StdSchedulerFactory();

			// 通过schedulerFactory获取一个调度器
			Scheduler scheduler = schedulerfactory.getScheduler();

			// 创建jobDetail实例，绑定Job实现类
			JobDetail jobDetail = JobBuilder.newJob(WithJobDataMapJob.class).withIdentity("myJob", "group1").build();

			// 使用JobDataMap填入想要携带的特殊信息。可以填入基本数据类型、字符串、集合，甚至是一个对象。填入方式很类似JSON
			StudentDTO studentDTO = new StudentDTO("Jack", 20, "male");
			List<String> interests = new ArrayList<String>();
			interests.add("dancing");
			interests.add("singing");
			interests.add("swimming");
			String word = "Hello World!";
			JobDataMap map = jobDetail.getJobDataMap();
			map.put("student", studentDTO);
			map.put("interests", interests);
			map.put("word", word);

			// 定义调度触发规则，本例中使用CronScheduleBuilder创建了一个5s执行一次的触发器
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "triggerGroup1").startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

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
