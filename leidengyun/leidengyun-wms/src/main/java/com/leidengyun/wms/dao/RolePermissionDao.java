package com.leidengyun.wms.dao;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.wms.model.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限映射持久化接口
 * 
 * @author Joe
 */
public interface RolePermissionDao extends Dao<RolePermission, Integer> {
	
	public List<RolePermission> findByRoleId(@Param("roleId") Integer roleId);
	
	public int deleteByPermissionIds(@Param("idList") List<Integer> idList);
	
	public int deleteByRoleIds(@Param("idList") List<Integer> idList);
	
	public int deleteByAppIds(@Param("idList") List<Integer> idList);
}
