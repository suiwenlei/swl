/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.leidengyun.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.SchedulerException;

import com.leidengyun.model.ScheduleJob;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.Service;



public interface ScheduleJobService  extends Service<ScheduleJob, Integer> {
	
	
	public List<ScheduleJob> findList();
	
	
	public Pagination<ScheduleJob> findPaginationByName(String jobName, Pagination<ScheduleJob> p);
	
	public void enable(Boolean isEnable, List<Integer> idList);
	
	public List<ScheduleJob> findListById(List<Integer> idList);
	
	public void deleteById(List<Integer> idList);

	/**
	 * 更改任务 cron表达式
	 * 
	 * @throws SchedulerException
	 */
	public void updateCron(Integer jobId, String cron) throws SchedulerException;

	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void addJob(ScheduleJob job) throws SchedulerException; 

	@PostConstruct
	//public void init() throws Exception;

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<ScheduleJob> getAllJob() throws SchedulerException;

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<ScheduleJob> getRunningJob() throws SchedulerException;

	/**
	 * 暂停一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException;

	/**
	 * 恢复一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException;

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException;


	/**
	 * 立即执行job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException;

	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException;


	/**
	 * 
	 * @todo 验证cron表达式是否有效
	 * @author huiyunfei
	 * @date 2016-9-20 上午10:02:11
	 * @return
	 */
	public Map validateJob(ScheduleJob scheduleJob);
}