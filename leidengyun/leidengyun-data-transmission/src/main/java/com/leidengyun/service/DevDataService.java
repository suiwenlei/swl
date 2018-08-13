package com.leidengyun.service;

import java.util.List;
import java.util.Map;

import com.leidengyun.model.DevData;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.Service;

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
	
	public void deleteByIds(List<String> idList);
	
	public void deleteByUuid(String uuid);
	
	
	/**
	 * 定时任务处理数据
	 * @param devId
	 * @param qsrq
	 * @param zzrq
	 */
	public void ProcessDevData(Integer devId,String qsrq,String zzrq);
	public Map<String, Object> getZdMapInst(String type,String isntId,String A);
	
	public int updateDevDataByuuid(String guid,int ifsync);
	

}