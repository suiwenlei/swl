package com.leidengyun.wms.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.leidengyun.wms.dao.RolePermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.wms.model.RolePermission;
import com.leidengyun.wms.service.AppService;
import com.leidengyun.wms.service.PermissionJmsService;
import com.leidengyun.wms.service.RolePermissionService;
import com.leidengyun.wms.service.RoleService;

@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermission, Integer> implements RolePermissionService {
	
	@Resource
	private RoleService roleService;
	@Resource
	private AppService appService;
	@Resource
	private PermissionJmsService permissionJmsService;

	@Override
	@Autowired
	public void setDao(RolePermissionDao dao) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public void allocate(Integer roleId, List<RolePermission> list) {
		dao.deleteByRoleIds(Arrays.asList(roleId));
		super.save(list);
		// JMS通知权限变更
		permissionJmsService.send(appService.get(roleService.get(roleId).getAppId()).getCode());
	}

	@Override
	public List<RolePermission> findByRoleId(Integer roleId) {
		return dao.findByRoleId(roleId);
	}

	@Override
	public void deleteByPermissionIds(List<Integer> idList) {
		dao.deleteByPermissionIds(idList);
	}
	
	@Override
	public void deleteByRoleIds(List<Integer> idList) {
		dao.deleteByRoleIds(idList);
	}
	
	@Override
	public void deleteByAppIds(List<Integer> idList) {
		dao.deleteByAppIds(idList);
	}
}
