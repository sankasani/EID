package reporting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import common.Constants;

public class ReportCustomization {
	
	public static void forTCB() {
		
		String oldFileName = null;
		File dir = new File(Constants.Results);
	    FileFilter fileFilter = new WildcardFileFilter("*." + "html");
	    File[] files = dir.listFiles(fileFilter);

	    if (files.length > 0) {
	        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
	        oldFileName = files[0].toString();
	    }

		
	     // String oldFileName = Constants.REPORT;
	      String tmpFileName = Constants.Results+"\\TCB_EID_TestReport.html";

	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      try {
	         br = new BufferedReader(new FileReader(oldFileName));
	         bw = new BufferedWriter(new FileWriter(tmpFileName));
	         String line;
	         while ((line = br.readLine()) != null) {
	            if (line.contains("<a href=\"#!\" class=\"brand-logo blue darken-3\">Extent</a>")) {
	                line=line.replace("<a href=\"#!\" class=\"brand-logo blue darken-3\">Extent</a>","<img src=\"\\\\opscifs1\\group\\$ Enterprise Initiative Delivery\\Private\\QA\\EnterpriseInitiativeAutomation\\framework\\images\\TcbLogo.gif\" alt=\"Home\">");

	            }else if(line.contains("<span class='label blue darken-3'>")) {
	                line=line.replace("v3.0.7","TCB.EID.TestReport");

	            }else if (line.contains("<span class='report-name'>ExtentReports</span>")) {
	            	line=line.replace("<span class='report-name'>ExtentReports</span>","");
	            }
	            bw.write(line+"\n");
	         }
	      } catch (Exception e) {
	         return;
	      } finally {
	         try {
	            if(br != null)
	               br.close();
	         } catch (IOException e) {
	            //
	         }
	         try {
	            if(bw != null)
	               bw.close();
	         } catch (IOException e) {
	            //
	         }
	      }
	      // Once everything is complete, delete old file..
	      File oldFile = new File(oldFileName);
	      oldFile.delete();

	      // And rename tmp file's name to old file name
	      File newFile = new File(tmpFileName);
	      newFile.renameTo(oldFile);

	   }

}
