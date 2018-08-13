package com.leidengyun.wms.dao;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.wms.model.UserApp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员角色映射持久化接口
 * 
 * @author Joe
 */
public interface UserAppDao extends Dao<UserApp, Integer> {

	public UserApp findByUserAppId(@Param("userId") Integer userId, @Param("appId") Integer appId);

	public int deleteByAppIds(@Param("idList") List<Integer> idList);

	public int deleteByUserIds(@Param("idList") List<Integer> idList);
	
	public List<UserApp> findByUserListAppId(@Param("userId")Integer userId,@Param("appId") Integer appId);
}
