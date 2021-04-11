package core.api;

import java.util.List;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestSuite;

public class APIDriver {

	WsdlProject project = null;
	
	public APIDriver(String projectXmlPath) {
		try {
			project = new WsdlProject(projectXmlPath);
		//	project.getAuthRepository();
		//	System.out.println(project.getAuthRepository());
			//((Object) project).getAuthProfile();
			
			//System.out.println(((Object) project).getAuthProfile());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	public List<TestSuite> getAllTestSuites() {
		return project.getTestSuiteList();
	}
	
	public List<? extends TestCase> getAllTestCases(String testSuiteName) {
		TestSuite testSuite = project.getTestSuiteByName(testSuiteName);
		
		return testSuite.getTestCaseList();
	}
	
	
	public void close() {
		project.release();
	}

}
