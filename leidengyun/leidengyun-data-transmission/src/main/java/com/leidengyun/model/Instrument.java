package com.leidengyun.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.leidengyun.mvc.enums.TrueFalseEnum;
import com.leidengyun.mvc.model.PersistentObject;

import java.util.Date;

/**
 * 
 * 
 * @author suiwenlei
 */
public class Instrument extends PersistentObject {

	private static final long serialVersionUID = 7902814112969375973L;
	

	/** 仪器编号 */
	private String insId;
	/** 设备类型  */
	private String insType;
	/** 仪器名称  */
	private String insName;
	/** 设备代码 */
	private String insDm;
	/** 仪器描述  */
	private String insDESC;
	/** 单位  */
	private String unit;
	/** 数量级  */
	private String magNitude;
	/** 仪器所挂参数个数 */
	private String pramNum;
	
	/** 排序 */
	private Integer sort = Integer.valueOf(1);
	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/** 是否启用 */
	private Boolean isEnable = Boolean.valueOf(true);
	
	

	

	public Instrument() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Instrument(String insId, String insType, String insName, String insDm, String insDESC, String unit,
			String magNitude, String pramNum, Integer sort, Date createTime, Boolean isEnable) {
		super();
		this.insId = insId;
		this.insType = insType;
		this.insName = insName;
		this.insDm = insDm;
		this.insDESC = insDESC;
		this.unit = unit;
		this.magNitude = magNitude;
		this.pramNum = pramNum;
		this.sort = sort;
		this.createTime = createTime;
		this.isEnable = isEnable;
	}


	public String getInsId() {
		return insId;
	}


	public void setInsId(String insId) {
		this.insId = insId;
	}


	public String getInsType() {
		return insType;
	}


	public void setInsType(String insType) {
		this.insType = insType;
	}


	public String getInsName() {
		return insName;
	}


	public void setInsName(String insName) {
		this.insName = insName;
	}


	public String getInsDm() {
		return insDm;
	}


	public void setInsDm(String insDm) {
		this.insDm = insDm;
	}


	public String getInsDESC() {
		return insDESC;
	}


	public void setInsDESC(String insDESC) {
		this.insDESC = insDESC;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getMagNitude() {
		return magNitude;
	}


	public void setMagNitude(String magNitude) {
		this.magNitude = magNitude;
	}


	public String getPramNum() {
		return pramNum;
	}


	public void setPramNum(String pramNum) {
		this.pramNum = pramNum;
	}


	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Boolean getIsEnable() {
		return isEnable;
	}


	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}



	/** 以下为显示辅助参数 */
	private Boolean isChecked = Boolean.valueOf(false);
	
	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	public String getIsEnableStr() {
		return (isEnable != null && isEnable) ? TrueFalseEnum.TRUE.getLabel() : TrueFalseEnum.FALSE.getLabel();
	}
}
