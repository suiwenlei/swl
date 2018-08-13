package com.leidengyun.sjptn.model;

import com.leidengyun.mvc.model.PersistentObject;

/**
 * 应用
 * 
 * @author Joe
 */
public class InsTData extends PersistentObject {

	private static final long serialVersionUID = 7902814112969375973L;
	
	/** 设备关联ID */
	private String guid;
	private String uuid;
	/**设备地址*/
	private String addr;
	/** 设备类型  */
	private String type;
	/** 仪器ID */
	private String instId;
	/** 数值 1 */
	private Double v ;
	/** 数值2 */
	private Double v1;
	

	public InsTData(){}


	public String getGuid() {
		return guid;
	}


	public void setGuid(String guid) {
		this.guid = guid;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getInstId() {
		return instId;
	}


	public void setInstId(String instId) {
		this.instId = instId;
	}


	public Double getV() {
		return v;
	}


	public void setV(Double v) {
		this.v = v;
	}


	public Double getV1() {
		return v1;
	}


	public void setV1(Double v1) {
		this.v1 = v1;
	}


	public InsTData(String guid, String uuid, String addr, String type, String instId, Double v, Double v1) {
		super();
		this.guid = guid;
		this.uuid = uuid;
		this.addr = addr;
		this.type = type;
		this.instId = instId;
		this.v = v;
		this.v1 = v1;
	}
}
