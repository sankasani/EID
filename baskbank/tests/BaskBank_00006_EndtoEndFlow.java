package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.BaskGeneralPageProperties;
import reusableFunctions.BaskBankApplicationSpecific;
import reusableFunctions.BaskBankLogin;

public class BaskBank_00006_EndtoEndFlow extends UIDriver {

	public BaskBank_00006_EndtoEndFlow(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
	
	public static void endtoEndFlow(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		
		
		try {
			BaskBankLogin.login(ApplicationName,browserType);
			
			test.log(Status.INFO, "*****************Start of Available balance check*****************");
			BaskBankApplicationSpecific.AvaiableBalanceCheck();	
			GenericLibrary.getScreenshot("AvailableBalance");
			test.log(Status.INFO, "*****************End of Available balance check*****************");
			
			
			// End to Test Case-1
			test.log(Status.INFO, "*****************Start of AAdvantage miles check*****************");
			GenericLibrary.click(BaskGeneralPageProperties.HomeTab);
			BaskBankApplicationSpecific.AAdvantageMilesCheck();	
			GenericLibrary.getScreenshot("AAdvantageMiles");
			test.log(Status.INFO, "*****************End of AAdvantage miles check*****************");
			
			
			// End of Test Case-2
			test.log(Status.INFO, "*****************Start of Personal details check*****************");
			GenericLibrary.click(BaskGeneralPageProperties.HomeTab);
			BaskBankApplicationSpecific.PersonalDetails();	
			GenericLibrary.getScreenshot("PersonalDetails");
			test.log(Status.INFO, "*****************End of Personal details check*****************");
						

			// End of TestCase 3
			test.log(Status.INFO, "*****************Start of Notilication alert check*****************");
			BaskBankApplicationSpecific.NotificationAlersCheck(TestCaseID);
			test.log(Status.INFO, "*****************End of Notification alert check*****************");
			// End of Test Case -4
			
			GenericLibrary.closeBrowser();

			
		} catch (Exception createLeadFail) {
			GenericLibrary.getScreenshot(TestCaseID);
			test.log(Status.FAIL, createLeadFail);
			GenericLibrary.closeBrowser();
		}
		
		
	}


}
