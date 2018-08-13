package com.leidengyun.wms.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.leidengyun.mvc.enums.TrueFalseEnum;
import com.leidengyun.mvc.model.PersistentObject;

import java.util.Date;

/**
 * 
 * 
 * @author Joe
 */
public class Dev extends PersistentObject {

	private static final long serialVersionUID = 7902814112969375973L;
	
	/** 设备编号 */
	private String devId;
	/** 设备所在省名称  */
	private String devProvMc;
	/** 设备所在市名称  */
	private String devCityMc;
	/** 设备备注  */
	private String bz;
	
	/** 排序 */
	private Integer sort = Integer.valueOf(1);
	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/** 是否启用 */
	private Boolean isEnable = Boolean.valueOf(true);
	
	

	

	public Dev() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Dev(String devId, String devProvMc, String devCityMc, String bz, Integer sort, Date createTime,
			Boolean isEnable) {
		super();
		this.devId = devId;
		this.devProvMc = devProvMc;
		this.devCityMc = devCityMc;
		this.bz = bz;
		this.sort = sort;
		this.createTime = createTime;
		this.isEnable = isEnable;
	}



	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}



	public String getDevProvMc() {
		return devProvMc;
	}

	public void setDevProvMc(String devProvMc) {
		this.devProvMc = devProvMc;
	}

	public String getDevCityMc() {
		return devCityMc;
	}

	public void setDevCityMc(String devCityMc) {
		this.devCityMc = devCityMc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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
		return this.isEnable;
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
