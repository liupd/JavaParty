package com.zp.tent.app.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zp.tent.common.util.StatisticsUtil;

public class MyContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		// 启动时，记录服务器启动时间
		StatisticsUtil.START_DATE = new Date();
	}

	public void contextDestroyed(ServletContextEvent event) {
		// 关闭时，将结果清除。也可以将结果保存到硬盘上。
		StatisticsUtil.START_DATE = null;
		StatisticsUtil.MAX_ONLINE_COUNT_DATE = null;
	}
}
