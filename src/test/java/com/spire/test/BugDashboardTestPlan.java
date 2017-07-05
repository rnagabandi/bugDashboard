package com.spire.test;

import org.testng.annotations.Test;

import com.spire.base.controller.ContextManager;
import com.spire.base.controller.TestPlan;
import com.spire.bean.Report;
import com.spire.database.DBModule;
import com.spire.helper.SendEmail;

public class BugDashboardTestPlan extends TestPlan {

    @Test(groups = {"sendEmail"})
    public void sendEmail() throws Exception {

        String toList = ContextManager.getThreadContext().getEmailToList();
        String productName = ContextManager.getThreadContext().getProductName();

        //String toList="raghavender.nagabandi@spire2grow.com,product-engg-crm-qe@spire2grow.com";
        //String productName="Talentvista";
        String subject = null;

        for (int i = 0; i < 2; i++) {

        	StringBuilder builder=null;
        	StringBuilder componentBuilder=null;

            if (i == 0) {
            	builder = Report.createHeader(false);
                builder = Report.createBody(DBModule.getBugs(productName, "Bug"), builder,false);
                componentBuilder=Report.createComponentHeader(null);
                componentBuilder=Report.createComponentBody(productName, componentBuilder);
                subject = "Bug Dashboard";
            } else if (i == 1){
            	builder = Report.createHeader(false);
                builder = Report.createBody(DBModule.getBugs(productName, "Enhancements"), builder,false);
                subject = "Enhancements Dashboard";
            }else {
            	builder = Report.createHeader(true);
                builder = Report.createBody(DBModule.getBugs(productName, "ProductionBugs"), builder,true);
                subject = "Production Bugs/Enhanacements Dashboard";
            }
            
            StringBuilder body = Report.closeTable(builder);
            
            if (componentBuilder!=null){ 				
			   componentBuilder=Report.closeComponentTable(componentBuilder);
			   SendEmail.sendEmail(toList,componentBuilder.append(body.toString()) , subject);
            }else
            	SendEmail.sendEmail(toList,body,subject);
            
            
            
        }
    }


    @Test(groups = {"sendEmailTalent"})
    public void sendEmailTalent() throws Exception {

        String toList = ContextManager.getThreadContext().getEmailToList();
        String productName = ContextManager.getThreadContext().getProductName();
        String priority = ContextManager.getThreadContext().getPriority();
        String subject = null;

        for (int i = 1; i < 2; i++) {
            StringBuilder builder = Report.createHeader(false);
            if (i == 1) {
                System.out.println("pri =======> " + priority);
                builder = Report.createBody(DBModule.getBugs(productName, priority), builder,false);
                subject = priority + "Dash Board & Total " + priority + " Bugs Count :" + DBModule.getBugs(productName, priority).size();
            }
            StringBuilder body = Report.closeTable(builder);
            SendEmail.sendEmail(toList, body, subject);

        }


    }


}
