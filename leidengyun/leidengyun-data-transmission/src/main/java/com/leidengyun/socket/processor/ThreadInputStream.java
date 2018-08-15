package com.leidengyun.socket.processor;

import com.leidengyun.socket.business.GprsPacket;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadInputStream implements Runnable {
	private static Logger logger = Logger.getLogger(ThreadInputStream.class);
	private static final int buffer_size = 1024;
	Socket socket;
	DataInputStream input;
	PrintWriter out;
	public ThreadInputStream(Socket socket) {
		this.socket = socket;
	}
	public Socket getSocket() {
		return this.socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		byte[] buff = new byte[buffer_size];
		try {
			this.input = new DataInputStream(this.socket.getInputStream());
			this.out = new PrintWriter(this.socket.getOutputStream());
			int len = 0;
			while (len > -1) {
				if (this.input != null) {
					try {
						len = this.input.read(buff);
						int parse = GprsPacket.gprsPacketParse(buff, len);
						switch (parse) {
						case 0:
							this.out.write("PAROK");
							break;
						default:
							this.out.write("RECOK");
							logger.info("数据校验未通过，接收长度有误!");
						}
						this.out.flush();
					} catch (InterruptedIOException ie) {
						logger.debug("这里发生了一个{InterruptedIOException}异常!", ie);
						close();
					}
				} else {
					logger.info("没有响应数据，客户端断开连接!");
					close();
					break;
				}
			}
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
	/**
	 * 关闭流
	 */
	public void close() {
		try {
			if ((this.input != null) && (this.out != null) && (!this.socket.isClosed())) {
				this.input.close();
				this.out.close();
				this.socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
