package com.leidengyun.socket.task;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.CRC32;

import com.leidengyun.socket.processor.ThreadInputStream;
import com.leidengyun.socket.processor.clock_ThreadInputStream;
import com.leidengyun.socket.util.newStringUtil;
import org.apache.log4j.Logger;

public class Task implements Runnable {

	private static Logger logger = Logger.getLogger(Task.class);
	
	private Socket socket;
	private int socketPort;
	ConcurrentHashMap<Integer, Socket> serverMap;
	
	
	public Task(int socketPort, ConcurrentHashMap<Integer, Socket> serverMap) {
		this.socketPort = socketPort;
		this.serverMap = serverMap;
	}
	
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
			////时钟校验crc32校验
			if(checkClockCrc32((socket))){
				new Thread(new clock_ThreadInputStream(socket)).start();
			}else{
				new Thread(new ThreadInputStream(socket)).start();
			}
		} catch (Exception e) {
			logger.debug("调用数据解析的线程发生了一个错误", e);
			this.serverMap.remove(Integer.valueOf(this.socketPort));
		}
	}
	
	//
	
	//时钟校验crc32校验
	private boolean checkClockCrc32(Socket socket) throws IOException{
		
		CRC32 crc32 = new CRC32(); 
		logger.info("时钟校验crc32校验...");
        byte buf[] = new byte[8];
        byte crc32_buf[] = new byte[4];
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        String reviceMsg="";
        int length; 
        if ((length = socket.getInputStream().read(buf)) != -1 ) {
        	reviceMsg = new String(buf);
        	logger.info("Receive Message: " + new String(buf));
        	crc32.update(buf,0,length);	
        }
        int n=0;
        int data_len=8; //代表8字节
        String reviceMsg_crc32="";//crc32
        
        while (n < data_len) {
        	int _crc32 = buf[(1 + n)] << 8 | buf[(1 + n + 1)];
        	reviceMsg_crc32= Integer.toHexString(_crc32);
        	n+=4;
        }
        System.out.println(newStringUtil.longToByteArray(crc32.getValue()));
        String reviceMsg_crc32_L = newStringUtil.bytesToHexString(newStringUtil.longToByteArray(crc32.getValue()));
        logger.info("reviceMsg_crc32:"+reviceMsg_crc32 +"---reviceMsg_crc32_L"+reviceMsg_crc32_L);
        if(reviceMsg_crc32.equals(reviceMsg_crc32_L)){
        	return true;
        }
		return false;
	}
}



	
