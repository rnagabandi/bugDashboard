package com.spire.bean;

public class Component {

	String componentName;
	String emailList;
	String totalBugs;
	String crossedSLABugs;
	String nearestSLABugs;

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getTotalBugs() {
		return totalBugs;
	}

	public void setTotalBugs(String totalBugs) {
		this.totalBugs = totalBugs;
	}

	public String getCrossedSLABugs() {
		return crossedSLABugs;
	}

	public void setCrossedSLABugs(String crossedSLABugs) {
		this.crossedSLABugs = crossedSLABugs;
	}

	public String getEmailList() {
		return emailList;
	}
	
	public void setEmailList(String emailList) {
		this.emailList = emailList;
	}
	
	/* Abhishiktha - 11-04-2017 - Added nearest OOSLA category */
	
	public String getNearestSLABugs() {
		return nearestSLABugs;
	}

	public void setNearestSLABugs(String nearestSLABugs) {
		this.nearestSLABugs = nearestSLABugs;
	}
}
