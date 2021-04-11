package tests.sf.relationship;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.Constants;
import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.GeneralPageProp;
import pageProperties.LeadPageProp;
import pageProperties.RelationshipPageProp;
import reusableFunctions.ApplicationSpecific;
import reusableFunctions.SalesforceLogin;

public class Salesforce_80003_Convert_Lead_To_Relationship extends UIDriver {

	public Salesforce_80003_Convert_Lead_To_Relationship(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}

	public static void convertLeadtoRelationship(String testCaseID, String role,String randomNumber,String applicationName,String ChromeHeadLess) throws Exception {
		try {
			SalesforceLogin.login(Constants.CreateLeadURL, role,applicationName,ChromeHeadLess);
			ApplicationSpecific.closeAllOpenTabs();
			GenericLibrary.click(LeadPageProp.LeadListFirst);
			GenericLibrary.click(LeadPageProp.LeadConvertButton);
			ApplicationSpecific.customInput(RelationshipPageProp.relationshipName, testCaseID, "RelationshipName",randomNumber, applicationName);
			GenericLibrary.click(RelationshipPageProp.convertRelationshipButton);
			Thread.sleep(12000);
			/*if(ApplicationSpecific.verifyRelationshipName(RelationshipPageProp.relationshipName,testCaseID, "RelationshipName",randomNumber,applicationName)) {
				test.log(Status.PASS, "Lead got converted to Relationship");
			}else {
				test.log(Status.FAIL, "RelationshipName does not match");

			}*/
			GenericLibrary.getScreenshot(GeneralPageProp.Relationships);
			GenericLibrary.closeBrowser();
			
		}catch(Exception convertLeadtoRel) {
			GenericLibrary.closeBrowser();
			test.log(Status.FAIL, convertLeadtoRel);

		}

	}


}
