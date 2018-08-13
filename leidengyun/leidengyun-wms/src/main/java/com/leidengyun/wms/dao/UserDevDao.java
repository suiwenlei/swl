package com.leidengyun.wms.dao;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.wms.model.UserDev;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员设备映射持久化接口
 * 
 * @author Joe
 */
public interface UserDevDao extends Dao<UserDev, Integer> {

	public UserDev findByUserDevId(@Param("userId") Integer userId, @Param("devId") String devId);

	public int deleteByDevIds(@Param("idList") List<Integer> idList);

	public int deleteByUserIds(@Param("idList") List<Integer> idList);
	
	public int deleteForChangeDev(@Param("userId") Integer userId, @Param("idList") List<Integer> idList);
}
