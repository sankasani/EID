package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.BaskBankLogMessages;
import pageProperties.BaskGeneralPageProperties;
import reusableFunctions.*;

public class BaskBank_00001_Verify_Available_Balance extends UIDriver {

	public BaskBank_00001_Verify_Available_Balance(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
	public static void verifyAvailableBalance(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {

		
		try {
			BaskBankLogin.login(ApplicationName,browserType);
			BaskBankApplicationSpecific.AvaiableBalanceCheck();		
			GenericLibrary.getScreenshot(TestCaseID);
			GenericLibrary.click(BaskGeneralPageProperties.SignOut);
			GenericLibrary.closeBrowser();
		} catch (Exception createLeadFail) {
			GenericLibrary.getScreenshot(TestCaseID);
			test.log(Status.FAIL, createLeadFail);
			GenericLibrary.closeBrowser();
		}

}

}
