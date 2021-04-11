package core.database;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PlanReader {
	
	public static Object getJsonPlan(String columnName) {
		 
		 Object MethodName1 = null;
		 JSONParser parser = new JSONParser();
			try {
				Object obj = parser.parse(new FileReader("C:\\DataValidator\\Source\\Config.json")); 
				JSONObject jsonObject = (JSONObject) obj;	
				
				if(columnName.equals("Delimiter")) {
					MethodName1 = jsonObject.get("Delimiter");

				}else if(columnName.equals("RowDelimier")) {
					MethodName1 = jsonObject.get("RowDelimier");
				}else if(columnName.equals("EuropeanChars")) {
					MethodName1 = jsonObject.get("EuropeanChars");
				}else  {
					JSONArray transformations = (JSONArray) jsonObject.get("ColumnMethods");  
					for(int i=0;i<transformations.size();i ++) {
				
						JSONObject colMethodObject = (JSONObject) transformations.get(i);
						 MethodName1 = colMethodObject.get(columnName);
						 if(MethodName1!=null) {
						     break;
						 }
					}
				
				}
				
	 		} catch (Exception e) {
				e.printStackTrace();
			
			}
			return MethodName1;
	 }

}
