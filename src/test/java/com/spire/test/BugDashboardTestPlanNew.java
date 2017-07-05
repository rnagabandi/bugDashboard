package com.spire.test;

import java.util.Properties;

import org.testng.annotations.Test;

import com.spire.base.controller.ContextManager;
import com.spire.base.controller.TestPlan;
import com.spire.bean.ProductBean;
import com.spire.bean.Report;
import com.spire.database.DBModule;
import com.spire.helper.SendEmail;
import com.spire.helper.SpireProperties;

public class BugDashboardTestPlanNew extends TestPlan {

    @Test(groups = {"crmSendEmail"})
    public void sendEmail() throws Exception {

        String toList = ContextManager.getThreadContext().getEmailToList();
        String productName = ContextManager.getThreadContext().getProductName();
        String bugType=ContextManager.getThreadContext().getPriority();//"Enhancements";//Bug or Enhancements
        
        ProductBean productDetails=ProductBean.populateProductBean(productName);
        productDetails.setBugType(bugType);

        String subject = bugType+" Dashboard";

    	StringBuilder builder=null;
    	StringBuilder componentBuilder=null;
    	StringBuilder loginEmailBuilder=null;

       	builder = Report.createHeader(false);
        builder = Report.createBody(DBModule.getBugs(productName, bugType), builder,false);
        StringBuilder body = Report.closeTable(builder);
        
        componentBuilder=Report.createComponentHeader(productDetails);
        componentBuilder=Report.createComponentBody(productDetails, componentBuilder);
        componentBuilder=Report.closeComponentTable(componentBuilder);     
        
        loginEmailBuilder=Report.createLoginEmailHeader(productDetails);
        loginEmailBuilder=Report.createLoginNameBody(productDetails, loginEmailBuilder);
        loginEmailBuilder=Report.closeLoginNameTable(loginEmailBuilder);
        
        SendEmail.sendEmail(toList,componentBuilder.append(loginEmailBuilder.toString()).append(body.toString()) , subject);
        
    }

}
