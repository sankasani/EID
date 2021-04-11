package reporting;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import common.Constants;

public class EmailReport {
	
	// emailReports Values from Main -- "Self","Team"
	public static void email(String emailReports) throws ParserConfigurationException, SAXException, IOException {
		
		String toList ="";
		//String emailDomain = "@texascapitalbank.com,";
		String emailDomain = Constants.emailDomain;
		
		//Getting emailID's from Email.xml
		
		File emailXml = new File(Constants.Email+"\\Email.xml");
		
		if(emailXml.exists()) {
			
		
	   	DocumentBuilderFactory emailFactory = DocumentBuilderFactory.newInstance();
	   	DocumentBuilder emailBuilder = emailFactory.newDocumentBuilder();
	   	Document emaildoc = emailBuilder.parse(emailXml);			
	   	emaildoc.getDocumentElement().normalize();	   	

		
	    for (int j=1;j<emaildoc.getDocumentElement().getChildNodes().getLength();j++) {
	          if (j % 2 != 0){
	  		String nodeName = emaildoc.getDocumentElement().getChildNodes().item(j).getNodeName();	
	  		
	  		if(emailReports.equals(nodeName)) {				
					if(emailReports.equals("")) {					
					}else {					
						for (int l=1;l<emaildoc.getDocumentElement().getChildNodes().item(j).getChildNodes().getLength();l++) {
							 if (l % 2 != 0){
									String childNodeNametc = emaildoc.getDocumentElement().getChildNodes().item(j).getChildNodes().item(l).getNodeName();
									if(childNodeNametc.equals("email")) {
										String parameters = emaildoc.getDocumentElement().getChildNodes().item(j).getChildNodes().item(l).getTextContent();														
										toList = toList + parameters + emailDomain;

									}
									
									
							 }
						}
		  		
					}
		  		
			   

		          }
	  		}
	  		
	  		
	      }
	   	
		
	    toList = toList.substring(0, toList.length() - 1);


		
		
		// Recipient's email ID needs to be mentioned.
		// String to ="Sachin.Moghe@texascapitalbank.com,Lata.Bolar@texascapitalbank.com,Sreekanth.Gutlapalli@texascapitalbank.com,Ramakrishna.Munaga@texascapitalbank.com,Jitendra.Kunisetty@texascapitalbank.com,balaji.kayam@texascapitalbank.com";
		//String to = "ravikanth.sappidi@texascapitalbank.com,Jitendra.Kunisetty@texascapitalbank.com";

		// Sender's email ID needs to be mentioned
		String from = "ravikanth.sappidi@texascapitalbank.com";

		final String username = "rasapp";
		final String password = "7GjAdMch1";
		String host = "exchange.tcbna.net";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
		//	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toList));
			message.setSubject("Enterprise Initiative Delivery Automated Test Results");
			BodyPart messageBodyPart = new MimeBodyPart();
			
			
			File filename = null;
			File dir = new File(Constants.Results);
		    FileFilter fileFilter = new WildcardFileFilter("*." + "html");
		    File[] files = dir.listFiles(fileFilter);

		    if (files.length > 0) {
		        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		        filename = files[0];
		    }

	        URL url = filename.toURI().toURL();
	        	
			

			// message contains HTML markups
			String message1 = "Greetings Team!<br>";
		//	String message1 = "<i>Greetings Team!</i><br>";

			message1 += "<b></b><br>";
/*			message1 += "<b>Please find attached test results for Enterprize Initiative Delivery POC</b><br>";
			message1 += "<b>This is an auto generated email. Please do not reply to this mail. This mailbox is not monitored</b><br>";
			message1 += "<b>For more information, please reach out to automation team!</b><br>";
			message1 += "<b></b><br>";
			message1 += "<b></b><br>";
			message1 += "<b>Thanks & Regards,</b><br>";
			message1 += "<font color=black>Automation Team</font>";*/
			
			message1 += "Please find below the test results link for Enterprise Initiative Delivery POC.<br>";
			message1 += "This is an auto generated email. Please do not reply to this mail.This mailbox is not monitored.<br>";
			message1 += "For more information, please reach out to automation team.<br>";
			message1 += "<b></b><br>";
			message1 += "<b></b><br>";
			message1 += "Thanks & Regards,<br>";
			message1 += "<font color=black>Automation Team</font><br>";
			message1 += "<b></b><br>";
			message1 += "<b></b><br>";
			message1 += "<b></b><br>";
			message1 += "<a href="+url+">Enterprize Initiative Delivery Test Results</a><br>";
			

			//message1 += ""+url+"";

			// messageBodyPart.setText("This is message body");
			messageBodyPart.setContent(message1, "text/html");
			// messageBodyPart.setText(message1);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			
			
	/*		File filename = null;
			File dir = new File(Constants.Results);
		    FileFilter fileFilter = new WildcardFileFilter("*." + "html");
		    File[] files = dir.listFiles(fileFilter);

		    if (files.length > 0) {
		        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		        filename = files[0];
		    }
*/
			
			
			// commented code for attachment
		/*	DataSource source = new FileDataSource(filename); 
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(source.getName());
			multipart.addBodyPart(messageBodyPart);*/

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		}
	}
}