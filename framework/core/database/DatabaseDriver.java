package core.database;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DatabaseDriver {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		File fout = new File("C:\\Winium\\columnIdentifier_New.txt");   // .txt,.csv,.dat,xls,xlsx,multitabbed xlsx
		File fileDir = new File("C:\\Winium\\columnIdentifier.txt");
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fout), "UTF-8"));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
		Object delimFromJson = PlanReader.getJsonPlan("Delimiter");  // Gets Delimiter value from Table Plan file

		String line;
		int k = 0;
		while ((line = br.readLine()) != null) {
			String delimiter = "\\"+delimFromJson.toString();
			String[] colSplit = line.split(delimiter);
			int columnCount = colSplit.length;
			String joined = null;
			StringJoiner joiner = new StringJoiner(delimFromJson.toString());

			if (k != 0) {
				for (int j = 0; j < columnCount; j++) {
					String colValue = colSplit[j];
							
					colValue = DataCleansing.stripStartandEndQuotes(colValue); // remove leading and trailing double quotes
					String valueFromJson = null;
					Object methodName = PlanReader.getJsonPlan("" + j + "");
					if (methodName == null) {
						methodName = "NoValue";
						valueFromJson = methodName.toString();
					} else {
						valueFromJson = methodName.toString();
					}
					if (!valueFromJson.equals("NoValue")) {
						Object transformedColumn = null;
						Class<?> classobj = Class.forName("database.ConversionUtil");
						Method[] methods = classobj.getDeclaredMethods();
						for (Method method : methods) {
							String MethodName = method.getName();
							if (valueFromJson.toString().equals(MethodName)) {
								transformedColumn = method.invoke(MethodName, colValue);
							}
						}
						joiner.add(transformedColumn.toString());
						joined = joiner.toString();
					} else {
						joiner.add(colValue);
						joined = joiner.toString();
					}
				}

			} else {
				for (int j = 0; j < columnCount; j++) {
					String colValue = colSplit[j];
					joiner.add(colValue);
					joined = joiner.toString();
				}
			}

			out.write(joined);
			out.newLine();
			k++;
		}
		br.close();
		out.close();

	}

}