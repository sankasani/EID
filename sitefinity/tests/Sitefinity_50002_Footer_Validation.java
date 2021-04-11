package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.SitefinityGeneralPageProperties;
import reusableFunctions.SitefinityApplicationSpecific;

public class Sitefinity_50002_Footer_Validation extends UIDriver {

	public Sitefinity_50002_Footer_Validation(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
public static void footerValidation(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		
		try {
			GenericLibrary.openBrowser(browserType);
			GenericLibrary.navigate(SitefinityGeneralPageProperties.SitefinityURL);
			GenericLibrary.maximizeWindow();
		
			  String results=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[3]/a[1]","//div[@class='row footer-three']//p[1]",""); 
			  SitefinityApplicationSpecific.validation("COPYRIGHT © 2003-2020 TEXAS CAPITAL BANCSHARES, INC. ALL RIGHTS RESERVED.",results,TestCaseID);
			  String results1=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[3]/a[1]","//p[contains(text(),'TEXAS CAPITAL BANK AND THE TEXAS CAPITAL BANK LOGO')]",""); 
			  SitefinityApplicationSpecific.validation("TEXAS CAPITAL BANK AND THE TEXAS CAPITAL BANK LOGO ARE TRADEMARKS OF TEXAS CAPITAL BANCSHARES, INC. AND TEXAS CAPITAL BANK, N.A.",results1,TestCaseID);
			  GenericLibrary.closeBrowser();

						
		}catch(Exception footerValidation) {
			GenericLibrary.getScreenshot(TestCaseID);
			test.log(Status.FAIL, footerValidation);
			GenericLibrary.closeBrowser();
		}
		
	}
	

}
