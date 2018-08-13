package com.leidengyun.wms.common;

import java.text.NumberFormat;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 获取Spring管理的Service实现
 * 
 * @author Joe
 */
public final class SpringUtils {

	/**
	 * 通过实体名称得它对应的service
	 * 
	 * @param beanName
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		// 获取当前运行环境下Spring上下文
		WebApplicationContext webApp = ContextLoader.getCurrentWebApplicationContext();
		return (T) webApp.getBean(beanName);
	}

	/**
	 * 按类型获取spring bean 实例
	 * 
	 * @param beanType
	 * @return
	 */
	public static <T> T getBean(Class<T> beanType) {
		// 获取当前运行环境下Spring上下文
		WebApplicationContext webApp = ContextLoader.getCurrentWebApplicationContext();
		return webApp.getBean(beanType);
	}
	
	
	
	//处理设备数据
	public static Object formatZero(double num, double magNitude) {
		if ((43691.0== num) || (43947.0 == num)) {
			return "-";
		}
		return NumberFormat.getInstance().format(num*magNitude);
	}
}
