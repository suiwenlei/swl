package com.leidengyun.service.impl;

import com.leidengyun.common.StringUtis;
import com.leidengyun.dao.DevDataDao;
import com.leidengyun.model.DevData;
import com.leidengyun.model.DevDataApp;
import com.leidengyun.model.InsTData;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.service.mybatis.impl.ServiceImpl;
import com.leidengyun.mvc.util.StringUtils;
import com.leidengyun.service.DevDataAppService;
import com.leidengyun.service.DevDataService;
import com.leidengyun.service.InsTDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("devDataService")
public class DevDataServiceImpl extends ServiceImpl<DevDataDao, DevData, Integer> implements DevDataService {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	InsTDataService insTataService;
	
	
	@Resource
	DevDataAppService devDataAppService;

	@Override
	@Autowired
	public void setDao(DevDataDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Pagination<DevData> findPaginationByDevId(String devId, String qsrq, String zzrq, Pagination<DevData> p) {
		dao.findPaginationByDevId(devId,qsrq,zzrq,p);
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
	public DevData findByDevId(String devId) {
		return dao.findByDevId(devId);
	}
	
	@Override
	public Map<String, Object> getZdMapInst(String type, String isntId, String A) {
		if (StringUtils.isNotBlank(A)) {
			try {
				return jdbcTemplate
						.queryForMap("select * "
								+ "from sys_instrument a where "
								+ "concat(a.insType,a.insId)='" + A + "' ");
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
		try {
			return jdbcTemplate.queryForMap(
					"select * "
					+ "from sys_instrument a "
					+ "where a.insId='" + isntId + "'" + "and a.insType='" + type + "'");
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@Override
	public void deleteByIds(List<String> idList) {
		// TODO Auto-generated method stub
		insTataService.deleteInstByIds(idList);//先删除字表数据
		verifyRows(dao.deleteByIds(idList), idList.size(), "数据库删除失败");
	}

	@SuppressWarnings("unused")
	@Override
	public void ProcessDevData(Integer devId, String qsrq, String zzrq) {

		// 获取数据源
		try {

			logger.info("data process...............");
			List<DevData> list = dao.findDevList(devId, qsrq, zzrq);
			logger.info("data is total ： " + list.size());

			if (null == list || list.size() == 0) {
				return;
			}
			// TODO Auto-generated method stub
			Map<String, Object> dataMap = new HashMap();
			for (DevData devData : list) {

				String devTypeAarry = "";
				String devNameArray = "";
				String devValueArray = "";

				List<InsTData> insTlist = insTataService.findByDevId(devData.getGuid());
				for (InsTData insTData : insTlist) {
					Map<String, Object> zdMap = this.getZdMapInst(insTData.getType(), insTData.getInstId(), "");
					if (null != zdMap) {

						if (Integer.parseInt(zdMap.get("PRAMNUM").toString()) == 2) {

							devTypeAarry += insTData.getAddr() + "-" + insTData.getType() + insTData.getInstId() + "@";
							devTypeAarry += insTData.getAddr() + "-" + insTData.getType() + insTData.getInstId() + "-@";
							
							
							devValueArray += StringUtis.formatZero(insTData.getV(),
									Double.valueOf(zdMap.get("MAGNITUDE").toString())) + "@";
							
							devValueArray += StringUtis.formatZero(insTData.getV1(),
									Double.valueOf(zdMap.get("MAGNITUDE").toString())) + "@";
							
							
							devNameArray+="(" + insTData.getAddr() + ")"
							+ zdMap.get("INSNAME").toString().substring(0, 2) + "温度" + "["
							+ zdMap.get("UNIT").toString().split(",")[0] + "]@";
							
							devNameArray+="(" + insTData.getAddr() + ")"
							+ zdMap.get("INSNAME").toString().substring(0, 2) + "湿度" + "["
							+ zdMap.get("UNIT").toString().split(",")[1] + "]@";
					
						} else {
							devTypeAarry += insTData.getAddr() + "-" + insTData.getType() + insTData.getInstId() + "@";
							devValueArray += StringUtis.formatZero(insTData.getV(),
									Double.valueOf(zdMap.get("MAGNITUDE").toString())) + "@";
							devNameArray+="(" + insTData.getAddr() + ")"
									+ zdMap.get("INSNAME").toString()+ "["
									+ zdMap.get("UNIT").toString() + "]@";
						}

					}
					devData.setDevTypeAarry(devTypeAarry.substring(0, devTypeAarry.length() - 1));
					devData.setDevValueArray(devValueArray.substring(0, devValueArray.length() - 1));
					devData.setDevNameArray(devNameArray.substring(0, devNameArray.length() - 1));

					// 封装对象，入库保存
					
					//1.删除新表数据
					devDataAppService.deleteByUuid(devData.getGuid());
					//2.向新表插入新计算过的数据	
					DevDataApp devApp = new DevDataApp();
					devApp.setDevId(devData.getDevId());
					devApp.setDevCode(devData.getDevCode());
					devApp.setDevLength(devData.getLength());
					devApp.setDevTime(devData.getDevTime());
					devApp.setDevData(devData.getDevData());
					devApp.setLoVolt(Double.valueOf(devData.getLoVolt()));
					devApp.setDevNameArray(devData.getDevNameArray());
					devApp.setDevTypeArray(devData.getDevTypeAarry());
					devApp.setDevValueArray(devData.getDevValueArray());
					devApp.setIfRevice("Y");
					devApp.setUuid(devData.getGuid());
					devDataAppService.save(devApp);
					//3.修改原数据表数据状态
					this.updateDevDataByuuid(devData.getGuid(),1);
					//this.deleteByUuid(devData.getGuid());
				}
			}

			logger.info("data ending................");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.debug("data is error ：",e);
		}

	}

	@Override
	public void deleteByUuid(String uuid) {
		// TODO Auto-generated method stub
		insTataService.deleteInstByUuid(uuid);//删除从表数据
		dao.deleteByUuid(uuid);//删除主表数据
	}
	
	@Override
	public int updateDevDataByuuid(String guid, int ifsync){
		return dao.updateDevDataByuuid(guid,ifsync);
	}
}
