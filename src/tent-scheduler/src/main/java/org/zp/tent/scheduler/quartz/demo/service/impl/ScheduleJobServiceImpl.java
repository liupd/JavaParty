package org.zp.tent.scheduler.quartz.demo.service.impl;

import java.util.List;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zp.tent.scheduler.quartz.demo.dto.SchedulerJobDTO;
import org.zp.tent.scheduler.quartz.demo.service.ScheduleJobService;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	/** 调度工厂Bean */
	@Autowired
	private Scheduler scheduler;

	@Override
	public void initScheduleJob() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long insert(SchedulerJobDTO scheduleJobVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SchedulerJobDTO scheduleJobVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delUpdate(SchedulerJobDTO scheduleJobVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long scheduleJobId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runOnce(Long scheduleJobId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pauseJob(Long scheduleJobId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resumeJob(Long scheduleJobId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SchedulerJobDTO get(Long scheduleJobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SchedulerJobDTO> queryList(SchedulerJobDTO scheduleJobVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SchedulerJobDTO> queryExecutingJobList() {
		// TODO Auto-generated method stub
		return null;
	}

}
