package com.leidengyun.quartz.impl;

import com.leidengyun.common.SpringContextHolder;
import com.leidengyun.mvc.util.DateUtils;
import com.leidengyun.mvc.util.DateUtis2;
import com.leidengyun.quartz.IAutoSyncDevService;
import com.leidengyun.service.DevDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service("autoSyncDevServiceImpl")
public class AutoSyncDevServiceImpl implements IAutoSyncDevService {

	private static final Logger logger = LoggerFactory.getLogger(AutoSyncDevServiceImpl.class);
	
	private DevDataService devDataService;
	
	
	@PostConstruct
	public void init() throws Exception {
		// TODO Auto-generated method stub
		getDevDataService();
	}
	
	private DevDataService getDevDataService() {
		// TODO Auto-generated method stub
		if(devDataService==null){
			devDataService=SpringContextHolder.getBean(DevDataService.class);
		}
		return devDataService;
	}
	
	
	@Override
	
	/**
	 * 同步设备数据传输
	 */
	public void updateDeviceData() {
		// TODO Auto-generated method stub
		//日期
		logger.info("task is process.............");
		logger.info("执行时间   : "+DateUtis2.sdfDate2.format(DateUtis2.getCurDate()));
		try {
			String zzrq = DateUtis2.sdfDate0.format(DateUtils.addDay(new Date(), -1));
			//devDataService.ProcessDevData(null, zzrq, zzrq);
			
			
			this.getDevDataService().ProcessDevData(640100011, "2017-07-01", "2018-10-01");
			this.getDevDataService().ProcessDevData(131000001, "2017-07-01", "2018-10-01");
			this.getDevDataService().ProcessDevData(640100040, "2017-07-01", "2018-10-01");
			
//			this.getDevDataService().ProcessDevData(190100032, "2017-07-01", "2018-10-01");
//			this.getDevDataService().ProcessDevData(640100011, "2017-07-01", "2018-10-01");
//			this.getDevDataService().ProcessDevData(130100005, "2017-07-01", "2018-10-01");
//			this.getDevDataService().ProcessDevData(130100007, "2017-07-01", "2018-10-01");
//			this.getDevDataService().ProcessDevData(640100016, "2017-07-01", "2018-10-01");
//			this.getDevDataService().ProcessDevData(130100011, "2017-07-01", "2018-10-01");
//			this.getDevDataService().ProcessDevData(640100004, "2017-07-01", "2018-10-01");
//			
//			this.getDevDataService().ProcessDevData(130100054, "2017-07-01", "2018-10-01");
//			this.getDevDataService().ProcessDevData(130300001, "2017-07-01", "2018-10-01");
//			this.getDevDataService().ProcessDevData(130100053, "2017-07-01", "2018-10-01");
//			this.getDevDataService().ProcessDevData(130100010, "2017-07-01", "2018-10-01");
//			this.getDevDataService().ProcessDevData(170100016, "2017-07-01", "2018-10-01");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.debug("task is error :" ,e);
		}
		
		logger.info("task is end.............");
	}
	
	
	/**
	 * 同步设备数据传输
	 * 每天1分钟定时推送数据
	 */
	public void updateDeviceDataDay() {
		// TODO Auto-generated method stub
		//日期
		logger.info(" day task is process.............");
		logger.info("执行时间   : "+DateUtis2.sdfDate2.format(DateUtis2.getCurDate()));
		try {
			String zzrq = DateUtis2.sdfDate0.format(DateUtils.addDay(new Date(), 1));
			String qsrq = DateUtis2.sdfDate0.format(DateUtis2.getCurDate());
			this.getDevDataService().ProcessDevData(640100011, qsrq,zzrq);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.debug("task is error :" ,e);
		}
		logger.info("task is end.............");
	}
}
