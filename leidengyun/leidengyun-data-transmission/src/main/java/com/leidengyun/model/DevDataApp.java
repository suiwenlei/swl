package com.leidengyun.model;

import com.leidengyun.mvc.model.PersistentObject;

/**
 * 应用
 * 
 * @author Joe
 */
public class DevDataApp extends PersistentObject {

	private static final long serialVersionUID = 7902814112969375973L;
	
	/** 设备编号 */
	private String devId;
	/** 设备识别码  */
	private String devCode;
	/**采集日期*/
	private String devTime;
	/** 数据域长度  */
	private String devLength;
	/** 数据  */
	private String devData;
	/** 电流电压  */
	private Double loVolt;
	
	
	/** 数据域数组 */  
	private Object devTypeArray;
	
	/*数据名称*/
	private Object devNameArray;
	
	/*数值*/
	private Object devValueArray;
	
	private String ifSend;
	private String ifRevice;
	private String uuid;
	
	

	public String getDevId() {
		return devId;
	}



	public void setDevId(String devId) {
		this.devId = devId;
	}



	public String getDevCode() {
		return devCode;
	}



	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}



	public String getDevTime() {
		return devTime;
	}



	public void setDevTime(String devTime) {
		this.devTime = devTime;
	}



	public String getDevLength() {
		return devLength;
	}



	public void setDevLength(String devLength) {
		this.devLength = devLength;
	}



	public String getDevData() {
		return devData;
	}



	public void setDevData(String devData) {
		this.devData = devData;
	}


	public Double getLoVolt() {
		return loVolt;
	}



	public void setLoVolt(Double loVolt) {
		this.loVolt = loVolt;
	}



	public Object getDevTypeArray() {
		return devTypeArray;
	}



	public void setDevTypeArray(Object devTypeArray) {
		this.devTypeArray = devTypeArray;
	}



	public Object getDevNameArray() {
		return devNameArray;
	}



	public void setDevNameArray(Object devNameArray) {
		this.devNameArray = devNameArray;
	}



	public Object getDevValueArray() {
		return devValueArray;
	}



	public void setDevValueArray(Object devValueArray) {
		this.devValueArray = devValueArray;
	}



	public String getIfSend() {
		return ifSend;
	}



	public void setIfSend(String ifSend) {
		this.ifSend = ifSend;
	}



	public String getIfRevice() {
		return ifRevice;
	}



	public void setIfRevice(String ifRevice) {
		this.ifRevice = ifRevice;
	}



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	

	



	public DevDataApp(String devId, String devCode, String devTime, String devLength, String devData, Double loVolt,
			Object devTypeArray, Object devNameArray, Object devValueArray, String ifSend, String ifRevice,
			String uuid) {
		super();
		this.devId = devId;
		this.devCode = devCode;
		this.devTime = devTime;
		this.devLength = devLength;
		this.devData = devData;
		this.loVolt = loVolt;
		this.devTypeArray = devTypeArray;
		this.devNameArray = devNameArray;
		this.devValueArray = devValueArray;
		this.ifSend = ifSend;
		this.ifRevice = ifRevice;
		this.uuid = uuid;
	}



	public DevDataApp() {
		super();
		// TODO Auto-generated constructor stub
	}
}
