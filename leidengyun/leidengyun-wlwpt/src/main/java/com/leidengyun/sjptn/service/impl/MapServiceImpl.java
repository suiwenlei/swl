package com.leidengyun.sjptn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.leidengyun.mvc.util.StringUtils;
import com.leidengyun.sjptn.service.MapService;

@Service("mapService")
public class MapServiceImpl  implements MapService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Object countMapData(List<Map> devList,String flag){
		
		
		// TODO Auto-generated method stub
		String sql="select substring_index(b.devCityMc,'市',1) name,count(*) value,count(*) presentSum,"
				+ " GROUP_CONCAT(b.devid) devId from ( select a.id,a.devid,a.bz,a.sort,a.createTime,a.isEnable,"
				+ " (select name from china b where b.id=RPAD(substr(a.devid,1,2),6,0)) devProvMc,"
				+ " (select name from china c where c.id=substr(a.devId,1,6)) devCityMc "
				+ " from sys_dev a CONDITION ) b where b.devCityMc is not null group by devCityMc";
		
		StringBuffer br = new StringBuffer();
		StringBuffer br1 = new StringBuffer();
		br.append(" where a.devId in (");
		for (Map map : devList) {
			br.append("'"+map.get("name")+"'");
			br.append(",");
		}
		String sql1 = br.toString().substring(0, br.toString().length()-1);
		br1.append(sql1);
		br1.append(")");
		
		if(StringUtils.isNotBlank(br)){
			sql=sql.replaceAll("CONDITION",br1.toString());
		}else{
			sql=sql.replaceAll("CONDITION","");
		}
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
	
		if("tips"== flag){
			return list;
		}
		return JSONArray.toJSONString(list);
	}
	
	
	public String countMapTipsData(List<Map> devList){
		
		List<Map<String,Object>> tipList=(List<Map<String,Object>>)this.countMapData(devList,"tips");
		Map<String,Object> result=new HashMap<String,Object>();
		for (Map<String, Object> map : tipList) {
			Map<String,Object> rsMap=new HashMap<String,Object>();
			rsMap.put("presentSum", map.get("PRESENTSUM"));
			rsMap.put("presentDevId", map.get("DEVID").toString());
			result.put(map.get("NAME").toString(),rsMap);
		}
		return JSONArray.toJSONString(result);
	}
}
