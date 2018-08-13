package com.leidengyun.sjptn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leidengyun.mvc.model.Result;
import com.leidengyun.sso.client.ApplicationPermissionUtils;
import com.leidengyun.sso.client.SessionPermission;
import com.leidengyun.sso.client.SessionUser;
import com.leidengyun.sso.client.SessionUtils;

import io.swagger.annotations.ApiOperation;

/**
 * @author Joe
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String execute(HttpServletRequest request, Model model) {
		SessionUser sessionUser = SessionUtils.getSessionUser(request);
		model.addAttribute("userName", sessionUser.getAccount());
		SessionPermission sessionPermission = SessionUtils.getSessionPermission(request);
		if (sessionPermission != null){
			model.addAttribute("userMenus", sessionPermission.getMenuList());
			model.addAttribute("userPermissions", sessionPermission.getPermissionSet());
		}
		return "/index";
	}
	
	@ApiOperation("菜单")
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public @ResponseBody Result menu(HttpServletRequest request) {
		SessionPermission sessionPermission = SessionUtils.getSessionPermission(request);
		// 如果配置的权限拦截器，则获取登录用户权限下的菜单，没有权限拦截限制的情况下，获取当前系统菜单呈现
		return Result.createSuccessResult().setData(
				sessionPermission == null ? ApplicationPermissionUtils.getApplicationMenuList() : sessionPermission
						.getMenuList());
	}
	
}