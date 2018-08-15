package com.leidengyun.wms.service.impl;

import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.sso.rpc.RpcPermission;
import com.leidengyun.wms.dao.PermissionDao;
import com.leidengyun.wms.model.Permission;
import com.leidengyun.wms.service.AppService;
import com.leidengyun.wms.service.PermissionJmsService;
import com.leidengyun.wms.service.PermissionService;
import com.leidengyun.wms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission, Integer> implements PermissionService {

	@Resource
	private RolePermissionService rolePermissionService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private AppService appService;
	@Resource
	private PermissionJmsService permissionJmsService;

	@Override
	@Autowired
	public void setDao(PermissionDao dao) {
		this.dao = dao;
	}

	@Override
	public void save(Permission t) {
		super.save(t);
		// JMS通知权限变更
		permissionJmsService.send(appService.get(t.getAppId()).getCode());
	}

	@Override
	public List<Permission> findByName(String name, Integer appId, Boolean isEnable) {
		return dao.findByName(name, appId, isEnable);
	}

	@Override
	@Transactional
	public void deletePermission(Integer id, Integer appId) {
		List<Integer> idList = new ArrayList<Integer>();

		List<Permission> list = permissionService.findByName(null, appId, null);
		loopSubList(id, idList, list);
		idList.add(id);

		//rolePermissionService.deleteByPermissionIds(idList);

		verifyRows(dao.deleteById(idList), idList.size(), "权限数据库删除失败");
	}

	// 递归方法，删除子权限
	protected void loopSubList(Integer id, List<Integer> idList, List<Permission> list) {
		for (Permission p : list) {
			if (id.equals(p.getParentId())) {
				idList.add(p.getId());
				loopSubList(p.getId(), idList, list);
			}
		}
	}

	@Override
	public void deleteByAppIds(List<Integer> idList) {
		dao.deleteByAppIds(idList);
	}

	@Override
	public List<RpcPermission> findListById(String appCode, Integer userId) {
		return dao.findListById(appCode, userId);
	}
}
