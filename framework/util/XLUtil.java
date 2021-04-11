package util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.eviware.soapui.model.support.PropertiesMap;

import java.io.*;


public class XLUtil {
	
	static String destFilePath;
	public static  Xls_Reader reportxls;
	
	// Test Data for the test case
	public static List<PropertiesMap> getTestData(String suiteName , String testCaseName, Xls_Reader xls) {
		
		int rows = xls.getRowCount(suiteName);
		System.out.println("Total Rows --> " + rows);
		
		
		
		// row number of the test case
		int testCaseRowNum= 1;
		for(testCaseRowNum=1; testCaseRowNum<rows;testCaseRowNum++) {
			if(xls.getCellData(suiteName,0, testCaseRowNum).equals(testCaseName))
				break;		
			
		}
		System.out.println("Test Case----> " + testCaseName+ " Starts from row Number " + testCaseRowNum);
		
		
		// Total col in the tests
		int colRowNum= testCaseRowNum +1;
		int dataStartRowNum = testCaseRowNum + 2;		
		int totalCols=0;		 
		while(!xls.getCellData(suiteName, totalCols, colRowNum).equals("")) {
			totalCols++;
		}		
		System.out.println("Test Columns in ----> " + testCaseName+ " are " + totalCols);
		
		// Total rows in the tests
		int totalRows = 0;
		while(!xls.getCellData(suiteName, 1, dataStartRowNum + totalRows).equals("")) {
			totalRows++;
		}
		System.out.println("Test Rows in ----> " + testCaseName+ " are " + totalRows);
		
		
		//extract the data
		
		String data=null;
		String key = null;
		PropertiesMap map = null;
		List<PropertiesMap> dataList = new ArrayList<PropertiesMap>();
		for(int rNum=dataStartRowNum;rNum<(dataStartRowNum+totalRows); rNum++) {
			map = new PropertiesMap();
			//row Data
			for(int cNum=0;cNum<totalCols;cNum++) {
				key = xls.getCellData(suiteName, cNum, colRowNum);
				data= xls.getCellData(suiteName, cNum, rNum);
				map.put(key, data);
			}
			dataList.add(map);
			//System.out.println();
		}
		return dataList;
		
	}
	
	
	public static void generateReport(String testSuiteName, String testCaseName, List<PropertiesMap> testDataSets) {
		// first find the test case in the sheet
		// write the result
		reportxls = new Xls_Reader(destFilePath);
		int rows = reportxls.getRowCount(testSuiteName);
		
		//row number of the test case
		int testCaseRowNum= 1;
		for(testCaseRowNum=1; testCaseRowNum<rows;testCaseRowNum++) {
			if(reportxls.getCellData(testSuiteName,0, testCaseRowNum).equals(testCaseName))
				break;		
			
		}
		
		int dataStartRowNum = testCaseRowNum+2;
		
		String result = null;
		for(int rNum=dataStartRowNum; rNum<testDataSets.size()+dataStartRowNum;rNum++) {
			result = (String) testDataSets.get(rNum-dataStartRowNum).get("Result");
			reportxls.setCellData(testSuiteName, rNum, 0, result);
		}
		
		reportxls = null;
	}
	
	public static void prepareResultSheet(String srcFilePath, String destFolderPath) {
		InputStream inStream = null;
		OutputStream outStream = null;

	    	try{
	    		Date d = new Date();
	    		String fileName = "Result-" +d.toString().replace(":","_").replace(" ","_")+".xlsx";
	    		destFilePath= destFolderPath + fileName;
	    	    File afile =new File(srcFilePath);
	    	    File bfile =new File(destFilePath);

	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);

	    	    byte[] buffer = new byte[1024];

	    	    int length;
	    	    //copy the file content in bytes
	    	    while ((length = inStream.read(buffer)) > 0){

	    	    	outStream.write(buffer, 0, length);

	    	    }

	    	    inStream.close();
	    	    outStream.close();

	    	 //   reportxls = new Xls_Reader(destFilePath);

	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
	}
	
	public static boolean isExecutable(String testCaseName, Xls_Reader xls,String sheetName) {
		int rows = xls.getRowCount(sheetName);
		
		for(int rNum=2;rNum<=rows;rNum++) {
			if(xls.getCellData(sheetName, "TestCaseName", rNum).equals(testCaseName)) {
				if(xls.getCellData(sheetName, "Execute", rNum).equals("Y")) 
					return true;
				else
					return false;
						
				
			}
			
		}
		
		return false;
		
		
		
	}
	

}
