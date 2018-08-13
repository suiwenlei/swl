package com.leidengyun.socket.task;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.CRC32;

import org.apache.log4j.Logger;

public class clockTask implements Runnable {

	private static Logger logger = Logger.getLogger(clockTask.class);
	
	private Socket socket;
	private int socketPort;
	ConcurrentHashMap<Integer, Socket> serverMap;
	
	
	public clockTask(int socketPort, ConcurrentHashMap<Integer, Socket> serverMap) {
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
			CRC32 crc32 = new CRC32(); 
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
			int checkResult=-1;
			String sendStr="";
			logger.info("==waiting message from client...");
            byte buf[] = new byte[8];
            int length; 
            if ((length = socket.getInputStream().read(buf)) != -1 ) {
            	crc32.update(buf,0,length); 
            	logger.info("Receive Message: " + new String(buf));
            }
            long crc32value = crc32.getValue();
            logger.info("crc32value:"+crc32value);
            
            if(checkResult>0){
            	 System.out.println("==sending message to client...");
                 sendStr = "This is the message for client.";
            }else{
            	sendStr="RECOK";
            }
            socketOut.write(sendStr);
            socketOut.flush();
            socketOut.close();
            socket.close();
      
		} catch (Exception e) {
			logger.debug("发生了一个错误", e);
			this.serverMap.remove(Integer.valueOf(this.socketPort));
		}
	}	
}



	
