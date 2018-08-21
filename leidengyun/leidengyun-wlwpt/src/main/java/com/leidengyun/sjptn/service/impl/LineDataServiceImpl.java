package com.leidengyun.sjptn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.github.abel533.echarts.AxisPointer;
import com.github.abel533.echarts.DataZoom;
import com.github.abel533.echarts.Grid;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.AxisTick;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Series;
import com.leidengyun.mvc.util.StringUtils;
import com.leidengyun.sjptn.model.DevDataApp;
import com.leidengyun.sjptn.model.LineModel;
import com.leidengyun.sjptn.model.ValueAxisNew;
import com.leidengyun.sjptn.service.DevDataAppService;
import com.leidengyun.sjptn.service.DevDataService;
import com.leidengyun.sjptn.service.InsTDataService;
import com.leidengyun.sjptn.service.LineDataService;

@Service("lineDataService")
public class LineDataServiceImpl  implements LineDataService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Resource
	InsTDataService insTataService;
	@Resource
	DevDataService devDataService;
	@Resource
	DevDataAppService devDataAppService;
	
	@Override
	public Map<String, Object> getDataForLine(Integer devId, String qsrq, String zzrq) {
		// TODO Auto-generated method stub
		List<LineModel> lineList = new ArrayList();
		List<Object> xList = new ArrayList();
		List<Object> typeList = new ArrayList();
		List<Object> legList = new ArrayList();
		Map resultMap = new HashMap<String,Object>();
		List<DevDataApp> list = devDataAppService.findDevDataAppList(devId,"1",qsrq, zzrq);
		//x轴数据】
		if(null==list || list.size()==0){
			return null;
		}
		for (DevDataApp devDataApp : list) {
			LineModel model = new LineModel();
			model.setX(devDataApp.getDevTime());
			model.settArray(devDataApp.getDevTypeArray());
			model.setvArray(devDataApp.getDevValueArray());
			lineList.add(model);
			xList.add(devDataApp.getDevTime());
		}
		Map dataMap = new HashMap<String,Double>();
		
		for (int i = 0; i < lineList.size(); i++) {
			
			LineModel model = (LineModel)lineList.get(i);
			
			String TArray = model.gettArray() == null ? "" : model.gettArray().toString();
			String VArray = model.getvArray() == null ? "" : model.getvArray().toString();
			if (!StringUtils.isBlank(TArray)) {
				String[] TArrays = TArray.split("@");
				String[] VArrays = VArray.split("@");
				for (int j = 0; j < TArrays.length; j++) {
					dataMap.put(i+"-"+TArrays[j],VArrays[j].replaceAll("-", "0"));
				}
			}
		}
		
		//获取表头
		
		Map legendMap = new HashMap<String,Double>();
		try {
			List<Map> titleList = devDataService.getTitleList(devId,"1", qsrq, zzrq);
			if(titleList.size()>0 && titleList !=null){
				Map<String, Object> map = titleList.get(0);
				legList.add(map.get("NameArray").toString());
				typeList.add(map.get("TypeArray").toString());
			}
		} catch (Exception e) {}
		
		resultMap.put("dataMap", dataMap);
		resultMap.put("legList", legList);
		resultMap.put("typeList", typeList);
		resultMap.put("xList", xList);
		return resultMap;
	}

	@Override
	public Object getGsonLine(Map<String, Object> map) {
		// TODO Auto-generated method stub
		GsonOption option = new GsonOption();
		if(null!=map){
			Title title = new Title();
			Tooltip tooltip = new Tooltip();

			/*************** tooltip start *********************/
			tooltip.setTrigger(Trigger.axis);
			AxisPointer axisPointer = new AxisPointer();
			axisPointer.setType(PointerType.cross);
			tooltip.setAxisPointer(axisPointer);
			
			
			title.setText("物联网监测数据指标变化分析图");
		    title.setX(X.center);
		   // title.setY(Y.top);
		    //tooltip.setTrigger(Trigger.item);
		    tooltip.showDelay(Integer.valueOf(0));
			
			/*************** tooltip end *********************/
		    
		    /*************** DataZoom start *********************/

		    DataZoom dataZoom = new DataZoom();
		    dataZoom.setShow(Boolean.valueOf(true));
		    dataZoom.setRealtime(Boolean.valueOf(true));
		    dataZoom.setStart(Integer.valueOf(0));
		    dataZoom.setEnd(Integer.valueOf(100));
		    
		    /*************** DataZoom end *********************/
		    
			/*************** grid start *********************/
			Grid grid = new Grid();
			grid.setTop("20%");
			grid.setShow(true);
			/*************** grid end *********************/

			/*************** toolbox start *********************/
			Toolbox toolbox = new Toolbox();
			toolbox.setShow(Boolean.valueOf(true));
		    toolbox.setOrient(Orient.vertical);
		    toolbox.setX(X.left);
		    toolbox.setY(Y.center);
			Map<String, Feature> featrue = new HashMap();
			
			List<Object> type = new ArrayList();
		    type.add("line");type.add("bar");
		    featrue.put("magicType", Feature.magicType.show(Boolean.valueOf(true)).type(type));
			featrue.put("dataView", Feature.dataView.show(Boolean.valueOf(true)));
			featrue.put("restore", Feature.restore.show(Boolean.valueOf(true)));
			featrue.put("saveAsImage", Feature.saveAsImage.show(Boolean.valueOf(true)));
			toolbox.setFeature(featrue);
			
			
			
			/*************** toolbox end *********************/

			/*************** xAxis start *********************/
			CategoryAxis xAxis = new CategoryAxis();
			List<Axis> yAxisList = new ArrayList();
			List<Series> seriesList = new ArrayList();

			xAxis.setType(AxisType.category);
			AxisTick tick = new AxisTick();
			//tick.setInside(true);
			xAxis.setAxisTick(tick);
			List timeList = (List) map.get("xList");
			xAxis.setData(timeList);
			/*************** xAxis end *********************/

			/*************** legend start *********************/
			Legend legend = new Legend();
			legend.setOrient(Orient.horizontal);
		    legend.setY(Integer.valueOf(40));
		    legend.setX(X.center);
			List<Object> legList = (List) map.get("legList");
			List<Object> legsList = new ArrayList();
			String legs =(String) legList.get(0);
			String[] legsArray = legs.split("@");
			for (int i = 0; i < legsArray.length; i++) {
				legsList.add(legsArray[i]);
			}
			legend.setData(legsList);

			/*************** legend end *********************/

			/*************** yAxis start *********************/

			for (Object name : legList) {

				String[] lenList = name.toString().split("@");
				for (int i = 0; i < lenList.length; i++) {
					String header = (String) lenList[i];
					ValueAxisNew yAxis1 = new ValueAxisNew();
					yAxis1.setType(AxisType.value);
					if (i == 0) {
						//yAxis1.setName(header);
						yAxis1.setPosition("left");
					}else if(i == 1){
						//yAxis1.setName(header);
						yAxis1.setPosition("right");
					}else{
						//yAxis1.setName(header);
						yAxis1.setPosition("right");
						
					}
			
					AxisLabel label1 = new AxisLabel();
					String unit = getUnit(header);
					label1.setFormatter("{value} ");
					yAxis1.setAxisLabel(label1);
					yAxisList.add(yAxis1);
				}
			}

			/*************** yAxis end *********************/

			/*************** series start *********************/

			Map dataMap = (Map) map.get("dataMap");
			List typeList = (List) map.get("typeList");
			Object obj = typeList.get(0);
			String u = (String) obj;
			String[] uarray = u.split("@");
			for (Object legArry : legList) {
				String[] legArrys = legArry.toString().split("@");
				for (int i = 0; i < legArrys.length; i++) {
					String name = legArrys[i];
					Line line = new Line();
					line.setName(name);
					line.setType(SeriesType.line);
					if(i!=0){
					line.setyAxisIndex(1);
					}
					List<Double> dataList = new ArrayList<Double>();
					for (int j = 0; j < timeList.size(); j++) {
						dataList.add(dataMap.get(j + "-" + uarray[i])==null?0.0:
							Double.valueOf(dataMap.get(j + "-" + uarray[i]).toString().replace(",","")));
						
					}
					line.setData(dataList);
					seriesList.add(line);
				}
			}

			/*************** series end *********************/
			option.title(title)
				  .tooltip(tooltip)
				  .legend(legend)
				  .toolbox(toolbox)
				  .calculable(Boolean.valueOf(true))
				  .dataZoom(dataZoom)
				  .grid(grid)
				  .xAxis(new Axis[] { xAxis })
				  .yAxis(yAxisList).series(seriesList);
		}
		return com.alibaba.fastjson.JSONArray.toJSON(option);
	}

	private String getUnit(String header) {
		String unit="";
		String regex = "(?<=\\[)(\\S+)(?=\\])";  
		Pattern pattern = Pattern.compile (regex);  
		Matcher matcher = pattern.matcher (header);  
		while (matcher.find ())  
		{  
			unit=matcher.group();  
		}
		return unit;
	}	
}
