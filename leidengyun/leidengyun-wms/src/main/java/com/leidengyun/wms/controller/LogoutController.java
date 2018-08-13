package com.leidengyun.wms.controller;

import com.leidengyun.mvc.exception.ServiceException;
import com.leidengyun.mvc.util.CookieUtils;
import com.leidengyun.mvc.util.StringUtils;
import com.leidengyun.sso.client.HttpClient;
import com.leidengyun.sso.client.SessionUtils;
import com.leidengyun.sso.client.SsoFilter;
import com.leidengyun.sso.client.SsoResultCode;
import com.leidengyun.wms.common.ConfigServlet;
import com.leidengyun.wms.common.TokenManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Joe
 */
@Api(tags = "单点登出")
@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@Resource
	private TokenManager tokenManager;
	

	@ApiOperation("登出")
	@RequestMapping(method = RequestMethod.GET)
	public String logout(@ApiParam(value = "返回链接") String backUrl, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = CookieUtils.getCookie(request, "token");
		if (StringUtils.isNotBlank(token)) {
			tokenManager.remove(token);
		}
		SessionUtils.invalidate(request);
		if(StringUtils.isBlank(backUrl)){
			redirectLogin(request, response);
			return null;
		}
		return "redirect:" + backUrl;
	}
	
	/**
	 * 跳转登录
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpClient client = new HttpClient(response);
		client.setParameter("backUrl", ConfigServlet.ssoServerUrl);
		client.setParameter("appCode", ConfigServlet.ssoAppCode);
		client.sendByPost(ConfigServlet.ssoServerUrl+"/page");
	}
}