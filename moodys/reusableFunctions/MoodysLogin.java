package reusableFunctions;

import org.openqa.selenium.By;
import com.aventstack.extentreports.ExtentTest;

import common.GenericLibrary;
import core.ui.UIDriver;

public class MoodysLogin extends UIDriver {

	public MoodysLogin(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}

	public static void login(String URL,String ApplicationName,String browserType) throws Exception {
		
		GenericLibrary.openBrowser(browserType);			
		GenericLibrary.navigate(URL);
     	GenericLibrary.maximizeWindow();
		
		
		try {

			GenericLibrary.getDriver().findElement(By.id("txtUserName")).sendKeys("mosass");
			GenericLibrary.getDriver().findElement(By.id("txtPassword")).sendKeys("2020@Test");
			GenericLibrary.click("id_btnLogin");
			MoodysApplicationSpecific.popupHandler("//button[contains(text(),'Yes')]");
			Thread.sleep(5000);

		} catch (Exception login) {
			System.out.println("exception is " + login.getMessage());
		}
	
	}
	
}
