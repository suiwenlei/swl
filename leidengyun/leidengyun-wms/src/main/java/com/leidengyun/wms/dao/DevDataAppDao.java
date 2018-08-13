package com.leidengyun.wms.dao;

import org.apache.ibatis.annotations.Param;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.wms.model.DevDataApp;

/**
 * 应用持久化接口
 * 
 * @author Joe
 */
public interface DevDataAppDao extends Dao<DevDataApp, Integer> {
	
	//删除数据
	public int deleteByUuid(@Param("uuid") String uuid);
}
