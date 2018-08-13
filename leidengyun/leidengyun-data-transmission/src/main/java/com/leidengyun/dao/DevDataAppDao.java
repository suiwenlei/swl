package com.leidengyun.dao;

import com.leidengyun.model.DevDataApp;
import org.apache.ibatis.annotations.Param;

import com.leidengyun.mvc.dao.mybatis.Dao;

/**
 * 应用持久化接口
 * 
 * @author Joe
 */
public interface DevDataAppDao extends Dao<DevDataApp, Integer> {
	
	//删除数据
	public int deleteByUuid(@Param("uuid") String uuid);
}
