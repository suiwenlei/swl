package com.leidengyun.sjptn.model;

import com.github.abel533.echarts.axis.ValueAxis;

public class ValueAxisNew extends ValueAxis {
	private Integer offset;

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public ValueAxisNew() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValueAxisNew(Integer offset) {
		super();
		this.offset = offset;
	}
	
	
	
}
