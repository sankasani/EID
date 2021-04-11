package tests;

import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import core.ui.UIDriver;
import pageProperties.Q2GeneralPageProp;
import reusableFunctions.Q2Login;

public class Q2Central_40001_Create_Customer extends UIDriver {

	public Q2Central_40001_Create_Customer(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
	public static void createCustomer(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		
		try {
			Q2Login.login(ApplicationName);
		 	Screen Q2Screen = new Screen();
	        Pattern Reporting = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.ReportingLink);
	        Q2Screen.wait(Reporting, 3600);
	        Thread.sleep(2000);
			Q2Screen.click(Reporting);

	        Pattern CustomerLink = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.CustomerLink);
	        Q2Screen.wait(CustomerLink, 3600);
	        Q2Screen.doubleClick(CustomerLink);	

	        Pattern CustomerPage = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.CustomerPage);
	        Q2Screen.wait(CustomerPage, 3600);
	        Q2Screen.rightClick(CustomerPage);
	        

	        Pattern NewCustomer = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.NewCustomer);
	        Q2Screen.wait(NewCustomer, 3600);
	        Q2Screen.click(NewCustomer);
	        
	        
	        Pattern CustomerName = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.CustomerName);
	        Q2Screen.wait(CustomerName, 3600);
	        Q2Screen.click(CustomerName);
	        Q2Screen.type("EDI"+randomNumber); 
	        
	      
	        
	        Pattern Country = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.Country);
	        Q2Screen.wait(Country, 3600);
	        Q2Screen.click(Country);
	        
	        Pattern SelectCountry = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.SelectCountry);
	        Q2Screen.wait(SelectCountry, 3600);
	        Q2Screen.click(SelectCountry);
	        
	        
	        Pattern Address1 = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.Address1);
	        Q2Screen.wait(Address1, 3600);
	        Q2Screen.click(Address1);
	        Q2Screen.type("9100 Independence Parkway"); 
	        
	        Pattern Address2 = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.Address2);
	        Q2Screen.wait(Address2, 3600);
	        Q2Screen.click(Address2);
	        Q2Screen.type("Plano"); 
	        
	        Pattern City = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.City);
	        Q2Screen.wait(City, 3600);
	        Q2Screen.click(City);
	        Q2Screen.type("Dallas"); 
	        
	        Pattern Zip = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.Zip);
	        Q2Screen.wait(Zip, 3600);
	        Q2Screen.click(Zip);
	        
	        for(int i=0;i<15;i++) {
		        Q2Screen.type(Key.BACKSPACE);
	        }
	        Q2Screen.type("750258000");
	        
	        Pattern CustomerType = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.CustomerType);
	        Q2Screen.wait(CustomerType, 3600);
	        Q2Screen.click(CustomerType);
	        
	        
	        Pattern TAXID = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.TAXID);
	        Q2Screen.wait(TAXID, 3600);
	        Q2Screen.click(TAXID);
	        
	        for(int i=0;i<15;i++) {
		        Q2Screen.type(Key.BACKSPACE);
	        }
	        Q2Screen.type("111111111");
	       
	        Pattern SaveButton = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.SaveButton);
	        Q2Screen.wait(SaveButton, 3600);
	        Q2Screen.click(SaveButton);
	        
	        Pattern SaveComplete = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.SaveComplete);
	        Q2Screen.wait(SaveComplete, 3600);
	        Q2Screen.click(SaveComplete);
	        
	        test.log(Status.PASS, "New Customer createw with name -->" +"EDI"+randomNumber);
	        
	        Pattern NewCustomerCloseButton = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.NewCustomerCloseButton);
	        Q2Screen.wait(NewCustomerCloseButton, 3600);
	        Q2Screen.click(NewCustomerCloseButton);
	        
	     /*   Pattern LogOffButton = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.LogOffButton);
	        Q2Screen.wait(LogOffButton, 3600);
	        Q2Screen.click(LogOffButton);
	        
	        Pattern CloseQ2Central = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.CloseQ2Central);
	        Q2Screen.wait(CloseQ2Central, 3600);
	        Q2Screen.click(CloseQ2Central);
	        */
	        Thread.sleep(4000);
	        Pattern CloseQ2 = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.CloseQ2);
	        Q2Screen.wait(CloseQ2, 3600);
	        Q2Screen.click(CloseQ2);
	        
			Process process = Runtime.getRuntime().exec("taskkill /F /IM Winium.Desktop.Driver.exe");
	        process.waitFor();
	        process.destroy();
	        test.log(Status.PASS, "Create Customer Successful");
		}catch(Exception e) {
	        test.log(Status.FAIL, "Create Customer UnSuccessful");
	        Process process = Runtime.getRuntime().exec("taskkill /F /IM Winium.Desktop.Driver.exe");
	        process.waitFor();
	        process.destroy();
		}
			
	}


}
