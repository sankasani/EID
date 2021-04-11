package reporting;

import common.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.lang.reflect.InvocationTargetException;


public class ExtentManager {

	public static ExtentReports Extent = null;
	
	public ExtentManager()
	{
		
	}
	
	public static ExtentReports GetInstance(String randomNumber) throws InvocationTargetException {
		Extent = null;
		
		if (Extent == null) {
			String filename= "TCB_EID_TestReport_"+randomNumber+".html";
			 ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Constants.Results+filename);
			 Extent = new ExtentReports();
		     Extent.attachReporter(htmlReporter);
		     
		     	htmlReporter.config().setChartVisibilityOnOpen(false);
		     	htmlReporter.config().setDocumentTitle("TCB.EID.TestReport");
		     	htmlReporter.config().setEncoding("utf-8");
		     	//htmlReporter.config().setReportName("TCB.EMS.TestReport");
		     	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);	
		     	//htmlReporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss a");
		        htmlReporter.config().setTheme(Theme.STANDARD);		          
		     //   htmlReporter.config().setCSS("css-string");
		     //   htmlReporter.config().setJS("js-string");
		        
		        Extent.setSystemInfo("Windows", "10");
		        Extent.setSystemInfo("Environment", "Test");
		        Extent.setSystemInfo("Project", "TCB_EMS");
		}
		
		return Extent;
	}
	
	protected static ExtentReports getInstance() throws Exception {
		throw new Exception(null, null);
	}	
	
}
