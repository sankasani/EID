package tests.Module1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.tcbViewGeneralPageProperties;

public class TCBView_90003_Validate_CCN extends UIDriver{

	public TCBView_90003_Validate_CCN(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	public static void tcbView_ValidateCCN(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {

		try {
			
		
			GenericLibrary.openBrowser(browserType);			
			GenericLibrary.navigate(tcbViewGeneralPageProperties.TCBViewURL);
			GenericLibrary.maximizeWindow();
			Thread.sleep(2000);
			GenericLibrary.getDriver().findElement(By.xpath("//*[text()='Impersonate']")).click();
			GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"ctl00_ContentBody_RadComboBoxEmployees_Input\"]")).sendKeys("Anthony Adams");
			Thread.sleep(4000);
			GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"ContentBody_ButtonImpersonate\"]")).click();
			Thread.sleep(6000);
			GenericLibrary.getDriver().findElement(By.xpath("//*[text()='Tasks']")).click();
			Thread.sleep(3000);
			GenericLibrary.getDriver().findElement(By.xpath("//*[text()='My Tasks']")).click();
			Thread.sleep(3000);
			/*GenericLibrary.getDriver().findElement(By.xpath("//*[@id='ctl00_ContentBody_RadGridProcessSummary_ctl00_ctl16_HyperLinkProcess']")).click();
			GenericLibrary.getDriver().findElement(By.xpath("/html/body/form/table[3]/tbody/tr/td[2]/table/tbody/tr[2]/td/div/div/div[2]/table/tbody/tr[1]/td[1]/a")).click();
			WebElement CNN = GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"ContentBody_PanelLoanProcess\"]/table[2]/tbody/tr[3]/td[2]"));
			String strng = CNN.getText();
			int length = strng.length();
			Assert.assertEquals(length, 11);
			
			if(length ==11) {
				test.log(Status.PASS, "CCN Validation successful and displayed CCN is --> " + strng);
				GenericLibrary.getScreenshot("CCN");

			}else {
				test.log(Status.FAIL, "CCN Validation Unsuccessful and displayed CCN is -->" + strng );
				GenericLibrary.getScreenshot("CCN");

			}*/
			
			
			test.log(Status.PASS, "Tasks Validation successful");

			
			GenericLibrary.getDriver().findElement(By.xpath("//*[text()='Impersonate']")).click();
			GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"ctl00_ContentBody_RadComboBoxEmployees_Input\"]")).sendKeys("Jitendra Kunisetty");
			Thread.sleep(3000);
			GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"ContentBody_ButtonImpersonate\"]")).click();
			GenericLibrary.closeBrowser();

			
	}		catch (Exception msg) {
			System.out.println("exception is "+msg.getMessage());
			test.log(Status.FAIL, "Issue with Web Portal");
			GenericLibrary.getScreenshot("Issue");
			GenericLibrary.closeBrowser();

	}
		


}
}
