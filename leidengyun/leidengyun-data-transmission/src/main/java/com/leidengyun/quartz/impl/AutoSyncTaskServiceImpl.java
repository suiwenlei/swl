package com.leidengyun.quartz.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.leidengyun.common.StringUtis;
import com.leidengyun.dao.TableTaskJdbcDao;
import com.leidengyun.quartz.IAutoSyncTaskService;
import com.leidengyun.socket.util.JdbcUtis;
import com.mysql.jdbc.Connection;

@Service("autoSyncDevServiceImpl")
public class AutoSyncTaskServiceImpl implements IAutoSyncTaskService {

	private static final Logger logger = LoggerFactory.getLogger(AutoSyncTaskServiceImpl.class);
	
	@Override
	public void syncSysDeviceDataTask() {
		// TODO Auto-generated method stub
		
		//数据库连接
		logger.info("数据库开始连接.............");
		Connection con = null;
		try {con = JdbcUtis.getJdbcConn();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		TableTaskJdbcDao taskJdbcDao = new TableTaskJdbcDao();
		
		//当前设备  type 1
		String sql = "select DISTINCT devid from sys_dev ";
		
		List<Map<String, Object>> list=null;
		try {
			list = taskJdbcDao.read(con, sql);
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (Map<String, Object> map : list) {
			
			String devId=map.get("devId")==null?"":map.get("devId").toString();
			List<Map> list_day = this.getTitleList(Integer.valueOf(devId), "1", "", "");
			List<Map> list_ls = this.getTitleList(Integer.valueOf(devId), "2", "", "");
			
			logger.info("开始设备"+devId+"获取表头任务...当前设备数据..........");
			if(list_day.size()>0 && list_day!=null){
				Map<String, Object> map_day = list_day.get(0);
				map_day.put("devId", devId);
				map_day.put("type","1");
				
				try {
					taskJdbcDao.delete(con, Integer.valueOf(devId), "1");
					taskJdbcDao.insert(con, map_day);
				} catch (SQLException | IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			logger.info("开始设备"+devId+"获取表头任务...当前设备数据结束!..........");
			logger.info("开始设备"+devId+"获取表头任务...历史设备数据..........");
			if(list_ls.size()>0 && list_ls!=null){
				Map<String, Object> map_ls = list_ls.get(0);
				map_ls.put("devId", devId);
				map_ls.put("type","2");
				try {
					taskJdbcDao.delete(con, Integer.valueOf(devId), "2");
					taskJdbcDao.insert(con, map_ls);
				} catch (SQLException | IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			logger.info("开始设备"+devId+"获取表头任务...历史设备数据结束..........");
		}
	}
	
	private List<Map> getTitleList(Integer devId,String type, String qsrq, String zzrq) {
		// TODO Auto-generated method stub
		//数据库连接
		logger.info("数据库开始连接.............");
		Connection con = null;
		try {con = JdbcUtis.getJdbcConn();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		TableTaskJdbcDao taskJdbcDao = new TableTaskJdbcDao();
		
		List<Map> reslList = new ArrayList<Map>();
		Map<String, Object> map=null;
		String NameArray = "";
		String TypeArray = "";


		if("2".equals(type)){
			//需要做缓存处理当前数据,目前速度较慢
			String sql = " "
					+ "SELECT  devNameArray NameArray ,"
					+ "devTypeArray TypeArray  FROM sys_device_data t "
					+ "where t.devId='"+devId+"' group by devNameArray,devTypeArray order by id desc  ";
			List<Map<String, Object>> list=null;
			try {
				list = taskJdbcDao.read(con, sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringBuilder rs = new  StringBuilder();
			StringBuilder rs1 = new  StringBuilder();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map<String, Object> map2 = (Map<String, Object>) iterator.next();

				NameArray=map2.get("devNameArray") == null ? "" :
						map2.get("devNameArray").toString();
				TypeArray=map2.get("devTypeArray") == null ? "" :
						map2.get("devTypeArray").toString();

				rs.append(NameArray);
				rs.append("@");
				rs1.append(TypeArray);
				rs1.append("@");

			}
			List<Map> rslList = new ArrayList<Map>();
			Map<String, Object> rsmap=new HashMap<>();
			rsmap.put("NameArray", rs.toString());
			rsmap.put("TypeArray", rs1.toString());
			rslList.add(rsmap);

			if(rslList.size()>0 && rslList !=null){
				map = rslList.get(0);
				String NAR=(String)map.get("NameArray");
				String TAR=(String)map.get("TypeArray");
				String[] Narray =NAR.split("@");
				String[] Tarray = TAR.split("@");
				List _nList = Arrays.asList(Narray);
				List _tList = Arrays.asList(Tarray);
				List nList = new ArrayList(_nList);
				List tList = new ArrayList(_tList);
				StringUtis.removeDuplicate(nList);
				StringUtis.removeDuplicate(tList);
				StringBuilder nN = new  StringBuilder();
				StringBuilder tN = new  StringBuilder();
				for (Object nName : nList) {
					nN.append(nName);
					nN.append("@");
				}
				for (Object tName : tList) {
					tN.append(tName);
					tN.append("@");
				}
				map.put("NameArray", nN);
				map.put("TypeArray", tN);
			}
			reslList.add(map);

		}else if("1".equals(type)){

			String sql = " SELECT devNameArray NameArray ,devTypeArray TypeArray "
					+ " FROM sys_device_data t where t.devId='"+devId+"' order by id desc  ";
			List<Map<String, Object>> list=null;
			try {
				list = taskJdbcDao.read(con, sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Map<String, Object> rsmap=new HashMap<>();
			if(list.size()>0 && list !=null){
				Map map_last=list.get(0);
				String NameArray_last=map_last.get("devNameArray") == null ? "" :
						map_last.get("devNameArray").toString();
				String TypeArray_last=map_last.get("devTypeArray") == null ? "" :
						map_last.get("devTypeArray").toString();
				rsmap.put("NameArray", NameArray_last);
				rsmap.put("TypeArray", TypeArray_last);
				reslList.add(rsmap);
			}
		}
		return reslList;
	}
	
}
