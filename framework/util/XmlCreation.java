package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;

/**
 * Pretty-prints xml, supplied as a string.
 * <p/>
 * eg.
 * <code>
 * String formattedXml = new XmlFormatter().format("<tag><nested>hello</nested></tag>");
 * </code>
 */
public class XmlCreation {

    public static void main(String[] args) {
    	//applicationXml();
    	//testSuitesXml("Salesforce");
    	//testDataXml();
    	//emailXml();
    	//drive();
    	
    	System.out.println(System.getProperty("user.dir"));
    }
       
    public static void applicationXml() {
    	try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            // students root element
            Element rootElement = doc.createElement("Applications");
            doc.appendChild(rootElement);
            
            // WebApplication elements
         
                Element webApplication = doc.createElement("WebApplications");                
                Element project = doc.createElement("Project");
                project.setTextContent("Salesforce");
                Element uRL = doc.createElement("SalesforceURL");
                uRL.setTextContent("https://www.google.com");
                Element browser = doc.createElement("SalesforceBrowser");
                browser.setTextContent("Chrome");                
                
                webApplication.appendChild(project);
                webApplication.appendChild(uRL);
                webApplication.appendChild(browser);
                rootElement.appendChild(webApplication);
                
             // WebServices elements
                
                Element webservices = doc.createElement("WebServices");                
                Element apiProject = doc.createElement("Project");
                apiProject.setTextContent("API_ProjectName");                             
                webservices.appendChild(apiProject);
                rootElement.appendChild(webservices);  
                
                
            // Database elements
                   
                   Element database = doc.createElement("Database");                
                   Element dbProject = doc.createElement("Project");
                   dbProject.setTextContent("DB_ProjectName");                                
                   database.appendChild(dbProject);
                   rootElement.appendChild(database);   
                   
        // DesktopApplications elements
                   
                   Element DesktopApplications = doc.createElement("DesktopApplications");                
                   Element desktopProject = doc.createElement("Project");
                   desktopProject.setTextContent("Desktop_ProjectName");                                
                   DesktopApplications.appendChild(desktopProject);
                   rootElement.appendChild(DesktopApplications);   
                   
                   
        // MobileApplications elements
                   
                   Element MobileApplications = doc.createElement("MobileApplications");                
                   Element mobileProject = doc.createElement("Project");
                   mobileProject.setTextContent("Mobile_ProjectName");                                
                   MobileApplications.appendChild(mobileProject);
                   rootElement.appendChild(MobileApplications);   
                
       
            
            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Winium\\Applications.xml"));
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Beautify the format of the resulted XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void testSuitesXml(String projectName) {
    	try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            // students root element
            Element rootElement = doc.createElement(projectName);
            doc.appendChild(rootElement);
            
            // WebApplication elements
         
                Element testCaseName = doc.createElement("TestCaseName");                
                Element execute = doc.createElement("execute");
                execute.setTextContent("Yes");                
                testCaseName.appendChild(execute);
                rootElement.appendChild(testCaseName);
                
            
            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Winium\\testSuites.xml"));
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Beautify the format of the resulted XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void testDataXml() {
    	try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            // students root element
            Element rootElement = doc.createElement("TestData");
            doc.appendChild(rootElement);
            
            // WebApplication elements
         
                Element testCaseName = doc.createElement("TestCaseName");                
                Element param1 = doc.createElement("Param");
                param1.setTextContent("Parameter to Send to Method");  
                Element param2 = doc.createElement("Param");
                param2.setTextContent("Parameter to Send to Method");
                Element fieldName = doc.createElement("FieldName");
                fieldName.setTextContent("Application Field Name");   
                testCaseName.appendChild(param1);
                testCaseName.appendChild(param2);
                testCaseName.appendChild(fieldName);
                rootElement.appendChild(testCaseName);
                
            
            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Winium\\testData.xml"));
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Beautify the format of the resulted XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void emailXml() {
    	try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Email");
            doc.appendChild(rootElement);
            
         
                Element Self = doc.createElement("Self");                
                Element selfEmail = doc.createElement("email");
                selfEmail.setTextContent("Firstname.LastName");  
                Element Team = doc.createElement("Team");
                Element teamEmail = doc.createElement("email");
                teamEmail.setTextContent("Firstname.LastName");  

                Self.appendChild(selfEmail);
                Team.appendChild(teamEmail);
                rootElement.appendChild(Self);
                rootElement.appendChild(Team);
                
            
            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Winium\\email.xml"));
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Beautify the format of the resulted XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
       
    public static void drive() {
    	
    	File[] drives = File.listRoots();
    	if (drives != null && drives.length > 0) {
    	    for (File aDrive : drives) {
    	        System.out.println(aDrive);
    	    }
    	}
    }
}