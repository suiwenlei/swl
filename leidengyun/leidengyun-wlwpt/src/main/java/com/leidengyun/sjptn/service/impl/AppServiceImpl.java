package com.leidengyun.sjptn.service.impl;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.sjptn.dao.AppDao;
import com.leidengyun.sjptn.model.App;
import com.leidengyun.sjptn.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("appService")
public class AppServiceImpl extends ServiceImpl<AppDao, App, Integer> implements AppService {
	
	

	@Override
	@Autowired
	public void setDao(AppDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(dao.enable(isEnable, idList), idList.size(), "应用数据库更新失败");
	}
	
	@Override
	public void save(App t) {
		super.save(t);
	}

	@Override
	public List<App> findByAll(String name) {
		return dao.findPaginationByName(name, null);
	}

	@Override
	public Pagination<App> findPaginationByName(String name, Pagination<App> p) {
		dao.findPaginationByName(name, p);
		return p;
	}

	@Override
	public App findByCode(String code) {
		return dao.findByCode(code);
	}
	
	@Override
	public List<App> findByUserId(Boolean isEnable, Integer userId) {
		return dao.findByUserId(isEnable, userId);
	}
	
	

	@Override
	public Set<String> findAppCodeByUserId(Boolean isEnable, Integer userId) {
		return dao.findAppCodeByUserId(isEnable, userId);
	}
}
