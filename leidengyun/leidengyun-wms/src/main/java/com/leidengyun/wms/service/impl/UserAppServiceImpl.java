package com.leidengyun.wms.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.leidengyun.wms.dao.UserAppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.wms.model.UserApp;
import com.leidengyun.wms.service.UserAppService;
import com.leidengyun.wms.service.UserRoleService;

@Service("userAppService")
public class UserAppServiceImpl extends ServiceImpl<UserAppDao, UserApp, Integer> implements UserAppService {

	@Resource
	private UserRoleService userRoleService;
	
	@Override
	@Autowired
	public void setDao(UserAppDao dao) {
		this.dao = dao;
	}
	
	@Override
	@Transactional
	public void allocate(Integer userId, List<Integer> idList, List<UserApp> list) {
		userRoleService.deleteForChangeApp(userId, idList);
		dao.deleteByUserIds(Arrays.asList(userId));
		super.save(list);
	}
	
	@Override
	public UserApp findByUserAppId(Integer userId, Integer roleId) {
		return dao.findByUserAppId(userId, roleId);
	}
	
	@Override
	public void deleteByUserIds(List<Integer> idList) {
		dao.deleteByUserIds(idList);
	}
	
	@Override
	public void deleteByAppIds(List<Integer> idList) {
		dao.deleteByAppIds(idList);
	}

	@Override
	public List<UserApp> findByUserAppId(Integer userId) {
		// TODO Auto-generated method stub
		return dao.findByUserListAppId(userId,null);
	}
}
