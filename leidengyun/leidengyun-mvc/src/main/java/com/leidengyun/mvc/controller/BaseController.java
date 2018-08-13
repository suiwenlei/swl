package com.leidengyun.mvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.leidengyun.mvc.util.StringUtils;

/**
 * Controller基类
 * 
 * @author Joe
 */
public class BaseController {

	private Integer[] getAjaxIds(final String str, final String separator) {
		Integer[] ids = null;
		if (str != null) {
			String[] strs = str.split(separator);
			ids = new Integer[strs.length];
			for (int i = 0, length = strs.length; i < length; i++) {
				ids[i] = Integer.valueOf(strs[i]);
			}
		}
		return ids;
	}

	protected List<Integer> getAjaxIds(final String ids) {
		return StringUtils.isBlank(ids) ? new ArrayList<Integer>(0) : Arrays.asList(getAjaxIds(ids, ","));
	}
	
	
	protected List<String> getAjaxForStrIds(final String ids) {
		return StringUtils.isBlank(ids) ? new ArrayList<String>(0) : Arrays.asList(getAjaxStrIds(ids, ","));
	}
	
	
	private String[] getAjaxStrIds(final String str, final String separator) {
		String[] ids = null;
		if (str != null) {
			String[] strs = str.split(separator);
			ids = new String[strs.length];
			for (int i = 0, length = strs.length; i < length; i++) {
				ids[i] = String.valueOf(strs[i]);
			}
		}
		return ids;
	}

	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		return ip;
	}
}