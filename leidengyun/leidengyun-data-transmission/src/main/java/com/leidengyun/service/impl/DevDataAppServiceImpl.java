package com.leidengyun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leidengyun.dao.DevDataAppDao;
import com.leidengyun.model.DevDataApp;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.service.DevDataAppService;

@Service("devDataAppService")
public class DevDataAppServiceImpl extends ServiceImpl<DevDataAppDao, DevDataApp, Integer> implements DevDataAppService {
	

	@Override
	@Autowired
	public void setDao(DevDataAppDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void save(DevDataApp t) {
		super.save(t);
	}

	@Override
	public void deleteByUuid(String uuid) {
		// TODO Auto-generated method stub
		dao.deleteByUuid(uuid);
	}
}
