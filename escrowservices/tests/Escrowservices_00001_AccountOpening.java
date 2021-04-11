package tests;

import com.aventstack.extentreports.ExtentTest;

import core.ui.UIDriver;
import pageProgerties.EscrowServiceGeneralPageProperties;
import resuableFunctions.EscrowLogin;

public class Escrowservices_00001_AccountOpening extends UIDriver{
	
	public Escrowservices_00001_AccountOpening(ExtentTest test) {
		super(test);
	}
	
	public static void accountOpening(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		try {
			EscrowLogin.LoginAddVantage(EscrowServiceGeneralPageProperties.EscrowURL, ApplicationName, browserType);
			
		}
		catch(Exception e){
			
		}
	}
}
