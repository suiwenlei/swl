package com.leidengyun.wms.service;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.Service;
import com.leidengyun.wms.model.InsTData;

import java.util.List;

/**
 * 应用服务接口
 * 
 * @author Joe
 */
public interface InsTDataService extends Service<InsTData, Integer> {
	
	

	public Pagination<InsTData> findPaginationByDevId(String devId, Pagination<InsTData> p);
	public List<InsTData> findListByDevId(String devId);
	public List<InsTData> findByDevId(String guid);
	public void deleteInstByIds(List<String> idList);
	public void deleteInstByUuid(String uuid);
}