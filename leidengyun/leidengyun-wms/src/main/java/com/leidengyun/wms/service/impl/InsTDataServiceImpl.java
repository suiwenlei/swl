package com.leidengyun.wms.service.impl;

import java.util.List;

import com.leidengyun.wms.dao.InsTDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.wms.model.InsTData;
import com.leidengyun.wms.service.InsTDataService;

@Service("insTDataService")
public class InsTDataServiceImpl extends ServiceImpl<InsTDataDao, InsTData, Integer> implements InsTDataService {

	
	@Override
	@Autowired
	public void setDao(InsTDataDao dao) {
		this.dao = dao;
	}

	@Override
	public Pagination<InsTData> findPaginationByDevId(String devId, Pagination<InsTData> p) {
		// TODO Auto-generated method stub
		dao.findPaginationByDevId(devId,p);
		return p;
	}

	@Override
	public List<InsTData> findListByDevId(String devId) {
		// TODO Auto-generated method stub
		return dao.findListByDevId(devId);
	}

	@Override
	public List<InsTData> findByDevId(String guid) {
		// TODO Auto-generated method stub
		return dao.findByDevId(guid);
	}

	@Override
	public void deleteInstByIds(List<String> idList) {
		// TODO Auto-generated method stub
		dao.deleteInstByIds(idList);
	}

	@Override
	public void deleteInstByUuid(String uuid) {
		// TODO Auto-generated method stub
		dao.deleteByUuid(uuid);
	}
}
