package org.zp.tent.scheduler.quartz.demo.job;

import java.util.Date;

public class Hello {
	public void sayHello() {
		System.out.println(String.format("Hello World! Time:%s", new Date()));
	}
}
