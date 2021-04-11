package tests;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import core.ui.UIDriver;

public class Sitefinity_50004_Server_Status extends UIDriver {

	public Sitefinity_50004_Server_Status(ExtentTest test) {
		super(test);
		// TODO Auto-generated constructor stub
	}
	
	//public static boolean sendPingRequest(Object[] array) throws UnknownHostException, IOException {
	public static boolean sendPingRequest(String TestCaseID,String ipAddress1,String ipAddress2,String randomNumber,String ApplicationName,String browserType) throws UnknownHostException, IOException {

	/*String TestCaseID = array[0].toString();
	String ipAddress1 = array[1].toString();
	String ipAddress2 = array[2].toString();
	String randomNumber = array[3].toString();
	String ApplicationName = array[4].toString();*/


	InetAddress serverIp = InetAddress.getByName(ipAddress1);
	InetAddress serverIp2 = InetAddress.getByName(ipAddress2);
	
	System.out.println(ipAddress1);
	System.out.println(ipAddress2);

try {

if (serverIp.isReachable(5000) || serverIp2.isReachable(5000)) {
	
	if(serverIp.isReachable(5000)==true) {
		test.log(Status.INFO, "Sending Ping Request to " + ipAddress1);
		test.log(Status.PASS, ipAddress1+ ":--> Server is Up and Running");
		System.out.println("I am in server1");

	} else if (serverIp2.isReachable(5000)==true) {
		test.log(Status.INFO, "Sending Ping Request to " + ipAddress2);
		test.log(Status.PASS, ipAddress2+ ":--> Server is Up and Running");
		System.out.println("I am  in server2");

	} 

	return true;
}

else {
	test.log(Status.FAIL, "Sorry ! We can't reach to this host");
	return false;
}



}catch (Exception e) {
test.log(Status.FAIL, "Sorry ! We can't reach to this host");	
test.log(Status.FAIL, "Exception during Ping ------> " + e);
return false;
}
//return false;

	

}

}
