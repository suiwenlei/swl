package com.leidengyun.socket.task;

import com.leidengyun.socket.listener.SocketListener;
import org.apache.log4j.Logger;

import com.leidengyun.common.ConfigServlet;

public class AdminTask {
	
	public static Logger logger = Logger.getLogger(AdminTask.class);

	public static void receiveDevData() throws ClassNotFoundException {
		
		SocketListener listener = new SocketListener();
		try {
			logger.info("......SOCKET...IS.....WORKING ");
			listener.setPort(ConfigServlet.PORT);
			new Thread(listener).start();
		} catch (Exception e) {
			logger.error("创建服务器失败!", e);
		}
	}
}
