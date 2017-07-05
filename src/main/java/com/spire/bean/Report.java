package com.spire.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import com.mysql.fabric.xmlrpc.base.Data;
import com.spire.database.DBModule;

public class Report {

	public final static int P1=1;
	public final static int P2=3;
	public final static int P3=15;
	public final static int p4=30;
	public static final String crmTeamMailList="'nimesh.agarwal@spire2grow.com','chandan.patro@spire2grow.com','roshan.deshpande@spire2grow.com','siddharth.kumar@spire2grow.com','supreet.totagi@spire2grow.com'"; 
	public static final String searchTeamMailList="'karthic.v@spire2grow.com','manojkumar@spire2grow.com','ujjawal.raj@spire2grow.com','dipankar.saha@spire2grow.com','saptarshi.chakrabarty@spire2grow.com','amit.kumar@spire2grow.com'";
	public static final String uiTeamMailList="'suhail.ak@spire2grow.com','devibhavani.p@spire2grow.com','anjana.singh@spire2grow.com'";
	
	public static StringBuilder createHeader(boolean isProdDashboard) {

		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<table>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> S.NO");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Bug-Id");
		sb.append("</th>");

		if (isProdDashboard) {

			sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Bug Type");
			sb.append("</th>");

		}

		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Assignee");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Priority");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Status");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Environment");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Deadline");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Component");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Creation Date");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Summary");
		sb.append("</th>");

		return sb;

	}
	
	public static StringBuilder createComponentHeader(ProductBean bean) {

		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<table>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> S.NO");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Component");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total "+bean.getBugType());
		sb.append("</th>");
		if (bean!=null && bean.getBugType().equalsIgnoreCase("Bug")) {
			sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total OOSLA Bugs");
			sb.append("</th>");
		}
		
		/* Abhishiktha - 11-04-2017 - Added code to Display nearest OOSLA Bugs */
		if (bean!=null && bean.getBugType().equalsIgnoreCase("Bug")) {
			sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total Nearing SLA Bugs");
			sb.append("</th>");
		}
		return sb;

	}
	
	public static StringBuilder createLoginEmailHeader(ProductBean bean) {

		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<table>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> S.NO");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Name");
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> P1 "+bean.getBugType());
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> P2 "+bean.getBugType());
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> P3 "+bean.getBugType());
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> P4 "+bean.getBugType());
		sb.append("</th>");
		sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total "+bean.getBugType());
		sb.append("</th>");
		
		/* Abhishiktha - 28-03-2017 - Added another column to fetch Total OOSLA bugs against each user */
		/* Start */
		if (bean!=null && bean.getBugType().equalsIgnoreCase("Bug")) {
			sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> OOSLA Bugs");
			sb.append("</th>");
		}
		/* End */
		
		/* Abhishiktha - 11-04-2017 - Added another column to fetch Nearest Total OOSLA bugs against each user */
		/* Start */
		if (bean!=null && bean.getBugType().equalsIgnoreCase("Bug")) {
			sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Nearing SLA Bugs");
			sb.append("</th>");
		}
		/* End */
		
		return sb;

	}

	public static StringBuilder createBody(
			/* HashMap<String,Bug> bugs */ArrayList<Bug> listBugs,
			StringBuilder sb, boolean isProdDashboard) {

		int j = 1;

		if (listBugs != null) {

			for (Bug bug : listBugs) {

				if (bug.getIsOOSLA().equalsIgnoreCase("true")) 
					sb.append("<tr style = \"background: #FFA07A;\">");
				else if(bug.getIsNearOOSLA().equalsIgnoreCase("true"))
					sb.append("<tr style = \"background: #C3FF38;\">");
				else	
					sb.append("<tr>");
				
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ j);
				sb.append("</td>");
				String bugId = bug.getId();
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">"
						+ "<a href='http://bugdb.spire2grow.com/show_bug.cgi?id="
						+ bugId + "'>" + bugId);
				sb.append("</td>");

				if (isProdDashboard) {

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ bug.getBugType());
					sb.append("</td>");

				}

				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ bug.getAssignee());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ bug.getPriority());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ bug.getStatus());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ bug.getIsProduction());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ bug.getDeadline());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ bug.getComponent());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ bug.getCreationDate());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ bug.getDescription());
				sb.append("</td>");
				sb.append("</tr>");

				j++;
			}

		}

		return sb;
	}
	
	public static StringBuilder createComponentBody(String productName,StringBuilder sb) throws Exception {

		String[] loginIds={crmTeamMailList,searchTeamMailList,uiTeamMailList};
		ArrayList<Component> components= new ArrayList<Component>();
		
		for (int i = 0; i < loginIds.length; i++) {
			
			ArrayList<Bug> bugs=DBModule.getBugsByLoginName(productName, "Bug",loginIds[i],null);	
			
			Component component = new Component();
			
			if (i==0) 
				component.setComponentName("CRM");
			else if (i==1)
				component.setComponentName("SERACH");
			else 
				component.setComponentName("UI");
			
			component.setTotalBugs(bugs.size()+"");
			component.setCrossedSLABugs(getOOSLABugCount(bugs)+"");
			component.setNearestSLABugs(getNearOOSLABugCount(bugs)+"");
			
			components.add(component);
			
		}
		
		int j = 1;
		for (Component component:components) {
			
			sb.append("<tr>");
			
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ j);
			sb.append("</td>");
			
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ component.getComponentName());
			sb.append("</td>");
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ component.getTotalBugs());
			sb.append("</td>");
			
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ component.getCrossedSLABugs());
			sb.append("</td>");
			
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ component.getNearestSLABugs());
			sb.append("</td>");
			sb.append("</tr>");

			j++;
			
		}
				
		return sb;
	}
		
	public static StringBuilder createComponentBody(ProductBean product,StringBuilder sb) throws Exception {

		for (int i = 0; i < product.getComponents().size(); i++) {
			
			ArrayList<Bug> bugs=DBModule.getBugsByLoginName(product.getProductName(), product.getBugType(),product.getComponents().get(i).getEmailList(),null);	
			Component component=product.getComponents().get(i);
			component.setTotalBugs(bugs.size()+"");
			component.setCrossedSLABugs(getOOSLABugCount(bugs)+"");
			component.setNearestSLABugs(getNearOOSLABugCount(bugs)+"");
			product.getComponents().set(i, component);
		}
		
		int j = 1;
		for (int i = 0; i < product.getComponents().size(); i++) {
			
			sb.append("<tr>");
			
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ j);
			sb.append("</td>");
			
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ product.getComponents().get(i).getComponentName());
			sb.append("</td>");
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ product.getComponents().get(i).getTotalBugs());
			sb.append("</td>");
			if (product!=null && product.getBugType().equalsIgnoreCase("Bug")) {
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ product.getComponents().get(i).getCrossedSLABugs());
				sb.append("</td>");
			}
			
			sb.append("</td>");
			if (product!=null && product.getBugType().equalsIgnoreCase("Bug")) {
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ product.getComponents().get(i).getNearestSLABugs());
				sb.append("</td>");
			}
			
			sb.append("</tr>");

			j++;
			
		}
				
		return sb;
	}
	
	/*	 Abhishiktha - 28-03-2017 - Added code to fetch Total OOSLA bugs against each user */
	
	/*   Abhishiktha - 05-04-2017 - Added code to fetch Bug Id'sfor P1 and P2 */
	
	public static StringBuilder createLoginNameBody(ProductBean product,StringBuilder sb) throws Exception {

		ArrayList<String> emailList= new ArrayList<String>();
		ArrayList<String> bugId = new ArrayList<String>();
		
		for (int i = 0; i < product.getComponents().size(); i++) {
			
			String[] ids= product.getComponents().get(i).getEmailList().split(",");
			for (int j = 0; j < ids.length; j++) 
				emailList.add(ids[j]);
			
		}
		
		for (int i = 0; i < emailList.size(); i++) {
			
			ArrayList<Bug> p1Bugs=DBModule.getBugsByLoginName(product.getProductName(), product.getBugType(),emailList.get(i),"P1-Critical");
			ArrayList<Bug> p2Bugs=DBModule.getBugsByLoginName(product.getProductName(), product.getBugType(),emailList.get(i),"P2-High");
			ArrayList<Bug> p3Bugs=DBModule.getBugsByLoginName(product.getProductName(), product.getBugType(),emailList.get(i),"P3-Medium");
			ArrayList<Bug> p4Bugs=DBModule.getBugsByLoginName(product.getProductName(), product.getBugType(),emailList.get(i),"P4-Low");
			
			/* Start */
			ArrayList<Bug> ooslaBugs=DBModule.getBugsByLoginName(product.getProductName(), product.getBugType(),emailList.get(i),null);	
			/* End */
			
			sb.append("<tr>");
			
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ (i+1));
			sb.append("</td>");
			
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ emailList.get(i));
			sb.append("</td>");
			
			/* Start - To print P1 & P2 BugId with Links*/
					
			if(p1Bugs.size()>0) {
				
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ (p1Bugs.size()));
				sb.append(" ( ");
				
				bugId = getBugId(p1Bugs);
				
				for(int m=0; m<p1Bugs.size(); m++) {
					if(m>=1)
					{
						sb.append("  ,  ");
					}
					sb.append("<a href='http://bugdb.spire2grow.com/show_bug.cgi?id="
							+ bugId.get(m) + "'>" + bugId.get(m));
					sb.append("</a>");
				}
				sb.append(" ) ");
				sb.append("</td>");
			}
			else {
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ p1Bugs.size());
				sb.append("</td>");
			}
			
			if(p2Bugs.size()>0) {
				
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ (p2Bugs.size()));
				sb.append(" ( ");
				
				bugId = getBugId(p2Bugs);
				
				for(int m=0; m<p2Bugs.size(); m++) {
					if(m>=1)
					{
						sb.append("  ,  ");
					}
					sb.append("<a href='http://bugdb.spire2grow.com/show_bug.cgi?id="
							+ bugId.get(m) + "'>" + bugId.get(m));
					sb.append("</a>");
				}
				sb.append(" ) ");
				sb.append("</td>");
			}
			else {
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ p2Bugs.size());
				sb.append("</td>");
			}
			/* End */
			
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ p3Bugs.size());
			sb.append("</td>");
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ p4Bugs.size());
			sb.append("</td>");
			sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
					+ (p1Bugs.size()+p2Bugs.size()+p3Bugs.size()+p4Bugs.size()));
			sb.append("</td>");
			
			/* Start */
			ArrayList<String> ooslaBugId = getOOSLABugId(ooslaBugs);
			if (product!=null && product.getBugType().equalsIgnoreCase("Bug")) {
			
			if(ooslaBugId.size()>0) {
				
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ (getOOSLABugCount(ooslaBugs)));
				sb.append(" ( ");
				for(int m=0; m<ooslaBugId.size(); m++) {
					if(m>=1)
					{
						sb.append("  ,  ");
					}
					
					sb.append("<a href='http://bugdb.spire2grow.com/show_bug.cgi?id="
							+ ooslaBugId.get(m) + "'>" + ooslaBugId.get(m));
					sb.append("</a>");
				}
				
				sb.append(" ) ");
				sb.append("</td>");
			}
			else {
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ (getOOSLABugCount(ooslaBugs)));
				sb.append("</td>");
			}
			/* End */
			
			/* Abhishiktha - 11-04-2017 - Added Code to fetch Nearest OOSLA Bugs */
			/* Start */
			ArrayList<String> nearOoslaBugId = getNearOOSLABugId(ooslaBugs);
			if (product!=null && product.getBugType().equalsIgnoreCase("Bug")) {
			
			if(nearOoslaBugId.size()>0) {
				
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ (getNearOOSLABugCount(ooslaBugs)));
				sb.append(" ( ");
				for(int m=0; m<nearOoslaBugId.size(); m++) {
					if(m>=1)
					{
						sb.append("  ,  ");
					}
					
					sb.append("<a href='http://bugdb.spire2grow.com/show_bug.cgi?id="
							+ nearOoslaBugId.get(m) + "'>" + nearOoslaBugId.get(m));
					sb.append("</a>");
				}
				
				sb.append(" ) ");
				sb.append("</td>");
			}
			else {
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ (getNearOOSLABugCount(ooslaBugs)));
				sb.append("</td>");
			}
			/* End */
			
			sb.append("</tr>");
			
			}
		}
	}
				
		return sb;
	}

	public static StringBuilder closeTable(StringBuilder sb) {

		sb.append("</table>");
		sb.append("</body>");
		sb.append("</html>");

		return sb;
	}
	
	public static StringBuilder closeComponentTable(StringBuilder sb) {

		sb.append("</table>");
		sb.append("</body>");
		sb.append("</html>");

		return sb;
	}
	
	public static StringBuilder closeLoginNameTable(StringBuilder sb) {

		sb.append("</table>");
		sb.append("</body>");
		sb.append("</html>");

		return sb;
	}
	
	public static String getBackGroundColor(Bug bug){
		
		String color=null;
    	
    	try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date bugCretionDate=myFormat.parse(bug.getCreationDate());
			Date currentDate=new Date();
			long diff = currentDate.getTime() - bugCretionDate.getTime();
			long numOfDays=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			
			if (bug.getPriority().equalsIgnoreCase("P1-Critical") && numOfDays>1) {
				color="#FF0000";
			}else if (bug.getPriority().equalsIgnoreCase("P2-High") && numOfDays>3) {
				color="#FF0000";
			}else if (bug.getPriority().equalsIgnoreCase("P2-High") && numOfDays>3) {
				color="#FF0000";
			}else if (bug.getPriority().equalsIgnoreCase("P3-Medium") && numOfDays>15) {
				color="#FF0000";
			}else if (bug.getPriority().equalsIgnoreCase("P4-Low") && numOfDays>30) {
				color="#FF0000";
			}
		} catch (ParseException e) {
			return null;
		}
    	
    	return color;
		
	}
	
	public static int getOOSLABugCount(ArrayList<Bug> bugs){
		
		int count=0;
		
		for (Bug bug:bugs) {
			
			if (bug.getIsOOSLA()!=null&&bug.isOOSLA.equalsIgnoreCase("true")) {
				count++;
			}
			
		}
		
		return count;
		
	}

	public static void main(String[] args) throws Exception{

        /*StringBuilder sb = closeTable(createBody(null, createHeader(false),false));

        System.out.println(sb.toString());*/
    	
    	String date="2016-08-18 15:46:01.0";
    	String prority="P1-Critical";
    	String color=null;
    	
    	
    	/*P1-Critical
    	P2-High
    	P3-Medium
    	P4-Low*/
    	
    	SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date bugCretionDate=myFormat.parse(date);
    	Date currentDate=new Date();
    	long diff = currentDate.getTime() - bugCretionDate.getTime();
    	long numOfDays=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    	
    	if (prority.equalsIgnoreCase("P1-Critical") && numOfDays>1) {
    		color="#FF0000";
		}else if (prority.equalsIgnoreCase("P2-High") && numOfDays>3) {
    		color="#FF0000";
		//}else if (prority.equalsIgnoreCase("P2-High") && numOfDays>3) {
    	//	color="#FF0000";
		}else if (prority.equalsIgnoreCase("P3-Medium") && numOfDays>15) {
    		color="#FF0000";
		}else if (prority.equalsIgnoreCase("P4-Low") && numOfDays>30) {
    		color="#FF0000";
		}

    	System.out.println(color);
    	

    }
	
	/* Abhishiktha - 30-03-2017 - Added code to fetch the OOSLA Bug Id's*/
	
	public static ArrayList<String> getOOSLABugId(List<Bug> bugs){
		Collections.sort(bugs);
		
		ArrayList<String> bugid = new ArrayList<String> ();
		for (Bug bug:bugs) {
			if (bug.getIsOOSLA()!=null&&bug.isOOSLA.equalsIgnoreCase("true")) {
				String id = bug.getId();
				System.out.println("BugId:" +id);
				bugid.add(id);	
			}
		}
		return bugid;
	}
	
	/* Abhsihiktha - 05-04-2017 - Added code to fetch Bug Id's */
	
	public static ArrayList<String> getBugId(ArrayList<Bug> bugs){
		ArrayList<String> bugid = new ArrayList<String> ();
		for (Bug bug:bugs) {
				bugid.add(bug.getId());	
		}
		return bugid;
	}
	
	/* Abhishiktha - 11-04-2017 - Added code to fetch nearest OOSLA category */
	
	public static int getNearOOSLABugCount(ArrayList<Bug> bugs){
		int count=0;
		for (Bug bug:bugs) {
			if (bug.getIsNearOOSLA()!=null&&bug.isNearOOSLA.equalsIgnoreCase("true")) {
				count++;
			}
		}
		return count;
	}
	
	public static ArrayList<String> getNearOOSLABugId(List<Bug> bugs){
		Collections.sort(bugs);
		
		ArrayList<String> bugid = new ArrayList<String> ();
		for (Bug bug:bugs) {
			if (bug.getIsNearOOSLA()!=null&&bug.isNearOOSLA.equalsIgnoreCase("true")) {
				String id = bug.getId();
				System.out.println("BugId:" +id);
				bugid.add(id);	
			}
		}
		return bugid;
	}
}
