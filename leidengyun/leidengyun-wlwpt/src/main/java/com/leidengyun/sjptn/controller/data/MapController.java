package com.leidengyun.sjptn.controller.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leidengyun.mvc.controller.BaseController;
import com.leidengyun.mvc.validator.Validator;
import com.leidengyun.mvc.validator.annotation.ValidateParam;
import com.leidengyun.sjptn.service.MapService;
import com.leidengyun.sso.client.SessionPermission;
import com.leidengyun.sso.client.SessionUtils;
import com.leidengyun.sso.rpc.RpcPermission;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author suiwenlei
 */
@Controller
@RequestMapping("/sjpt/map")
public class MapController extends BaseController {

	@Resource
	private MapService mapService;
	

	@ApiOperation("初始页")
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String execute(
			@ApiParam(value = "管理员id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer userId, Model model) {
		return "/map/map";
	}
	
	
	
	@ApiOperation("初始页")
	@RequestMapping(value = "/showMap", method = RequestMethod.GET)
	public String ShowMap(
			@ApiParam(value = "管理员id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer userId, 
			HttpServletRequest request,Model model) {
		
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
		}
		String GsonData = (String)mapService.countMapData(devList,"data");
		String GsonTips = mapService.countMapTipsData(devList);
		model.addAttribute("GsonData", GsonData);
		model.addAttribute("GsonTips", GsonTips);
		return "/map/maps";
	}
}