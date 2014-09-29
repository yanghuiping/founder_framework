package com.founder.pod.galihezi.common.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigUtil {
	protected static Logger log = Logger.getLogger(ConfigUtil.class);
	private static Properties prop;
	static {
	    prop = new Properties();    
	    try {
			prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			log.error("加载配置文件错误", e);
		}
	}
	
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
	
	public static int getPropertyInt(String key){
		String value = prop.getProperty(key);
		int v = Integer.parseInt(value);
		return v;
	}
	
	public static long getPropertyLong(String key){
		String value = prop.getProperty(key);
		long v = Long.parseLong(value);
		return v;
	}
	
	public static boolean getPropertyBoolean(String key){
		String value = prop.getProperty(key);
		boolean v = Boolean.valueOf(value);
		return v;
	}
	public static float getPropertyFloat(String key){
		String value = prop.getProperty(key);
		float v = Float.valueOf(value);
		return v;
	}
}
