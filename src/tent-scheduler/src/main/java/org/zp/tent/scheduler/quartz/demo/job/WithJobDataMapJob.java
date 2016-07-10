package org.zp.tent.scheduler.quartz.demo.job;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.zp.tent.scheduler.quartz.demo.dto.StudentDTO;

/**
 * @Title WithJobDataMapJob
 * @Description 测试JobDataMap
 * @Author zhangpeng0913
 * @Date 2016年7月7日
 */
@SuppressWarnings("unchecked")
public class WithJobDataMapJob implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 基本信息
		JobKey jobKey = context.getJobDetail().getKey();
		TriggerKey triggerKey = context.getTrigger().getKey();

		// 获取JobDataMap的方式：如果是基本类型，JobDataMap提供了多种get方法；如果是引用类型，可以直接get，然后进行强制转换
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		StudentDTO studentDTO = (StudentDTO) dataMap.get("student");
		List<String> interests = (List<String>) dataMap.get("interests");
		String word = dataMap.getString("word");

		System.out.println(String.format("[JobKey:%s][TriggerKey:%s] of DumbJob print info:", jobKey, triggerKey));
		System.out.println(String.format("[Student]name:%s, age:%d, sex:%s", studentDTO.getName(), studentDTO.getAge(),
				studentDTO.getSex()));
		StringBuilder interestsStr = new StringBuilder();
		for (String item : interests) {
			interestsStr.append(item + " ");
		}
		System.out.println("His interests ars: " + interestsStr.toString());
		System.out.println("He want to say: " + word);
		System.out.println("===================================");
	}
}