package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.BaskGeneralPageProperties;
import reusableFunctions.*;

public class BaskBank_00003_Verify_Personal_Details extends UIDriver {

	public BaskBank_00003_Verify_Personal_Details(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
	
public static void verifyPersonalDetails(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {

		
	try {
		BaskBankLogin.login(ApplicationName,browserType);
		BaskBankApplicationSpecific.PersonalDetails();
		GenericLibrary.getScreenshot(TestCaseID);
		GenericLibrary.click(BaskGeneralPageProperties.SignOut);
		GenericLibrary.closeBrowser();
	} catch (Exception createLeadFail) {
		//GenericLibrary.getScreenshot(TestCaseID);
		GenericLibrary.click(BaskGeneralPageProperties.SignOut);
		Thread.sleep(3000);
		test.log(Status.FAIL, createLeadFail);
		GenericLibrary.closeBrowser();
	}
}
	

}
