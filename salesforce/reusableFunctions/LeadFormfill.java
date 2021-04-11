package reusableFunctions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.LeadPageProp;

public class LeadFormfill extends UIDriver{
	
	public LeadFormfill(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}

	public static void form(String TestCaseID,String Role,String randomNumber,String ApplicationName) throws Exception {
		
		try {
			ApplicationSpecific.closeAllOpenTabs();

			GenericLibrary.click(LeadPageProp.LeadNewButton);
			List<WebElement> count= GenericLibrary.getElementCount("//ul[@class='tabBarItems slds-grid']/li[contains(@class,'tabItem')]");
			if(count.size()>1) {
				 List<String> namesTab = new ArrayList<>();

					for (int i=2;i<count.size()+2;i++) {
						String taboptions= "//ul[@class='tabBarItems slds-grid']/li["+i+"][contains(@class,'tabItem')]/a/span[2]";				
						System.out.println(GenericLibrary.getDriver().findElement(By.xpath(taboptions)).getText());
						//String TabName = GenericLibrary.getDriver().findElement(By.xpath(taboptions)).getText();
						String TabName = GenericLibrary.getText(taboptions);
						namesTab.add(TabName);

					}
					
					for (int j=0;j<namesTab.size();j++) {
						if(namesTab.get(j).equals("New Lead: Standard")) {
							GenericLibrary.click("//a[contains(@class,'tabHeader slds-context-bar__label-action')]//span[contains(@class,'title slds-truncate')][contains(text(),'New Lead: Standard')]");
						}
				/*		String TabClose ="//a[contains(@title, '"+namesTab.get(j)+"')]/../div[2]/button/lightning-primitive-icon";
					//	GenericLibrary.getDriver().findElement(By.xpath(TabClose)).click();
						//a[contains(@class,'tabHeader slds-context-bar__label-action')]//span[contains(@class,'title slds-truncate')][contains(text(),'New Lead: Standard')]
						GenericLibrary.click(TabClose);*/

					}
				}
			
			  
			
			
			GenericLibrary.click(LeadPageProp.Salutation);
			GenericLibrary.click(LeadPageProp.SelectSalutation);
			GenericLibrary.input(LeadPageProp.LeadFirstName, TestCaseID,"LeadFirstName",ApplicationName);
			ApplicationSpecific.customInput(LeadPageProp.LeadLastName,TestCaseID, "LeadLastName",randomNumber,ApplicationName);
			ApplicationSpecific.customInput(LeadPageProp.LeadTitle,TestCaseID, "LeadTitle",randomNumber,ApplicationName);
			GenericLibrary.input(LeadPageProp.LeadCompany,TestCaseID, "LeadCompany",ApplicationName);
			GenericLibrary.click(LeadPageProp.LeadSource);
			GenericLibrary.click(LeadPageProp.LeadSourceSelect);
			GenericLibrary.click(LeadPageProp.LeadStatus);
			GenericLibrary.click(LeadPageProp.LeadStatusSelect);
			GenericLibrary.input(LeadPageProp.LeadPhone,TestCaseID,"LeadPhone",ApplicationName);
			GenericLibrary.input(LeadPageProp.LeadEmail,TestCaseID,"LeadEmail",ApplicationName);
			GenericLibrary.input(LeadPageProp.LeadMobile,TestCaseID,"LeadMobile",ApplicationName);
			GenericLibrary.input(LeadPageProp.LeadAddress,TestCaseID,"LeadAddress",ApplicationName);
			GenericLibrary.input(LeadPageProp.LeadCity,TestCaseID,"LeadCity",ApplicationName);
			GenericLibrary.input(LeadPageProp.LeadStateProvince,TestCaseID,"LeadStateProvince",ApplicationName);
			GenericLibrary.input(LeadPageProp.LeadZipPostalCode,TestCaseID,"LeadZipPostalCode",ApplicationName);
			GenericLibrary.input(LeadPageProp.LeadCountry,TestCaseID,"LeadCountry",ApplicationName);
			GenericLibrary.click(LeadPageProp.LeadSaveButton);
			test.log(Status.PASS, "Lead Creation Completed with Role--> " + Role);
		}catch(Exception formfill) {
			test.log(Status.FAIL,formfill);
			GenericLibrary.closeBrowser();
		}
		
	}

}
