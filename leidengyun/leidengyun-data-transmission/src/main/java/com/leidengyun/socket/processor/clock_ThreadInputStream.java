package com.leidengyun.socket.processor;

import com.leidengyun.mvc.util.DateUtils;
import com.leidengyun.socket.business.Packet_clock;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class clock_ThreadInputStream implements Runnable {

	private static Logger logger = Logger.getLogger(clock_ThreadInputStream.class);
	Socket socket;
	DataInputStream input;
	OutputStream out;

	public clock_ThreadInputStream(Socket socket) {
		this.socket = socket;
	}
	public Socket getSocket() {
		return this.socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		try {
			String packetID="5AAA0000";
			String tmp = DateUtils.format(new Date());
			out = socket.getOutputStream();
			out.write(new Packet_clock(packetID, tmp.length(),tmp).getBuf());
			
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.debug("这里发生了一个{IO}异常!", e1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("这里发生了一个{解析数据}异常!", e);
		} finally {
			close();
		}
	}
	//关闭流
	public void close() {
		try {
			if ((this.input != null) && (this.out != null) && (!this.socket.isClosed())) {
				this.input.close();
			}
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
