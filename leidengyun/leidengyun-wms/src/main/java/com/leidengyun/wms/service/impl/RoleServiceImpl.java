package com.leidengyun.wms.service.impl;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.wms.dao.RoleDao;
import com.leidengyun.wms.model.Role;
import com.leidengyun.wms.service.RolePermissionService;
import com.leidengyun.wms.service.RoleService;
import com.leidengyun.wms.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role, Integer> implements RoleService {

	@Resource
	private UserRoleService userRoleService;
	@Resource
	private RolePermissionService rolePermissionService;

	@Override
	@Autowired
	public void setDao(RoleDao dao) {
		this.dao = dao;
	}

	@Override
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(dao.enable(isEnable, idList), idList.size(), "角色数据库更新失败");
	}

	@Override
	public void save(Role t) {
		super.save(t);
	}

	@Override
	public Pagination<Role> findPaginationByName(String name, Integer appId, Pagination<Role> p) {
		dao.findPaginationByName(name, null, appId, p);
		return p;
	}

	@Override
	public List<Role> findByAppId(Boolean isEnable, Integer appId) {
		if (appId == null) {
			return new ArrayList<Role>(0);
		}
		return dao.findPaginationByName(null, isEnable, appId, null);
	}

	@Override
	@Transactional
	public void deleteById(List<Integer> idList) {
		userRoleService.deleteByRoleIds(idList);
		rolePermissionService.deleteByRoleIds(idList);
		verifyRows(dao.deleteById(idList), idList.size(), "角色数据库删除失败");
	}

	@Override
	public void deleteByAppIds(List<Integer> idList) {
		dao.deleteByAppIds(idList);
	}
}
