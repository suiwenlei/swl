package com.leidengyun.sjptn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.sjptn.model.InsTData;

/**
 * 应用持久化接口
 * 
 * @author Joe
 */
public interface InsTDataDao extends Dao<InsTData, Integer> {
	

	public List<InsTData> findPaginationByDevId(@Param("devId") String name, Pagination<InsTData> p);
	//根据DevId获取仪器集合
	public List<InsTData> findListByDevId(@Param("devId") String devId);
	//根据guid获取对象
	public List<InsTData> findByDevId(@Param("guid") String guid);
	public void deleteInstByIds(@Param("idList") List<String> idList);
}
