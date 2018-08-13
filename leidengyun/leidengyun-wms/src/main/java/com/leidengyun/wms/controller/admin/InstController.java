package com.leidengyun.wms.controller.admin;

import com.leidengyun.mvc.controller.BaseController;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.model.Result;
import com.leidengyun.mvc.model.ResultCode;
import com.leidengyun.mvc.validator.Validator;
import com.leidengyun.mvc.validator.annotation.ValidateParam;
import com.leidengyun.wms.model.Instrument;
import com.leidengyun.wms.service.InstrumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author suiwenlei
 */
@Api(tags = "仪器管理")
@Controller
@RequestMapping("/admin/inst")
public class InstController extends BaseController {

	@Resource
	private InstrumentService instrumentService;
	

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute() {
		return "/instrument/instrument";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		Instrument instru;
		if (id == null) {
			instru = new Instrument();
		}
		else {
			instru = instrumentService.get(id);
		}
		List<Map> list = instrumentService.getDevIdType();
		model.addAttribute("instru", instru);
		model.addAttribute("devIdTypeList", list);
		return "/instrument/instrumentEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
			@ApiParam(value = "仪器名称 ") String insName,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) throws UnsupportedEncodingException {
		
		insName = new String(insName.getBytes("ISO-8859-1"),"utf-8");
		return Result.createSuccessResult().setData(instrumentService.findPaginationByInsName(insName,new Pagination<Instrument>(pageNo, pageSize)));
	}

	@ApiOperation("验证设备ID")
	@RequestMapping(value = "/validateCode", method = RequestMethod.POST)
	public @ResponseBody Result validateCode(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "仪器名称", required = true) @ValidateParam({ Validator.NOT_BLANK }) String insName) {
		Result result = Result.createSuccessResult();
		Instrument db = instrumentService.findByInsName(insName);
		if (null != db && !db.getId().equals(id)) {
			result.setCode(ResultCode.ERROR).setMessage("仪器已存在");
		}
		return result;
	}

	@ApiOperation("启用/禁用")
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Result enable(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		instrumentService.enable(isEnable, getAjaxIds(ids));
		return Result.createSuccessResult();
	}

	@ApiOperation("新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "仪器编号", required = true) @ValidateParam({ Validator.NOT_BLANK }) String insId,
			@ApiParam(value = "设备类型", required = true) @ValidateParam({ Validator.NOT_BLANK }) String insType,
			@ApiParam(value = "仪器名称", required = true) @ValidateParam({ Validator.NOT_BLANK }) String insName,
			@ApiParam(value = "设备代码", required = true) @ValidateParam({ Validator.NOT_BLANK }) String insDm,
			@ApiParam(value = "仪器描述", required = true) @ValidateParam({ Validator.NOT_BLANK }) String insDESC,
			@ApiParam(value = "单位", required = true) @ValidateParam({ Validator.NOT_BLANK }) String unit,
			@ApiParam(value = "数量级", required = true) @ValidateParam({ Validator.NOT_BLANK }) String magNitude,
			@ApiParam(value = "仪器所挂参数个数", required = true) @ValidateParam({ Validator.NOT_BLANK }) String pramNum,
			@ApiParam(value = "是否有效", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable,
			@ApiParam(value = "排序", required = true) @ValidateParam({ Validator.NOT_BLANK, Validator.INT }) Integer sort) {
		Instrument instru;
		if (id == null) {
			instru = new Instrument();
			instru.setCreateTime(new Date());
		}
		else {
			instru = instrumentService.get(id);
		}
		instru.setInsId(insId);
		instru.setInsDm(insDm);
		instru.setInsDESC(insDESC);
		instru.setInsName(insName);
		instru.setInsType(insType);
		instru.setMagNitude(magNitude);
		instru.setPramNum(pramNum);
		instru.setUnit(unit);
		
		instru.setSort(sort);
		instru.setIsEnable(isEnable);
	
		instrumentService.save(instru);
		return Result.createSuccessResult();
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		instrumentService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}
}