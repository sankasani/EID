package reusableFunctions;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;

public class SitefinityApplicationSpecific extends UIDriver   {

	public SitefinityApplicationSpecific(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}

	public static String GetLinkText(String tabObj1, String linkObj2, String subLinkObj) throws Exception {
		
		GenericLibrary.click(tabObj1);
	//	GenericLibrary.getDriver().findElement(By.xpath(tabObj1)).click();
		if (subLinkObj.equals("")) {
			//String results = GenericLibrary.getDriver().findElement(By.xpath(linkObj2)).getText();
			String results = GenericLibrary.getText(linkObj2);
			
			return results;
		} else {
			GenericLibrary.getDriver().findElement(By.xpath(linkObj2)).click();
			//String results = GenericLibrary.getDriver().findElement(By.xpath(subLinkObj)).getText();
			String results = GenericLibrary.getText(subLinkObj); 

			return results;
		}

	}

	public static void validation(String Expected, String Actual, String TestCaseID) throws Exception {
		if (Expected.equals(Actual)) {
			System.out.println("Pass-->" + Actual);
			test.log(Status.PASS, "Validation Successful, Actual Text on screen is -->" + Actual);
			GenericLibrary.getScreenshot(TestCaseID);

		} else {
			System.out.println("Fail-Actual Text-->" + Actual);
			test.log(Status.PASS, "Validation Unsuccessful, Actual Text on screen is -->" + Actual);
			GenericLibrary.getScreenshot(TestCaseID);

		}
	}

}
