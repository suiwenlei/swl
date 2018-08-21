package com.leidengyun.sjptn.controller.data;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.leidengyun.mvc.controller.BaseController;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.model.Result;
import com.leidengyun.mvc.validator.Validator;
import com.leidengyun.mvc.validator.annotation.ValidateParam;
import com.leidengyun.sjptn.model.DevDataApp;
import com.leidengyun.sjptn.service.DevDataAppService;
import com.leidengyun.sjptn.service.DevDataService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author suiwenlei
 */
@Controller
@Scope("prototype")
@RequestMapping("/sjpt/data")
public class DataController extends BaseController {
	
	@Resource
	private DevDataService devDataService;
	
	@Resource
	private DevDataAppService devDataAppService;
	
	
	
	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(@ApiParam(value = "devId") Integer devId,
						  @ApiParam(value = "type") String type,
						  Model model) {

		//String zzrq = DateUtis2.sdfDate20.format(DateUtis2.getCurDate());
		//String qsrq = DateUtis2.sdfDate20.format(DateUtils.addDay(new Date(), -1));
		String gsonTitle = devDataService.gsonDataTitle(devId,"1","","");
		model.addAttribute("gsonTitle", gsonTitle);
		model.addAttribute("devId", devId);
		model.addAttribute("type", "1");
		//model.addAttribute("zzrq", zzrq);
		//model.addAttribute("qsrq", qsrq);
		return "/list/init_data";
	}


	@ApiOperation("初始页")
	@RequestMapping(value = "/ls", method = RequestMethod.GET)
	public String execute_ls(@ApiParam(value = "devId") Integer devId,
						  @ApiParam(value = "type") String type,
						  Model model) {

		//String zzrq = DateUtis2.sdfDate20.format(DateUtis2.getCurDate());
		//String qsrq = DateUtis2.sdfDate20.format(DateUtils.addDay(new Date(), -1));
		String gsonTitle = devDataService.gsonDataTitle(devId,"2","","");
		model.addAttribute("gsonTitle", gsonTitle);
		model.addAttribute("devId", devId);
		model.addAttribute("type", "2");
		//model.addAttribute("qsrq", qsrq);
		return "/list/init_data";
	}


	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
			@ApiParam(value = "设备编号 ") String devId,
			@ApiParam(value = "设备编号 ") String type,
			@ApiParam(value = "起始时间 ") String qsrq,
			@ApiParam(value = "终止时间 ") String zzrq,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		Pagination<DevDataApp> page = devDataAppService.findPaginationByDevId(devId,type,qsrq,zzrq,new Pagination<DevDataApp>(pageNo, pageSize));
		return Result.createSuccessResult().setData(JSONArray.toJSON(page));
	}


	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		devDataService.deleteByIds(getAjaxForStrIds(ids));
		return Result.createSuccessResult();
	}
}