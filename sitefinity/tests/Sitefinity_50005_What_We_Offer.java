package tests;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.SitefinityGeneralPageProperties;
import reusableFunctions.SitefinityApplicationSpecific;

public class Sitefinity_50005_What_We_Offer extends UIDriver {

	public Sitefinity_50005_What_We_Offer(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
public static void whatweOffer(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		
		try {
			GenericLibrary.openBrowser(browserType);
			GenericLibrary.navigate(SitefinityGeneralPageProperties.SitefinityURL);
			GenericLibrary.maximizeWindow();
			GenericLibrary.getDriver().findElement(By.xpath("//nav[2]/ul[1]/li[2]/a[1]")).click();
			String results=SitefinityApplicationSpecific.GetLinkText("//nav[2]/ul[1]/li[2]/a[1]","//div[@class='menu-col-2 menu-col-2-double']//a[contains(text(),'Business Solutions')]","");
			SitefinityApplicationSpecific.validation("Business Solutions",results,TestCaseID);	
			GenericLibrary.closeBrowser();
			
		}catch(Exception whatweOffer) {
			GenericLibrary.getScreenshot(TestCaseID);
			test.log(Status.FAIL, whatweOffer);
			GenericLibrary.closeBrowser();
		}
		
	}

}
