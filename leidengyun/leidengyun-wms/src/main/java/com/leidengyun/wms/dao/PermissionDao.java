package com.leidengyun.wms.dao;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.sso.rpc.RpcPermission;
import com.leidengyun.wms.model.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限持久化接口
 * 
 * @author Joe
 */
public interface PermissionDao extends Dao<Permission, Integer> {
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	
	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

	public List<Permission> findByName(@Param("name") String name, @Param("appId") Integer appId, @Param("isEnable") Boolean isEnable);
	
	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public List<RpcPermission> findListById(@Param("appCode") String appCode, @Param("userId") Integer userId);

	public int deletePermissionByPId(@Param("pid") Integer pid);
	public List<Permission> findByParentId(@Param("pid")Integer pid, @Param("appId") Integer appId);
}
