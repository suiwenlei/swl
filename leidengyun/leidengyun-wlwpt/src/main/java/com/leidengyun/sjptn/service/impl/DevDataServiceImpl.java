package com.leidengyun.sjptn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.mvc.util.StringUtils;
import com.leidengyun.sjptn.common.SortList;
import com.leidengyun.sjptn.common.StringUtis;
import com.leidengyun.sjptn.dao.DevDataDao;
import com.leidengyun.sjptn.model.DevData;
import com.leidengyun.sjptn.model.InsTData;
import com.leidengyun.sjptn.model.TitleModel;
import com.leidengyun.sjptn.service.DevDataAppService;
import com.leidengyun.sjptn.service.DevDataService;
import com.leidengyun.sjptn.service.InsTDataService;

@Service("devDataService")
public class DevDataServiceImpl extends ServiceImpl<DevDataDao, DevData, Integer> implements DevDataService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	InsTDataService insTataService;
	
	@Resource
	DevDataAppService devDataAppService;
	
	private String[] indexXh = { "a-", "c-", "b-" };
	private String[] indexArray = { "id", "devTime", "loVolt" };
	private String[] nameArray = { "标识", "采集时间", "电池电压" };
	
	@Override
	public void deleteByIds(List<String> idList) {
		// TODO Auto-generated method stub
		devDataAppService.deleteByIds(idList);//先删除字表数据
	}
	
	@Override
	public DevData findByDevId(String devId) {
		return dao.findByDevId(devId);
	}

	@Override
	public List<DevData> findDevList(Integer devId, String qsrq, String zzrq) {
		// TODO Auto-generated method stub
		return dao.findDevList(devId, qsrq, zzrq);
	}
	
	@Override
	public Pagination<DevData> findPaginationByDevId(String devId,String qsrq, String zzrq, Pagination<DevData> p) {
		
		List<DevData> list = p.getList();
		Map<String, Object> dataMap = new HashMap();
		for (DevData devData : list) {
			List<InsTData> insTlist = insTataService.findByDevId(devData.getGuid());
			for (InsTData insTData : insTlist) {
				Map<String, Object> zdMap = this.getZdMapInst(insTData.getType(), insTData.getInstId(),"");
				if(null==zdMap){
					break;
				}
				if(Integer.parseInt(zdMap.get("PRAMNUM").toString())==2){
					dataMap.put(insTData.getAddr()+"-"+insTData.getType()+insTData.getInstId()+ "-",
							StringUtis.formatZero(insTData.getV1(),Double.valueOf(zdMap.get("MAGNITUDE").toString())));
		            dataMap.put(insTData.getAddr()+"-"+insTData.getType()+insTData.getInstId(),
		            		StringUtis.formatZero(insTData.getV(),Double.valueOf(zdMap.get("MAGNITUDE").toString())));
		            break;
				}
				dataMap.put(insTData.getAddr()+"-"+insTData.getType()+insTData.getInstId(),
						StringUtis.formatZero(insTData.getV(),Double.valueOf(zdMap.get("MAGNITUDE").toString())));
			}
			devData.setObject(dataMap);
		}
		p.setList(list);
		return p;
	}

	@Override
	public Map<String, Object> getZdMapInst(String type, String isntId, String A){
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

	@Override
	public String gsonDataTitle(Integer devId,String type,String qsrq,String zzrq) {
	
		Map<String, Object> map=null;
		String NameArray = "";
		String TypeArray = "";
		try {
			List<Map> list = getTitleList(devId,type,qsrq, zzrq);
			if(list.size()>0 && list!=null){
				map=list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<TitleModel> modelList = new ArrayList<TitleModel>();
		
		//固定表头内容
		int length = this.nameArray.length;
		for (int i = 0; i < length; i++) {
			TitleModel model = new TitleModel();
			if(i==0){model.setHide(true);
			}else{model.setHide(false);}
			model.setMobileHide(false);
			model.setField(indexArray[i]);
			model.setTitle(nameArray[i]);
			model.setTypeArray(indexXh[i]);
			modelList.add(model);
		}
		
		//动态表头内容
		if(null!=map){
			NameArray=map.get("NameArray") == null ? "" : map.get("NameArray").toString();
			TypeArray=map.get("TypeArray") == null ? "" : map.get("TypeArray").toString();
		}


		if (!StringUtils.isBlank(TypeArray)) {
			String[] Narray = NameArray.split("@");
			String[] Tarray = TypeArray.split("@");
			for (int i = 0; i < Tarray.length; i++) {
				TitleModel model = new TitleModel();
				model.setHide(false);
				model.setMobileHide(false);
				model.setField("object." + Tarray[i]);
				model.setTitle(Narray[i]);
				model.setTypeArray(Tarray[i]);
				modelList.add(model);
			}
		}
		if("2".equals(type)){ //只有历史数据需要排序
			SortList<TitleModel> sortList = new SortList<TitleModel>();  
	        sortList.Sort(modelList, "getTypeArray", "desc"); 
		}
		String JsonTitle = JSONArray.toJSONString(modelList);
		return JsonTitle;
	}

	@Override
	@Autowired
	public void setDao(DevDataDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Map> getTitleList(Integer devId,String type, String qsrq, String zzrq) {
		// TODO Auto-generated method stub
		
		List<Map> reslList = new ArrayList<Map>();
		Map<String, Object> map=null;
		String NameArray = "";
		String TypeArray = "";


		if("2".equals(type)){
			//需要做缓存处理当前数据,目前速度较慢
			String sql = "select * from sys_dev_title t where t.devId='"+devId+"' and t.type='"+type+"' ";
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			Map<String, Object> rsmap=new HashMap<>();
			if(list.size()>0 && list !=null){
				Map map_last=list.get(0);
				String NameArray_last=map_last.get("NameArray") == null ? "" :
						map_last.get("NameArray").toString();
				String TypeArray_last=map_last.get("TypeArray") == null ? "" :
						map_last.get("TypeArray").toString();
				rsmap.put("NameArray", NameArray_last);
				rsmap.put("TypeArray", TypeArray_last);
				reslList.add(rsmap);
			}
			reslList.add(map);
			

		}else if("1".equals(type)){

			String sql = "select * from sys_dev_title t where t.devId='"+devId+"' and t.type='"+type+"' ";
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			Map<String, Object> rsmap=new HashMap<>();
			if(list.size()>0 && list !=null){
				Map map_last=list.get(0);
				String NameArray_last=map_last.get("NameArray") == null ? "" :
						map_last.get("NameArray").toString();
				String TypeArray_last=map_last.get("TypeArray") == null ? "" :
						map_last.get("TypeArray").toString();
				rsmap.put("NameArray", NameArray_last);
				rsmap.put("TypeArray", TypeArray_last);
				reslList.add(rsmap);
			}
		}
		return reslList;
	}


	public String getLastCondition(Integer devId){
	    String title="";
		List<Map> list= getTitleList(devId,"1", "", "");
		if(list.size()>0 && list !=null) {
			Map map = list.get(0);
			title = map.get("TypeArray") == null ? "" :
					map.get("TypeArray").toString();
		}
		return title;
	}
}
