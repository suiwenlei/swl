
package com.leidengyun.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.leidengyun.mvc.model.PersistentObject;

import java.util.Date;

/**
 * 定时任务Entity
 */
public class ScheduleJob extends PersistentObject{


	private static final long serialVersionUID = 7902814112969375973L;
	private String jobName;		// 任务名称
	private String jobGroup;		// 任务分组
	private String cronExpression;		// cron表达式
	private String beanClass;		// 任务执行时调用的方法,包名+类名
	private String methodName;		// 任务调用的方法名
	private String springId;		// spring bean
	
	private String remarks;

	/** 排序 */
	private Integer sort = Integer.valueOf(1);
	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/** 是否启用 */
	private Boolean isEnable = Boolean.valueOf(true);// 任务状态（1：正常；0：暂停）
	
	
	
	public ScheduleJob() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ScheduleJob(String jobName, String jobGroup, String cronExpression, String beanClass, String methodName,
			String springId, String remarks, Integer sort, Date createTime, Boolean isEnable) {
		super();
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.cronExpression = cronExpression;
		this.beanClass = beanClass;
		this.methodName = methodName;
		this.springId = springId;
		this.remarks = remarks;
		this.sort = sort;
		this.createTime = createTime;
		this.isEnable = isEnable;
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
	
	

	public Boolean getIsEnable() {
		return isEnable;
	}


	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}


	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	
	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	
	
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	public String getSpringId() {
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	/** 以下为显示辅助参数 */
	private Boolean isChecked = Boolean.valueOf(false);
	
	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	public String getIsEnableStr() {
		return (isEnable != null && isEnable) ? "运行中" : "停止";
	}
}