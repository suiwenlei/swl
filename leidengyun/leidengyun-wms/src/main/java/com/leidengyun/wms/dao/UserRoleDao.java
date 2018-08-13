package com.leidengyun.wms.dao;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.wms.model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员角色映射持久化接口
 * 
 * @author Joe
 */
public interface UserRoleDao extends Dao<UserRole, Integer> {

	public UserRole findByUserRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

	public int deleteByRoleIds(@Param("idList") List<Integer> idList);

	public int deleteByUserIds(@Param("idList") List<Integer> idList, @Param("appId") Integer appId);

	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public int deleteForChangeApp(@Param("userId") Integer userId, @Param("idList") List<Integer> idList);
}
