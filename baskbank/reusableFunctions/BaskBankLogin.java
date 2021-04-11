package reusableFunctions;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.BaskGeneralPageProperties;
import pageProperties.BaskbankOTPpageProperties;

public class BaskBankLogin extends UIDriver {

	public BaskBankLogin(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
	
public static void login(String ApplicationName,String browserType) throws Exception {
		
		if((UIDriver.cloudStatus).equals(true) || (UIDriver.cloudStatus).equals("NO")) {
			
			try {
				//ChromeUserProfile
		        Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");

				if(browserType.equals("Chrome")) {
					browserType = browserType+"UserProfile";
				}			
				GenericLibrary.openBrowser(browserType);
				GenericLibrary.navigate(BaskGeneralPageProperties.BaskBankURL);
				GenericLibrary.driver.navigate().refresh();
				GenericLibrary.maximizeWindow();
				GenericLibrary.input(BaskGeneralPageProperties.UserName,"Login","UserName",ApplicationName);
				GenericLibrary.input(BaskGeneralPageProperties.Password, "Login","Password",ApplicationName);
				GenericLibrary.click(BaskGeneralPageProperties.LoginButton);
				
				/*if(GenericLibrary.getElementCount(BaskGeneralPageProperties.SignOut).size()>0) {
					GenericLibrary.click(BaskGeneralPageProperties.SignOut);
					GenericLibrary.navigate(BaskGeneralPageProperties.BaskBankURL);
					GenericLibrary.driver.navigate().refresh();
					GenericLibrary.input(BaskGeneralPageProperties.UserName,"Login","UserName",ApplicationName);
					GenericLibrary.input(BaskGeneralPageProperties.Password, "Login","Password",ApplicationName);
					GenericLibrary.click(BaskGeneralPageProperties.LoginButton);


				}
*/				Thread.sleep(3000);
				if(GenericLibrary.getDriver().findElements(By.xpath(BaskbankOTPpageProperties.OTPScreen)).size()>0) {
						
						GenericLibrary.getDriver().findElement(By.xpath(BaskbankOTPpageProperties.PhoneNumber)).click();
						String OTP = JOptionPane.showInputDialog("Enter OTP");
						int i = Integer.parseInt(OTP);
						GenericLibrary.getDriver().findElement(By.xpath(BaskbankOTPpageProperties.PhoneNumberEditField)).sendKeys(""+i+"");
						GenericLibrary.click(BaskbankOTPpageProperties.EnterOTPButton);
						
						Thread.sleep(2000);
						if(GenericLibrary.getElementCount(BaskbankOTPpageProperties.RegisterDeviceScreen).size()>0) {
							GenericLibrary.click(BaskbankOTPpageProperties.RegisterDeviceButton);
						}
						
					/*	if(GenericLibrary.getDriver().findElements(By.xpath(BaskbankOTPpageProperties.RegisterDeviceScreen)).size()>0) {
							GenericLibrary.getDriver().findElement(By.xpath(BaskbankOTPpageProperties.RegisterDeviceButton)).click();
						}*/
				}
				
				
				test.log(Status.INFO, BaskGeneralPageProperties.BaskBankUsername +" --> logged in successfully");
				
			}catch(Exception login) {
				test.log(Status.FAIL,  login);
			}
			
		}else {
			test.log(Status.FAIL,  "Server Unavailable");
		}	

	}
	
}
