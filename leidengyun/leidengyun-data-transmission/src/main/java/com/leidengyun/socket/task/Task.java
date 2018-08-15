package com.leidengyun.socket.task;

import com.leidengyun.socket.processor.ThreadInputStream;
import org.apache.log4j.Logger;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Task implements Runnable {

	private static Logger logger = Logger.getLogger(Task.class);
	private Socket socket;
	private int socketPort;
	ConcurrentHashMap<Integer, Socket> serverMap;

	public Task(int socketPort, ConcurrentHashMap<Integer, Socket> serverMap) {
		this.socketPort = socketPort;
		this.serverMap = serverMap;
	}
	@Override
	public void run() {
		try {
			if (this.serverMap != null) {
				this.socket = ((Socket) this.serverMap.get(Integer.valueOf(this.socketPort)));
				handleSocket(this.socket);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("执行数据解析发生一个错误", e);
		}
	}
	private void handleSocket(Socket socket) throws Exception {
		try {
			new Thread(new ThreadInputStream(socket)).start();
		} catch (Exception e) {
			logger.debug("调用数据解析的线程发生了一个错误", e);
			this.serverMap.remove(Integer.valueOf(this.socketPort));
		}
	}
}



	
