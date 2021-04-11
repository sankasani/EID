package pageProperties;

public class BaskGeneralPageProperties {
	
	public static String BaskBankURL ="https://aus-demo.q2ebanking.com/vfi_stage/tcbdigiattack/qa2/uux.aspx#/landingPage";
	
	public static String BaskBankUsername = "gronewflow";
	public static String BaskBankPassword = "Welcome01";
	public static String SignOut ="xpath_//div[contains(text(),'Sign Out')]";

	
	
	// Object Properties	
	public static String UserName ="id_fldUsername";
	public static String Password ="id_fldPassword";
	public static String LoginButton ="xpath_//*[@id=\"userLinks\"]/button";
	
	
	// Home Page
	public static String HomePageBalance ="xpath_//span[@class='ui-number ui-currency currency currency-retail currency-asset currency-positive']";

	public static String HomeTab = "xpath_//div[contains(text(),'Home')]";
	public static String SavingsTab = "xpath_//div[contains(text(),'Savings')]";
	public static String SavingsPageBalance ="xpath_//span[@class ='numAmount']";

	
	public static String SecureAccessCode = "xpath_//a[@class='mfa-button-linkTo btn btn-primary btn-block ember-view']";
	
	public static String ClickSACforOTP = "xpath_//button[contains(text(),'-7058')]";
	
	public static String ClicktoLoginAfterOTP = "xpath_//*[@id=\"login-inner\"]/div[4]/form/div[2]/button[1]";

	
	public static String HomePageMiles = "//dt[@class='aa-account']/..//dd/span";
	public static String BaskRewards ="//div[@id='rewards-tile']//div[@class='tile-card-action ui-btn']";
	public static String BaskRewardsDetails ="//a[contains(text(),'Details')]";
	
	public static String MilesonBaskRewards ="//div[contains(@class,'account-header')]//dd[1]";
	public static String BaskRewardsMilesinDetails="//dl[contains(@class,'standard-hade-list')]/div[2]/dd";
	
	// Personal Details
	
	public static String CustomerName ="xpath_//div[@class='customer-name']";
	public static String MoreTab = "xpath_//div[contains(text(),'More')]";

	public static String PersonalDetails = "xpath_//div[@id='block_1']";
	public static String PersonalDetailsCustomerName = "Gangadhararamachary";
	
	public static String NotificationandAlerts = "xpath_//div[@id='block_4']";
	public static String ToggleExternalTransfer = "xpath_//li[1]//q2-checkbox[1]";
	public static String AccountAlert = "xpath_//ul[@id='ui-dropdown-menu-ember849']//li[contains(text(),'Account Alert')]";
	
	public static String ToggleComputerBrowser = "xpath_//li[2]//q2-checkbox[1]";
	public static String ToggleExternalAccountProcess = "xpath_//li[6]//q2-checkbox[1]";	
	
	public static String ExternalTransferCheck ="xpath_//li[1]//q2-checkbox[1][@checked]";
	public static String ComputerBrowserRegister ="xpath_//li[2]//q2-checkbox[1][@checked]";



	
	
}
