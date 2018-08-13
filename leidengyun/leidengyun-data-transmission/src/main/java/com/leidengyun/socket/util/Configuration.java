package com.leidengyun.socket.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Configuration {
	
	private String url;
	private String driver;
	private String username;
	private String password;

	public Configuration() {
	}
	
	public Configuration(String url, String driver, String username,
			String password) {
		super();
		this.url = url;
		this.driver = driver;
		this.username = username;
		this.password = password;
	}
	
	public static Configuration getConfigure()
	{
		
		try {
			InputStream in = Configuration.class.getResourceAsStream("/db.xml");
			if (null!=in) {
				return load(in);
			}
			return null;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	private static Configuration load(InputStream in) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(in);
		Element jdbc = doc.getRootElement();
		String url = jdbc.element("url").getText();
		String driver = jdbc.element("driver").getText();
		String username = jdbc.element("username").getText();
		String password = jdbc.element("password").getText();
		Configuration cfg = new Configuration(url, driver, username, password);
		return cfg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}