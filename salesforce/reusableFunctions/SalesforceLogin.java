package reusableFunctions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.GeneralPageProp;
import common.Constants;

public class SalesforceLogin extends UIDriver {
	public SalesforceLogin(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}

	//public static WebDriver driver;
	//public static WebElement element;

	public static void login(String URL, String Role,String ApplicationName,String browserType) throws Exception {
		
		if((UIDriver.cloudStatus).equals(true) || (UIDriver.cloudStatus).equals("NO")) {
			
			try {
				GenericLibrary.openBrowser(browserType);			
				if (URL.equals(Constants.CreateLeadURL)) {
					GenericLibrary.navigate(Constants.CreateLeadURL);
				}else {
					GenericLibrary.navigate(URL);
				}
				GenericLibrary.maximizeWindow();
				if (Role.equals(Constants.STANDARDTCB_ROLE)) {
					GenericLibrary.input(GeneralPageProp.UserName, Constants.STANDARDTCB_USERNAME,"",ApplicationName);
					GenericLibrary.input(GeneralPageProp.Password, Constants.STANDARDTCB_PASSWORD,"",ApplicationName);
					GenericLibrary.click(GeneralPageProp.LoginButton);
					test.log(Status.INFO, "Logged in successfully with role --> " + Constants.STANDARDTCB_ROLE);
				} else if (Role.equals(Constants.MORTGAGEFINANCE_ROLE)) {
					GenericLibrary.input(GeneralPageProp.UserName, Constants.MORTGAGEFINANCE_ROLE,"",ApplicationName);
					GenericLibrary.input(GeneralPageProp.Password, Constants.MortgageFinancePassword,"",ApplicationName);
					GenericLibrary.click(GeneralPageProp.LoginButton);
				} else if (Role.equals(Constants.MTGFINCOLLATERALMGMT_ROLE)) {
				//	GenericLibrary.input(Constants.Obj_UserName, Constants.STANDARDTCB_USERNAME);
				//	GenericLibrary.input(Constants.Obj_Password, Constants.STANDARDTCB_PASSWORD);
					GenericLibrary.click(Constants.Obj_LoginButton);
				} else if (Role.equals(Constants.COMMUNITYDEVELOPMENT_ROLE)) {
				//	GenericLibrary.input(Constants.Obj_UserName, Constants.STANDARDTCB_USERNAME);
				//	GenericLibrary.input(Constants.Obj_Password, Constants.STANDARDTCB_PASSWORD);
					GenericLibrary.click(Constants.Obj_LoginButton);
				} else if (Role.equals(Constants.CUSTOMERSERVICE_ROLE)) {
				//	GenericLibrary.input(Constants.Obj_UserName, Constants.STANDARDTCB_USERNAME);
				//	GenericLibrary.input(Constants.Obj_Password, Constants.STANDARDTCB_PASSWORD);
					GenericLibrary.click(Constants.Obj_LoginButton);
				} else if (Role.equals(Constants.SFADMIN_ROLE)) {
				//	GenericLibrary.input(Constants.Obj_UserName, Constants.STANDARDTCB_USERNAME);
				//	GenericLibrary.input(Constants.Obj_Password, Constants.STANDARDTCB_PASSWORD);
					GenericLibrary.click(Constants.Obj_LoginButton);
				}
				
			}catch(Exception login) {
				test.log(Status.FAIL,  login);
			}
			
		}else {
			System.out.println("Server  Down");
		}	

	}

}
