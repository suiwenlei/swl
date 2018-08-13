package com.leidengyun.sjptn.model;

import java.util.List;

import com.leidengyun.mvc.model.PersistentObject;

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
	private String loVolt;
	/** 数据域数组 */
	private String devTypeAarry;
	
	private String uuid;
	
	private String guid;
	
	private List<InsTData> insTdata; //仪器数据
	
	private Object object; //MAP对象
	
	private String qsrq;
	
	private String zzrq;
	
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


	public String getLoVolt() {
		return loVolt;
	}


	public void setLoVolt(String loVolt) {
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


	public DevData(String devId, String devCode, String devTime, String devLength, String devData, String loVolt,
			String devTypeAarry, String uuid, String guid) {
		super();
		this.devId = devId;
		this.devCode = devCode;
		this.devTime = devTime;
		this.devLength = devLength;
		this.devData = devData;
		this.loVolt = loVolt;
		this.devTypeAarry = devTypeAarry;
		this.uuid = uuid;
		this.guid = guid;
	}


	public DevData(String devId, String devCode, String devTime, String devLength, String devData, String loVolt,
			String devTypeAarry, String uuid, String guid, List<InsTData> insTdata) {
		super();
		this.devId = devId;
		this.devCode = devCode;
		this.devTime = devTime;
		this.devLength = devLength;
		this.devData = devData;
		this.loVolt = loVolt;
		this.devTypeAarry = devTypeAarry;
		this.uuid = uuid;
		this.guid = guid;
		this.insTdata = insTdata;
	}


	public DevData(String devId, String devCode, String devTime, String devLength, String devData, String loVolt,
			String devTypeAarry, String uuid, String guid, List<InsTData> insTdata, Object object) {
		super();
		this.devId = devId;
		this.devCode = devCode;
		this.devTime = devTime;
		this.devLength = devLength;
		this.devData = devData;
		this.loVolt = loVolt;
		this.devTypeAarry = devTypeAarry;
		this.uuid = uuid;
		this.guid = guid;
		this.insTdata = insTdata;
		this.object = object;
	}
}
