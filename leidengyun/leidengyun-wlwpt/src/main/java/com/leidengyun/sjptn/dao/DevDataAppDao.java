package com.leidengyun.sjptn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.sjptn.model.DevDataApp;

/**
 * 应用持久化接口
 * 
 * @author Joe
 */
public interface DevDataAppDao extends Dao<DevDataApp, Integer> {
	
	//删除数据
	public int deleteByIds(@Param("idList") List<String> idList);
	public List<DevDataApp> findPaginationByDevId(
			@Param("devId") String devId,
			@Param("type") String type,
			@Param("condition") String condition,
			@Param("qsrq") String qsrq,
			@Param("zzrq") String zzrq,
			Pagination<DevDataApp> p
	);
	public List<DevDataApp> findDevDataAppList(@Param("devId") Integer devId, @Param("qsrq") String qsrq,
			@Param("zzrq") String zzrq);
}
