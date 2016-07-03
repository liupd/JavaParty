package com.zp.tent.app.quartz.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByeJob implements Job {
	private static int count = 0;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(String.format("[%d]再见,告别时间：%s", ++count, new Date()));
		System.out.println("开始计数");
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(String.format("[%d]再见,计数：(%d)", count, i));
		}
	}

}