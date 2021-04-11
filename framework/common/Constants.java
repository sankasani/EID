package common;

import authentication.LoginDialog;

public class Constants {

		public static final String Version = "2.1-Dev in Progress";

		public static final String DefaultDirectory = "\\\\opscifs1\\group\\$ Enterprise Initiative Delivery\\Private\\QA\\EnterpriseInitiativeAutomation\\";
		
		public static final String ApplicationXMl_Path =DefaultDirectory+"\\framework\\config\\Applications.xml";
		public static final String ServerDriversPath =DefaultDirectory+"\\framework\\drivers\\";
		public static final String LocalDriversPath ="C:\\EnterpriseInitiativeAutomation\\drivers";


		public static String emailDomain = "@texascapitalbank.com,";
		
/* ***********************************************Framework Directory Names**********************************************************************/	
		
		public static String Screenshots=DefaultDirectory+"\\Users\\"+LoginDialog.getUsername()+"\\screenshots\\";
		public static String Results=DefaultDirectory+"\\Users\\"+LoginDialog.getUsername()+"\\reports\\";
		public static String Email=DefaultDirectory+"\\Users\\"+LoginDialog.getUsername()+"\\email\\";
		

		public static String TESTCASEDIR_PATH(String projectName) {
			return  DefaultDirectory+"\\projects\\"+projectName+"\\testsuites\\";
		}
		
		public static String TESTCASESXML_PATH(String projectName, String testCaseFileName) {
			return DefaultDirectory+"\\projects\\"+projectName+"\\testsuites\\"+testCaseFileName+"_TestSuite.xml";
		}
		
		public static String TESTDATADIR_PATH(String projectName) {
			return  DefaultDirectory+"\\projects\\"+projectName+"\\testdata\\";
		}
		
		public static String TESTDATAXML_PATH(String projectName) {
			return DefaultDirectory+"\\projects\\"+projectName.toLowerCase()+"\\testdata\\"+projectName+"_TestData.xml";
		}
		
		public static String ImageDIR_PATH(String projectName) {
			return  DefaultDirectory+"\\projects\\"+projectName+"\\imageobjproperty\\";
		}

		
		/* ************************************************Reporting*************************************************************************************/	

		//public static String REPORT=Constants.DefaultDirectory+"\\Users\\"+LoginDialog.getUsername()+"\\Reports\\"+"\\TCB_EID_TestReport.html";
		
		public static final String HtmlReport = Results+"\\readme.html";


		/* ************************************************Test Urls & Browser Selection**************************************************************************************/	

		public static final String CreateLeadURL = "TestConfig_CreateLeadURL";
		public static final String RelationshipURL = "TestConfig_RelationshipURL";
		public static final String SalesForceURL = "TestConfig_SalesForceURL";
		
		// Application Escrow
		public static final String EscrowURL = "TestConfig_EscrowURL";
		public static String EscrowUser1_USERNAME = "TestConfig_EscrowUser1UserName";
		public static String EscrowUser1_PASSWORD = "TestConfig_EscrowUser1Password";

		

	/* ******************************************************************Salesforce*******************************************************************/	

		//UserDetails
		public static String STANDARDTCB_ROLE = "StandardTCBUser";
		public static String MORTGAGEFINANCE_ROLE = "MortgageFinance";
		public static String MTGFINCOLLATERALMGMT_ROLE = "MtgFinCollateralMgmt";
		public static String COMMUNITYDEVELOPMENT_ROLE = "CommunityDevelopment";
		public static String CUSTOMERSERVICE_ROLE = "CustomerService";
		public static String SFADMIN_ROLE = "SFAdmin";

	// Specific for Framework.	
		public static String STANDARDTCB_USERNAME = "TestConfig_StandardTCBUserName";
		public static String STANDARDTCB_PASSWORD = "TestConfig_StandardTCBPassword";
		public static String MortgageFinanceUserName = "TestConfig_MortgageFinanceUserName";
		public static String MortgageFinancePassword = "TestConfig_MortgageFinancePassword";
		public static String MtgFinCollateralMgmtUserName = "TestConfig_MtgFinCollateralMgmtUserName";
		public static String MtgFinCollateralMgmtPassword = "TestConfig_MtgFinCollateralMgmtPassword";
		public static String CommunityDevelopmentUserName = "TestConfig_CommunityDevelopmentUserName";
		public static String CommunityDevelopmentPassword = "TestConfig_CommunityDevelopmentPassword";
		public static String CustomerServiceUserName = "TestConfig_CustomerServiceUserName";
		public static String CustomerServicePassword = "TestConfig_CustomerServicePassword";
		public static String SFAdminUserName = "TestConfig_SFAdminUserName";
		public static String SFAdminPassword = "TestConfig_SFAdminPassword";


		//ObjectNames and Labels for Properties
		public static String Obj_UserName = "TestObj_UserName_id";
		public static String Obj_Password = "TestObj_Password_id";
		public static String Obj_LoginButton = "TestObj_LoginButton_id";
		
		// Database Connection details
		public static String AzureServerName = "TestConfig_AzureServerName";
		public static String AzureDatabaseName = "TestConfig_AzureDatabaseName";
		public static String AzureHostCertName = "TestConfig_AzureHostCertName";
				
		/* ******************************************************************Property Paths*******************************************************************/	

	
		public static String CONFIG_PATH = "\\\\OPSCIFS1\\group\\$ Enterprise Initiative Delivery\\Private\\QA\\EnterpriseInitiativeAutomation\\TCB.EID.Automation\\EnterpriseInitiativeDelivery.Automation\\projectProperties\\Testconfig.properties";

		
		
		//API Strategy
		public static String PROJECT_PATH="C:\\Project-Test\\TcbApiStrategyAutomation\\"; 
		public static String PROJECT_XML_PATH=PROJECT_PATH + "SoapProject\\TCB-ApiStrategy.xml";
		public static String PROJECT_DATA_PATH=PROJECT_PATH + "TestData\\Test Data.xlsx";		
		public static String REPORT_PATH=PROJECT_PATH;
		
		// Database to Database
		public static final String Transformations = "Db2Db";
		public static final String config = "Config";
		public static String TestData = "\\TestData\\DataValidator.xlsm";

	
}
