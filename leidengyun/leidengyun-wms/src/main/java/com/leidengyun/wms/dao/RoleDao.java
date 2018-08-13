package com.leidengyun.wms.dao;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.wms.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色持久化接口
 * 
 * @author Joe
 */
public interface RoleDao extends Dao<Role, Integer> {

	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);

	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

	public List<Role> findPaginationByName(@Param("name") String name, @Param("isEnable") Boolean isEnable,
			@Param("appId") Integer appId, Pagination<Role> p);

	public int deleteByAppIds(@Param("idList") List<Integer> idList);
}
