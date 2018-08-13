package com.leidengyun.sjptn.service;

import java.util.Map;


public interface LineDataService   {
	
	public Map<String, Object> getDataForLine(Integer devId, String qsrq, String zzrq);
	public Object getGsonLine(Map<String, Object> map);
}
