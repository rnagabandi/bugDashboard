package com.spire.bean;

import java.util.ArrayList;
import java.util.Properties;

import com.spire.helper.SpireProperties;

public class ProductBean {

	String productName;
	ArrayList<Component> components=new ArrayList<Component>();
	String bugType;
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	public String getBugType() {
		return bugType;
	}

	public void setBugType(String bugType) {
		this.bugType = bugType;
	}
	
	public ArrayList<Component> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<Component> components) {
		this.components = components;
	}

	public static ProductBean populateProductBean(String productName){
		
		ProductBean product = new ProductBean();
		product.setProductName(productName);
		
		Properties properties = SpireProperties.loadProperties();
		String[] componentList=properties.getProperty(productName).split(",");
		
		for (int i = 0; i < componentList.length; i++) {
			
			Component component = new Component();
			component.setComponentName(componentList[i]);
			component.setEmailList(properties.getProperty(componentList[i]));
			product.components.add(component);
		
		}
		
		return product;
		
	}
	
}
