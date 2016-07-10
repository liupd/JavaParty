package org.zp.tent.scheduler;

import org.zp.tent.scheduler.quartz.demo.dto.SchedulerJobDTO;
import org.zp.tent.scheduler.quartz.demo.job.HelloJob;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		SchedulerJobDTO schedulerJobDTO = new SchedulerJobDTO();
		schedulerJobDTO.setJobName("hello");
		schedulerJobDTO.setJobGroup("test");
		schedulerJobDTO.setJobClass(HelloJob.class.getName());
		schedulerJobDTO.setCronExpression("0/5 * * * * ?");
		schedulerJobDTO.setJobStatus("NORMAL");
	}
}
