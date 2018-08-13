package com.leidengyun.sjptn.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.sjptn.dao.AppDao;
import com.leidengyun.sjptn.model.App;
import com.leidengyun.sjptn.service.AppService;

@Service("appService")
public class AppServiceImpl extends ServiceImpl<AppDao, App, Integer> implements AppService {
	
	

	@Autowired
	public void setDao(AppDao dao) {
		this.dao = dao;
	}
	
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(dao.enable(isEnable, idList), idList.size(), "应用数据库更新失败");
	}
	
	public void save(App t) {
		super.save(t);
	}

	public List<App> findByAll(String name) {
		return dao.findPaginationByName(name, null);
	}

	public Pagination<App> findPaginationByName(String name, Pagination<App> p) {
		dao.findPaginationByName(name, p);
		return p;
	}

	public App findByCode(String code) {
		return dao.findByCode(code);
	}
	
	public List<App> findByUserId(Boolean isEnable, Integer userId) {
		return dao.findByUserId(isEnable, userId);
	}
	
	

	public Set<String> findAppCodeByUserId(Boolean isEnable, Integer userId) {
		return dao.findAppCodeByUserId(isEnable, userId);
	}
}
