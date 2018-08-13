package com.leidengyun.socket.listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.leidengyun.socket.task.Task;

public class SocketListener implements Runnable {

	private ServerSocket ss;
	private boolean listening = true;
	private int port;
	private int requestCount;
	private int backlog = 9999999;
	private static Logger logger = Logger.getLogger(SocketListener.class);
	private ConcurrentHashMap<Integer, Socket> serverMap = new ConcurrentHashMap<Integer, Socket>();

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void run() {
		init();  //监听初始化
		Listener(); //监听
	}

	public void init() {
		try {
			this.ss = new ServerSocket(this.port, this.backlog);
			logger.info("服务已经启动，开始在端口[" + this.port + "]监听,等待TCP客户端连接....");
		} catch (IOException ie) {
			ie.printStackTrace();
			logger.debug("无法启动监听,可能由于网络不通，或端口[" + this.port + "]已被占用.", ie);
		}
	}

	public void Listener() {
		while (this.listening) {
			try {
				Socket s = this.ss.accept();
				logger.info("收到客户端[" + s.getInetAddress().getHostAddress() + ":" + s.getPort() + "]连接请求.");
				int socketPort = s.getPort();
				this.serverMap.put(Integer.valueOf(socketPort), s);
				new Thread(new Task(socketPort, serverMap)).start();
			} catch (IOException ie) {
				ie.printStackTrace();
				logger.debug("服务器异常", ie);
				ReleaseResource();
				init();
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.debug("服务器异常", ex);
				ReleaseResource();
				init();
			}
		}
	}

	
	/**
	 * 释放资源
	 */
	public void ReleaseResource() {
		try {
			if ((this.ss != null) && (!this.ss.isClosed())) {
				this.ss.close();
			}
			Thread.sleep(5000L);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 请求次数
	 * @return
	 */
	public int getRequestCount() {
		if (this.requestCount > 1000000) {
			this.requestCount = 0;
		}
		return this.requestCount++;
	}
}
