package com.leidengyun.model;

import com.leidengyun.mvc.model.PersistentObject;

import java.util.List;

/**
 * 应用
 * 
 * @author Joe
 */
public class DevData extends PersistentObject {

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
	private float loVolt;
	
	private String length;
	
	private String uuid;
	
	private String guid;
	
	private List<InsTData> insTdata; //仪器数据
	
	private Object object; //MAP对象
	
	/** 数据域数组 */  
	private String devTypeAarry;
	
	/*数据名称*/
	private Object devNameArray;
	
	/*数值*/
	private Object devValueArray;
	
	private String qsrq;
	
	private String zzrq;
	
	private Integer ifsync;
	
	public DevData(){
	}


	public String getDevId() {
		return devId;
	}


	public void setDevId(String devId) {
		this.devId = devId;
	}
	
	public String getQsrq() {
		return qsrq;
	}


	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}


	public String getZzrq() {
		return zzrq;
	}


	public void setZzrq(String zzrq) {
		this.zzrq = zzrq;
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


	public float getLoVolt() {
		return loVolt;
	}


	public void setLoVolt(float loVolt) {
		this.loVolt = loVolt;
	}


	public String getDevTypeAarry() {
		return devTypeAarry;
	}


	public void setDevTypeAarry(String devTypeAarry) {
		this.devTypeAarry = devTypeAarry;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	

	public String getGuid() {
		return guid;
	}


	public void setGuid(String guid) {
		this.guid = guid;
	}


	public List<InsTData> getInsTdata() {
		return insTdata;
	}


	public void setInsTdata(List<InsTData> insTdata) {
		this.insTdata = insTdata;
	}


	public Object getObject() {
		return object;
	}


	public void setObject(Object object) {
		this.object = object;
	}
	

	public String getLength() {
		return length;
	}


	public void setLength(String length) {
		this.length = length;
	}


	
	public Integer getIfsync() {
		return ifsync;
	}


	public void setIfsync(Integer ifsync) {
		this.ifsync = ifsync;
	}


	

	public DevData(String devId, String devCode, String devTime, String devLength, String devData, float loVolt,
			String length, String uuid, String guid, List<InsTData> insTdata, Object object, String devTypeAarry,
			Object devNameArray, Object devValueArray, String qsrq, String zzrq, Integer ifsync) {
		super();
		this.devId = devId;
		this.devCode = devCode;
		this.devTime = devTime;
		this.devLength = devLength;
		this.devData = devData;
		this.loVolt = loVolt;
		this.length = length;
		this.uuid = uuid;
		this.guid = guid;
		this.insTdata = insTdata;
		this.object = object;
		this.devTypeAarry = devTypeAarry;
		this.devNameArray = devNameArray;
		this.devValueArray = devValueArray;
		this.qsrq = qsrq;
		this.zzrq = zzrq;
		this.ifsync = ifsync;
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
}
