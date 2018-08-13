package com.leidengyun.wms.controller.admin;

import com.leidengyun.mvc.controller.BaseController;
import com.leidengyun.mvc.model.Result;
import com.leidengyun.mvc.model.ResultCode;
import com.leidengyun.mvc.validator.Validator;
import com.leidengyun.mvc.validator.annotation.ValidateParam;
import com.leidengyun.sso.client.SessionUtils;
import com.leidengyun.wms.common.LoginUser;
import com.leidengyun.wms.common.TokenManager;
import com.leidengyun.wms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Joe
 */
@Api(tags = "个人中心")
@Controller
@RequestMapping("/admin/profile")
public class ProfileController extends BaseController {

	@Resource
	private TokenManager tokenManager;
	@Resource
	private UserService userService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model, HttpServletRequest request) {
		LoginUser loginUser = tokenManager.validate(SessionUtils.getSessionUser(request).getToken());
		if (loginUser != null) {
			model.addAttribute("user", userService.get(loginUser.getUserId()));
		}
		return "/admin/profile";
	}

	@ApiOperation("修改密码提交")
	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "新密码", required = true) @ValidateParam({ Validator.NOT_BLANK }) String newPassword,
			@ApiParam(value = "确认密码", required = true) @ValidateParam({ Validator.NOT_BLANK }) String confirmPassword,
			HttpServletRequest request) {
		LoginUser loginUser = tokenManager.validate(SessionUtils.getSessionUser(request).getToken());
		if (loginUser != null) {
			userService.updatePassword(loginUser.getUserId(), newPassword);
			return Result.createSuccessResult().setMessage("修改成功");
		}
		else {
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("修改失败");
		}
	}
}