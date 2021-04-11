package reusableFunctions;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import core.ui.UIDriver;
import pageProperties.Q2GeneralPageProp;

public class Q2Login extends UIDriver {

	public Q2Login(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}

	public static void login(String ApplicationName) throws Exception {
		Process process = Runtime.getRuntime().exec("taskkill /F /IM Winium.Desktop.Driver.exe");
		try {
			//Process process = Runtime.getRuntime().exec("taskkill /F /IM Winium.Desktop.Driver.exe");
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
	        test.log(Status.PASS, "Logged in Successful to Q2 Central");
		}catch(Exception e) {
			test.log(Status.FAIL, "Login Fail --> "+ e);
			process.waitFor();
			process.destroy();
		}
	}
	

}
