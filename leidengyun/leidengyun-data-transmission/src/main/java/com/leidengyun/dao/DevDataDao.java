package com.leidengyun.dao;

import com.leidengyun.model.DevData;
import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.mvc.model.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应用持久化接口
 * 
 * @author Joe
 */
public interface DevDataDao extends Dao<DevData, Integer> {
	

	public List<DevData> findPaginationByDevId(@Param("devId") String devId, @Param("qsrq") String qsrq,
			@Param("zzrq") String zzrq, Pagination<DevData> p);

	public DevData findByDevId(@Param("devId") String devId);

	public int deleteByIds(@Param("idList") List<String> idList);
	
	public List<DevData> findDevList(@Param("devId")Integer devId, @Param("qsrq")String qsrq, @Param("zzrq")String zzrq);
	
	public int deleteByUuid(@Param("uuid") String uuid);
	
	//根据guid更新主表状态
	public int updateDevDataByuuid(@Param("guid") String guid,@Param("ifsync") int ifsync);
}
