package com.leidengyun.sjptn.service.impl;

import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.mvc.util.StringUtils;
import com.leidengyun.sjptn.dao.DevDataAppDao;
import com.leidengyun.sjptn.model.DevDataApp;
import com.leidengyun.sjptn.service.DevDataAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("devDataAppService")
public class DevDataAppServiceImpl extends ServiceImpl<DevDataAppDao, DevDataApp, Integer> implements DevDataAppService {
		
	@Override
	@Autowired
	public void setDao(DevDataAppDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void save(DevDataApp t) {
		super.save(t);
	}

	@Override
	public Pagination<DevDataApp> findPaginationByDevId(String devId, String qsrq, String zzrq,
			Pagination<DevDataApp> p) {
		// TODO Auto-generated method stub
		dao.findPaginationByDevId(devId, qsrq, zzrq, p);
		
		
		List<DevDataApp> list = p.getList();
		
		String ValueArray = "";
		String TypeArray = "";

		for (DevDataApp devDataApp : list) {
			Map<String, Object> dataMap = new HashMap();
			String devTypeArray = devDataApp.getDevTypeArray().toString();
			ValueArray = devDataApp.getDevValueArray() == null ? "" : devDataApp.getDevValueArray().toString();
			TypeArray = devDataApp.getDevTypeArray() == null ? "" : devDataApp.getDevTypeArray().toString();

			if (!StringUtils.isBlank(TypeArray)) {
				String[] Varray = ValueArray.split("@");
				String[] Tarray = TypeArray.split("@");
				for (int i = 0; i < Tarray.length; i++) {

					dataMap.put(Tarray[i], Varray[i]);
					devDataApp.setObject(dataMap);
				}
			}
			
		}
		p.setList(list);
		return p;
	}

	@Override
	public void deleteByIds(List<String> idList) {
		// TODO Auto-generated method stub
		verifyRows(dao.deleteByIds(idList),idList.size(),"数据库删除失败");
	}

	@Override
	public List<DevDataApp> findDevDataAppList(Integer devId, String qsrq, String zzrq) {
		// TODO Auto-generated method stub
		return dao.findDevDataAppList(devId, qsrq, zzrq);
	}
}
