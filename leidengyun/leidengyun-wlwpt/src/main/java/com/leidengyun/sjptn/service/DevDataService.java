package com.leidengyun.sjptn.service;

import java.util.List;
import java.util.Map;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.Service;
import com.leidengyun.sjptn.model.DevData;

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
	public String gsonDataTitle(Integer devId,String type,String qsrq,String zzrq);
	
	public void deleteByIds(List<String> idList);
	
	/**
	 * 根据时间获取设备主表数据
	 * @param devId
	 * @param qsrq
	 * @param zzrq
	 * @return
	 */
	public List<DevData> findDevList(Integer devId,String qsrq,String zzrq);
	public Map<String, Object> getZdMapInst(String type,String isntId,String A);
	
	/**
	 *获取动态表格title
	 * @param devId
	 * @param qsrq
	 * @param zzrq
	 * @return
	 */
	public List<Map> getTitleList(Integer devId,String type,String qsrq, String zzrq);

	/**
	 * 根据devID TYPE 获取最新的表头
	 * @param devId
	 * @param type
	 * @return
	 */
	public String getLastCondition(Integer devId);
}