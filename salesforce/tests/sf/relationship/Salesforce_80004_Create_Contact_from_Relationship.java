package tests.sf.relationship;

import com.aventstack.extentreports.ExtentTest;
import common.Constants;
import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.GeneralPageProp;
import pageProperties.RelationshipPageProp;
import reusableFunctions.ApplicationSpecific;
import reusableFunctions.SalesforceLogin;

public class Salesforce_80004_Create_Contact_from_Relationship extends UIDriver {
	
	public Salesforce_80004_Create_Contact_from_Relationship(ExtentTest test) {
		super(test);
	}

	public static void createContact(String TestCaseID, String Role,String randomNumber,String ApplicationName,String ChromeHeadLess) throws Exception {
		
		try {
			SalesforceLogin.login(Constants.RelationshipURL, Role,ApplicationName,ChromeHeadLess);
			ApplicationSpecific.closeAllOpenTabs();
			GenericLibrary.click(RelationshipPageProp.RelationshipListName);
			GenericLibrary.click(RelationshipPageProp.RelationshipRelatedContacts);

			//LeadFormfill.form(TestCaseID,Role,randomNumber,ApplicationName);			
			//test.log(Status.INFO, "Verifying Created Lead");
			//GenericLibrary.click(LeadPageProp.Details);
			//GenericLibrary.verifyText(LeadPageProp.LeadVerifyTitle,TestCaseID,"LeadTitle",randomNumber,ApplicationName);
			GenericLibrary.getScreenshot(GeneralPageProp.Leads);
			GenericLibrary.closeBrowser();
		} catch (Exception createLeadFail) {
			GenericLibrary.getScreenshot(GeneralPageProp.Leads);
			GenericLibrary.closeBrowser();
		}
		
	}

}
