package reusableFunctions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.BaskBankLogMessages;
import pageProperties.BaskGeneralPageProperties;

public class BaskBankApplicationSpecific extends UIDriver {

	
	public BaskBankApplicationSpecific(ExtentTest test) {
		super(test);
	}

	public static void AvaiableBalanceCheck() throws Exception {
		String HomePageBalance = GenericLibrary.getText(BaskGeneralPageProperties.HomePageBalance);
		GenericLibrary.click(BaskGeneralPageProperties.SavingsTab);
		String SavingsPageBalance = GenericLibrary.getText(BaskGeneralPageProperties.SavingsPageBalance);
		
		if(HomePageBalance.equals(SavingsPageBalance)) {
			GenericLibrary.INFO(BaskBankLogMessages.AvailableBalance +HomePageBalance +BaskBankLogMessages.SavingsBalance +SavingsPageBalance);
			GenericLibrary.PASS(BaskBankLogMessages.BalanceMatch);
		}else {
			GenericLibrary.INFO(BaskBankLogMessages.AvailableBalance +HomePageBalance +BaskBankLogMessages.SavingsBalance +SavingsPageBalance);
			GenericLibrary.FAIL(BaskBankLogMessages.BalanceDonotMatch);

		}		
	}
	
	public static void AAdvantageMilesCheck() throws Exception {
		String HomePageMiles = GenericLibrary.getText(BaskGeneralPageProperties.HomePageMiles);
		GenericLibrary.click(BaskGeneralPageProperties.BaskRewards);
		String BaskRewards = GenericLibrary.getText(BaskGeneralPageProperties.MilesonBaskRewards);		
		if(HomePageMiles.equals(BaskRewards)) {
			GenericLibrary.INFO(BaskBankLogMessages.HomePageMiles +HomePageMiles +BaskBankLogMessages.RewardPageMiles +BaskRewards);
			GenericLibrary.PASS(BaskBankLogMessages.MilesMatch);
		}else {
			GenericLibrary.INFO(BaskBankLogMessages.HomePageMiles +HomePageMiles +BaskBankLogMessages.RewardPageMiles +BaskRewards);
			GenericLibrary.FAIL(BaskBankLogMessages.MilesDonotMatch);
		}		
	}
	
	public static void PersonalDetails() throws Exception {
		//Thread.sleep(3000);
		String CustomerName = GenericLibrary.getText(BaskGeneralPageProperties.CustomerName);
		//Thread.sleep(3000);
		System.out.println(CustomerName);
		GenericLibrary.click(BaskGeneralPageProperties.MoreTab);
		GenericLibrary.click(BaskGeneralPageProperties.PersonalDetails);		
		
        
		String ExpectedCustomerName = BaskGeneralPageProperties.PersonalDetailsCustomerName;
		System.out.println(ExpectedCustomerName);
		if(CustomerName.toLowerCase().contains(ExpectedCustomerName.toLowerCase())) {
			GenericLibrary.INFO(BaskBankLogMessages.CustomerNameHomePage +CustomerName +BaskBankLogMessages.CustomerNamePersonalDetails +ExpectedCustomerName);
			GenericLibrary.PASS(BaskBankLogMessages.CustomerNameMatch);
			
		}else {
			GenericLibrary.INFO(BaskBankLogMessages.CustomerNameHomePage +CustomerName +BaskBankLogMessages.CustomerNamePersonalDetails +ExpectedCustomerName);
			GenericLibrary.FAIL(BaskBankLogMessages.CustomerNameDonotMatch);
		}		
	}
	
	public static void NotificationAlersCheck(String TestCaseID) throws Exception {
		GenericLibrary.click(BaskGeneralPageProperties.MoreTab);
		GenericLibrary.click(BaskGeneralPageProperties.NotificationandAlerts);	
		Thread.sleep(3000);
		GenericLibrary.click(BaskGeneralPageProperties.ToggleExternalTransfer);	
		Thread.sleep(1000);
		GenericLibrary.click(BaskGeneralPageProperties.ToggleComputerBrowser);	
		/*Thread.sleep(1000);
		GenericLibrary.click(BaskGeneralPageProperties.ToggleExternalAccountProcess);*/	
		
		// External Transfer	
		Thread.sleep(3000);
		int externalTransfer =GenericLibrary.getElementCount(BaskGeneralPageProperties.ExternalTransferCheck).size();
		System.out.println(externalTransfer);
		if(externalTransfer==0) {
			GenericLibrary.PASS(BaskBankLogMessages.ExternalTransferToggleOff);
			GenericLibrary.getScreenshot(TestCaseID);			
		}else {
			GenericLibrary.FAIL(BaskBankLogMessages.ExternalTransferToggleNotSaved);
			GenericLibrary.getScreenshot(TestCaseID);

		}
		
		// Computer / Browser Register	
		int compBrowser =GenericLibrary.getElementCount(BaskGeneralPageProperties.ComputerBrowserRegister).size();
		System.out.println(compBrowser);
		if(compBrowser==0) {
			GenericLibrary.PASS(BaskBankLogMessages.ComputerBrowserRegisterToggleOFF);
			GenericLibrary.getScreenshot(TestCaseID);			
		}else {
			GenericLibrary.FAIL(BaskBankLogMessages.ComputerBrowserRegisterToggleNotSaved);
			GenericLibrary.getScreenshot(TestCaseID);
		}
		/*
		// External Account Process	
				int externalAccountProcess =GenericLibrary.getElementCount("//li[6]//q2-checkbox[1][@checked]").size();
				System.out.println(externalAccountProcess);
				if(externalAccountProcess==0) {
					GenericLibrary.PASS("External Account Process Alert Toggle set to OFF");
					GenericLibrary.getScreenshot(TestCaseID);			
				}else {
					GenericLibrary.FAIL("External Account Process Alert Toggle not saved");
					GenericLibrary.getScreenshot(TestCaseID);
				}*/
		
		
		Thread.sleep(3000);
		GenericLibrary.click(BaskGeneralPageProperties.ToggleExternalTransfer);	
		Thread.sleep(1000);
		GenericLibrary.click(BaskGeneralPageProperties.ToggleComputerBrowser);	
		/*Thread.sleep(1000);
		GenericLibrary.click(BaskGeneralPageProperties.ToggleExternalAccountProcess);	*/
		Thread.sleep(3000);
		GenericLibrary.click(BaskGeneralPageProperties.SignOut);
		Thread.sleep(3000);
	}

}
