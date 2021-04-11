package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.BaskGeneralPageProperties;
import reusableFunctions.*;

public class BaskBank_00004_Notification_Add_New_Alert extends UIDriver {

	public BaskBank_00004_Notification_Add_New_Alert(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
public static void notificationAddNewAlert(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {

	try {
		BaskBankLogin.login(ApplicationName,browserType);
		BaskBankApplicationSpecific.NotificationAlersCheck(TestCaseID);
		GenericLibrary.closeBrowser();
	} catch (Exception createLeadFail) {
		GenericLibrary.click(BaskGeneralPageProperties.SignOut);
		Thread.sleep(3000);
		test.log(Status.FAIL, createLeadFail);
		GenericLibrary.closeBrowser();
	}
}
}


