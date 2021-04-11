package reusableFunctions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;

public class ApplicationSpecific extends UIDriver{
	
	public ApplicationSpecific(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}


	
	public static void closeAllOpenTabs() throws Exception {
		
		//Thread.sleep(8000);
		
		try {
			//List<WebElement> count=GenericLibrary.getDriver().findElements(By.xpath("//ul[@class='tabBarItems slds-grid']/li[contains(@class,'tabItem')]"));
			GenericLibrary.getElement("//ul[@class='tabBarItems slds-grid']/li[contains(@class,'tabItem')]");
			List<WebElement> count= GenericLibrary.getElementCount("//ul[@class='tabBarItems slds-grid']/li[contains(@class,'tabItem')]");
			//List<WebElement> count=GenericLibrary.getElementCount("//ul[@class='tabBarItems slds-grid']/li[contains(@class,'tabItem')]");

			System.out.println(count.size());
	        List<String> namesTab = new ArrayList<>();

		for (int i=2;i<count.size()+2;i++) {
			String taboptions= "//ul[@class='tabBarItems slds-grid']/li["+i+"][contains(@class,'tabItem')]/a/span[2]";				
			System.out.println(GenericLibrary.getDriver().findElement(By.xpath(taboptions)).getText());
			//String TabName = GenericLibrary.getDriver().findElement(By.xpath(taboptions)).getText();
			String TabName = GenericLibrary.getText(taboptions);
			namesTab.add(TabName);

		}
		
		for (int j=0;j<namesTab.size();j++) {
			String TabClose ="//a[contains(@title, '"+namesTab.get(j)+"')]/../div[2]/button/lightning-primitive-icon";
		//	GenericLibrary.getDriver().findElement(By.xpath(TabClose)).click();
			GenericLibrary.click(TabClose);

		}
		}catch(Exception e) {
			test.log(Status.FAIL, e);
			GenericLibrary.closeBrowser();
		}
		
		//a[contains(@class,'tabHeader slds-context-bar__label-action')]//span[contains(@class,'title slds-truncate')][contains(text(),'New Lead: Standard')]		
	}
	
	
	public static void customInput(String objProperty, String TestCaseID,String tagName,String randomNumber,String ApplicationName) throws Exception {
		try {
			String InputValue;
			if(tagName.equals("")) {
				 InputValue = GenericLibrary.propLoad(TestCaseID);
			}else {
				 InputValue= GenericLibrary.testDataFromXml(TestCaseID,tagName,ApplicationName);
				 InputValue=InputValue +"_"+randomNumber;

			}			
			if (GenericLibrary.isElementPresent(objProperty) == true) {
				WebElement element = GenericLibrary.getElement(objProperty);
				element.clear();
				element.sendKeys(InputValue);
			}
		}

		catch (Exception inputField) {
			test.log(Status.FAIL, objProperty);

		}
	}
	
	public static boolean verifyRelationshipName(String objProperty,String testCaseID, String tagName,String randomNumber,String applicationName) {
		
		String expectedRelationshipName = GenericLibrary.testDataFromXml(testCaseID,tagName,applicationName);
		expectedRelationshipName = expectedRelationshipName +"_"+randomNumber;
		test.log(Status.INFO, "expectedRelationshipName-->" + expectedRelationshipName);

		String verifyResults = null;
		try {
			List<String> actualRelationshipName = new ArrayList<String>();

			actualRelationshipName = GenericLibrary.getElementsText(objProperty);
			
			for (int i=0;i<actualRelationshipName.size();i++) {
				
				if(expectedRelationshipName.equals(actualRelationshipName.get(i))){
					test.log(Status.INFO, "actualRelationshipName-->" + actualRelationshipName.get(i));

					verifyResults = "Pass";
					}			
			}
			
			if (verifyResults.equals("Pass")) {
				return true;

			} else {
				return false;

			}
			
		}catch(Exception verifyRelationshipName) {
			return false;
		}
		
		
	}
	
	

}
