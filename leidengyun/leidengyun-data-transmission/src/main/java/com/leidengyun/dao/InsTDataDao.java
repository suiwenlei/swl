package com.leidengyun.dao;

import com.leidengyun.model.InsTData;
import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.mvc.model.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
	public int deleteByUuid(@Param("uuid") String uuid);
}
