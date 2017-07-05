package com.spire.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class SpireProperties {
	
	public static Properties loadReportProperties(){
		
		Properties prop = new Properties();
		
	 
		try {
	 
			File file = new File("C:\\automation_reports\\report.properties");
			
			FileInputStream fileInput = new FileInputStream(file);
			
			prop.load(fileInput);
			
	   
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return prop;
		
	}
	
	
	public static Properties loadProperties(){
		
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			prop=new FileReader().loadPropertiesFile("./src/main/resources/config.properties");
	   
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return prop;
		
	}
	
	public static void main(String[] args) {
		
		Properties prop=loadProperties();
		
		Enumeration enuKeys = prop.keys();
		
		while (enuKeys.hasMoreElements()) {
			
			String key = (String) enuKeys.nextElement();
			String value = prop.getProperty(key);
			System.out.println(key + ": " + value);
		}
		
	}

}
