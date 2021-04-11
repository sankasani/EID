package tests.sf.leads;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.GeneralPageProp;
import pageProperties.LeadPageProp;
import common.Constants;
import reusableFunctions.LeadFormfill;
import reusableFunctions.SalesforceLogin;

public class Salesforce_80001_Create_Lead extends UIDriver {

	public Salesforce_80001_Create_Lead(ExtentTest test) {
		super(test);
	}

	public static void createLead(String TestCaseID, String Role,String randomNumber,String ApplicationName,String browserType) throws Exception {

	
			try {
				SalesforceLogin.login(Constants.CreateLeadURL, Role,ApplicationName,browserType);
				LeadFormfill.form(TestCaseID,Role,randomNumber,ApplicationName);			
				test.log(Status.INFO, "Verifying Created Lead");
				GenericLibrary.click(LeadPageProp.Details);
				GenericLibrary.verifyText(LeadPageProp.LeadVerifyTitle,TestCaseID,"LeadTitle",randomNumber,ApplicationName);
				GenericLibrary.getScreenshot(GeneralPageProp.Leads);
				GenericLibrary.closeBrowser();
			} catch (Exception createLeadFail) {
				GenericLibrary.closeBrowser();
				//Assert.assertFalse(true);
			}					
	}

}
