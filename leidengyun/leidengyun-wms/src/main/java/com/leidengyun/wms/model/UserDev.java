package com.leidengyun.wms.model;

import com.leidengyun.mvc.model.PersistentObject;

/**
 * 管理员设备映射
 * 
 * @author Joe
 */
public class UserDev extends PersistentObject {

	private static final long serialVersionUID = 4942358338145288018L;

	/** 应用ID */
	private Integer devId;
	/** 管理员ID */
	private Integer userId;
	
	

	public Integer getDevId() {
		return devId;
	}

	public void setDevId(Integer devId) {
		this.devId = devId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
