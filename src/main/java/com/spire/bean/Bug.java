package com.spire.bean;

/* Abhishiktha - 05-04-2017 - Added code to sort OOSLA BugIds's according to priority */

public class Bug implements Comparable<Bug>{
	
	String id;
	String assignee;
	String priority;
	String status;
	String component;
	String creationDate;
	String description;
	String deadline;
	String bugType;
	String isProduction;
	String isOOSLA;
	String isNearOOSLA;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getBugType() {
		return bugType;
	}
	public void setBugType(String bugType) {
		this.bugType = bugType;
	}
	public String getIsProduction() {
		return isProduction;
	}
	public void setIsProduction(String isProduction) {
		this.isProduction = isProduction;
	}
	public String getIsOOSLA() {
		return isOOSLA;
	}
	public void setIsOOSLA(String isOOSLA) {
		this.isOOSLA = isOOSLA;
	}
	
	@Override
	public int compareTo(Bug bug) {
		return this.priority.compareTo(bug.priority);
	}
	
	/* Abhishikths - 11-04-2017 - Added nearest OOSLA category */
	
	public String getIsNearOOSLA() {
		return isNearOOSLA;
	}
	public void setIsNearOOSLA(String isOOSLA) {
		this.isNearOOSLA = isOOSLA;
	}
}
