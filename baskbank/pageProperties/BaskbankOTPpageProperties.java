package pageProperties;

public class BaskbankOTPpageProperties {
	
	public static String OTPScreen ="//*[@id=\"login-inner\"]/div[4]/div";
	public static String PhoneNumber ="//button[@type='button' and contains(., 'Text me : (XXX) XXX-7058')]";
	public static String PhoneNumberEditField ="//*[@id=\"tacEntry\"]";
	public static String EnterOTPButton ="//*[@id=\"login-inner\"]/div[4]/form/div[2]/button[1]";
	public static String RegisterDeviceScreen ="//*[@id=\"login-inner\"]/div[4]/div";
	public static String RegisterDeviceButton ="//button[@type='button' and contains(., 'Register Device')]";
}
