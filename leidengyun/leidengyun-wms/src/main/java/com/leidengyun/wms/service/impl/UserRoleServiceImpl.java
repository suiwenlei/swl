package com.leidengyun.wms.service.impl;

import java.util.Arrays;
import java.util.List;

import com.leidengyun.wms.dao.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.wms.model.UserRole;
import com.leidengyun.wms.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole, Integer> implements UserRoleService {

	@Override
	@Autowired
	public void setDao(UserRoleDao dao) {
		this.dao = dao;
	}
	
	@Override
	@Transactional
	public void allocate(Integer userId, Integer appId, List<UserRole> list) {
		dao.deleteByUserIds(Arrays.asList(userId), appId);
		super.save(list);
	}
	
	@Override
	public UserRole findByUserRoleId(Integer userId, Integer roleId) {
		return dao.findByUserRoleId(userId, roleId);
	}
	
	@Override
	public void deleteByRoleIds(List<Integer> idList) {
		dao.deleteByRoleIds(idList);
	}
	
	@Override
	public void deleteByUserIds(List<Integer> idList, Integer appId) {
		dao.deleteByUserIds(idList, appId);
	}
	
	@Override
	public void deleteByAppIds(List<Integer> idList) {
		dao.deleteByAppIds(idList);
	}

	@Override
	public void deleteForChangeApp(Integer userId, List<Integer> idList) {
		dao.deleteForChangeApp(userId, idList);
	}
}
