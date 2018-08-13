package com.leidengyun.sjptn.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.sjptn.model.App;

/**
 * 应用持久化接口
 * 
 * @author Joe
 */
public interface AppDao extends Dao<App, Integer> {
	
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	public List<App> findPaginationByName(@Param("name") String name, Pagination<App> p);
	public App findByCode(@Param("code") String code);
	public List<App> findByUserId(@Param("isEnable") Boolean isEnable, @Param("userId") Integer userId);
	public Set<String> findAppCodeByUserId(@Param("isEnable") Boolean isEnable, @Param("userId") Integer userId);
}
