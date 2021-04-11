package tests.sf.relationship;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import common.Constants;
import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.RelationshipPageProp;
import reusableFunctions.SalesforceLogin;

public class Salesforce_80005_Loan_Balance_CIF_1009900105239 extends UIDriver{
	
	public Salesforce_80005_Loan_Balance_CIF_1009900105239(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}

	public static void balanceCheck_1009900105239(String TestCaseID, String Role,String randomNumber,String ApplicationName,String ChromeHeadLess) throws Exception {
		
		try {			
			ArrayList<String> isPSF = new ArrayList<String>();
			ArrayList<String> IsCB = new ArrayList<String>();
			ArrayList<String> IsUnB = new ArrayList<String>();
			ArrayList<String> IsLOC = new ArrayList<String>();
			ArrayList<String> isCIF = new ArrayList<String>();
			ArrayList<String> IsUb = new ArrayList<String>();
			ArrayList<String> HHAccNo = new ArrayList<String>();
			ArrayList<String> AccHieType = new ArrayList<String>();
			ArrayList<String> AccNo = new ArrayList<String>();
			ArrayList<String> IsOutPB = new ArrayList<String>();
			
			Connection azureConn = null;
			ResultSet edpPzrs = null;
			
			GenericLibrary.INFO("Connecting to Azure Cloud to get Loan balance details from CDS Account for CIF 1009900105239");

			SQLServerDataSource azureEdp = GenericLibrary.activeDirectory(Constants.AzureServerName, Constants.AzureDatabaseName, Constants.AzureHostCertName);
			azureConn = azureEdp.getConnection();
			
			String balanceQuery = "Select IsPartSoldFlag,IsLetterofCreditFlag,CommitmentBalance,UnusedBalance,UsedBalance,OutstandingPrincipalBalance,AccountNumber,AccountHierarchyType,HighestHierarchyAccountNumber,ParentAccountNumber,\r\n" + 
					"PrimaryCustomerIntegrationID as CIF from pz_wh.cdsAccount where HighestHierarchyAccountNumber in ('3000004326','3000004327')\r\n" + 
					"  and AccountStatusDesc in ('ACTIVE ACCOUNT','OPEN/ACTIVE','OPEN/INACTIVE') order by AccountNumber";

			String edpPzSource = "" + balanceQuery + "";

			Statement statementSource = azureConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			edpPzrs = statementSource.executeQuery(edpPzSource);

			
			while (edpPzrs.next()) {

				String IsPartSoldFlag = edpPzrs.getString("IsPartSoldFlag");
				String IsLetterofCreditFlag = edpPzrs.getString("IsLetterofCreditFlag");
				String CommitmentBalance = edpPzrs.getString("CommitmentBalance");
				String UnusedBalance = edpPzrs.getString("UnusedBalance");
				String UsedBalance = edpPzrs.getString("UsedBalance");
				String OutstandingPrincipalBalance = edpPzrs.getString("OutstandingPrincipalBalance");
				String AccountNumber = edpPzrs.getString("AccountNumber");
				String AccountHierarchyType = edpPzrs.getString("AccountHierarchyType");
				String HighestHierarchyAccountNumber = edpPzrs.getString("HighestHierarchyAccountNumber");																									// precision.
				String CIF = edpPzrs.getString("CIF");

				isPSF.add(IsPartSoldFlag);
				IsLOC.add(IsLetterofCreditFlag);
				IsCB.add(CommitmentBalance);
				IsUnB.add(UnusedBalance);
				IsUb.add(UsedBalance);
				IsOutPB.add(OutstandingPrincipalBalance);
				AccNo.add(AccountNumber);
				AccHieType.add(AccountHierarchyType);
				HHAccNo.add(HighestHierarchyAccountNumber);
				isCIF.add(CIF);

			}
			
			

			// Commitment Balance

			String b1 = IsCB.get(1).substring(0, IsCB.get(1).length() - 2);
			String b2 = IsCB.get(2).substring(0, IsCB.get(2).length() - 2);
			String b3 = IsCB.get(4).substring(0, IsCB.get(4).length() - 2);

			//String b4 = IsOutPB.get(8).substring(0, IsOutPB.get(8).length() - 2);

			BigDecimal FinalCommitmentBalance = new BigDecimal(b1).subtract(new BigDecimal(b2)).add(new BigDecimal(b3));

			System.out.println(FinalCommitmentBalance);
			GenericLibrary.INFO("Commitment balance from source after removing partsold value from CDS account --> " + FinalCommitmentBalance.toString());

			// Loan Unused Balance

			String UUB1 = IsUnB.get(1).substring(0, IsUnB.get(1).length() - 2);
			String UUB2 = IsUnB.get(2).substring(0, IsUnB.get(2).length() - 2);
			String UUB3 = IsUnB.get(4).substring(0, IsUnB.get(4).length() - 2);


			BigDecimal FinalUnusedBalance = new BigDecimal(UUB1).subtract(new BigDecimal(UUB2)).add(new BigDecimal(UUB3));
			System.out.println(FinalUnusedBalance);
			GenericLibrary.INFO("Unused balance from source after removing partsold value from CDS account --> " + FinalUnusedBalance.toString());

			// Loan Used Balance orOutstanding Principal balance

			String UB1 = IsUb.get(1).substring(0, IsUb.get(1).length() - 2);
			String UB2 = IsUb.get(2).substring(0, IsUb.get(2).length() - 2);

			BigDecimal FinalUsedBalance = new BigDecimal(UB1).subtract(new BigDecimal(UB2));
			System.out.println(FinalUsedBalance);
			GenericLibrary.INFO("Used balance from source after removing partsold value from CDS account --> " + FinalUsedBalance.toString());

			// https://texascapitalbank--uat4.lightning.force.com/lightning/r/Account/0011600001w0zohAAA/view
			
			GenericLibrary.INFO("Connecting to On-premise SalesforceExtract database to get relationship ID for the loan");


			String serverName1 = "RLTDEXDMDB1";
			String HostCertName1 = ".tcbna.net";
			String portNo1 = "1555";
			String dataBaseName1 = "SalesforceExtract";
			ResultSet rsSource1 = null;
			Connection db2conn = null;

			String dbURL1 = "jdbc:sqlserver://" + serverName1 + HostCertName1 + ":" + portNo1 + ";databaseName="
					+ dataBaseName1 + ";integratedSecurity=true";

			db2conn = DriverManager.getConnection(dbURL1);

			String db1Query1 = "select Distinct ID from [Export].[SF_Pipeline_Account] where CIF__c ='1009900105239'";

			String querySource1 = "" + db1Query1 + "";
			Statement statementSource1 = db2conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);

			rsSource1 = statementSource1.executeQuery(querySource1);
			String ID = null;

			while (rsSource1.next()) {
				ID = rsSource1.getString("ID");
			}
			GenericLibrary.INFO("Received relationship ID--> " +ID);
			String URL1 = "https://texascapitalbank--uat1.lightning.force.com/lightning/r/Account/" + ID + "/view";
			System.out.println("Got ID");

			SalesforceLogin.login(URL1, Role,ApplicationName, ChromeHeadLess);

			System.out.println("Logged IN");
			GenericLibrary.click(RelationshipPageProp.Details);			
			System.out.println("Getting Balances");
			GenericLibrary.INFO("Comparing the balances between Source ( Cds Account) and Target (Salesforce)");
			
			
			String LoanOutStandingBalance = GenericLibrary.getElement(RelationshipPageProp.LoanOSBalance).getText();		
			String LoanUnusedBalance = GenericLibrary.getElement(RelationshipPageProp.LoanUnusedBalance).getText();
			String LoanCommitmentBalance = GenericLibrary.getElement(RelationshipPageProp.LoanCommitmentBalance).getText();
			
			System.out.println("Got Balances");

			LoanOutStandingBalance = LoanOutStandingBalance.replace("$", "").replace(",", "");
			LoanUnusedBalance = LoanUnusedBalance.replace("$", "").replace(",", "");
			LoanCommitmentBalance =LoanCommitmentBalance.replace("$", "").replace(",", "");
			
			if(FinalCommitmentBalance.toString().equals(LoanCommitmentBalance)) {
				GenericLibrary.PASS("Data matches between CDS account and Salesforce ----- > Loan Commitment Balance in Source (CDS account) is--> " + FinalCommitmentBalance.toString() + "------  Loan Commitment Balance in Target (Salesforce) is -->" + LoanCommitmentBalance );

			}else {
				GenericLibrary.FAIL("Data discrepency between CDS account and Salesforce ----- > Loan Commitment Balance in Source (CDS account) is--> " + FinalCommitmentBalance.toString() + "------  Loan Commitment Balance in Target (Salesforce) is -->" + LoanCommitmentBalance );

			}
			
			if(FinalUnusedBalance.toString().equals(LoanUnusedBalance)) {
				GenericLibrary.PASS("Data matches between CDS account and Salesforce ----- > Loan Unused Balance in Target (CDS account) is --> " + FinalUnusedBalance.toString() + "------  Loan Unused Balance in Target (Salesforce) is -->" + FinalUnusedBalance );

			}else{
				GenericLibrary.FAIL("Data discrepency between CDS account and Salesforce ----- > Loan Unused Balance in Target (CDS account) is --> " + FinalUnusedBalance.toString() + "------  Loan Unused Balance in Target (Salesforce) is -->" + FinalUnusedBalance );

			}
			
			if(FinalUsedBalance.toString().equals(LoanOutStandingBalance)) {
				GenericLibrary.PASS("Data matches between CDS account and Salesforce ----- > Loan OutStanding Balance in Target (CDS account) is --> " + FinalUsedBalance.toString() + "  Loan OutStanding Balance in Target (Salesforce) is -->" + LoanOutStandingBalance );

			} else {
				GenericLibrary.FAIL("Data discrepency between CDS account and Salesforce ----- > Loan OutStanding Balance in Target (CDS account) is --> " + FinalUsedBalance.toString() + "  Loan OutStanding Balance in Target (Salesforce) is -->" + LoanOutStandingBalance );

			}

			azureConn.close();
			db2conn.close();
			GenericLibrary.scrollTo(RelationshipPageProp.LoanCommitmentBalance);

			GenericLibrary.getScreenshot("Loan Balances");
			GenericLibrary.closeBrowser();
			
			
			
			
			
		}catch(Exception balance) {
		
			GenericLibrary.closeBrowser();
		}
		
		
	}

}
