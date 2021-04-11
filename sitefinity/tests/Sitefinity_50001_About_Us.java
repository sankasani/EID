package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.SitefinityGeneralPageProperties;
import reusableFunctions.SitefinityApplicationSpecific;

public class Sitefinity_50001_About_Us extends UIDriver {

	public Sitefinity_50001_About_Us(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
public static void aboutUs(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		
		try {
			GenericLibrary.openBrowser(browserType);
			GenericLibrary.navigate(SitefinityGeneralPageProperties.SitefinityURL);
			GenericLibrary.maximizeWindow();
		
			  
			  String results=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//a[contains(text(),'Get to Know Us')]",""); 
			  SitefinityApplicationSpecific.validation("Get to Know Us",results,TestCaseID);
			  String results1=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//div//a[contains(text(),'Texas Capitalism®')]","//h1[contains(text(),'TEXAS CAPITALISM')]");
			  SitefinityApplicationSpecific.validation("TEXAS CAPITALISM®",results1,TestCaseID);
			  String results2=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//div//a[contains(text(),'Corporate Overview')]","//h1[contains(text(),'CORPORATE OVERVIEW')]");
			  SitefinityApplicationSpecific.validation("CORPORATE OVERVIEW",results2,TestCaseID);  
			  String results3=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//div//a[contains(text(),'Executive Leadership')]","//h1[contains(text(),'EXECUTIVE LEADERSHIP')]");
			  SitefinityApplicationSpecific.validation("EXECUTIVE LEADERSHIP",results3,TestCaseID);  
			 String results4=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//div//a[contains(text(),'Board of Directors')]","//h1[contains(text(),'BOARD OF DIRECTORS')]");
			 SitefinityApplicationSpecific.validation("BOARD OF DIRECTORS",results4,TestCaseID);  
			 String results5=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//div//a[contains(text(),'History & Heritage')]","//h1[contains(text(),'20 Milestones for 20 Years')]");
			 SitefinityApplicationSpecific.validation("20 Milestones for 20 Years",results5,TestCaseID);  
			String results6=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//div//a[contains(text(),'Diversity & Inclusion')]","//h1[contains(text(),'DIVERSITY & INCLUSION')]");
			SitefinityApplicationSpecific.validation("DIVERSITY & INCLUSION",results6,TestCaseID); 
				 
			String results7=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//div//a[contains(text(),'Locations')]","//h1[contains(text(),'Locations')]");
			SitefinityApplicationSpecific.validation("LOCATIONS",results7,TestCaseID); 
			String results8=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//a[contains(text(),'Philanthropic Giving')]","//h1[contains(text(),'PHILANTHROPIC GIVING')]");
			SitefinityApplicationSpecific.validation("PHILANTHROPIC GIVING",results8,TestCaseID); 
			String results9=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//a[contains(text(),'Volunteerism')]","//h1[contains(text(),'VOLUNTEERISM')]");
			SitefinityApplicationSpecific.validation("VOLUNTEERISM",results9,TestCaseID); 
			String results10=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//a[contains(text(),'Strategic Investments')]","//h1[contains(text(),'STRATEGIC INVESTMENTS')]");
			SitefinityApplicationSpecific.validation("STRATEGIC INVESTMENTS",results10,TestCaseID); 
			String results11=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//a[contains(text(),'Mobile Center')]","//h1[contains(text(),'MOBILE CENTER')]");
			SitefinityApplicationSpecific.validation("MOBILE CENTER",results11,TestCaseID); 
			String results12=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//a[contains(text(),'Disaster Relief')]","//h1[contains(text(),'DISASTER RELIEF')]");
			SitefinityApplicationSpecific.validation("DISASTER RELIEF",results12,TestCaseID); 
		    String results13=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//ul//a[contains(text(),'Newsroom')]","//h1[contains(text(),'NEWSROOM')]");
		    SitefinityApplicationSpecific.validation("NEWSROOM",results13,TestCaseID); 
		//	String results14=SitefinityApplicationSpecific.GetLinkText("//a[@id='nav-menu-link-42b1f04b-3d39-6471-b8b8-ff0000aa52e8']","//div[@class='menu-col-2']//a[contains(text(),'Upcoming Events')]","//h1[contains(text(),'UPCOMING EVENTS')]");
		//	SitefinityApplicationSpecific.validation("UPCOMING EVENTS",results14,TestCaseID); 
			 	  
			  
			  
			GenericLibrary.closeBrowser();

			  
			  
			  
			
						
		}catch(Exception footerValidation) {
			GenericLibrary.getScreenshot(TestCaseID);
			test.log(Status.FAIL, footerValidation);
			GenericLibrary.closeBrowser();
		}
		
	}
	

}
