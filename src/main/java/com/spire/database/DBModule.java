package com.spire.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.spire.bean.Bug;
import com.spire.helper.SpireProperties;

public class DBModule {

	public static Connection getConnection() throws Exception {

		Properties properties = SpireProperties.loadProperties();
		Properties dbProperties = new Properties();
		dbProperties.put("user", properties.getProperty("BUGZILLA_DB_USER_ID"));
		dbProperties.put("password", properties.getProperty("BUGZILLA_DB_PASSWORD"));
		dbProperties.put("characterEncoding", "ISO-8859-1");
		dbProperties.put("useUnicode", "true");

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(properties.getProperty("BUGZILLA_DB_URL"), dbProperties);

		return connection;

	}

	public static Connection getNewDBConnection() throws Exception {

		Properties properties = SpireProperties.loadProperties();
		Properties dbProperties = new Properties();
		dbProperties.put("user", properties.getProperty("BUGZILLA_NEWDB_USER_ID"));
		dbProperties.put("password", properties.getProperty("BUGZILLA_NEWDB_PASSWORD"));
		dbProperties.put("characterEncoding", "ISO-8859-1");
		dbProperties.put("useUnicode", "true");

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(properties.getProperty("BUGZILLA_NEWDB_URL"), dbProperties);

		return connection;

	}
	
	/* Abhishiktha - 28-03-2016 - Added code to eliminate RESOLVED status as well */

	public static String getSQLQuery(String productName, String bugType) {

		String query = null;

		if (bugType.equalsIgnoreCase("Bug")) {

			query = "select DISTINCT a.bug_id, a.bug_severity, a.bug_status, a.short_desc, "
					+ "a.priority,a.creation_ts, a.deadline , b.login_name, c.thetext, d.name , cf_reported_environment from bugs a, profiles b, longdescs c, components d  where a.assigned_to"
					+ " = b.userid and a.bug_id = c.bug_id and a.component_id = d.id and a.product_id in "
					+ "(select id from products where name like '" + productName
					+ "')  and a.bug_status not in ('CLOSED', 'RESOLVED') and a.bug_severity in ('Bug') order by b.login_name;";

		} else if (bugType.equalsIgnoreCase("Enhancements")){
			query = "select DISTINCT a.bug_id, a.bug_severity, a.bug_status, a.short_desc, "
					+ "a.priority,a.creation_ts, a.deadline , b.login_name, c.thetext, d.name , cf_reported_environment from bugs a, profiles b, longdescs c, components d where a.assigned_to"
					+ " = b.userid and a.bug_id = c.bug_id and a.component_id = d.id and a.product_id in "
					+ "(select id from products where name like '" + productName
					+ "')  and a.bug_status not in ('CLOSED', 'RESOLVED') and a.bug_severity in ('Enhancement' , 'Feature Request') order by b.login_name;";

		}else {
			query = "select DISTINCT a.bug_id, a.bug_severity, a.bug_status, a.short_desc, "
					+ "a.priority,a.creation_ts, a.deadline , b.login_name, c.thetext, d.name from bugs a, profiles b, longdescs c, components d where a.assigned_to"
					+ " = b.userid and a.bug_id = c.bug_id and a.component_id = d.id and a.product_id in "
					+ "(select id from products where name like '" + productName
					+ "')  and a.bug_status not in ('CLOSED', 'RESOLVED') and cf_reported_environment='prod' order by b.login_name;";
		}
		
		if (bugType.equalsIgnoreCase("P1-Critical") || bugType.equalsIgnoreCase("P2-High")
				|| bugType.equalsIgnoreCase("P3-Medium") || bugType.equalsIgnoreCase("P4-Low")) {
			query = "select DISTINCT a.bug_id, a.bug_severity, a.bug_status, a.short_desc, "
					+ "a.priority,a.creation_ts, a.deadline , b.login_name, c.thetext, d.name from bugs a, profiles b, longdescs c, components d where a.assigned_to"
					+ " = b.userid and a.bug_id = c.bug_id and a.component_id = d.id and a.product_id in "
					+ "(select id from products where name like '" + productName
					+ "')  and a.bug_status not in ('RESOLVED','CLOSED') and a.bug_severity in ('Bug') and a.priority in ('"
					+ bugType + "') order by d.name,a.priority;";

		}

		return query;

	}
	
	public static String getSQLQuery(String productName, String bugType,String loginName) {

		String query = null;

		if (bugType.equalsIgnoreCase("Bug")) {

			query = "select DISTINCT a.bug_id, a.bug_severity, a.bug_status, a.short_desc, "
					+ "a.priority,a.creation_ts, a.deadline , b.login_name, c.thetext, d.name , cf_reported_environment from bugs a, profiles b, longdescs c, components d  where a.assigned_to"
					+ " = b.userid and a.bug_id = c.bug_id and a.component_id = d.id and a.product_id in "
					+ "(select id from products where name like '" + productName
					+ "')  and a.bug_status not in ('CLOSED', 'RESOLVED') and a.bug_severity in ('Bug') and b.login_name in ("+loginName+") order by b.login_name;";

		} else if (bugType.equalsIgnoreCase("Enhancements")){
			query = "select DISTINCT a.bug_id, a.bug_severity, a.bug_status, a.short_desc, "
					+ "a.priority,a.creation_ts, a.deadline , b.login_name, c.thetext, d.name , cf_reported_environment from bugs a, profiles b, longdescs c, components d where a.assigned_to"
					+ " = b.userid and a.bug_id = c.bug_id and a.component_id = d.id and a.product_id in "
					+ "(select id from products where name like '" + productName
					+ "')  and a.bug_status not in ('CLOSED', 'RESOLVED') and a.bug_severity in ('Enhancement' , 'Feature Request') and b.login_name in ("+loginName+") order by b.login_name;";

		}

		return query;

	}
	
	public static String getSQLQuery(String productName, String bugType,String loginName,String priority) {

		String query = null;

		if (bugType.equalsIgnoreCase("Bug")) {

			query = "select DISTINCT a.bug_id, a.bug_severity, a.bug_status, a.short_desc, "
					+ "a.priority,a.creation_ts, a.deadline , b.login_name, c.thetext, d.name , cf_reported_environment from bugs a, profiles b, longdescs c, components d  where a.assigned_to"
					+ " = b.userid and a.bug_id = c.bug_id and a.component_id = d.id and a.product_id in "
					+ "(select id from products where name like '" + productName
					+ "')  and a.bug_status not in ('CLOSED', 'RESOLVED') and a.bug_severity in ('Bug') and b.login_name in ("+loginName+") and a.priority in ('"
					+ priority + "') order by b.login_name;";

		} else if (bugType.equalsIgnoreCase("Enhancements")){
			query = "select DISTINCT a.bug_id, a.bug_severity, a.bug_status, a.short_desc, "
					+ "a.priority,a.creation_ts, a.deadline , b.login_name, c.thetext, d.name , cf_reported_environment from bugs a, profiles b, longdescs c, components d where a.assigned_to"
					+ " = b.userid and a.bug_id = c.bug_id and a.component_id = d.id and a.product_id in "
					+ "(select id from products where name like '" + productName
					+ "')  and a.bug_status not in ('CLOSED', 'RESOLVED') and a.bug_severity in ('Enhancement' , 'Feature Request') and b.login_name in ("+loginName+") and a.priority in ('"
					+ priority + "') order by b.login_name;";

		}

		return query;

	}

	public static ArrayList<Bug> getBugs(String productName, String bugType) throws Exception {

		HashMap<String, Bug> map = new HashMap<String, Bug>();

		ArrayList<Bug> listBugs = new ArrayList<Bug>();
		Connection connection = getNewDBConnection();

		ResultSet resultSet = connection.createStatement().executeQuery(getSQLQuery(productName, bugType));

		while (resultSet.next()) {

			Bug bug = new Bug();

			bug.setId(resultSet.getString("bug_id"));
			bug.setAssignee(resultSet.getString("login_name"));
			bug.setComponent(resultSet.getString("name"));
			bug.setCreationDate(resultSet.getString("creation_ts"));
			bug.setDescription(resultSet.getString("short_desc"));
			bug.setPriority(resultSet.getString("priority"));
			bug.setStatus(resultSet.getString("bug_status"));
			bug.setDeadline(resultSet.getString("deadline"));
			bug.setBugType(resultSet.getString("bug_severity"));
			bug.setIsProduction(resultSet.getString("cf_reported_environment"));
			bug.setIsOOSLA(getIsOOSLA(bug));
			bug.setIsNearOOSLA(getIsNearOOSLA(bug));
			
			if (!map.containsKey(bug.getId())) {

				map.put(bug.getId(), bug);
				listBugs.add(bug);

			}

		}

		System.out.println("size is :" + map.size());

		return listBugs;

	}
	
	public static ArrayList<Bug> getBugsByLoginName(String productName, String bugType , String loginName , String pirority) throws Exception {

		HashMap<String, Bug> map = new HashMap<String, Bug>();

		ArrayList<Bug> listBugs = new ArrayList<Bug>();
		Connection connection = getNewDBConnection();
		ResultSet resultSet=null;
		
		if (pirority==null) 
			resultSet = connection.createStatement().executeQuery(getSQLQuery(productName, bugType,loginName));	
		else
			resultSet = connection.createStatement().executeQuery(getSQLQuery(productName, bugType,loginName,pirority));
		

		while (resultSet.next()) {

			Bug bug = new Bug();

			bug.setId(resultSet.getString("bug_id"));
			bug.setAssignee(resultSet.getString("login_name"));
			bug.setComponent(resultSet.getString("name"));
			bug.setCreationDate(resultSet.getString("creation_ts"));
			bug.setDescription(resultSet.getString("short_desc"));
			bug.setPriority(resultSet.getString("priority"));
			bug.setStatus(resultSet.getString("bug_status"));
			bug.setDeadline(resultSet.getString("deadline"));
			bug.setBugType(resultSet.getString("bug_severity"));
			bug.setIsProduction(resultSet.getString("cf_reported_environment"));
			bug.setIsOOSLA(getIsOOSLA(bug));
			bug.setIsNearOOSLA(getIsNearOOSLA(bug));
			
			if (!map.containsKey(bug.getId())) {

				map.put(bug.getId(), bug);
				listBugs.add(bug);

			}

		}

		System.out.println("size is :" + map.size());

		return listBugs;

	}
	
	public static String getIsOOSLA(Bug bug){
		
		String isOOSLA="false";
    	
		if (bug.getBugType().equalsIgnoreCase("Bug") && bug.getIsProduction().equalsIgnoreCase("Prod")) {
				
	    	try {
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date bugCretionDate=myFormat.parse(bug.getCreationDate());
				Date currentDate=new Date();
				long diff = currentDate.getTime() - bugCretionDate.getTime();
				long numOfDays=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				
				if (bug.getPriority().equalsIgnoreCase("P1-Critical") && numOfDays>1) {
					isOOSLA="true";
				}else if (bug.getPriority().equalsIgnoreCase("P2-High") && numOfDays>3) {
					isOOSLA="true";
				//}else if (bug.getPriority().equalsIgnoreCase("P2-High") && numOfDays>3) {
				//	isOOSLA="true";
				}else if (bug.getPriority().equalsIgnoreCase("P3-Medium") && numOfDays>15) {
					isOOSLA="true";
				}else if (bug.getPriority().equalsIgnoreCase("P4-Low") && numOfDays>30) {
					isOOSLA="true";
				}
			} catch (ParseException e) {
				return null;
			}
	    	
	    	return isOOSLA;
		}
		return "false";
				
		
	}

	public static void main(String[] args) throws Exception {

		System.out.println(getBugs("Talentvista", "Bug").size());
		System.out.println(getBugs("Talentvista", "Enhancement").size());

	}
	
	/* Abhishiktha - 11-04-2017 - Added code to fetch nearest to OOSLA category */
	
	public static String getIsNearOOSLA(Bug bug){
		
		String isNearOOSLA="false";
    	
		if (bug.getBugType().equalsIgnoreCase("Bug") && bug.getIsProduction().equalsIgnoreCase("Prod")) {
				
	    	try {
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date bugCretionDate=myFormat.parse(bug.getCreationDate());
				Date currentDate=new Date();
				long diff = (currentDate.getTime() - bugCretionDate.getTime());
				long numOfDays =TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				long numOfDaysNsla = numOfDays + 7;
				//long days = numOfDays + 7;
				
				System.out.println("NumOfDays is "+numOfDays +"for BugId: " + bug.getId());
				//System.out.println("Days is "+days +"for BugId: " + bug.getId());
				
				if (bug.getPriority().equalsIgnoreCase("P1-Critical") && numOfDaysNsla>=7 &&  numOfDays<=1) {
					isNearOOSLA="true";
				}else if (bug.getPriority().equalsIgnoreCase("P2-High") && numOfDaysNsla>=10 && numOfDays<=3) {
					isNearOOSLA="true";
				}else if (bug.getPriority().equalsIgnoreCase("P3-Medium") && numOfDaysNsla>=22 && numOfDays<=15) {
					isNearOOSLA="true";
				}else if (bug.getPriority().equalsIgnoreCase("P4-Low") && numOfDaysNsla>=37 &&  numOfDays<=30) {
					isNearOOSLA="true";
				}
			} catch (ParseException e) {
				return null;
			}
	    	return isNearOOSLA;
		}
		return "false";	
	}
}
