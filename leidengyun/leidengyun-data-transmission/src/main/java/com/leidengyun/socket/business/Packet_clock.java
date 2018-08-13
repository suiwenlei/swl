package com.leidengyun.socket.business;

import java.net.Socket;

/**
 * 时钟校验解析
 * @author swl
 *
 */
public class Packet_clock  {

	
	private byte[] buf = null;

	/**
	 * 将int转为低字节在前，高字节在后的byte数组
	 */
	private static byte[] toLH(int n) {
		byte[] b = new byte[4];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		b[2] = (byte) (n >> 16 & 0xff);
		b[3] = (byte) (n >> 24 & 0xff);
		return b;
	}

	/**
	 * 将float转为低字节在前，高字节在后的byte数组
	 */
	private static byte[] toLH(float f) {
		return toLH(Float.floatToRawIntBits(f));
	}

	
	/** 
	* 构造并转换 
	*/ 
	public Packet_clock(String packetID, int packetLen, String packetBody) { 
	byte[] temp = null; 
	buf = new byte[packetBody.getBytes().length + 6]; //包头+命令字
	System.arraycopy(packetID.getBytes(), 0, buf, 0, packetID.length()); 
	temp = toLH(packetLen); 
	System.arraycopy(temp, 0, buf, 4, temp.length);//数据域长度 
	System.arraycopy(packetBody.getBytes(), 0, buf, 6,packetBody.length()); //数据区
	} 

	/**
	 * 返回要发送的数组
	 */
	public byte[] getBuf() {
		return buf;
	}

	/** 
	* 发送测试 
	*/ 
	public static void main(String[] args) { 
		try {
			
		String tmp = "test string!"; 
		Socket sock = new Socket("127.0.0.1", 8888); 
		sock.getOutputStream().write( 
		new Packet_clock("5AAA0000", tmp.length(), tmp).getBuf()); 
		sock.close(); 
		} catch (Exception e) { 
		e.printStackTrace(); 
		} 
	}	
}
