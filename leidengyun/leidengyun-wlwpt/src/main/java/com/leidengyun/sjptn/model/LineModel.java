package com.leidengyun.sjptn.model;

public class LineModel {
	
	
	private Object x;  //存储x值
	
	private Object tArray; //存储ledend
	
	private Object vArray; //存储数据

	public Object getX() {
		return x;
	}

	public void setX(Object x) {
		this.x = x;
	}

	public Object gettArray() {
		return tArray;
	}

	public void settArray(Object tArray) {
		this.tArray = tArray;
	}

	public Object getvArray() {
		return vArray;
	}

	public void setvArray(Object vArray) {
		this.vArray = vArray;
	}

	public LineModel(Object x, Object tArray, Object vArray) {
		super();
		this.x = x;
		this.tArray = tArray;
		this.vArray = vArray;
	}

	public LineModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	


	
}
