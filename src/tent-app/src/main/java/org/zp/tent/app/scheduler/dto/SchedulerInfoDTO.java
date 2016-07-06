package org.zp.tent.app.scheduler.dto;

import java.io.Serializable;
import java.util.Date;

public class SchedulerInfoDTO implements Serializable {
	private static final long serialVersionUID = 1174103779389275797L;
	private Long id;
	private String jobName;
	private String jobGroup;
	private String cronExpression;
	private String serviceName;
	private String description;
	private String triggerState;
	
	/** 自定义信息 */
	private Short triggerType;
	private Short invokeType;
	private Date startTime;
	private Date endTime;
	private Integer repeatCount;
	private Long repeatInterval;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	public Short getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(Short triggerType) {
		this.triggerType = triggerType;
	}

	public Short getInvokeType() {
		return invokeType;
	}

	public void setInvokeType(Short invokeType) {
		this.invokeType = invokeType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	public Long getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(Long repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
}
