package core.api;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.List;

import util.XLUtil;
import util.Xls_Reader;
//import com.api.validations.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.report.JUnitReport;

import common.Constants;
import core.ui.UIDriver;
import reporting.ExtentManager;

public class ProjectTestRunner {
	
	public static ExtentTest test = null;

	public ProjectTestRunner(ExtentTest test) {
		ProjectTestRunner.test = test;
	}

	//public static void main(String[] args) throws IOException, InvocationTargetException
	public static void webServices(ExtentReports rep) throws IOException, InvocationTargetException

	{

		APIDriver soapApp = new APIDriver(Constants.PROJECT_XML_PATH);

	//	String path = System.getProperty("user.dir")+ "//src//com//api//data//Test Data.xlsx";
		String path = Constants.PROJECT_PATH+"\\TestData//Test Data.xlsx";
		Xls_Reader xls = new Xls_Reader(path);
		String resultFileFolder= Constants.PROJECT_PATH +"\\xlreports\\";
		List<TestSuite> allTestSuites= soapApp.getAllTestSuites();
		
		JUnitReport report = null;		
		String testSuiteName = null;
		String testCaseName = null;
		List<? extends TestCase> allTestCases = null;
		TestCaseRunner tcRunner = null;
		
		
		//prepare the result sheet
		
		XLUtil.prepareResultSheet(Constants.PROJECT_DATA_PATH, resultFileFolder);
		//ExtentReports rep = null;
		//rep = ExtentManager.GetInstance("API");	
		
		
		double startTime=0;
		double endTime=0;
		String result = null;
		for(int tsid=0; tsid<allTestSuites.size(); tsid++){  
			testSuiteName = allTestSuites.get(tsid).getName();
			report = new JUnitReport();
			report.setTestSuiteName(testSuiteName);
		    allTestCases= soapApp.getAllTestCases(testSuiteName);
		    
		    
		    System.out.println("******************" +testSuiteName + "**********************");
		    
		    for(int tcid = 0; tcid <allTestCases.size();tcid++) {  // For testCase
		    	testCaseName = allTestCases.get(tcid).getName();
		    	
			    List<PropertiesMap> testDataSets = 	XLUtil.getTestData(testSuiteName, testCaseName, xls);

			    
		    	if(XLUtil.isExecutable(testCaseName, xls, "TestCases")) {
		    	for(int tRepeat=0; tRepeat<testDataSets.size(); tRepeat++) {
		    		//System.out.println(testCaseName);
		    		test = rep.createTest(testCaseName+"-Iteration-"+tRepeat);   // report creation
			    	test.assignCategory(testSuiteName);
		    		if(testDataSets.get(tRepeat).get("Execute").equals("Y")) {
		    			//startTime= getCurrentTime();
			    		test.log(Status.INFO,"Test Execution Started for the Test ---> " +  testDataSets.get(tRepeat).get("TestCaseName").toString());

		    			tcRunner = (TestCaseRunner) allTestCases.get(tcid).run(testDataSets.get(tRepeat), false);
				    	System.out.println(testCaseName+ "------"+tcRunner.getStatus() + "----" + allTestCases.get(tcid).getPropertyValue("result"));
				    	testDataSets.get(tRepeat).put("Result", allTestCases.get(tcid).getPropertyValue("result"));
				    	endTime = getCurrentTime();
				    	
				    	result = allTestCases.get(tcid).getPropertyValue("result");
				    	
			    		test.log(Status.INFO,"Test Execution Completed for the Test ---> " +  testDataSets.get(tRepeat).get("TestCaseName").toString());

				    	if(result.contains("PASS")) {
				    		//report.addTestCase(testCaseName+ "- (Iteration -" +(tRepeat+1)+")", (endTime-startTime)/1000, null);
				    		report.addTestCase(testCaseName+ "- (Iteration -" +(tRepeat+1)+")", (endTime-startTime)/1000, null);
				    		
				    		test.log(Status.PASS, result);
				    	}else {
				    		report.addTestCaseWithFailure(testCaseName+ "- (Iteration -" +(tRepeat+1)+")", (endTime-startTime)/1000, result, null, null);
				    		test.log(Status.FAIL, result);
				    	}
		    		} else {
		    			System.out.println(testCaseName + "---- Skipped Test Execution");
		    			testDataSets.get(tRepeat).put("Result", "Skipped Test Execution");
		    			report.addTestCase(testCaseName+ "- (Iteration -" +(tRepeat+1)+")", 0, null);
		    			test.log(Status.SKIP, result);
		    		
		    		}
		    		
			    
		    	}
		    }	else { // Skip the Test Case
		    	//int tRepeat = 0;
				//testDataSets.get(tRepeat).put("Result", "Skipped Test Execution");
		    	test = rep.createTest(testCaseName);   // report creation
		    	test.assignCategory(testSuiteName);
		    	test.log(Status.SKIP, "Test Execution skipped in Test Strategy document");
		    }
		    	//testDatasets updated the result of each row of test case
		    	XLUtil.generateReport(testSuiteName,allTestCases.get(tcid).getName(),testDataSets);
		    	
		    }
		    report.save(new File("C:\\Project-Test\\TcbApiStrategyAutomation\\TEST-"+testSuiteName+"-Results.xml"));
		    report.finishReport();
		    
		  //  rep.flush();
		}
		//XLUtil.reportxls.close();
		soapApp.close();
		//System.exit(0);
		//rep = null;
		test = null;
	}
	
	public static long getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTimeInMillis();
	}

}
