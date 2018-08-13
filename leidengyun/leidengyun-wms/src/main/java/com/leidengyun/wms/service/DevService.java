package com.leidengyun.wms.service;

import java.util.List;
import java.util.Set;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.Service;
import com.leidengyun.wms.model.Dev;

/**
 * 应用服务接口
 * 
 * @author Joe
 */
public interface DevService extends Service<Dev, Integer> {
	
	/**
	 * 启用禁用操作
	 * @param isEnable 是否启用
	 * @param idList 应用ID集合
	 * @return
	 */
	public void enable(Boolean isEnable, List<Integer> idList);
	
	/**
	 * 根据名称查询
	 * @param name 应用名称
	 * @return
	 */
	public List<Dev> findByAll(String deviceId);
	
	/**
	 * 根据名称分页查询
	 * @param name 应用名称
	 * @return
	 */
	public Pagination<Dev> findPaginationByDevId(String devId, Pagination<Dev> p);
	
	/**
	 * 根据应用编码查询
	 * @param code 应用编码
	 * @return
	 */
	public Dev findByDevId(String devId);
	
	/**
	 * 根据管理员ID查询已分配应用
	 * @param userId 管理员ID
	 * @return
	 */
	public List<Dev> findByUserId(Boolean isEnable, Integer userId);
	
	/**
	 * 根据管理员ID查询已分配应用编码
	 * @param userId
	 * @return
	 */
	public Set<String> findDevCodeByUserId(Boolean isEnable, Integer userId);
}