package com.leidengyun.wms.service;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.Service;
import com.leidengyun.wms.model.DevData;

/**
 * 应用服务接口
 * 
 * @author Joe
 */
public interface DevDataService extends Service<DevData, Integer> {
	
	

	/**
	 * 根据名称分页查询
	 * @param name 应用名称
	 * @return
	 */
	public Pagination<DevData> findPaginationByDevId(String devId,String qsrq,String zzrq,Pagination<DevData> p);
	
	public DevData findByDevId(String devId);
	
	/**
	 * 动态获取表头
	 * @param devId
	 * @return
	 */
	//public String gsonDataTitle(Integer devId,String qsrq,String zzrq);
	
	
	
}