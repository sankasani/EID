package tests;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import common.Constants;
import pageProperties.Q2GeneralPageProp;

public class SikuliTestQ2 {
	
	
	public static void login() throws IOException, InterruptedException, FindFailed {

		Process process = Runtime.getRuntime().exec("taskkill /F /IM Winium.Desktop.Driver.exe");
		process.waitFor();
		process.destroy();	
		
		DesktopOptions option = new DesktopOptions();
		option.setApplicationPath(Q2GeneralPageProp.Q2App);
		
		WiniumDriverService service = new WiniumDriverService.Builder().usingDriverExecutable(new
				File(Q2GeneralPageProp.winiumDriverPath)).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
				service.start(); // Build and Start a Winium Driver service
				Thread.sleep(3000);
				WiniumDriver driver = new WiniumDriver(service, option); // Start a winium driver
		
	
		Thread.sleep(1000);	
        Screen Q2Screen = new Screen();
        Pattern loginField = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.LoginField);
        Q2Screen.click(loginField);
        Q2Screen.type(Q2GeneralPageProp.Login); 
     //   Q2Screen.find(Q2GeneralPageProp.filepath + Q2GeneralPageProp.LoginField).type("-hamoha");
        Pattern pwdField = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.PasswordField);
        Q2Screen.click(pwdField);
        Q2Screen.type(Q2GeneralPageProp.Password);        
        Q2Screen.click(Q2GeneralPageProp.filepath +Q2GeneralPageProp.LoginButton);   
       
     //   process.waitFor();
     //   process.destroy();
	}
	
	public static void createCustomer() throws FindFailed, InterruptedException {
		
        Screen Q2Screen = new Screen();
        Pattern Reporting = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.ReportingLink);
        Q2Screen.wait(Reporting, 3600);
        Thread.sleep(2000);
		Q2Screen.click(Reporting);

        Pattern CustomerLink = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.CustomerLink);
        Q2Screen.wait(CustomerLink, 3600);
        Q2Screen.doubleClick(CustomerLink);	

        Pattern CustomerPage = new Pattern(Q2GeneralPageProp.filepath + Q2GeneralPageProp.CustomerPage);
        Q2Screen.wait(CustomerPage, 3600);
        Q2Screen.rightClick(CustomerPage); 
	}

	public static void microsoftEdge() {
		System.setProperty("webdriver.edge.driver", "C:\\EnterpriseInitiativeAutomation\\drivers\\MicrosoftWebDriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://www.texascapitalbank.com");
	}
	
	public static void main(String[] args) throws FindFailed, InterruptedException, IOException {
		
		
			/*login();
			createCustomer();
			Thread.sleep(3000);
			Process process = Runtime.getRuntime().exec("taskkill /F /IM Winium.Desktop.Driver.exe");
			process.waitFor();
			process.destroy();	*/
		
		//microsoftEdge();
	/*	File filename = null;

		File file = new File("\\\\opscifs1\\group\\$ Enterprise Initiative Delivery\\Private\\QA\\EnterpriseInitiativeAutomation\\Users\\rasapp\\reports\\");
		//File file = new File(Constants.Results);

		 FileFilter fileFilter = new WildcardFileFilter("*." + "html");
		    File[] files = file.listFiles(fileFilter);

		    if (files.length > 0) {
		        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		        filename = files[0];
		    }
		
		
        System.out.println("1. Absolute file path :"+file.getAbsolutePath());

        //Convert local path to URL
        URL url = filename.toURI().toURL();
        System.out.println("2. URL of given file is:"+url);

        //Convert local path to URI
        URI uri = file.toURI();
        System.out.println("3. URI of given file is:"+uri);*/
		
		
		InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        System.out.println("Host Name:- " + inetAddress.getHostName());
	      
		}
		

}
