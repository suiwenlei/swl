package com.leidengyun.sjptn.dao;

import java.util.List;

/**
 * 应用持久化接口
 * 
 * @author Joe
 */
public interface MapDao  {
	
	public List findListBySql(String sql);
}
	
	
