package tests.Customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.MoodysGeneralPageProperties;
import reusableFunctions.MoodysApplicationSpecific;
import reusableFunctions.MoodysLogin;

public class Moodys_70001_Delete_Customer extends UIDriver {

	public Moodys_70001_Delete_Customer(ExtentTest test) {
		super(test);
	}

	
public static void deleteCustomer(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		
		try {
			MoodysLogin.login(MoodysGeneralPageProperties.MoodysURL, ApplicationName, browserType);
			
			GenericLibrary.getDriver().switchTo().frame("main");
			//GenericLibrary.getDriver().findElement(By.xpath("//a[text()='Delete Customer']")).click();
			GenericLibrary.click(MoodysGeneralPageProperties.MoodyClick);
			GenericLibrary.getDriver().findElement(By.id("btnSearch")).click();
			//driver.findElement(By.xpath("//a[text()='']")).click();
			WebElement Firm_Type = GenericLibrary.getDriver().findElement(By.xpath("//tbody[1]/tr[1]/td[1]/select[1]/option[1]"));
			Firm_Type.click();
			GenericLibrary.getDriver().findElement(By.id("btnOK")).click();
			test.log(Status.PASS, "DeleteCustomer Successful");
	        GenericLibrary.getScreenshot("DeleteCustomer");
	        MoodysApplicationSpecific.Logout();
		}catch(Exception newCustomer) {
			System.out.println(newCustomer);
			test.log(Status.FAIL, "DeleteCustomer Fail");
	        GenericLibrary.getScreenshot("DeleteCustomerFail");

		}
		
		GenericLibrary.closeBrowser();
	}
	
}
