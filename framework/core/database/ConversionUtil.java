package core.database;

public class ConversionUtil {
	
	  public static String AppendWithTCB(String columnValue) {    	
	    	String columnValueOutput = columnValue+"-"+"TCB";
			return columnValueOutput;	    	
	    }
		    
	  public static String ssnTransform(String colValue) {  
		 // Requirement
		 // ColValue 6 or less return same value
		 // ColValue 7 then append with 2 zeros
		 // ColValue 8 the append with  1 zero
         // ColValue 11 then remove hyphens.
		  
		  String SSN = null;		  
		 // colValue= colValue.replaceAll("[\\s\\-()]", "");  -- will deliver same as below
		    colValue= colValue.replaceAll("\\D", "");

		  int colValueCount=colValue.length();
			  if(colValueCount <=5) {
				SSN =colValue;
			  }else if(colValueCount==6) {
				  SSN =colValue;
			  }else if (colValueCount==7) {
				  SSN ="00"+colValue;
			  }else if (colValueCount==8) {
				  SSN ="0"+colValue;
			  }else if(colValueCount ==9) {
				  SSN = colValue;
			  }else if (colValueCount>9) {
				  
			  }  	  
		  
		  return SSN;
		  
	  }
	  
	  public static String date_MMDDYYYY_YYYYMMDD(String colValue) {  
		  // Input value -- 07/27/1999
		  // output valye -- 1999-07-27
		  
		  String delimiter ="/";		  
			String[] colSplit = colValue.split(delimiter);
			colValue =colSplit[2]+"-"+colSplit[0]+"-"+colSplit[1];	
			
		  return colValue;
		  
	  }
	  
	  
}
