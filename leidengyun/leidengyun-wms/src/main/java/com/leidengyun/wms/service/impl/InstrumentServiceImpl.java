package com.leidengyun.wms.service.impl;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.wms.dao.InstrumentDao;
import com.leidengyun.wms.model.Instrument;
import com.leidengyun.wms.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("instrumentService")
public class InstrumentServiceImpl extends ServiceImpl<InstrumentDao, Instrument, Integer> implements InstrumentService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDao(InstrumentDao dao) {
		this.dao = dao;
	}
	
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(dao.enable(isEnable, idList), idList.size(), "应用数据库更新失败");
	}
	
	public void save(Instrument t) {
		super.save(t);
	}

	public List<Instrument> findByAll(String insName) {
		return dao.findPaginationByInsName(insName, null);
	}

	public Pagination<Instrument> findPaginationByInsName(String insName, Pagination<Instrument> p) {
		dao.findPaginationByInsName(insName, p);
		return p;
	}

	public Instrument findByInsName(String insName) {
		return dao.findByInsName(insName);
	}

	@Override
	public List<Map>  getDevIdType() {
		// TODO Auto-generated method stub
		List<Map> list=new ArrayList<Map>();
		List<Map<String, Object>> result = jdbcTemplate.queryForList(" select xh,dm from zd_sensor ");
		for (Map<String, Object> rsmap : result) {
			HashMap map = new HashMap();
			map.put("dm", rsmap.get("dm"));
			list.add(map);
		}
		return list;
	}
}
