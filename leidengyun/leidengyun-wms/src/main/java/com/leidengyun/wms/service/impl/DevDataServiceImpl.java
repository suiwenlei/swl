package com.leidengyun.wms.service.impl;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.mvc.util.StringUtils;
import com.leidengyun.wms.dao.DevDataDao;
import com.leidengyun.wms.model.DevData;
import com.leidengyun.wms.service.DevDataAppService;
import com.leidengyun.wms.service.DevDataService;
import com.leidengyun.wms.service.InsTDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("devDataService")
public class DevDataServiceImpl extends ServiceImpl<DevDataDao, DevData, Integer> implements DevDataService {
	

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	InsTDataService insTataService;
	
	
	@Resource
	DevDataAppService devDataAppService;

	@Autowired
	public void setDao(DevDataDao dao) {
		this.dao = dao;
	}
	
	public Pagination<DevData> findPaginationByDevId(String devId,String qsrq,String zzrq,Pagination<DevData> p) {
		dao.findPaginationByDevId(devId,qsrq,zzrq,p);
		return p;
	}

	public DevData findByDevId(String devId) {
		return dao.findByDevId(devId);
	}
	
	public Map<String, Object> getZdMapInst(String type,String isntId,String A){
		if(StringUtils.isNotBlank(A)){
			try {
				return jdbcTemplate.queryForMap("select * from sys_instrument a where concat(a.insType,a.insId)='"+A+"' ");
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
		try {
			return jdbcTemplate.queryForMap("select * from sys_instrument a where a.insId='"+isntId+"'"
					+ "and a.insType='"+type+"'");
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}	
	}
}
