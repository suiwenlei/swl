package com.leidengyun.sjptn.service;

import java.util.List;
import java.util.Map;

public interface MapService{
	
	
	//为地图热点提供数据
	public Object countMapData(List<Map> devList,String flag);
	
	public String countMapTipsData(List<Map> devList);
}
