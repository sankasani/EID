package pageProperties;

public class RelationshipPageProp {
	
//	public static String Details = "xpath_//ul[@class='tabs__nav']/li[1][contains(@data-aura-class,'uiTabItem')]/a/span[contains(text(),'Details')]";
	
	public static String Details = "xpath_//span[@class='title'][contains(text(),'Details')]";

	public static String LoanOSBalance = "xpath_//span[text()='Loan O/S Balance']/../../div[2]/span/span";
	//public static String LoanOSBalance = "xpath_//span[text()='Loan O/S Balance']/../../div[2]/span/slot/slot";

	public static String LoanUnusedBalance = "xpath_//span[text()='Loan Unused Balance']/../../div[2]/span/span";
	//public static String LoanUnusedBalance = "xpath_//span[text()='Loan Unused Balance']/../../div[2]/span/slot/slot";

	public static String LoanCommitmentBalance = "xpath_//span[text()='Loan Commitment Balance']/../../div[2]/span/span";
	//public static String LoanCommitmentBalance = "xpath_//span[text()='Loan Commitment Balance']/../../div[2]/span/slot/slot";


	public static String relationshipName ="xpath_//span[text()='Relationship Name']/../../input";
	//public static String convertRelationshipButton ="xpath_//span[contains(@class,'label bBody')][contains(text(),'Convert')]";
	public static String convertRelationshipButton ="xpath_//div/button[2]//span[contains(@class,'label bBody')][contains(text(),'Convert')]";

	public static String LeadConverted ="xpath_//span[contains(text(),'Your lead has been converted')]";
	public static String VerifyRelationship ="xpath_//div[text()='Relationship']/..//a";	
	public static String RelationshipListName ="xpath_//tbody/tr[1]/th//a";	
	//public static String RelationshipRelatedContacts = "xpath_//ul[@class='tabs__nav']/li[1][contains(@data-aura-class,'uiTabItem')]/a/span[contains(text(),'Related Contacts')]";
	public static String RelationshipRelatedContacts = "xpath_//ul[@class='slds-tabs_default__nav']/li[2][contains(@data-label,'Related Contacts')]/a[contains(text(),'Related Contacts')]";


	
	//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton runtime_sales_leadConvertModalFooter']	


}
