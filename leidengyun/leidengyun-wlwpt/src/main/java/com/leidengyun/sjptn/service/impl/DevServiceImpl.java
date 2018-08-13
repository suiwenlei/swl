package com.leidengyun.sjptn.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.sjptn.dao.DevDao;
import com.leidengyun.sjptn.model.Dev;
import com.leidengyun.sjptn.service.DevService;


@Service("devService")
public class DevServiceImpl extends ServiceImpl<DevDao, Dev, Integer> implements DevService {
	
	

	@Autowired
	public void setDao(DevDao dao) {
		this.dao = dao;
	}
	
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(dao.enable(isEnable, idList), idList.size(), "应用数据库更新失败");
	}
	
	public void save(Dev t) {
		super.save(t);
	}

	public List<Dev> findByAll(String deviceId) {
		return dao.findPaginationByDevId(deviceId, null);
	}

	public Pagination<Dev> findPaginationByDevId(String devId, Pagination<Dev> p) {
		dao.findPaginationByDevId(devId, p);
		return p;
	}

	public Dev findByDevId(String devId) {
		return dao.findByDevId(devId);
	}
	
	public List<Dev> findByUserId(Boolean isEnable, Integer userId) {
		return dao.findByUserId(isEnable, userId);
	}
	

	public Set<String> findDevCodeByUserId(Boolean isEnable, Integer userId) {
		return dao.findDevCodeByUserId(isEnable, userId);
	}
}
