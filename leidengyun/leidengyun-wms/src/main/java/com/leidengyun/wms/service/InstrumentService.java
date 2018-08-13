package com.leidengyun.wms.service;

import java.util.List;
import java.util.Map;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.Service;
import com.leidengyun.wms.model.Instrument;

/**
 * 应用服务接口
 * 
 * @author Joe
 */
public interface InstrumentService extends Service<Instrument, Integer> {
	
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
	public List<Instrument> findByAll(String insName);
	
	/**
	 * 根据名称分页查询
	 * @param name 应用名称
	 * @return
	 */
	public Pagination<Instrument> findPaginationByInsName(String insName, Pagination<Instrument> p);
	
	/**
	 * 根据应用编码查询
	 * @param code 应用编码
	 * @return
	 */
	public Instrument findByInsName(String insName);
	
	/**
	 * 设备编号+设备类型
	 * @return
	 */
	public List<Map>  getDevIdType();
	
}