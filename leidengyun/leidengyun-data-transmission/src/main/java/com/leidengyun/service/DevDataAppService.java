package com.leidengyun.service;

import com.leidengyun.model.DevDataApp;
import com.leidengyun.mvc.service.mybatis.Service;

/**
 * 应用服务接口
 * 
 * @author Joe
 */
public interface DevDataAppService extends Service<DevDataApp, Integer> {
	public void deleteByUuid(String uuid);
}