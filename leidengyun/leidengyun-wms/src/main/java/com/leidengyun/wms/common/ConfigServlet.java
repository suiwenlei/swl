package com.leidengyun.wms.common;

import com.leidengyun.mvc.config.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 初始化全局参数
 * 
 * @author Joe
 */
public class ConfigServlet extends HttpServlet {

	private static final long serialVersionUID = -7462526216386306510L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServlet.class);
	// 单点登录服务端URL
	public static String  ssoServerUrl;
	public static String  ssoAppCode;

	@Override
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ssoServerUrl=ConfigUtils.getProperty("sso.server.url");
		ssoAppCode=ConfigUtils.getProperty("sso.app.code");
		
		servletContext.setAttribute("_path", servletContext.getContextPath());
		try {
			servletContext.setAttribute("_staticPath", ConfigUtils.getProperty("static.url"));
			servletContext.setAttribute("_systemName", ConfigUtils.getProperty("system.name"));
			servletContext.setAttribute("_systemAdminName", ConfigUtils.getProperty("system.admin.name"));
			servletContext.setAttribute("_companyName", ConfigUtils.getProperty("system.company.name"));
			servletContext.setAttribute("_companyPhone", ConfigUtils.getProperty("system.company.phone"));
			servletContext.setAttribute("_copyRight", ConfigUtils.getProperty("system.copy.right"));
		}
		catch (Exception e) {
			LOGGER.error("系统初始化参数配置有误", e);
		}
	}
	
	public static final String MEUN_NAME = "设备列表";
	public static final String MEUN_URL = "/sjpt/data";
	public static final String MEUN_SUB_URL = "/sjpt/data?devId=";
	public static final String MEUN_ICON = "fa fa-list-alt";


	public static final String MEUN_NAME_ls = "历史数据";
	public static final String MEUN_URL_ls = "/sjpt/data/ls";
	public static final String MEUN_SUB_URL_ls = "/sjpt/data/ls?devId=";
	public static final String MEUN_ICON_ls = "fa-external-link";
	
	public static final Boolean STATUS_RUNNING = true;
	public static final Boolean STATUS_NOT_RUNNING = false;
	
	//设备上传相关参数
	
	public static final int PORT=8888;
}
