package com.leidengyun.wms.service.impl;

import com.leidengyun.wms.dao.DevDataAppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.wms.model.DevDataApp;
import com.leidengyun.wms.service.DevDataAppService;

@Service("devDataAppService")
public class DevDataAppServiceImpl extends ServiceImpl<DevDataAppDao, DevDataApp, Integer> implements DevDataAppService {
	

	@Autowired
	public void setDao(DevDataAppDao dao) {
		this.dao = dao;
	}
	
	public void save(DevDataApp t) {
		super.save(t);
	}

	@Override
	public void deleteByUuid(String uuid) {
		// TODO Auto-generated method stub
		dao.deleteByUuid(uuid);
	}
}
