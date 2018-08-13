package com.leidengyun.wms.dao;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.wms.model.Dev;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface DevDao extends Dao<Dev, Integer> {
	
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	
	public List<Dev> findPaginationByDevId(@Param("devId") String devId, Pagination<Dev> p);
	
	public Dev findByDevId(@Param("devId") String devId);
	
	public List<Dev> findByUserId(@Param("isEnable") Boolean isEnable, @Param("userId") Integer userId);
	
	public Set<String> findDevCodeByUserId(@Param("isEnable") Boolean isEnable, @Param("userId") Integer userId);

}