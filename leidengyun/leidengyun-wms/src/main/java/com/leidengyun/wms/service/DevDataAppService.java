package com.leidengyun.wms.service;

import com.leidengyun.mvc.service.mybatis.Service;
import com.leidengyun.wms.model.DevDataApp;

/**
 * 应用服务接口
 * 
 * @author Joe
 */
public interface DevDataAppService extends Service<DevDataApp, Integer> {
	public void deleteByUuid(String uuid);
}