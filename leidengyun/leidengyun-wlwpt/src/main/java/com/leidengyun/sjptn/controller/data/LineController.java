package com.leidengyun.sjptn.controller.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.leidengyun.mvc.controller.BaseController;
import com.leidengyun.mvc.util.DateUtils;
import com.leidengyun.mvc.util.DateUtis2;
import com.leidengyun.sjptn.service.DevDataService;
import com.leidengyun.sjptn.service.DevService;
import com.leidengyun.sjptn.service.LineDataService;
import com.leidengyun.sso.client.SessionPermission;
import com.leidengyun.sso.client.SessionUtils;
import com.leidengyun.sso.rpc.RpcPermission;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author suiwenlei
 */
@Controller
@RequestMapping("/sjpt/line")
public class LineController extends BaseController {

	

	@Resource
	private DevDataService devDataService;
	@Resource
	private DevService devService;
	@Resource
	private LineDataService lineDataService;
	
	
	
	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(HttpServletRequest request,@ApiParam(value = "devId") Integer devId, Model model) {
		
		String zzrq = DateUtis2.sdfDate0.format(DateUtis2.getCurDate());
		String qsrq = DateUtis2.sdfDate0.format(DateUtils.addDay(new Date(), -1));
		SessionPermission sessionPermission = SessionUtils.getSessionPermission(request);
		List<Map> devList=new ArrayList<Map>();
		if (sessionPermission != null){
			List<RpcPermission> list = sessionPermission.getMenuList();
			for (RpcPermission rpcPermission : list) {
				if(rpcPermission.getUrl().indexOf("/sjpt/data?devId")>-1){
					HashMap map = new HashMap();
					map.put("name", rpcPermission.getName());
					devList.add(map);
				}
			}
			model.addAttribute("devList", devList);
		}
		model.addAttribute("devId", devId);
//		model.addAttribute("zzrq", zzrq);
//		model.addAttribute("qsrq", qsrq);
		return "/list/line";
	}
	
	@ApiOperation("折现图")
	@RequestMapping(value = "/showLine", method = RequestMethod.GET)
	public String showLine(
			@ApiParam(value = "devId") Integer devId,
			@ApiParam(value = "起始时间 ") String qsrq,
			@ApiParam(value = "终止时间 ") String zzrq, 
			Model model) {
		
		Map<String, Object> map = lineDataService.getDataForLine(devId, qsrq,zzrq);
		Object LineJsonObj = lineDataService.getGsonLine(map);
		String LineJson = JSONArray.toJSONString(LineJsonObj);
		model.addAttribute("LineJson", LineJson);
		//model.addAttribute("zzrq", zzrq);
		//model.addAttribute("qsrq", qsrq);
		return "/list/lines";
	}
}