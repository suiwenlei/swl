package com.leidengyun.sjptn.service;

import java.util.List;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.Service;
import com.leidengyun.sjptn.model.DevDataApp;

/**
 * 应用服务接口
 * 
 * @author Joe
 */
public interface DevDataAppService extends Service<DevDataApp, Integer> {
	
	

	/**
	 * 根据名称分页查询
	 * @param name 应用名称
	 * @return
	 */
	public Pagination<DevDataApp> findPaginationByDevId(String devId,String qsrq,String zzrq,Pagination<DevDataApp> p);
	public void deleteByIds(List<String> idList);
	public List<DevDataApp> findDevDataAppList(Integer devId, String qsrq, String zzrq);
	
}