package core.database;

public class DataCleansing {
		
	public static String stripStartandEndQuotes(String colValue){
		
	//	 String regex = "\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)";         
    //   colValue = colValue.replaceAll(regex, "");
		colValue= colValue.trim();
		int colValueCount=colValue.length();
		if (colValue.charAt(0)=='"') {  // handle '
			colValue=colValue.substring(1,colValueCount--);
		}
		if (colValue.charAt(--colValueCount)=='"') {
			colValue=colValue.substring(0,colValueCount);
		}
		return colValue;
	}
	
}


// European Chars 
// Multi Line
// think about joins and reduce columns in output dir