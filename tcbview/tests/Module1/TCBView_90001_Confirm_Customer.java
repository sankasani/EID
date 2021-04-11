package tests.Module1;

import org.openqa.selenium.By;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.tcbViewGeneralPageProperties;

public class TCBView_90001_Confirm_Customer extends UIDriver {

	public TCBView_90001_Confirm_Customer(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}

	public static void tcbView_ConfirmCustomer(String TestCaseID,String randomNumber,String ApplicationName,String browserType) throws Exception {
		
		
		GenericLibrary.openBrowser(browserType);			
		GenericLibrary.navigate(tcbViewGeneralPageProperties.TCBViewURL);
		GenericLibrary.maximizeWindow();

		//WebDriverWait wait = new WebDriverWait(GenericLibrary.getDriver(), 50);
		// WebElement element;
		// test.assignCategory(Constants.CloudApp1);
		try {
			
			GenericLibrary.click("//*[text()='Impersonate']");

		//	GenericLibrary.getDriver().findElement(By.xpath("//*[text()='Impersonate']")).click();
			GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"ctl00_ContentBody_RadComboBoxEmployees_Input\"]"))
					.sendKeys("Irma Marquez");
			
			Thread.sleep(4000);
			//GenericLibrary.waitUntilElementIsPresent("//*[@id=\"ContentBody_ButtonImpersonate\"]");
			GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"ContentBody_ButtonImpersonate\"]")).click();
			Thread.sleep(4000);

			//GenericLibrary.waitUntilElementIsPresent("//*[text()='Tasks']");
			GenericLibrary.getDriver().findElement(By.xpath("//*[text()='Tasks']")).click();
			Thread.sleep(2000);
			GenericLibrary.getDriver().findElement(By.xpath("//*[text()='Deposit Documentation']")).click();
			
			Thread.sleep(3000);
			GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"ctl00_ContentBody_RadGridProcessSummary_ctl00_ctl04_HyperLinkProcess\"]")).click();

			/*GenericLibrary.getDriver().findElement(
					By.xpath("//*[@id=\"ctl00_ContentBody_RadGridProcessSummary_ctl00_ctl16_HyperLinkProcess\"]"))
					.click();

			int custrows = GenericLibrary.getDriver()
					.findElements(
							By.xpath("//*[@id=\"ctl00_ContentBody_RadGridLoanProcessPastMature_ctl00\"]/tbody/tr"))
					.size();
			System.out.println("No of rows are : " + custrows);

			int custcols = GenericLibrary.getDriver()
					.findElements(
							By.xpath("//*[@id=\"ctl00_ContentBody_RadGridLoanProcessPastMature_ctl00\"]/tbody/tr[1]/*"))
					.size();
			System.out.println("No of Columns are: " + custcols);

			if (custrows > 0)
				GenericLibrary.getDriver().findElement(
						By.xpath("//*[@id=\"ctl00_ContentBody_RadGridLoanProcessPastMature_ctl00__0\"]/td[1]/a"))
						.click();
			String custname = GenericLibrary.getDriver()
					.findElement(By.xpath("//*[@id=\"ContentBody_PanelLoanProcess\"]/table[2]/tbody/tr[2]/td[2]"))
					.getText();
			System.out.println(custname);

			String cifnum = GenericLibrary.getDriver()
					.findElement(By.xpath("//*[@id=\"ContentBody_PanelLoanProcess\"]/table[2]/tbody/tr[1]/td[2]"))
					.getText();
			System.out.println(cifnum);

			GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"RepeaterBreadcrumb_HyperLinkBreadcrumb_2\"]")).click();

			String custnamevalidation = GenericLibrary.getDriver()
					.findElement(
							By.xpath("//*[@id=\"ctl00_ContentBody_RadGridLoanProcessPastMature_ctl00__0\"]/td[1]/a"))
					.getText();

			if (custnamevalidation.contains(custname)) {
				System.out.println("Custname validation sucess");
				test.log(Status.PASS, "Custname validation sucess");
				GenericLibrary.getScreenshot("Custname validation");

			}				
			else {
				System.out.println("Custname validation Failed");
				test.log(Status.FAIL, "Custname validation Failed");
				GenericLibrary.getScreenshot("Custname validation");

			}

			String cifnumvalidation = GenericLibrary.getDriver()
					.findElement(
							By.xpath("//*[@id=\"ctl00_ContentBody_RadGridLoanProcessPastMature_ctl00__0\"]/td[1]/a"))
					.getText();
			if (cifnumvalidation.contains(cifnum)) {
				System.out.println("cifnumvalidation validation sucess");
				test.log(Status.PASS, "cifnumvalidation validation sucess");
				GenericLibrary.getScreenshot("cifnumvalidation validation");

			}else {
				System.out.println("cifnumvalidation validation failed");
				test.log(Status.FAIL, "cifnumvalidation validation Failed");
				GenericLibrary.getScreenshot("cifnumvalidation validation");


			}*/
			Thread.sleep(3000);
			
			GenericLibrary.getDriver().findElement(By.xpath("//*[text()='Impersonate']")).click();
			GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"ctl00_ContentBody_RadComboBoxEmployees_Input\"]"))
					.sendKeys("Jitendra Kunisetty");
			Thread.sleep(4000);
			GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"ContentBody_ButtonImpersonate\"]")).click();
			test.log(Status.PASS, "Deposit document valition successful");

			GenericLibrary.closeBrowser();


		} catch (Exception msg) {
			System.out.println("exception is " + msg.getMessage());
			test.log(Status.FAIL, "Issue with Web Portal");
			GenericLibrary.getScreenshot("Issue");
			GenericLibrary.closeBrowser();

		}

	}

}