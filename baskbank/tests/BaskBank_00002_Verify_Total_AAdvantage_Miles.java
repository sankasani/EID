package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.BaskGeneralPageProperties;
import reusableFunctions.*;

public class BaskBank_00002_Verify_Total_AAdvantage_Miles extends UIDriver {

	public BaskBank_00002_Verify_Total_AAdvantage_Miles(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
public static void verifyTotalAAdvantageMiles(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {

		
		try {
			BaskBankLogin.login(ApplicationName,browserType);
			BaskBankApplicationSpecific.AAdvantageMilesCheck();
			GenericLibrary.getScreenshot(TestCaseID);
			GenericLibrary.click(BaskGeneralPageProperties.SignOut);
			GenericLibrary.closeBrowser();
		} catch (Exception miles) {
			GenericLibrary.getScreenshot(TestCaseID);
			test.log(Status.FAIL, miles);
			GenericLibrary.closeBrowser();
		}
				
}

}
