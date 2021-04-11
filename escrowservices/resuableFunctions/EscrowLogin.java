package resuableFunctions;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.BaskGeneralPageProperties;
import pageProperties.BaskbankOTPpageProperties;
import reusableFunctions.MoodysApplicationSpecific;

public class EscrowLogin extends UIDriver {
	
	public EscrowLogin(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
	public static void LoginAddVantage(String URL,String ApplicationName,String browserType) throws Exception
	{
		GenericLibrary.openBrowser(browserType);			
		GenericLibrary.navigate(URL);
     	GenericLibrary.maximizeWindow();
		
		try {

			GenericLibrary.getDriver().findElement(By.id("userID")).sendKeys("txphmohammed");
			GenericLibrary.getDriver().findElement(By.id("UserPWD")).sendKeys("Feb@2020");
			GenericLibrary.click("btnOK");
			
			Thread.sleep(5000);

		} catch (Exception login) {
			System.out.println("exception is " + login.getMessage());
		}

		
		
//		
//		driver.findElement(By.id("userID")).sendKeys(userName);
//		driver.findElement(By.id("UserPWD")).sendKeys(password);
//		driver.findElement(By.id("btnOK")).click();
//		
//		//Selecting the environment
//		Thread.sleep(5000);
//		Assert.assertTrue(driver.findElement(By.className("titleClass")).getText().equals("Application Area"));
//		String xpathEnvironment = "//input[@value='"+environment+"']";
//		driver.findElement(By.xpath(xpathEnvironment)).click();
//		driver.findElement(By.id("OK")).click();
//		
//		//Validating the default page after login
//		Thread.sleep(8000);
//		driver.switchTo().frame("frmMain");
//		//System.out.println(driver.findElement(By.id("reporttitle")).getText().toString());
//		//System.out.println(driver.findElement(By.xpath("//label[text()='Advisor Dashboard']")).getText().toString());
//		Assert.assertTrue(driver.findElement(By.id("reporttitle")).getText().equals("Advisor Dashboard"));
//		System.out.println("Login Successful");
	}

}
