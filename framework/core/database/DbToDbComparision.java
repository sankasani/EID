/**
 * 
 */
package core.database;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import authentication.LoginDialog;
import common.Constants;

/**
 * 
 * @author rasapp Jun 28, 2018 9:45:35 AM
 */
public class DbToDbComparision {
	public static ExtentTest test = null;
	static String reportStatus = "NULL";
	public static String CompletedMessage = null;
	static String Error = null;

	public DbToDbComparision(ExtentTest test) {
		DbToDbComparision.test = test;
	}
	
	// Excel Code
/*	static XSSFWorkbook workbook = new XSSFWorkbook();
	static XSSFSheet sheet = workbook.createSheet("Db2DBComparisionResults");
	static Row header = sheet.createRow(0);
	static Map<String, Object[]> data = new TreeMap<String, Object[]>();*/
	//

	public static void dbToDb(ExtentReports rep) throws IOException, SQLException, InvocationTargetException {
		int dialogButton = 0;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Have you set the Test Data sheet \r\n "
				+ "Does the source and target query placed in the Test Data Sheet", "Alert", dialogButton);
		if (dialogResult == JOptionPane.NO_OPTION) {
			CompletedMessage = "Set the Test Data for DB to DB Comparison";
		} else if (dialogResult == JOptionPane.YES_OPTION) {
			// Excel Code

		/*	Common.shadeAlt(workbook.getSheet("Db2DBComparisionResults"));
			header.createCell(0).setCellValue("SourceTableName");
			header.createCell(1).setCellValue("TargetTableName");
			header.createCell(2).setCellValue("ComparisionResult");
			header.createCell(3).setCellValue("Comments");*/
			//

			String TestDataPath = null;
			/*if (DataValidator.SelectedData != null) {
				TestDataPath = Constants.DefaultDirectory + LoginDialog.userName + "\\TestData\\"
						+ DataValidator.SelectedData;
			} else {*/
				TestDataPath = Constants.DefaultDirectory + LoginDialog.userName + Constants.TestData;
			//}

			FileInputStream fi = new FileInputStream(TestDataPath);

			Connection db1conn = null;
			Connection db2conn = null;
			ResultSet rsSource = null;
			ResultSet rsTarget = null;

			Workbook wb = new XSSFWorkbook(fi);
			Sheet sh = wb.getSheet(Constants.Transformations);
			int starRow = sh.getFirstRowNum();
			int endRow = sh.getLastRowNum();


			// Config sheet startrow and End Row

			Sheet sh1 = wb.getSheet(Constants.config);
			int starRowConfig = sh1.getFirstRowNum();
			int endRowConfig = sh1.getLastRowNum();

			for (int i = starRow + 2; i <= endRow; i++) {
				Cell sourceName = wb.getSheet(Constants.Transformations).getRow(i).getCell(0);
				Cell tableName = wb.getSheet(Constants.Transformations).getRow(i).getCell(1);

				// DB1 Details
				Cell db1ServerName = wb.getSheet(Constants.Transformations).getRow(i).getCell(2);
				Cell db1DataBaseName = wb.getSheet(Constants.Transformations).getRow(i).getCell(3);
				Cell db1HostCertName = null, db1PortNo = null, db1Authentication = null, dB1UserName = null,
						db1Password = null;

				// Get from Config Sheet

				for (int k = starRowConfig + 1; k <= endRowConfig; k++) {
					Cell db1ServerName1 = wb.getSheet(Constants.config).getRow(k).getCell(6);
					Cell db1DataBaseName1 = wb.getSheet(Constants.config).getRow(k).getCell(7);

					if (db1ServerName.toString().equalsIgnoreCase(db1ServerName1.toString())) {

						if (db1DataBaseName.toString().equalsIgnoreCase(db1DataBaseName1.toString())) {
							db1HostCertName = wb.getSheet(Constants.config).getRow(k).getCell(8);
							db1PortNo = wb.getSheet(Constants.config).getRow(k).getCell(9);
							db1Authentication = wb.getSheet(Constants.config).getRow(k).getCell(10);
							dB1UserName = wb.getSheet(Constants.config).getRow(k).getCell(11);
							db1Password = wb.getSheet(Constants.config).getRow(k).getCell(12);

							break;
						}

					}

				}

				// End of Config Sheet
				Cell db1Query = wb.getSheet(Constants.Transformations).getRow(i).getCell(4);

				// DB2 Details
				Cell db2ServerName = wb.getSheet(Constants.Transformations).getRow(i).getCell(5);
				Cell db2DataBaseName = wb.getSheet(Constants.Transformations).getRow(i).getCell(6);
				Cell db2HostCertName = null, db2PortNo = null, db2Authentication = null, dB2UserName = null,
						db2Password = null;

				// Get from Config Sheet

				for (int k = starRowConfig + 1; k <= endRowConfig; k++) {
					Cell db2ServerName1 = wb.getSheet(Constants.config).getRow(k).getCell(6);
					Cell db2DataBaseName1 = wb.getSheet(Constants.config).getRow(k).getCell(7);

					if (db2ServerName.toString().equalsIgnoreCase(db2ServerName1.toString())) {

						if (db2DataBaseName.toString().equalsIgnoreCase(db2DataBaseName1.toString())) {
							db2HostCertName = wb.getSheet(Constants.config).getRow(k).getCell(8);
							db2PortNo = wb.getSheet(Constants.config).getRow(k).getCell(9);
							db2Authentication = wb.getSheet(Constants.config).getRow(k).getCell(10);
							dB2UserName = wb.getSheet(Constants.config).getRow(k).getCell(11);
							db2Password = wb.getSheet(Constants.config).getRow(k).getCell(12);

							break;
						}

					}

				}

				Cell db2Query = wb.getSheet(Constants.Transformations).getRow(i).getCell(7);
				// Execute
				Cell execute = wb.getSheet(Constants.Transformations).getRow(i).getCell(8);

				/*
				 * String[] Authentication = {"Auth","ActiveDirectoryDGroup"};
				 * 
				 * for(@SuppressWarnings("unused") int k=0;i<Authentication.length;i++) {
				 * if(!Authentication[i].contains((CharSequence) db1Authentication)) {
				 * System.out.println("Hello"); break; } }
				 */
				if(execute==null || db1ServerName==null ||db1DataBaseName ==null ||db1Query==null ||db2ServerName ==null||db2DataBaseName ==null||db2Query ==null) {
					reportStatus = "BLANKVALUE";					
				}else if (execute.toString().equalsIgnoreCase("YES")) {
						test = rep.createTest(tableName.toString());
						test.assignCategory(sourceName.toString());
					try {

						if (db1Authentication.toString().equals("ActiveDirectoryDGroup")) {
							SQLServerDataSource rk = ActiveDirectory(db1ServerName, db1DataBaseName, db1HostCertName);
							db1conn = rk.getConnection();
						} else if (db1Authentication.toString().contains("Auth")) {
							String dbURL1 = connectingString(db1ServerName, db1HostCertName, db1DataBaseName, db1PortNo,
									db1Authentication, dB1UserName, db1Password);
							db1conn = DriverManager.getConnection(dbURL1);
							
						}
					} catch (Exception e) {
						CompletedMessage = "Server-" + db1ServerName + "-DataBase-" + db1DataBaseName
								+ " Connection Issue";
						Component frame = null;
						JOptionPane.showMessageDialog(frame, CompletedMessage);
						
						test.log(Status.ERROR, "Server-" + db1ServerName + "-DataBase-" + db1DataBaseName+ " Connection Issue");
						test.log(Status.ERROR, e);
						break;
					}

					try {
						if (db2Authentication.toString().equals("ActiveDirectoryDGroup")) {
							SQLServerDataSource rk1 = ActiveDirectory(db2ServerName, db2DataBaseName, db2HostCertName);
							db2conn = rk1.getConnection();
						} else if (db2Authentication.toString().contains("Auth")) {
							String dbURL2 = connectingString(db2ServerName, db2HostCertName, db2DataBaseName, db2PortNo,
									db2Authentication, dB2UserName, db2Password);
							db2conn = DriverManager.getConnection(dbURL2);

						}
					} catch (Exception e) {

						CompletedMessage = "Server-" + db2ServerName + "-DataBase-" + db2DataBaseName
								+ " Connection Issue";
						Component frame = null;
						JOptionPane.showMessageDialog(frame, CompletedMessage);
						test.log(Status.ERROR, "Server-" + db2ServerName + "-DataBase-" + db2DataBaseName+ " Connection Issue");
						test.log(Status.ERROR, e);
						break;
					}

					try {

						if (db1conn != null) {
							Error = " Syntax Error in DB Query -- DataSheet Row No-" + i + "  ServerName--"
									+ db1ServerName + "-DataBaseName--" + db1DataBaseName;

							String querySource = "" + db1Query + "";

							Statement statementSource = db1conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_READ_ONLY);

							rsSource = statementSource.executeQuery(querySource);							
							

						} else {

							break;
						}

						if (db2conn != null) {
							Error = " Syntax Error in DB Query-- DataSheet Row No-" + i + "  ServerName--"
									+ db2ServerName + "-DataBaseName--" + db2DataBaseName;
							String queryTarget = "" + db2Query + "";

							Statement statementTarget = db2conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_READ_ONLY);

							rsTarget = statementTarget.executeQuery(queryTarget);

						} else {
							break;
						}

						compareData(rsSource, rsTarget, sourceName, tableName);

						if (reportStatus.equals("FAIL")) {
							/*data.put("" + i + "",
									new Object[] { "" + rsSource.getMetaData().getTableName(1) + "",
											"" + rsTarget.getMetaData().getTableName(1) + "", "FAIL",
											"" + "Check Comparision Result in C:\\DataValidator\\"
													+ LoginDialog.userName + "\\db2dbResults" });*/
							
							test.log(Status.FAIL, rsSource.getMetaData().getTableName(1) + "," + rsTarget.getMetaData().getTableName(1) + "------>Check Comparision Result in ---- >   C:\\DataValidator\\"+ LoginDialog.userName + "\\db2dbResults");

						} else if (reportStatus.equals("COUNT")) {
							/*data.put("" + i + "",
									new Object[] { "" + rsSource.getMetaData().getTableName(1) + "",
											"" + rsTarget.getMetaData().getTableName(1) + "", "FAIL",
											"Column Count Mismatch" });*/
							test.log(Status.FAIL, rsSource.getMetaData().getTableName(1) + "," + rsTarget.getMetaData().getTableName(1) + "Column Count Mismatch");


						} else if (reportStatus.equals("NULL")) {
							/*data.put("" + i + "", new Object[] { "" + rsSource.getMetaData().getTableName(1) + "",
									"" + rsTarget.getMetaData().getTableName(1) + "", "FAIL", "NO DATA in DB TABLE" });*/
							
							test.log(Status.FAIL, rsSource.getMetaData().getTableName(1) + "," + rsTarget.getMetaData().getTableName(1) + "NO DATA in DB TABLE");

						} else {
							/*data.put("" + i + "", new Object[] { "" + rsTarget.getMetaData().getTableName(1) + "",
									"" + rsTarget.getMetaData().getTableName(1) + "", "PASS", "" });*/
							test.log(Status.PASS, rsSource.getMetaData().getTableName(1) + "," + rsTarget.getMetaData().getTableName(1) + "Data Matches between Source and Target");

						}

					} catch (SQLException ex) {
						reportStatus = "SYNTAX";

						/*
						 * StackTraceElement[] elements = ex.getStackTrace(); int l = elements.length;
						 * 
						 * for (int q=0;q<=l;q++) { System.out.println(elements[q]); }
						 */

						// CompletedMessage =Error;
						ex.printStackTrace();

					} finally {
						try {
							if (db1conn != null && !db1conn.isClosed()) {
								db1conn.close();
							}
							if (db2conn != null && !db2conn.isClosed()) {
								db2conn.close();
							}

						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
				}else if(execute.toString().equalsIgnoreCase("No")) {
					reportStatus = "NOTEXECUTED";					
				}

				if (reportStatus.equals("SYNTAX")) {
				/*	data.put("" + i + "", new Object[] { "" + tableName + "",
							"", Error, "Error due to DB Connection" });*/
					test.log(Status.ERROR, " Error, \"Error due to DB Connection");
				} else if (reportStatus.equals("NOTEXECUTED")) {
					/*data.put("" + i + "",
							new Object[] { "" + tableName + "",
									"", "TEST SKIPPED", "NOT EXECUTED" });*/
					test = rep.createTest(tableName.toString());
					test.assignCategory(sourceName.toString());
					test.log(Status.SKIP, "Test Execution Skipped, Execution checked marked as NO");
				}else if(reportStatus.equals("BLANKVALUE")) {
					/*data.put("" + i + "",
							new Object[] { "" + tableName + "",
									"", "Please provide the required Parameters", "NOT EXECUTED- Please verify Test data sheet" });*/
					test = rep.createTest(tableName.toString());
					test.assignCategory(sourceName.toString());
					test.log(Status.SKIP, "Please provide the required Parameters\", \"NOT EXECUTED- Please verify Test data sheet");
				}

			}

			CompletedMessage = "Database Comparison Completed";

			wb.close();
			// reportStatus =null;

			// Excel Code

	/*		// Iterate over data and write to sheet
			Set<String> keyset = data.keySet();
			int rownum = 1;
			for (String key : keyset) {
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);
					if (obj instanceof String)
						cell.setCellValue((String) obj);
					else if (obj instanceof Integer)
						cell.setCellValue((Integer) obj);
				}
			}
			try {
				// Write the workbook in file system
				FileOutputStream out = new FileOutputStream(
						new File(Constants.DefaultDirectory + LoginDialog.userName + "\\Reports\\DB2DBReport.xlsx"));
				workbook.write(out);
				out.close();

				// System.out.println("DB Results printed successfully");

			} catch (Exception e) {
				e.printStackTrace();
			}*/
			// Excel code end
		} else if (dialogResult == JOptionPane.CLOSED_OPTION) {
			CompletedMessage = "Set the Test Data for DB to DB Comparison";
		}
		// ENd of Popup
		 test = null;  

	}

	private static void compareData(ResultSet rsSource, ResultSet rsTarget, Cell sourceName, Cell tableName)
			throws SQLException, IOException {
		
		int colCountSource = rsSource.getMetaData().getColumnCount();
		int colCountTarget = rsTarget.getMetaData().getColumnCount();
		reportStatus = "PASS";
		String fileWriteStatus= "";
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ssa");

		// String tableName = rsSource.getMetaData().getTableName(1);

		Files.createDirectories(Paths.get(Constants.DefaultDirectory + LoginDialog.userName + "\\db2dbResults\\"
				+ sourceName.toString() + "\\" + tableName.toString() + "\\"));
		File f1 = new File(
				Constants.DefaultDirectory + LoginDialog.userName + "\\db2dbResults\\" + sourceName.toString() + "\\"
						+ tableName.toString() + "\\" + tableName.toString() + "_" + ft.format(date) + ".txt");

		// File f1 = new
		// File(Constants.DefaultDirectory+LoginDialog.userName+"\\db2dbResults\\"+tableName+ft.format(date)+"_Results.txt");

		FileWriter w = new FileWriter(f1);
		BufferedWriter bw = new BufferedWriter(w);

		// Solution -1
		/*
		 * for (int i = 1; i <= colCountSource; i++) {
		 * 
		 * String sourceColumn = rsSource.getMetaData().getColumnName(i); for(int
		 * j=1;j<=colCountTarget;j++) { String targetColumn =
		 * rsTarget.getMetaData().getColumnName(j);
		 * 
		 * if(sourceColumn.equals(targetColumn)) { bw.write("Column Name --->" +
		 * targetColumn); bw.newLine(); while (rsSource.next() && rsTarget.next()) {
		 * String src = rsSource.getString(j); String trg = rsTarget.getString(j);
		 * 
		 * if(src == null) { src ="ABC"; }
		 * 
		 * if(trg == null) { trg ="ABC"; }
		 * 
		 * if (src.equals(trg)) {
		 * 
		 * 
		 * }else { bw.write("FAIL" + src + "---->" + trg); bw.newLine(); } }
		 * rsSource.first(); rsTarget.first();
		 * 
		 * System.out.println(sourceColumn + "--" + targetColumn); } }
		 * 
		 * 
		 * }
		 */
		// bw.close();

		// Solution-2
		String joined = null;
		ResultSetMetaData rsmd = rsTarget.getMetaData();

		if (colCountSource == colCountTarget) {
			StringJoiner joinHeader = new StringJoiner(",");
			joinHeader.add("RowNo");
			for (int i = 1; i <= colCountTarget; i++) {
				String header = rsmd.getColumnName(i);
				joinHeader.add(header);
				joined = joinHeader.toString();
			}

			bw.write(joined);
			bw.newLine();

			int j = 1;
			while (rsSource.next() && rsTarget.next()) {
				StringJoiner joinData = new StringJoiner(",");
				joinData.add("" + j + "");
				fileWriteStatus ="";
				for (int i = 1; i <= colCountTarget; i++) {

					String src = rsSource.getString(i);
					String trg = rsTarget.getString(i);

					if (src == null) {
						src = "";
					}
					if (trg == null) {
						trg = "";
					}

					if (src.equals(trg)) {
						String status = "";
						joinData.add(status);

					} else {
						String status = src + "/" + trg;
						// String status = "F";
						reportStatus = "FAIL";
						fileWriteStatus ="FAIL";
						joinData.add(status);
					}
					joined = joinData.toString();
					
					System.out.println(joined);


				}

				// Not Required for Data Strategy - Requested for Loan Core Team

				/*
				 * if(reportStatus =="FAIL") { joined = null; StringJoiner joinData1 = new
				 * StringJoiner(",");
				 * 
				 * for (int i = 1; i <= colCountTarget; i++) {
				 * 
				 * String src = rsSource.getString(i); String trg = rsTarget.getString(i);
				 * 
				 * 
				 * if(src == null) { src =""; } if(trg ==null) { trg=""; }
				 * 
				 * if (src.equals(trg)) { String status = "P-"+src; joinData1.add(status);
				 * 
				 * }else { String status = src + "/" + trg; // String status = "F"; //
				 * reportStatus ="FAIL"; joinData1.add(status); } joined = joinData1.toString();
				 * 
				 * } }
				 */
				// to write only the Failed rows in logs
				if(fileWriteStatus == "FAIL") {
					bw.write(joined);
					bw.newLine();
				}

				// to write Pass and failed records in logs
				
		//		bw.write(joined);
		//		bw.newLine();
				j++;
			}

		} else {
			reportStatus = "COUNT";

			// Component frame = null;
			// JOptionPane.showMessageDialog(frame,"Column Count Mismatch, Update Test Data
			// Sheet", "Column Count Warning", 1);
		}
		System.out.println("Completed");

		bw.close();
	}

	private static String connectingString(Cell serverName, Cell HostCertName, Cell dataBaseName, Cell portNo,
			Cell Authentication, Cell userName, Cell password) {

		String Url = null;
		String serverName1 = serverName.toString();
		String HostCertName1 = HostCertName.toString();
		String dataBaseName1 = dataBaseName.toString();
		String portNo1 = portNo.toString();
		if (portNo1.length() == 4 || portNo1 == "") {

		} else {
			portNo1 = portNo1.substring(0, portNo1.length() - 2);
		}

		String Auth = Authentication.toString();

		if (Auth.equalsIgnoreCase("WindowsAuth")) {

			/*
			 * Url = "jdbc:sqlserver://" + serverName1 + ".tcbna.net:" + portNo1 +
			 * ";databaseName=" + dataBaseName1 + ";integratedSecurity=true";
			 */
			Url = "jdbc:sqlserver://" + serverName1 + HostCertName1 + ":" + portNo1 + ";databaseName=" + dataBaseName1
					+ ";integratedSecurity=true";

		} else if (Auth.equalsIgnoreCase("SQLAuthwithPort")) {
			String userName1 = userName.toString();
			String password1 = password.toString();

			/*
			 * Url = "jdbc:sqlserver://" + serverName1 + ".tcbna.net:" + portNo1 +
			 * ";databaseName=" + dataBaseName1 + ";user=" + userName1 + "password=" +
			 * password1;
			 */
			Url = "jdbc:sqlserver://" + serverName1 + HostCertName1 + ":" + portNo1 + ";databaseName=" + dataBaseName1
					+ ";user=" + userName1 + "password=" + password1;

		} else if (Auth.equalsIgnoreCase("SQLAuthWithoutPort")) {
			String userName1 = userName.toString();
			String password1 = password.toString();
			/*
			 * Url = "jdbc:sqlserver://" + serverName1 + ";databaseName=" + dataBaseName1 +
			 * ";user=" + userName1 + ";password=" + password1;
			 */
			Url = "jdbc:sqlserver://" + serverName1 + HostCertName1 + ";databaseName=" + dataBaseName1 + ";user="
					+ userName1 + ";password=" + password1;
		}
		return Url;
	}

	private static SQLServerDataSource ActiveDirectory(Cell serverName, Cell dataBaseName, Cell HostCertName) {
		String serverName1 = serverName.toString();
		String HostCertName1 = HostCertName.toString();
		String dataBaseName1 = dataBaseName.toString();

		SQLServerDataSource ds = new SQLServerDataSource();

		ds.setServerName("" + serverName1 + HostCertName1 + "");
		ds.setDatabaseName("" + dataBaseName1 + "");
		ds.setAuthentication("ActiveDirectoryIntegrated");
		ds.setHostNameInCertificate("*.database.windows.net");

		return ds;
	}

	/*static void shadeAlt(Sheet sheet) {
		SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
		// Condition 1: Formula Is =A2=A1 (White Font)
		ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule("MOD(ROW(),2)");
		PatternFormatting fill1 = rule1.createPatternFormatting();
		fill1.setFillBackgroundColor(IndexedColors.LIGHT_GREEN.index);
		fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
		CellRangeAddress[] regions = { CellRangeAddress.valueOf("A1:Z1000") };
		sheetCF.addConditionalFormatting(regions, rule1);
	}*/

}
