package com.leidengyun.wms.dao;

import com.leidengyun.mvc.dao.mybatis.Dao;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.wms.model.Instrument;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InstrumentDao extends Dao<Instrument, Integer> {
	
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	
	public List<Instrument> findPaginationByInsName(@Param("insName") String insName, Pagination<Instrument> p);
	
	public Instrument findByInsName(@Param("insName") String insName);
	
}