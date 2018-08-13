package com.leidengyun.sjptn.service;

import java.util.List;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.Service;
import com.leidengyun.sjptn.model.InsTData;

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
}