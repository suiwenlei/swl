package com.leidengyun.wms.service.impl;

import java.util.Arrays;
import java.util.List;

import com.leidengyun.wms.dao.UserDevDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.wms.model.UserDev;
import com.leidengyun.wms.service.UserDevService;

@Service("userDevService")
public class UserDevServiceImpl extends ServiceImpl<UserDevDao, UserDev, Integer> implements UserDevService {

	
	@Override
	@Autowired
	public void setDao(UserDevDao dao) {
		this.dao = dao;
	}
	
	@Override
	@Transactional
	public void allocate(Integer userId, List<Integer> idList, List<UserDev> list) {
		if(idList ==null || idList.size()==0){idList.add(null);}
		dao.deleteForChangeDev(userId, idList);
		dao.deleteByUserIds(Arrays.asList(userId));
		if(list!=null || idList.size()>0){super.save(list);}
	}
	
	@Override
	public UserDev findByUserDevId(Integer userId, String devId) {
		return dao.findByUserDevId(userId, devId);
	}
	
	@Override
	public void deleteByUserIds(List<Integer> idList) {
		dao.deleteByUserIds(idList);
	}
	
	@Override
	public void deleteByDevIds(List<Integer> idList) {
		dao.deleteByDevIds(idList);
	}
}
