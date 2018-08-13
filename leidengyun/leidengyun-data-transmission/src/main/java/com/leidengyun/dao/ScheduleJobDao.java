package com.leidengyun.dao;

import com.leidengyun.model.ScheduleJob;
import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.mvc.model.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ScheduleJobDao extends Dao<ScheduleJob, Integer> {
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	public List<ScheduleJob> findPaginationByName(@Param("jobName")String jobName, Pagination<ScheduleJob> p);
	public int deleteById(@Param("idList") List<Integer> idList);
	public List<ScheduleJob> findListById(@Param("idList") List<Integer> idList);
	public List<ScheduleJob> findListAll();
}
