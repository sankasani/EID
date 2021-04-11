package pageProperties;

public class LeadPageProp {
	public static String CreateLead = "CreateLead";
	public static String LeadNewTab = "xpath_//a[contains(@class,'tabHeader slds-context-bar__label-action')]//span[@class='title slds-truncate'][contains(text(),'New Lead: Standard')]";
	public static String LeadNewButton = "xpath_//div[contains(text(),'New')]";
	public static String Salutation = "xpath_//div//a[@class='select'][contains(text(),'--None--')]";
	public static String SelectSalutation = "xpath_//a[contains(text(),'Dr.')]";
	public static String LeadFirstName = "xpath_//input[@placeholder='First Name']";
	public static String LeadLastName = "xpath_//input[@placeholder='Last Name']";
	public static String LeadTitle = "xpath_//span[text()='Title']/../../input";
	public static String LeadCompany = "xpath_//span[text()='Company']/../../input";
	public static String LeadSource = "xpath_//a[contains(text(),'RM Generated')]";
	public static String LeadSourceSelect = "xpath_//a[contains(text(),'Property Management')]";
	public static String LeadStatus = "xpath_//a[@class='select'][contains(text(),'New')]";
	public static String LeadStatusSelect = "xpath_//a[contains(text(),'Eligible')]";
	public static String LeadPhone = "xpath_//span[text()='Phone']/../../input";
	public static String LeadEmail = "xpath_//span[text()='Email']/../../input";
	public static String LeadMobile = "xpath_//span[text()='Mobile']/../../input";
	public static String LeadAddress = "xpath_//span[text()='Street']/../../textarea";
	public static String LeadCity = "xpath_//span[text()='City']/../../input";
	public static String LeadStateProvince = "xpath_//span[text()='State/Province']/../../input";
	public static String LeadZipPostalCode = "xpath_//input[@placeholder='Zip/Postal Code']";
	public static String LeadCountry = "xpath_//input[@placeholder='Country']";
	public static String LeadSaveButton = "xpath_//button[@class='slds-button slds-button--neutral uiButton--brand uiButton forceActionButton']//span[contains(@class,'label bBody')][contains(text(),'Save')]";
	//public static String Details = "xpath_//ul[@class='tabs__nav']/li[1][contains(@data-aura-class,'uiTabItem')]/a/span[contains(text(),'Details')]";
	public static String Details = "xpath_//span[@class='title'][contains(text(),'Details')]";

	//public static String LeadVerifyTitle = "xpath_//span[text()='Title']/../../div[2]/span/span";
	public static String LeadVerifyTitle = "xpath_//span[@class='test-id__field-label'][contains(text(),'Title')]/../../div[2]/span/span";
	//public static String LeadVerifyTitle = "xpath_//span[@class='test-id__field-label'][contains(text(),'Title')]/../../div[2]/span/slot/slot";
	public static String LeadListFirst ="xpath_//tbody/tr[1]/th//a";
	public static String LeadConvertButton="xpath_//div[contains(text(),'Convert')]";

	//span[@class='test-id__field-label'][contains(text(),'Title')]/../../div[2]/span/span
	
}
