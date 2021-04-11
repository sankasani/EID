package core.ui;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.Constants;
import common.GenericLibrary;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UIDriver {
	
//	public static ExtentReports rep = null;
	public static ExtentTest test = null;
	public static int executedCountfromTestSuite =0;
	public static int executedCountfromTestCase =0;
	public static String executedTestCase ="";
	public static int completedExecution=0;
	
	public UIDriver(ExtentTest test) {
		UIDriver.test = test;
	}
	 public static Object cloudStatus= "NO";
     public static void Start(String nodeNameApplication, String appTestSuiteTestCase, String browserType,String testExecutionType,String randomNumber,ExtentReports rep) {	 		  

    try {
    	//rep=null;
		//String randomNumber = GenericLibrary.randomNumber();
		
		//	rep = ExtentManager.GetInstance(randomNumber);

		if(testExecutionType.equals("TestSuite")) {    	
			
			if(browserType.equals("Default Browser")) {
				
				File applicationXml = new File(Constants.ApplicationXMl_Path);
				DocumentBuilderFactory dbFactoryapp = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilderapp = dbFactoryapp.newDocumentBuilder();
				Document docapp = dBuilderapp.parse(applicationXml);
				docapp.getDocumentElement().normalize();
				

				for (int k = 1; k < docapp.getDocumentElement().getChildNodes().getLength(); k++) {
					if (k % 2 != 0) {
						// Test Case Numbers/ Names
						for (int l = 1; l < docapp.getDocumentElement().getChildNodes().item(k).getChildNodes()
								.getLength(); l++) {
							if (l % 2 != 0) {
								// Test Case Parameters
								String childNodeNametc = docapp.getDocumentElement().getChildNodes().item(k)
										.getChildNodes().item(l).getNodeName();
																
								if (childNodeNametc.toLowerCase().equals(nodeNameApplication.toLowerCase()+"url")) {
									String URL = docapp.getDocumentElement().getChildNodes().item(k)
											.getChildNodes().item(l).getTextContent();
									System.out.println(URL);
								}
								
								if (childNodeNametc.toLowerCase().equals(nodeNameApplication.toLowerCase()+"browser")) {
									String browser = docapp.getDocumentElement().getChildNodes().item(k)
											.getChildNodes().item(l).getTextContent();
									System.out.println(browser);
									browserType =browser;
								}

							}
						}
					}

				}

				
			}
			
			
			
					// Select Testcase folder---***************************************************************************************************
								
								File folder = new File(Constants.TESTCASEDIR_PATH(nodeNameApplication.toLowerCase()));
								File[] listOfFiles = folder.listFiles();
								String testCaseFileName;
								for (int fileCount = 0; fileCount < listOfFiles.length; fileCount++) {			
									testCaseFileName = listOfFiles[fileCount].getName();
								    
								    if(testCaseFileName.contains("_TestSuite")) {
								    	testCaseFileName =testCaseFileName.substring(0, testCaseFileName.length()-14);
									    
									    if(testCaseFileName.equals(appTestSuiteTestCase)) {
					//------------------------------------------------------------From Test Case Sheet XML---------------------------------------------------------------------
										    System.out.println("Selected TestCase file for Execution -->" +testCaseFileName+ "_TestSuite.xml");

									    	File testCasesXml = new File(Constants.TESTCASESXML_PATH(nodeNameApplication.toLowerCase(),testCaseFileName));
									    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
									    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
									    	Document doc = dBuilder.parse(testCasesXml);			
									    	doc.getDocumentElement().normalize();										    	
									    	for (int i=1;i<doc.getDocumentElement().getChildNodes().getLength();i++) {
									            if (i % 2 != 0){
									            	// Test Case Numbers/ Names
									    		String nodeName = doc.getDocumentElement().getChildNodes().item(i).getNodeName();
									    		if(nodeName.equals("")) {
									    			
									    		}else {
									    			test = rep.createTest(nodeName);
													test.assignCategory(nodeNameApplication);
									    			for (int j=1;j<doc.getDocumentElement().getChildNodes().item(i).getChildNodes().getLength();j++) {
									    				 if (j % 2 != 0){
									    					 // Test Case Parameters
									    						String childNodeName = doc.getDocumentElement().getChildNodes().item(i).getChildNodes().item(j).getNodeName();
									    						if(childNodeName.equals("execute")) {
									    							String executeValue = doc.getDocumentElement().getChildNodes().item(i).getChildNodes().item(j).getTextContent();
									    							if(executeValue.equals("Yes")) {
									    								executedCountfromTestSuite = executedCountfromTestSuite+1;
									    								executedTestCase=nodeName;
									    								System.out.println("Test execution in Progress for ---> " +nodeName);
									    								test.log(Status.INFO, "Test execution started for testcase---> " +nodeName);
									    								
									    			//------------------------------------------------------------From TestData Sheet XML---------------------------------------------------------------------
									    								
									    								File testDataXml = new File(Constants.TESTDATAXML_PATH(nodeNameApplication));
									    								DocumentBuilderFactory dbFactorytc = DocumentBuilderFactory.newInstance();
									    								DocumentBuilder dBuildertc = dbFactorytc.newDocumentBuilder();
									    								Document doctc = dBuildertc.parse(testDataXml);			
									    								doctc.getDocumentElement().normalize();									    								
									    								for (int k=1;k<doctc.getDocumentElement().getChildNodes().getLength();k++) {
									    							        if (k % 2 != 0){
									    							        	// Test Case Numbers/ Names
									    									String nodeNametc = doctc.getDocumentElement().getChildNodes().item(k).getNodeName();
									    									if(nodeName.equals(nodeNametc)) {
									    										
									    										if(nodeNametc.equals("")) {
									    											
									    										}else {
									    											ArrayList<Object> newObj = new ArrayList<Object>(Arrays.asList());
									    											newObj.add(nodeNametc); // Default Parameter 1
									    											for (int l=1;l<doctc.getDocumentElement().getChildNodes().item(k).getChildNodes().getLength();l++) {
									    												 if (l % 2 != 0){
									    													 // Test Case Parameters
									    														String childNodeNametc = doctc.getDocumentElement().getChildNodes().item(k).getChildNodes().item(l).getNodeName();
									    														if(childNodeNametc.equals("Param")) {
									    															//System.out.println("Param Value :" + doctc.getDocumentElement().getChildNodes().item(k).getChildNodes().item(l).getTextContent());
									    															String parameters = doctc.getDocumentElement().getChildNodes().item(k).getChildNodes().item(l).getTextContent();														
									    													         newObj.add(parameters);													         
									    													         
									    														}
									    														
									    														
									    												 }
									    											}
									    											
									    											newObj.add(randomNumber);// Default Parameter 2
									    											newObj.add(nodeNameApplication);// Default Parameter 3
									    											newObj.add(browserType);// Default Parameter 4
									    											// Place holder for Test Execution
									    											
									    											String testCandidate = GenericLibrary.packageIdentifier(nodeNameApplication, nodeNametc);
									    											System.out.println("Test Candidate --->"+testCandidate);
									    								
									    											Class<?> classobj = Class.forName("" + testCandidate + "");
								    												Method[] methods = classobj.getDeclaredMethods();

								    												for (Method method : methods) {
								    													String MethodName = method.getName();
								    													if(nodeNametc.contains("ServerStatus")) {
								    														cloudStatus = method.invoke(MethodName,newObj.toArray());
								    													}else {
								    														// Method Parameters  (nodeNametc aka TestCaseName/ID,Param from TestData, randomNumber,nodeNameApplication)
								    														method.invoke(MethodName,newObj.toArray());
								    													}
								    													
								    												}
									    											

									    										}
									    										
									    									}else {
									    										
									    									}

									    							        }

									    								}
									    								
									    								
									    		

									    							}else {
									    								System.out.println("Skipped Test execution for ---> " +nodeName);
									    								test.skip("Skipped Test execution for ---> " +nodeName);
									    							}

									    						}
									    				 }
									    			}
									    			

									    		}
									            }

									    	}
									    	//------------------------------------------------------------End of  Test Case Sheet---------------------------------------------------------------------
									    	System.gc();

									    }

								    }
								}
    }else if(testExecutionType.equals("TestCase")) {

    	executedCountfromTestCase =executedCountfromTestCase+1;
    	executedTestCase=appTestSuiteTestCase;
    	String projectName = null;
    	String	packages = null;
    	String [] parts1 = 	appTestSuiteTestCase.split("_");
		System.out.println(parts1[0]);
		String projectName1= parts1[0];
		String clasPath1 = "\\\\OPSCIFS1\\group\\$ Enterprise Initiative Delivery\\Private\\QA\\EnterpriseInitiativeAutomation\\TCB.EID.Automation\\EnterpriseInitiativeDelivery.Automation\\"+projectName1.toLowerCase();
		
		String clasPath = "\\\\OPSCIFS1\\group\\$ Enterprise Initiative Delivery\\Private\\QA\\EnterpriseInitiativeAutomation\\TCB.EID.Automation\\EnterpriseInitiativeDelivery.Automation";

    //	try (Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir")))) {
    	try (Stream<Path> walk = Files.walk(Paths.get(clasPath1))) {
			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(appTestSuiteTestCase+".java")).collect(Collectors.toList());		
		//	packages=result.get(0).replace(System.getProperty("user.dir"),"");  
			packages=result.get(0).replace(clasPath,"");  

			System.out.println(packages); // \salesforce\tests\sf\leads\CreateLead.java
			String [] parts = 	packages.split("\\\\");
			projectName = parts[1];
			projectName = projectName.substring(0,1).toUpperCase() + projectName.substring(1).toLowerCase();
			packages=packages.replace("\\"+projectName.toLowerCase()+"\\", "");
			packages=packages.replace("\\","."); 
			packages = packages.replace(".java","");
			System.out.println(parts[1]);
			
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	test = rep.createTest(appTestSuiteTestCase);
		test.assignCategory(projectName);
		
		
		if(browserType.equals("Default Browser")) {
			
			File applicationXml = new File(Constants.ApplicationXMl_Path);
			DocumentBuilderFactory dbFactoryapp = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilderapp = dbFactoryapp.newDocumentBuilder();
			Document docapp = dBuilderapp.parse(applicationXml);
			docapp.getDocumentElement().normalize();
			

			for (int k = 1; k < docapp.getDocumentElement().getChildNodes().getLength(); k++) {
				if (k % 2 != 0) {
					// Test Case Numbers/ Names
					for (int l = 1; l < docapp.getDocumentElement().getChildNodes().item(k).getChildNodes()
							.getLength(); l++) {
						if (l % 2 != 0) {
							// Test Case Parameters
							String childNodeNametc = docapp.getDocumentElement().getChildNodes().item(k)
									.getChildNodes().item(l).getNodeName();
															
							if (childNodeNametc.toLowerCase().equals(projectName.toLowerCase()+"url")) {
								String URL = docapp.getDocumentElement().getChildNodes().item(k)
										.getChildNodes().item(l).getTextContent();
								System.out.println(URL);
							}
							
							if (childNodeNametc.toLowerCase().equals(projectName.toLowerCase()+"browser")) {
								String browser = docapp.getDocumentElement().getChildNodes().item(k)
										.getChildNodes().item(l).getTextContent();
								System.out.println(browser);
								browserType =browser;
							}

						}
					}
				}

			}

			
		}
		
		
		
		
    	

		File testDataXml = new File(Constants.TESTDATAXML_PATH(projectName));
		DocumentBuilderFactory dbFactorytc = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuildertc = dbFactorytc.newDocumentBuilder();
		Document doctc = dBuildertc.parse(testDataXml);			
		doctc.getDocumentElement().normalize();									    								
		for (int k=1;k<doctc.getDocumentElement().getChildNodes().getLength();k++) {
	        if (k % 2 != 0){
	        	// Test Case Numbers/ Names
			String nodeNametc = doctc.getDocumentElement().getChildNodes().item(k).getNodeName();
			if(appTestSuiteTestCase.equals(nodeNametc)) {
				
				if(nodeNametc.equals("")) {
					
				}else {
					ArrayList<Object> newObj = new ArrayList<Object>(Arrays.asList());
					newObj.add(nodeNametc); // Default Parameter 1
					for (int l=1;l<doctc.getDocumentElement().getChildNodes().item(k).getChildNodes().getLength();l++) {
						 if (l % 2 != 0){
							 // Test Case Parameters
								String childNodeNametc = doctc.getDocumentElement().getChildNodes().item(k).getChildNodes().item(l).getNodeName();
								if(childNodeNametc.equals("Param")) {
									//System.out.println("Param Value :" + doctc.getDocumentElement().getChildNodes().item(k).getChildNodes().item(l).getTextContent());
									String parameters = doctc.getDocumentElement().getChildNodes().item(k).getChildNodes().item(l).getTextContent();														
							         newObj.add(parameters);													         
							         
								}
								
								
						 }
					}
					
					newObj.add(randomNumber);// Default Parameter 2
					newObj.add(projectName);// Default Parameter 3
					newObj.add(browserType);// Default Parameter 4
					// Place holder for Test Execution
					

					Class<?> classobj = Class.forName(""+packages+"");
					Method[] methods = classobj.getDeclaredMethods();

					for (Method method : methods) {
						String MethodName = method.getName();
						if(nodeNametc.contains("ServerStatus")) {
							cloudStatus = method.invoke(MethodName,newObj.toArray());
						}else {
							// Method Parameters  (nodeNametc aka TestCaseName/ID,Param from TestData, randomNumber,nodeNameApplication)
							method.invoke(MethodName,newObj.toArray());
						}
						
					}
					

				}
				
			}else {
				
			}

	        }

		}
    	
    }
				

    } catch (Exception e) {
	e.printStackTrace();
    }
    test = null;    
    executedCountfromTestCase =0;
    executedCountfromTestSuite=0;
    System.gc();
  }
     public static int executedCount() {    	 
    	 completedExecution = executedCountfromTestCase+executedCountfromTestSuite;
		return completedExecution;    	 
     }
     
     public static String executedCase() {
		return executedTestCase;
    	 
     }
	  

}