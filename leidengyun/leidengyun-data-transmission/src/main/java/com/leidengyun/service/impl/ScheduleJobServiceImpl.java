/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.leidengyun.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

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
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.leidengyun.common.ConfigServlet;
import com.leidengyun.common.SpringContextHolder;
import com.leidengyun.common.util.quartz.QuartzJobFactory;
import com.leidengyun.dao.ScheduleJobDao;
import com.leidengyun.model.ScheduleJob;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.mvc.util.SpringUtils;
import com.leidengyun.mvc.util.StringUtils;
import com.leidengyun.service.ScheduleJobService;

/**
 * 定时任务Service
 * @author huiyunfei
 * @version 2016-09-19
 */
@Service("scheduleJobServiceImpl")
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJob, Integer> implements ScheduleJobService {
	
	private SchedulerFactoryBean schedulerFactoryBean;
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);
	
	@Override
	@Autowired
	public void setDao(ScheduleJobDao dao) {
		this.dao = dao;
	}


	/**
	 * 更改任务 cron表达式
	 * 
	 * @throws SchedulerException
	 */
	@Override
	public void updateCron(Integer jobId, String cron) throws SchedulerException {
		ScheduleJob job = get(jobId);
		if (job == null) {
			return;
		}
		job.setCronExpression(cron);
		if (ConfigServlet.STATUS_RUNNING.equals(job.getIsEnable())) {
			updateJobCron(job);
		}
		//save(job);
	}

	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	@Override
	public void addJob(ScheduleJob job) throws SchedulerException {
		if (job == null || !ConfigServlet.STATUS_RUNNING.equals(job.getIsEnable())) {
			return;
		}
		Scheduler scheduler = this.getSchedulerFactory().getScheduler();
		logger.debug(scheduler + ".......................................................................................add");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			Class clazz = QuartzJobFactory.class;

			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

			jobDetail.getJobDataMap().put("scheduleJob", job);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	@PostConstruct
	public void init() throws Exception {

		Scheduler scheduler = getSchedulerFactory().getScheduler();
		// 这里获取任务信息数据
		List<ScheduleJob> jobList = findList();
		for (ScheduleJob job : jobList) {
			addJob(job);
		}
	}
	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	@Override
	public List<ScheduleJob> getAllJob() throws SchedulerException {
		Scheduler scheduler =  getSchedulerFactory().getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setRemarks("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				if(triggerState.name()=="1"){
					job.setIsEnable(true);}else{
						job.setIsEnable(false);
					}
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	@Override
	public List<ScheduleJob> getRunningJob() throws SchedulerException {
		Scheduler scheduler =  getSchedulerFactory().getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			ScheduleJob job = new ScheduleJob();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setRemarks("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			if(triggerState.name()=="1"){
			job.setIsEnable(true);}else{
				job.setIsEnable(false);
			}
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

	/**
	 * 暂停一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	@Override
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler =  getSchedulerFactory().getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	@Override
	public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler =  getSchedulerFactory().getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	@Override
	public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler =  getSchedulerFactory().getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);

	}

	/**
	 * 立即执行job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	@Override
	public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler =  getSchedulerFactory().getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	@Override
	public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler =  getSchedulerFactory().getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}


	private SchedulerFactoryBean getSchedulerFactory() {
		// TODO Auto-generated method stub
		if(schedulerFactoryBean==null){
			schedulerFactoryBean=SpringContextHolder.getBean(SchedulerFactoryBean.class);
		}
		return schedulerFactoryBean;
	}

	/**
	 * 
	 * @todo 验证cron表达式是否有效
	 * @author huiyunfei
	 * @date 2016-9-20 上午10:02:11
	 * @return
	 */
	@Override
	public Map validateJob(ScheduleJob scheduleJob) {
		String flag="false";
		String msg="";
		Map resMap=new HashMap();
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		} catch (Exception e) {
			msg="cron表达式有误，不能被解析！";
		}
		Object obj = null;
		try {
			if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
				obj = SpringUtils.getBean(scheduleJob.getSpringId());
			} else {
				Class clazz = Class.forName(scheduleJob.getBeanClass());
				obj = clazz.newInstance();
			}
		} catch (Exception e) {
			// do nothing.........
		}
		if (obj == null) {
			msg="未找到目标类！";
		} else {
			Class clazz = obj.getClass();
			Method method = null;
			try {
				method = clazz.getMethod(scheduleJob.getMethodName(), null);
			} catch (Exception e) {
				// do nothing.....
			}
			if (method == null) {
				msg="未找到目标类！";
			}
		}
		try {
			///taskService.addTask(scheduleJob);
		} catch (Exception e) {
			e.printStackTrace();
			msg="保存失败，检查 name group 组合是否有重复！";
		}
		flag="true";
		resMap.put("flag",flag);
		resMap.put("msg", msg);
		return resMap;
	}


	@Override
	public List<ScheduleJob> findList() {
		// TODO Auto-generated method stub
		return dao.findListAll();
	}


	@Override
	public Pagination<ScheduleJob> findPaginationByName(String jobName, Pagination<ScheduleJob> p) {
		dao.findPaginationByName(jobName, p);
		return p;
	}
	
	@Override
	public void deleteById(List<Integer> idList){
		dao.deleteById(idList);
	}
	
	@Override
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(dao.enable(isEnable, idList), idList.size(), "定时任务数据库更新失败");
	}

	@Override
	public List<ScheduleJob> findListById(List<Integer> idList) {
		// TODO Auto-generated method stub
		return dao.findListById(idList);
	}
	
}