package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import authentication.LoginDialog;
import core.ui.UIDriver;


public class GenericLibrary extends UIDriver {
	public GenericLibrary(ExtentTest test) {
		super(test);
	}

	public static WebDriver driver;
	public static WebElement element;
	public static boolean elementCount;
	public static boolean elementVisible;
	public static WebDriverWait wait;


	static JFrame frame;
	
	 public static WebDriver getDriver() {
	        return driver;
	    }
	 
	 public static WebElement getElement() {
	        return element;
	    }
	 
	 public static void driverCopytoLocal() {
		 
		 try {
			 File folder = new File(Constants.ServerDriversPath);
				File[] listOfFiles = folder.listFiles();
				
				for (int fileCount = 0; fileCount < listOfFiles.length; fileCount++) {			
					String driverName = listOfFiles[fileCount].getName();			
					File from = listOfFiles[fileCount];
					File to = new File(Constants.LocalDriversPath+"\\"+driverName);			
					if(!to.exists()) {
				        FileUtils.copyFile(from, to);			
					}
				}	 
		 }catch(Exception e) {
			 
		 }
		 
	 }
	 
	 public static String packageIdentifier(String projectName, String className) {
		 String packages = null;
		// try (Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir")+"\\"+ProjectName.toLowerCase()+"\\tests"))) {
		 
		 String clasPath = "\\\\OPSCIFS1\\group\\$ Enterprise Initiative Delivery\\Private\\QA\\EnterpriseInitiativeAutomation\\TCB.EID.Automation\\EnterpriseInitiativeDelivery.Automation";
		 try (Stream<Path> walk = Files.walk(Paths.get(clasPath+"\\"+projectName.toLowerCase()+"\\tests"))) {
				List<String> result = walk.map(x -> x.toString()).filter(f -> f.endsWith(className+".java")).collect(Collectors.toList());

				//result.forEach(System.out::println);				
				//packages=result.get(0).replace(System.getProperty("user.dir")+"\\"+ProjectName.toLowerCase()+"\\","");  
				packages=result.get(0).replace(clasPath+"\\"+projectName.toLowerCase()+"\\","");
				packages=packages.replace("\\","."); 
				packages = packages.replace(".java","");

			} catch (IOException e) {
				e.printStackTrace();
			}
		return packages;		 
	 }
	 	 
	public static String randomNumber() {
		Date date = new Date();
		  SimpleDateFormat formatOfDate =
			   //     new SimpleDateFormat (" E yyyy.MM.dd 'at' hh:mm:ss a zzz");
			        new SimpleDateFormat ("MM-dd-yyyy-hh_mm_ss_a");	  
		  		String randomNumber = formatOfDate.format(date);
			   // System.out.println(randomNumber);
				return randomNumber;
	}
	
	public static String getScreenshot(String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = Constants.Screenshots + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		test.info(screenshotName, MediaEntityBuilder.createScreenCaptureFromPath(destination).build());
		return destination;

	}

	public static String propLoad(String objProperty) throws IOException {
		String locatorValue;
		Properties prop = new Properties();

		if (objProperty.startsWith("TestConfig")) {
			InputStream is = new FileInputStream(Constants.CONFIG_PATH);
			prop.load(is);
			locatorValue = prop.getProperty(objProperty);

		} else if (objProperty.startsWith("TestObj")) {
			InputStream is = new FileInputStream(Constants.CONFIG_PATH);
			prop.load(is);
			locatorValue = prop.getProperty(objProperty);

		} else if (objProperty.startsWith("DevConfig")) {
			InputStream is = new FileInputStream(Constants.CONFIG_PATH);
			prop.load(is);
			locatorValue = prop.getProperty(objProperty);

		} else if (objProperty.startsWith("DevObj")) {
			InputStream is = new FileInputStream(Constants.CONFIG_PATH);
			prop.load(is);
			locatorValue = prop.getProperty(objProperty);

		} else if (objProperty.startsWith("ProdConfig")) {
			InputStream is = new FileInputStream(Constants.CONFIG_PATH);
			prop.load(is);
			locatorValue = prop.getProperty(objProperty);

		} else if (objProperty.startsWith("ProdObj")) {
			InputStream is = new FileInputStream(Constants.CONFIG_PATH);
			prop.load(is);
			locatorValue = prop.getProperty(objProperty);

		} else {
			locatorValue = objProperty;

		}
		return locatorValue;

	}

	public static String getObjProp(String objProperty) {
		String locatorValue = null;
		if(objProperty.startsWith("xpath_")) {
			 locatorValue = objProperty.substring(6);				
		}else if(objProperty.startsWith("id_")) {
			 locatorValue = objProperty.substring(3);				
		}else if(objProperty.startsWith("name_")) {
			 locatorValue = objProperty.substring(5);			
		}else if(objProperty.startsWith("css_")) {
			 locatorValue = objProperty.substring(4);			
		}else if(objProperty.startsWith("class_")) {
			 locatorValue = objProperty.substring(6);			
		}else {
			locatorValue = objProperty;
		}
		return locatorValue;
		
	}
	
	@SuppressWarnings("deprecation")
	public static void openBrowser(String browserType) throws IOException

	{

		try {
			//String browserName = propLoad(browserType);

			if (browserType.equals("Mozilla")) {
				driver = new FirefoxDriver();
				test.assignCategory(browserType);

			} else if (browserType.equals("Chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				String exePath = Constants.LocalDriversPath+"\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", exePath);
				driver = new ChromeDriver(options);
				test.assignCategory(browserType);

			} else if (browserType.equals("IE")) {
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability("ignoreZoomSetting", true);
				File file = new File(Constants.LocalDriversPath+"\\IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver(caps);
				test.assignCategory(browserType);


			} else if (browserType.equals("Safari")) {
				/*driver = new SafariDriver();
				test.assignCategory(browserType);
*/
				//String userProfile= "C:\\Users\\rasapp\\AppData\\Local\\Google\\Chrome\\User Data\\";
				String user = LoginDialog.getUsername();
				String userProfile= "C:\\Users\\"+user+"\\AppData\\Local\\Google\\Chrome\\User Data\\";

				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				options.addArguments("--disable-popup-blocking");
				options.addArguments("--disable-web-security");
				//options.addArguments("--window-size=1920,1080");
		        options.addArguments("--proxy-server='direct://'");
		        options.addArguments("--proxy-bypass-list=*");
		       // options.addArguments("--start-maximized");
		        options.addArguments("--disable-gpu");
		        options.addArguments("--disable-dev-shm-usage");
		        options.addArguments("--no-sandbox");
		        options.addArguments("--ignore-certificate-errors");
		        options.addArguments("--allow-insecure-localhost");
		        options.addArguments("--disable-session-crashed-bubble");
				String exePath = Constants.LocalDriversPath+"\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", exePath);
				options.addArguments("--user-data-dir="+userProfile);
		        options.addArguments("--user-data-dir=c:\\foo");
			    options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");

				driver = new ChromeDriver(options);
				test.assignCategory(browserType);
				
			} else if (browserType.equals("ChromeHeadless")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
			    options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
				String exePath = Constants.LocalDriversPath+"\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", exePath);
				driver = new ChromeDriver(options);
				test.assignCategory(browserType);


			}else if (browserType.equals("ChromeUserProfile")) {
				//String userProfile= "C:\\Users\\rasapp\\AppData\\Local\\Google\\Chrome\\User Data\\";
				String user = LoginDialog.getUsername();
				String userProfile= "C:\\Users\\"+user+"\\AppData\\Local\\Google\\Chrome\\User Data\\";

				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				options.addArguments("--disable-popup-blocking");
				options.addArguments("--disable-web-security");
				//options.addArguments("--window-size=1920,1080");
		        options.addArguments("--proxy-server='direct://'");
		        options.addArguments("--proxy-bypass-list=*");
		       // options.addArguments("--start-maximized");
		        options.addArguments("--disable-gpu");
		        options.addArguments("--disable-dev-shm-usage");
		        options.addArguments("--no-sandbox");
		        options.addArguments("--ignore-certificate-errors");
		        options.addArguments("--allow-insecure-localhost");
		        options.addArguments("--disable-session-crashed-bubble");
				String exePath = Constants.LocalDriversPath+"\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", exePath);
				options.addArguments("--user-data-dir="+userProfile);
		        options.addArguments("--user-data-dir=c:\\foo");
				driver = new ChromeDriver(options);
				test.assignCategory(browserType);
			} else {
				throw new Exception();
			}
		} catch (Exception OpenBrowserException) {
			test.log(Status.FAIL, OpenBrowserException);
		}
	}

	public static void navigate(String urlKey) throws Exception {
		String url = null;
		if(urlKey.startsWith("TestConfig")) {
			 url = propLoad(urlKey);

		}else {
			url =urlKey;
		}

		try {
			driver.get(url);
			test.log(Status.INFO, "URL navigation successful-->" + url);
		} catch (Exception navigate) {
			test.log(Status.FAIL, navigate);
		}
	}

	public static void maximizeWindow() {
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			test.log(Status.FAIL, e);

		}

	}

	public static void click(String objProperty) throws Exception {
		// String locatorValue = propLoad(objProperty);
		try {
			//waitUntilElementIsPresent(objProperty);
			
			element = getElement(objProperty);
			element.click();
			/*if (isElementPresent(objProperty) == true) {
				
				// element.SendKeys(Keys.Enter);
				// return Constants.PASS;
			} else {
				throw new Exception();
			}*/
		} catch (Exception click) {
			test.log(Status.FAIL, "Element Not found" + click);
			// Assert.Fail("Element Not Found");
			// return Constants.FAIL;
		}
	}

	public static void input(String objProperty, String testCaseID,String tagName,String applicationName) throws Exception {
		try {
			String InputValue;
			if(tagName.equals("")) {
				 InputValue = propLoad(testCaseID);
			}else {
				 InputValue= testDataFromXml(testCaseID,tagName,applicationName);

			}			
		//	waitUntilElementIsPresent(objProperty);
	//		if (isElementPresent(objProperty) == true) {
				WebElement element = getElement(objProperty);
				element.sendKeys(InputValue);
	//		}
		}

		catch (Exception inputField) {
			test.log(Status.FAIL, objProperty);

		}
	}

	public static WebElement getElement(String objProperty) throws Exception {
		try {
		
			waitUntilElementIsPresent(objProperty);
			if (objProperty.startsWith("id_")) {
				objProperty = objProperty.substring(3);
				element = driver.findElement(By.id(objProperty));
			} else if (objProperty.startsWith("xpath_")) {
				objProperty = objProperty.substring(6);	
				element = driver.findElement(By.xpath(objProperty));
			} else if (objProperty.startsWith("name_")) {
				objProperty = objProperty.substring(5);
				element = driver.findElement(By.name(objProperty));
			} else if (objProperty.startsWith("css_")) {
				objProperty = objProperty.substring(4);	
				element = driver.findElement(By.cssSelector(objProperty));
			} else if (objProperty.startsWith("class_")) {
				objProperty = objProperty.substring(6);	
				element = driver.findElement(By.className(objProperty));
			} else {
				element = driver.findElement(By.xpath(objProperty));
			}
		} catch (Exception getElement) {
			//test.log(Status.FAIL, getElement);
		}

		return element;
	}

	public static Boolean isElementPresent(String objProperty) throws Exception {

		try {
			//waitUntilElementIsPresent(objProperty);
			if (objProperty.startsWith("id_")) {
				objProperty = objProperty.substring(3);
				elementCount = driver.findElements(By.id(objProperty)).size()>0;
			} else if (objProperty.startsWith("xpath_")) {
				objProperty = objProperty.substring(6);	
				elementCount = driver.findElements(By.xpath(objProperty)).size()>0;
			} else if (objProperty.startsWith("name_")) {
				objProperty = objProperty.substring(5);
				elementCount = driver.findElements(By.name(objProperty)).size()>0;
			} else if (objProperty.startsWith("css_")) {
				objProperty = objProperty.substring(4);	
				elementCount = driver.findElements(By.cssSelector(objProperty)).size()>0;
			} else if (objProperty.startsWith("class_")) {
				objProperty = objProperty.substring(6);	
				elementCount = driver.findElements(By.className(objProperty)).size()>0;
			} else {
				elementCount = driver.findElements(By.xpath(objProperty)).size()>0;
			}

			if (elementCount = true) {
				return true;
			} else {
				return false;
			}

		} catch (Exception elementPresent) {
			test.log(Status.FAIL, elementPresent);
			return false;
		}

	}

	public static List<WebElement> getElementCount(String objProperty) throws Exception {

		List<WebElement> count = null;
		try {
			//waitUntilElementIsPresent(objProperty);
			if (objProperty.startsWith("id_")) {
				objProperty = objProperty.substring(3);
				count = driver.findElements(By.id(objProperty));
			} else if (objProperty.startsWith("xpath_")) {
				objProperty = objProperty.substring(6);	
				count = driver.findElements(By.xpath(objProperty));
			} else if (objProperty.startsWith("name_")) {
				objProperty = objProperty.substring(5);
				count = driver.findElements(By.name(objProperty));
			} else if (objProperty.startsWith("css_")) {
				objProperty = objProperty.substring(4);	
				count = driver.findElements(By.cssSelector(objProperty));
			} else if (objProperty.startsWith("class_")) {
				objProperty = objProperty.substring(6);	
				count = driver.findElements(By.className(objProperty));
			} else {
				count = driver.findElements(By.xpath(objProperty));
			}

			return count;

		} catch (Exception elementPresent) {
			test.log(Status.FAIL, elementPresent);
			return count;
		}

	}
	
	public static Boolean isElementVisible(String objProperty) throws Exception {

		try {
			if (objProperty.startsWith("id_")) {
				objProperty = objProperty.substring(3);
				elementVisible = driver.findElement(By.id(objProperty)).isDisplayed();
			} else if (objProperty.startsWith("xpath_")) {
				objProperty = objProperty.substring(6);	
				elementVisible = driver.findElement(By.xpath(objProperty)).isDisplayed();
			} else if (objProperty.startsWith("name_")) {
				objProperty = objProperty.substring(5);
				elementVisible = driver.findElement(By.name(objProperty)).isDisplayed();
			} else if (objProperty.startsWith("css_")) {
				objProperty = objProperty.substring(4);	
				elementVisible = driver.findElement(By.cssSelector(objProperty)).isDisplayed();
			} else if (objProperty.startsWith("class_")) {
				objProperty = objProperty.substring(6);	
				elementVisible = driver.findElement(By.className(objProperty)).isDisplayed();
			} else {
				elementVisible = driver.findElement(By.xpath(objProperty)).isDisplayed();
			}

			if (elementVisible == true) {
				return true;
			} else {
				return false;
			}
		} catch (Exception elementVisible) {
			test.log(Status.FAIL, elementVisible);
			return false;
		}

	}

	public static SQLServerDataSource activeDirectory(String serverName, String dataBaseName, String hostCertName) throws IOException {

		
			String azureServerName = propLoad(serverName);
			String azureHostCertName = propLoad(hostCertName);
			String azureDatabaseName = propLoad(dataBaseName);

			SQLServerDataSource ds = new SQLServerDataSource();

			ds.setServerName("" + azureServerName + azureHostCertName + "");
			ds.setDatabaseName("" + azureDatabaseName + "");
			ds.setAuthentication("ActiveDirectoryIntegrated");
			ds.setHostNameInCertificate("*.database.windows.net");

			return ds;	
		
	}

	public static String connectingString(String serverName, String hostCertName, String dataBaseName, String portNo,
			String authentication, String userName, String password) {

		String Url = null;
		String serverName1 = serverName.toString();
		String HostCertName1 = hostCertName.toString();
		String dataBaseName1 = dataBaseName.toString();
		String portNo1 = portNo.toString();
		if (portNo1.length() == 4 || portNo1 == "") {

		} else {
			portNo1 = portNo1.substring(0, portNo1.length() - 2);
		}

		String Auth = authentication.toString();

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

	public static void closeBrowser() throws Exception {
		try {
			//driver.close();
			driver.quit();;
		} catch (Exception closeBrowser) {
			test.log(Status.FAIL, closeBrowser);
		}
	}

	public static Boolean waitUntilElementIsPresent(String objProperty) throws Exception {

		wait = new WebDriverWait(driver, 25);
		try {
			
			if (objProperty.startsWith("id_")) {
				objProperty = objProperty.substring(3);	
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objProperty)));
			} else if (objProperty.startsWith("xpath_")) {
				objProperty = objProperty.substring(6);	
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objProperty)));
			} else if (objProperty.startsWith("name_")) {
				objProperty = objProperty.substring(5);
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(objProperty)));
			} else if (objProperty.startsWith("css_")) {
				objProperty = objProperty.substring(4);
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(objProperty)));
			} else if (objProperty.startsWith("class_")) {
				objProperty = objProperty.substring(6);
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(objProperty)));
			} else {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objProperty)));
			}

			if (element != null) {
				return true;

			} else {
				return false;

			}
		} catch (Exception waitForElement) {
			System.out.println("Exception from Wait");
			test.log(Status.FAIL, waitForElement);
			//Assert.assertTrue(true);
			GenericLibrary.closeBrowser();
			return false;
		}

	}

	public static String testDataFromXml(String testCaseID, String tagName,String applicationName) {
		String testData = null;
		try {

			File inputData = new File(Constants.TESTDATAXML_PATH(applicationName));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputData);
			doc.getDocumentElement().normalize();

		//	System.out.println("root of xml file" + doc.getDocumentElement().getNodeName());
			NodeList nodes = doc.getElementsByTagName(testCaseID);
		//	System.out.println("==========================");

			for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
        	testData = getValue(tagName, element);
			}
			}
			} catch (Exception ex) {
			ex.printStackTrace();
			}
		return testData;
	}
	
	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
		}
		
	public static void verifyText(String objProperty, String testCaseID, String tagName,String randomNumber,String applicationName) throws Exception
    {
		try {
			//waitUntilElementIsPresent(objProperty);			
	        element = getElement(objProperty);
	        String actualtext;

	         actualtext = element.getText();
	         
	         System.out.println(actualtext);
	         
	        String expectedResult= testDataFromXml(testCaseID,tagName,applicationName);
	        if(randomNumber.equals("")) {
	        	
	        }else {
		        expectedResult=expectedResult+"_"+randomNumber;

	        }
	        
	        if (actualtext.equals(expectedResult))
	        {
	            test.log(Status.PASS,"Expected Results -->"+ expectedResult+ " is same as -- Actual Result-->" + actualtext);
	         //   return Constants.PASS;
	        }
	        else
	        {
	            test.log(Status.FAIL, "Expected Results -->" + expectedResult + " is Not same as -- Actual Result-->" + actualtext);
	         //   return Constants.FAIL;
	        }
		}catch (Exception verifyText) {
			System.out.println("Exception from VerifyText");
            test.log(Status.ERROR, verifyText);

		}
        
    }
	
	public static String getText(String objProperty) throws Exception {
		//waitUntilElementIsPresent(objProperty);			
        element = getElement(objProperty);
        String actualtext;
        actualtext = element.getText();		
		return actualtext;
		
	}
	
	public static void scrollTo(String objProperty) throws Exception {
		
       /* element = getElement(objProperty);*/
	//	Actions actions = new Actions(driver);

	//	actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();

		for (int second = 0;; second++) {

			if(second >=25){

        break;

    }

    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,25)", ""); //y axis value is set to'400', which can be altered

    Thread.sleep(500);

		}
		
		
		Thread.sleep(5000);
	}
	
	public static List<String> getElementsText(String objProperty) throws Exception
    {
		waitUntilElementIsPresent(objProperty);
        List<WebElement> element = null;
		ArrayList<String> elementList = new ArrayList<String>();
        if (objProperty.startsWith("id_"))
        {
			objProperty = objProperty.substring(3);
            element = driver.findElements(By.id(objProperty));
            for (int i = 0; i < element.size(); i++)
            {
                elementList.add(element.get(i).getText());
            }
            return elementList;
        }
        else if (objProperty.startsWith("xpath_"))
        {
			objProperty = objProperty.substring(6);
            element = driver.findElements(By.xpath(objProperty));
            for (int i = 0; i < element.size(); i++)
            {
                elementList.add(element.get(i).getText());
            }
            return elementList;
        }
        else if (objProperty.startsWith("name_"))
        {
			objProperty = objProperty.substring(5);	
            element = driver.findElements(By.name(objProperty));
            for (int i = 0; i < element.size(); i++)
            {
                elementList.add(element.get(i).getText());
            }
            return elementList;
        }else if (objProperty.startsWith("css_"))
        {
			objProperty = objProperty.substring(4);	
            element = driver.findElements(By.name(objProperty));
            for (int i = 0; i < element.size(); i++)
            {
                elementList.add(element.get(i).getText());
            }
            return elementList;
        }else if (objProperty.startsWith("class_"))
        {
			objProperty = objProperty.substring(6);	
            element = driver.findElements(By.name(objProperty));
            for (int i = 0; i < element.size(); i++)
            {
                elementList.add(element.get(i).getText());
            }
            return elementList;
        }        
        else
        {
            element = driver.findElements(By.xpath(objProperty));
            for (int i = 0; i < element.size(); i++)
            {
                elementList.add(element.get(i).getText());
            }
            return elementList;
        }
        
   //     IList<IWebElement> element1 = null;  -- C-sharp
   //     List<WebElement> element2 = null;  -- Java
        
        
        


    }
		
	public static void PASS(String logMessage) {
		test.log(Status.PASS, logMessage);
	}
	
	public static void FAIL(String logMessage) {
		test.log(Status.FAIL, logMessage);
	}
	
	public static void INFO(String logMessage) {
		test.log(Status.INFO, logMessage);
	}
}
