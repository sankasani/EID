package tests;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.SitefinityGeneralPageProperties;
import pageProperties.SitefinityWhoweServePageProperties;
import reusableFunctions.SitefinityApplicationSpecific;

public class Sitefinity_50006_Who_We_Serve extends UIDriver  {

	public Sitefinity_50006_Who_We_Serve(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}

	public static void whoweServe(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		
		try {
			GenericLibrary.openBrowser(browserType);
			GenericLibrary.navigate(SitefinityGeneralPageProperties.SitefinityURL);
			GenericLibrary.maximizeWindow();
			GenericLibrary.getDriver().findElement(By.xpath(SitefinityWhoweServePageProperties.WhoweServeTab)).click();
			String results=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[1]/a[1]","//div[@class='menu-col-1']//a[contains(text(),'Business')]","");
			SitefinityApplicationSpecific.validation("Business",results,TestCaseID);	
			GenericLibrary.closeBrowser();
			
		}catch(Exception whatweServe) {
			GenericLibrary.getScreenshot(TestCaseID);
			test.log(Status.FAIL, whatweServe);
			GenericLibrary.closeBrowser();
		}
		
	}
	
}
