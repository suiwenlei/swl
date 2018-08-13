package com.leidengyun.wms.controller.admin;

import com.leidengyun.mvc.controller.BaseController;
import com.leidengyun.mvc.model.Pagination;
import com.leidengyun.mvc.model.Result;
import com.leidengyun.mvc.model.ResultCode;
import com.leidengyun.mvc.validator.Validator;
import com.leidengyun.mvc.validator.annotation.ValidateParam;
import com.leidengyun.wms.model.Dev;
import com.leidengyun.wms.service.DevService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author suiwenlei
 */
@Api(tags = "设备管理")
@Controller
@RequestMapping("/admin/dev")
public class DevController extends BaseController {

	@Resource
	private DevService devService;
	

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute() {
		return "/dev/dev";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		Dev dev;
		if (id == null) {
			dev = new Dev();
		}
		else {
			dev = devService.get(id);
		}
		model.addAttribute("dev", dev);
		return "/dev/devEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
			@ApiParam(value = "设备ID ") String devId,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(devService.findPaginationByDevId(devId, new Pagination<Dev>(pageNo, pageSize)));
	}

	@ApiOperation("验证设备ID")
	@RequestMapping(value = "/validateCode", method = RequestMethod.POST)
	public @ResponseBody Result validateCode(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "设备ID", required = true) @ValidateParam({ Validator.NOT_BLANK }) String devId) {
		Result result = Result.createSuccessResult();
		Dev db = devService.findByDevId(devId);
		if (null != db && !db.getId().equals(id)) {
			result.setCode(ResultCode.ERROR).setMessage("设备已存在");
		}
		return result;
	}

	@ApiOperation("启用/禁用")
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Result enable(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		devService.enable(isEnable, getAjaxIds(ids));
		return Result.createSuccessResult();
	}

	@ApiOperation("新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "设备编号", required = true) @ValidateParam({ Validator.NOT_BLANK }) String devId,
			@ApiParam(value = "设备备注", required = true) @ValidateParam({ Validator.NOT_BLANK }) String bz,
			@ApiParam(value = "是否有效", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable,
			@ApiParam(value = "排序", required = true) @ValidateParam({ Validator.NOT_BLANK, Validator.INT }) Integer sort) {
		Dev dev;
		if (id == null) {
			dev = new Dev();
			dev.setCreateTime(new Date());
		}
		else {
			dev = devService.get(id);
		}
		dev.setDevId(devId);
		dev.setBz(bz);
		dev.setSort(sort);
		dev.setIsEnable(isEnable);
	
		devService.save(dev);
		return Result.createSuccessResult();
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		devService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}
}