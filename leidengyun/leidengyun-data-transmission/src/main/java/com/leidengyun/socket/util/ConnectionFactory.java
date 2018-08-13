package com.leidengyun.socket.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static ConnectionFactory connectionFactory=null;
	private static Configuration config=Configuration.getConfigure();
	
    private ConnectionFactory()
    {
		try {
		Class.forName(config.getDriver());
	} catch (ClassNotFoundException e) {
	}
    }
    
    public Connection getConnection() throws SQLException
    {
    	
    	Connection con=null;
    	try {
			con=DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
			
			return con;
		}finally{
			
//			if (null != con) {
//				con.close();
//			}
		}
    	
    }
    
    public static ConnectionFactory getInstance()
    {  
    	if (null==connectionFactory) {
    		connectionFactory=new ConnectionFactory();
		}
    	
		return connectionFactory;
    	
    }

}
