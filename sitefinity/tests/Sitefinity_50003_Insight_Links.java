package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.SitefinityGeneralPageProperties;
import reusableFunctions.SitefinityApplicationSpecific;

public class Sitefinity_50003_Insight_Links extends UIDriver {

	public Sitefinity_50003_Insight_Links(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
	
public static void insightLinks(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		
	String results = null;
		try {
			
			
			GenericLibrary.openBrowser(browserType);
			GenericLibrary.navigate(SitefinityGeneralPageProperties.SitefinityURL);
			GenericLibrary.maximizeWindow();
			
		
		  results = SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[3]/a[1]", "//h3//a[contains(text(),'Insights')]","");
		  SitefinityApplicationSpecific.validation("Insights",results,TestCaseID);
		   String results1=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//div//a[contains(text(),'Business Planning Insights')]","//h1[contains(text(),'Business Planning Insights')]");
		  SitefinityApplicationSpecific.validation("BUSINESS PLANNING INSIGHTS",results1,TestCaseID);
		  
		  String  results2=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//div//a[contains(text(),'Market & Financial Insights')]","//h1[contains(text(),'Market & Financial Insights')]");
		  SitefinityApplicationSpecific.validation("MARKET & FINANCIAL INSIGHTS",results2,TestCaseID);
		  
		  String results3=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//div//a[contains(text(),'Personal Planning Insights')]","//h1[contains(text(),'Personal Planning Insights')]");
		  SitefinityApplicationSpecific.validation("PERSONAL PLANNING INSIGHTS",results3,TestCaseID);
		   
		  String  results4=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//div//a[contains(text(),'Success Stories')]","//h1[contains(text(),'SUCCESS STORIES')]");
		  SitefinityApplicationSpecific.validation("SUCCESS STORIES",results4,TestCaseID);
		   
		  String results5=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[3]/a[1]",
		  "//div[@class='menu-col-2']//div//a[contains(text(),'Videos')]",
		  "//h1[contains(text(),'VIDEOS')]");
		  SitefinityApplicationSpecific.validation("VIDEOS",results5,TestCaseID);
		   
		  String results6=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[3]/a[1]",
		  "//div[@class='menu-col-2']//div//a[contains(text(),'Calculators')]",
		  "//h1[contains(text(),'CALCULATORS')]");
		  SitefinityApplicationSpecific.validation("CALCULATORS",results6,TestCaseID);
		  
		  String results7=SitefinityApplicationSpecific.GetLinkText(
		  "//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//div//a[contains(text(),'Wire & ACH Instructions')]"
		  ,"//h1[contains(text(),'WIRE & ACH INSTRUCTIONS')]");
		  SitefinityApplicationSpecific.validation("WIRE & ACH INSTRUCTIONS",results7,TestCaseID); 
		  
		  String  results8=SitefinityApplicationSpecific.GetLinkText(
		  "//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//div//a[contains(text(),'Audit Confirmations')]"
		  ,"//h1[contains(text(),'AUDIT CONFIRMATIONS')]");
		  SitefinityApplicationSpecific.validation("AUDIT CONFIRMATIONS",results8,TestCaseID); 
		  
		  String  results9=SitefinityApplicationSpecific.GetLinkText(
		  "//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//a[contains(text(),'Standard Loan Documents')]"
		  ,"//h1[contains(text(),'STANDARD LOAN DOCUMENTS')]");
		  SitefinityApplicationSpecific.validation("STANDARD LOAN DOCUMENTS",results9,TestCaseID); 
		  
		  String  results10=SitefinityApplicationSpecific.GetLinkText(
		  "//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//a[contains(text(),'Current Rates')]"
		  ,"//h1[contains(text(),'Rates')]"); 
		  SitefinityApplicationSpecific.validation("RATES",results10,TestCaseID);
		   
		  String results11=SitefinityApplicationSpecific.GetLinkText(
		  "//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//a[contains(text(),'Fraud Protection')]"
		  ,"//h1[contains(text(),'FRAUD PROTECTION')]");
		  SitefinityApplicationSpecific.validation("FRAUD PROTECTION",results11,TestCaseID);
		  		 
		   String results12=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//a[contains(text(),'Online Banking Experience Guide')]","//h1[contains(text(),'ONLINE BANKING EXPERIENCE GUIDE')]");
		   SitefinityApplicationSpecific.validation("ONLINE BANKING EXPERIENCE GUIDE",results12,TestCaseID); 
		    
		
		  String results13=SitefinityApplicationSpecific.GetLinkText(
		  "//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//a[contains(text(),'Consumer Fee Schedule')]"
		  ,"//h1[contains(text(),'CONSUMER BANKING SERVICES')]");
		  SitefinityApplicationSpecific.validation("CONSUMER BANKING SERVICES",results13,TestCaseID); 
		   
		  String
		  results14=SitefinityApplicationSpecific.GetLinkText(
		  "//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//a[contains(text(),'Private Wealth Fee Schedule')]"
		  ,"//h1[contains(text(),'STARPOINT COLLECTION')]");
		  SitefinityApplicationSpecific.validation("STARPOINT COLLECTION",results14,TestCaseID); 
		   
		  String
		  results15=SitefinityApplicationSpecific.GetLinkText(
		  "//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//a[contains(text(),'Frequently Asked Questions')]"
		  ,"//h1[contains(text(),'FREQUENTLY ASKED QUESTIONS')]");
		  SitefinityApplicationSpecific.validation("FREQUENTLY ASKED QUESTIONS",results15,TestCaseID); 
		  
		  String results16=SitefinityApplicationSpecific.GetLinkText(
		  "//nav[2]/ul[1]/li[3]/a[1]","//div[@class='menu-col-2']//a[contains(text(),'Commercial Card Servicing & Resources')]"
		  ,"//h1[contains(text(),'COMMERCIAL CARD SERVICING & RESOURCES')]");
		  SitefinityApplicationSpecific.validation("COMMERCIAL CARD SERVICING & RESOURCES",results16,TestCaseID);
			
			
			GenericLibrary.closeBrowser();
	
				
			
		}catch(Exception insightLinks) {
			GenericLibrary.getScreenshot(TestCaseID);
			test.log(Status.FAIL, insightLinks);
			GenericLibrary.closeBrowser();
		}
		
		
		
		
		
	}

}
