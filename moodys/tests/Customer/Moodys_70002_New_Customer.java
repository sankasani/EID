package tests.Customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.MoodysGeneralPageProperties;
import reusableFunctions.MoodysApplicationSpecific;
import reusableFunctions.MoodysLogin;

public class Moodys_70002_New_Customer extends UIDriver {

	public Moodys_70002_New_Customer(ExtentTest test) {
		super(test);
	}
	
	
	public static void newCustomer(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		
		try {
			MoodysLogin.login(MoodysGeneralPageProperties.MoodysURL, ApplicationName, browserType);
			
			GenericLibrary.getDriver().switchTo().frame("main");
			GenericLibrary.getDriver().findElement(By.xpath("//a[text()='New Customer']")).click();
			GenericLibrary.getDriver().findElement(By.name("txtLongName")).sendKeys("Test Moodys " + randomNumber);
			GenericLibrary.getDriver().findElement(By.id("txtShortName")).sendKeys("Testmoodys");
			GenericLibrary.getDriver().findElement(By.id("txtGroup")).sendKeys("Test");
			WebElement Financial_Template = GenericLibrary.getDriver().findElement(By.id("lstModels"));
			Select Financial_Template_value = new Select(Financial_Template);
			Financial_Template_value.selectByIndex(1);
			WebElement Access_Group = GenericLibrary.getDriver().findElement(By.id("lstAccessGroups"));
			Select Access_Group_value = new Select(Access_Group);
			Access_Group_value.selectByValue("1");
			WebElement Firm_Type = GenericLibrary.getDriver().findElement(By.id("lstFirmType"));
			
			Select Firm_Type_value = new Select(Firm_Type);
			Firm_Type_value.selectByValue("PUB");
			GenericLibrary.getDriver().findElement(By.id("btnOK")).click();
		    GenericLibrary.getScreenshot("NewCustomer");
			GenericLibrary.getDriver().findElement(By.xpath("//a[text()='Save']")).click();
	    
			Thread.sleep(1000);
			GenericLibrary.getDriver().findElement(By.id("PageViewCustomer_IndustryClass")).sendKeys("Food");
	        GenericLibrary.getDriver().findElement(By.id("PageViewCustomer_IndustryCode")).sendKeys("1" + randomNumber);
	        GenericLibrary.getDriver().findElement(By.id("btnOK")).click();
	        GenericLibrary.getDriver().findElement(By.xpath("//a[text()='Close']")).click();
	    	Thread.sleep(1000);
	        GenericLibrary.getDriver().findElement(By.xpath("//button[@type = 'button']")).click();
	        
	        test.log(Status.PASS, "New Customer Created");
	        
	        MoodysApplicationSpecific.Logout();
	        
		}catch(Exception newCustomer) {
			System.out.println(newCustomer);
			 test.log(Status.FAIL, "New Customer Not Created");
		        GenericLibrary.getScreenshot("NewCustomerFail");

		}
		
		GenericLibrary.closeBrowser();
	}


}
