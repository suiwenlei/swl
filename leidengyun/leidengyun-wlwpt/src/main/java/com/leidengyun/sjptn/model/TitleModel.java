package com.leidengyun.sjptn.model;

public class TitleModel {
	private String field;
	private String title;
	private String typeArray;
	private Integer width;
	private Boolean hide;
	private Boolean mobileHide;
	
	

	public TitleModel(String field, String title, String typeArray, Integer width, Boolean hide, Boolean mobileHide) {
		super();
		this.field = field;
		this.title = title;
		this.typeArray = typeArray;
		this.width = width;
		this.hide = hide;
		this.mobileHide = mobileHide;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public TitleModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getHide() {
		return hide;
	}

	public void setHide(Boolean hide) {
		this.hide = hide;
	}

	public Boolean getMobileHide() {
		return mobileHide;
	}

	public void setMobileHide(Boolean mobileHide) {
		this.mobileHide = mobileHide;
	}

	public String getTypeArray() {
		return typeArray;
	}

	public void setTypeArray(String typeArray) {
		this.typeArray = typeArray;
	}
	
}
