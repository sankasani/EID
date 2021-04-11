package tests;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.GenericLibrary;
import core.ui.UIDriver;
import pageProperties.BaskBankLogMessages;

public class BaskBank_00005_Server_Status extends UIDriver {

	public BaskBank_00005_Server_Status(ExtentTest test) {
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
			GenericLibrary.INFO(BaskBankLogMessages.Server1PingRequest + ipAddress1);
			GenericLibrary.PASS(ipAddress1+ BaskBankLogMessages.ServerPingPass);
			System.out.println("I am in server1");

		} else if (serverIp2.isReachable(5000)==true) {
			GenericLibrary.INFO(BaskBankLogMessages.Server2PingRequest + ipAddress2);
			GenericLibrary.PASS(ipAddress2+ BaskBankLogMessages.ServerPingPass);
			System.out.println("I am  in server2");

		} 

		return true;
	}

	else {
		GenericLibrary.FAIL(BaskBankLogMessages.ServernotRechable);
		return false;
	}



	}catch (Exception e) {
		GenericLibrary.FAIL(BaskBankLogMessages.ServernotRechable);	
	return false;
	}
	//return false;

		

	}


}
