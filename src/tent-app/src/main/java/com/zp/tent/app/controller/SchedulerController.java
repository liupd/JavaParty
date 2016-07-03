package com.zp.tent.app.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zp.tent.app.scheduler.dto.SchedulerInfoDTO;
import com.zp.tent.common.constant.ErrorCodeEn;
import com.zp.tent.common.util.ResponseUtil;

@Controller
@RequestMapping(value = "/scheduler")
public class SchedulerController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Scheduler scheduler;

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ResponseUtil add(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup,
			@RequestParam("className") String className) {
		try {
			Class c = Class.forName(className);
			// 创建jobDetail实例，绑定Job实现类
			JobDetail jobDetail = JobBuilder.newJob(c).withIdentity(jobName, jobGroup).build();
			// 定义调度触发规则
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName + "Trigger", jobGroup + "TriggerGroup")
					.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).startNow().build();

			// 把作业和触发器注册到任务调度中
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动调度
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
			return new ResponseUtil(ErrorCodeEn.FAIL, null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return new ResponseUtil(ErrorCodeEn.FAIL, null);
		}

		return new ResponseUtil(ErrorCodeEn.OK, null);
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResponseUtil delete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
		log.info("Quartz delete");
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		if (null == jobKey) {
			return new ResponseUtil(ErrorCodeEn.QUARTZ_JOB_NOT_EXIST, null);
		}
		try {
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			return new ResponseUtil(ErrorCodeEn.QUARTZ_FAIL, null);
		}
		return new ResponseUtil(ErrorCodeEn.OK, null);
	}

	@ResponseBody
	@RequestMapping(value = "/execute", method = RequestMethod.GET)
	public ResponseUtil execute(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
		log.info("Quartz execute at once");
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		if (null == jobKey) {
			return new ResponseUtil(ErrorCodeEn.QUARTZ_JOB_NOT_EXIST, null);
		}
		try {
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			return new ResponseUtil(ErrorCodeEn.QUARTZ_FAIL, null);
		}
		return new ResponseUtil(ErrorCodeEn.OK, null);
	}

	@ResponseBody
	@RequestMapping(value = "/pause", method = RequestMethod.GET)
	public ResponseUtil pause(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
		log.info("Quartz pause");
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		if (null == jobKey) {
			return new ResponseUtil(ErrorCodeEn.QUARTZ_JOB_NOT_EXIST, null);
		}
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			return new ResponseUtil(ErrorCodeEn.QUARTZ_FAIL, null);
		}
		return new ResponseUtil(ErrorCodeEn.OK, null);
	}

	@RequestMapping(value = "/pause-all", method = RequestMethod.GET)
	public ResponseUtil pauseAll(HttpServletRequest request, HttpServletResponse response) {
		log.info("Quartz pause");
		try {
			scheduler.pauseAll();
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			return new ResponseUtil(ErrorCodeEn.QUARTZ_FAIL, null);
		}
		return new ResponseUtil(ErrorCodeEn.OK, null);
	}

	@ResponseBody
	@RequestMapping(value = "/resume", method = RequestMethod.GET)
	public ResponseUtil resume(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
		log.info("Quartz resume");
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		if (null == jobKey) {
			return new ResponseUtil(ErrorCodeEn.QUARTZ_JOB_NOT_EXIST, null);
		}
		try {
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			return new ResponseUtil(ErrorCodeEn.QUARTZ_FAIL, null);
		}
		return new ResponseUtil(ErrorCodeEn.OK, null);
	}

	@RequestMapping(value = "/resume-all", method = RequestMethod.GET)
	public ResponseUtil resumeAll(HttpServletRequest request, HttpServletResponse response) {
		log.info("Quartz resume-all");
		try {
			scheduler.pauseAll();
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			return new ResponseUtil(ErrorCodeEn.QUARTZ_FAIL, null);
		}
		return new ResponseUtil(ErrorCodeEn.OK, null);
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseUtil list(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "state", required = false, defaultValue = "ALL") String state)
			throws SchedulerException {
		log.info("Quartz list");
		JSONArray rows = new JSONArray();

		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				if ("ALL".equalsIgnoreCase(state) || triggerState.name().equalsIgnoreCase(state)) {
					// 组装SchedulerInfoDTO信息
					SchedulerInfoDTO info = new SchedulerInfoDTO();
					info.setJobName(jobKey.getName());
					info.setJobGroup(jobKey.getGroup());
					info.setDescription("触发器:" + trigger.getKey());
					info.setTriggerState(triggerState.name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						info.setCronExpression(cronExpression);
					}
					info.setStartTime(trigger.getStartTime());
					info.setEndTime(trigger.getEndTime());

					// 添加到JSONArray
					rows.add(JSONObject.toJSON(info));
				}
			}
		}

		JSONObject result = new JSONObject();
		result.put("total", rows.size());
		result.put("rows", rows);
		return new ResponseUtil(ErrorCodeEn.OK, result);
	}

	@ResponseBody
	@RequestMapping(value = "/running-list", method = RequestMethod.GET)
	public ResponseUtil runningList(HttpServletRequest request, HttpServletResponse response)
			throws SchedulerException {
		log.info("Quartz list");
		JSONArray rows = new JSONArray();

		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		for (JobExecutionContext executingJob : executingJobs) {
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();

			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());

			// 组装SchedulerInfoDTO信息
			SchedulerInfoDTO info = new SchedulerInfoDTO();
			info.setJobName(jobKey.getName());
			info.setJobGroup(jobKey.getGroup());
			info.setDescription("触发器:" + trigger.getKey());
			info.setTriggerState(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				info.setCronExpression(cronExpression);
			}
			info.setStartTime(trigger.getStartTime());
			info.setEndTime(trigger.getEndTime());

			// 添加到JSONArray
			rows.add(JSONObject.toJSON(info));
		}

		JSONObject result = new JSONObject();
		result.put("total", rows.size());
		result.put("rows", rows);
		return new ResponseUtil(ErrorCodeEn.OK, result);
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseUtil test(HttpServletRequest request, HttpServletResponse response) {
		String json = request.getParameter("json");
		if (StringUtils.isBlank(json)) {
			return new ResponseUtil(ErrorCodeEn.FAIL, null);
		}
		JSONObject input = JSONObject.parseObject(json);
		return new ResponseUtil(ErrorCodeEn.OK, null);
	}

	public JSONObject getRequestJson(HttpServletRequest request) {
		String json = request.getParameter("json");
		if (StringUtils.isBlank(json)) {
			return null;
		}
		return JSONObject.parseObject(json);
	}
}