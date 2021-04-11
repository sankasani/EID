package app.UI;

import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.text.DateFormatter;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.jdesktop.swingx.JXDatePicker;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import authentication.LoginDialog;
import common.Constants;
import common.GenericLibrary;
import core.api.ProjectTestRunner;
import core.database.DbToDbComparision;
import core.ui.UIDriver;
import reporting.EmailReport;
import reporting.ExtentManager;
import reporting.ReportCustomization;

import java.awt.SystemColor;
import java.awt.Component;
import java.awt.Desktop;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;

public class MainWindow  {

	private JFrame TCB_AUTO_UI_FRAME;
	public static ExtentReports rep = null;	
	public String emailReports="No Email";
	public String browserSelected;
	public String applicationType;
	ArrayList<String> browserType = new ArrayList<String>();
	ArrayList<String> emailList = new ArrayList<String>();
    ArrayList<String> selectedProjects = new ArrayList<String>(); 
	ArrayList<String> testingTypes = new ArrayList<String>();	
    final DefaultListModel<CheckListItem> model = new DefaultListModel<CheckListItem>();
    final DefaultListModel<CheckListItem> model1 = new DefaultListModel<CheckListItem>();

    final Timer timerval;
    public int totalCount=0;
    public int tcCount=0;

    
    File applicationXml = new File(Constants.ApplicationXMl_Path);
   	DocumentBuilderFactory appdbFactory = DocumentBuilderFactory.newInstance();
   	DocumentBuilder appdBuilder = appdbFactory.newDocumentBuilder();
   	Document appdoc = appdBuilder.parse(applicationXml);	

	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public MainWindow(String appType,String browser) throws SAXException, IOException, ParserConfigurationException {
	  
	 	long startTime = System.currentTimeMillis();

		applicationType=appType;
	   if(appType.equals("Admin")) {
 			applicationType="WebApplications";
	   }
		System.out.println(applicationType);

	   browserSelected= browser;		
		TCB_AUTO_UI_FRAME = new JFrame();
		TCB_AUTO_UI_FRAME.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() != Frame.MAXIMIZED_BOTH) {  
					TCB_AUTO_UI_FRAME.setBounds(150,100,1250,650);
                }
			}
		});
		ImageIcon img = new ImageIcon(Constants.DefaultDirectory+"\\framework\\images\\TCB_fav_Icon.png");
		
	    System.out.println("1st-->"+System.currentTimeMillis());


		TCB_AUTO_UI_FRAME.getContentPane().setBackground(SystemColor.activeCaption);
		TCB_AUTO_UI_FRAME.setBackground(new Color(135, 206, 250));
		TCB_AUTO_UI_FRAME.setFont(new Font("Arial", Font.PLAIN, 12));
		TCB_AUTO_UI_FRAME.setTitle("Texas Capital Bank");
		//TCB_AUTO_UI_FRAME.setBounds(100,50,1150,650);
		TCB_AUTO_UI_FRAME.setBounds(150,100,1250,650);

		TCB_AUTO_UI_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TCB_AUTO_UI_FRAME.setIconImage(img.getImage());

		JButton btnNewButton_1 = new JButton("Execute");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		JProgressBar progressBar = new JProgressBar();
		
	    System.out.println("2t-->"+System.currentTimeMillis());

		
// Project List from Application.xml based upon Application selection from UI	
		TestCaseSelector dual = new TestCaseSelector();
		 dual.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		 TestSuiteSelector dualListBox2 = new TestSuiteSelector();
		 dualListBox2.setFont(new Font("Segoe UI", Font.PLAIN, 9));
				
	   	appdoc.getDocumentElement().normalize();	   	
	   	
        JList<CheckListItem> list=new JList<CheckListItem>(model);
        list.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    for (int j=1;j<appdoc.getDocumentElement().getChildNodes().getLength();j++) {
	          if (j % 2 != 0){
	  		String nodeName = appdoc.getDocumentElement().getChildNodes().item(j).getNodeName();	
	  		
	  		if(appType.equals("Admin")) {
	  			if(nodeName.equals("WebApplications")) {
	  			if(appType.equals("")) {					
				}else {					
					for (int l=1;l<appdoc.getDocumentElement().getChildNodes().item(j).getChildNodes().getLength();l++) {
						 if (l % 2 != 0){
							 // Test Case Parameters
								String childNodeNametc = appdoc.getDocumentElement().getChildNodes().item(j).getChildNodes().item(l).getNodeName();
								if(childNodeNametc.equals("Project")) {
									String parameters = appdoc.getDocumentElement().getChildNodes().item(j).getChildNodes().item(l).getTextContent();	
						                model.addElement(new CheckListItem(parameters));						    
						                Files.createDirectories(Paths.get(Constants.TESTCASEDIR_PATH(parameters.toLowerCase())));
										Files.createDirectories(Paths.get(Constants.TESTDATADIR_PATH(parameters.toLowerCase())));
										Files.createDirectories(Paths.get(Constants.ImageDIR_PATH(parameters.toLowerCase())));
								}		
								
						 }
					}
	  		
				}
	  			}
	  		}else {
	  			if(nodeName.equals(appType)) {				
					if(appType.equals("")) {					
					}else {					
						for (int l=1;l<appdoc.getDocumentElement().getChildNodes().item(j).getChildNodes().getLength();l++) {
							 if (l % 2 != 0){
								 // Test Case Parameters
									String childNodeNametc = appdoc.getDocumentElement().getChildNodes().item(j).getChildNodes().item(l).getNodeName();
									if(childNodeNametc.equals("Project")) {
										String parameters = appdoc.getDocumentElement().getChildNodes().item(j).getChildNodes().item(l).getTextContent();														
							                model.addElement(new CheckListItem(parameters));
							                Files.createDirectories(Paths.get(Constants.TESTCASEDIR_PATH(parameters.toLowerCase())));
											Files.createDirectories(Paths.get(Constants.TESTDATADIR_PATH(parameters.toLowerCase())));
											Files.createDirectories(Paths.get(Constants.ImageDIR_PATH(parameters.toLowerCase())));
									}
									
									
							 }
						}
		  		
					}
		  		
			   

		          }
	  		}
	  		
	  		
	      }
	   	
	    }	
		
	    System.gc();
	    
	    System.out.println("3-->"+System.currentTimeMillis());

		list.setCellRenderer(new CheckListRenderer());		
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	      
	    list.addMouseListener(new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent event) {
	        JList<?> list = (JList<?>) event.getSource();
	        int index = list.locationToIndex(event.getPoint());// Get index of item
	        CheckListItem item = (CheckListItem) list.getModel()
	            .getElementAt(index);
	        item.setSelected(!item.isSelected()); 
	        list.repaint(list.getCellBounds(index, index));// Repaint cell	 
	        
	        if (item.isSelected())
	        {
	        	selectedProjects.add(item.toString());	        	
	    // Code for Auto display of test cases and test suites    	
	        	 if(selectedProjects.size()!=0) {
	        		 
			 			if(testingTypes.size()!=0) {
			 							 				
			 				for(int i=0;i<selectedProjects.size();i++) {
			 					
			 					String projectName = selectedProjects.get(i);
											 					
			 					File folder = new File(Constants.TESTCASEDIR_PATH(projectName.toLowerCase()));
								File[] listOfFiles = folder.listFiles();
								String testCaseFileName;
						    	
						    	for(int j=0;j<testingTypes.size();j++) {
						    		//String projectName =selectedProjects.get(i);
						    		String testingType =projectName+"_"+testingTypes.get(j);	
						    		System.out.println(testingType);
						    		dualListBox2.addSourceElements(new String[] { testingType });
						    		projectName =projectName.toLowerCase();
						    		testingType =testingType.toLowerCase();
										for (int fileCount = 0; fileCount < listOfFiles.length; fileCount++) {			
										testCaseFileName = listOfFiles[fileCount].getName();
									    String testSuiteName = testCaseFileName.toLowerCase();
									    	if(testSuiteName.contains("_testsuite")) {
									    	testCaseFileName =testCaseFileName.substring(0, testCaseFileName.length()-14);
									    	testSuiteName =testCaseFileName.toLowerCase();
										    	try {
										    		if(testSuiteName.equals(testingType)) {
											    	
											    	File testCasesXml = new File(Constants.TESTCASESXML_PATH(projectName,testCaseFileName));

												   	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
												   	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
												   	Document doc = dBuilder.parse(testCasesXml);			
												   	doc.getDocumentElement().normalize();	   	
													
													
												   		for (int n=1;n<doc.getDocumentElement().getChildNodes().getLength();n++) {
												          if (n % 2 != 0){
												          	// nodeName = TestCase Name from XML
												        	  String nodeName = doc.getDocumentElement().getChildNodes().item(n).getNodeName();												  		
												        	  dual.addSourceElements(new String[] { nodeName });													  
													    }
												      }	     
											    	
											    }
										    }catch(Exception e) {
										    	System.out.println(e);
										    }
										    
									    }
									}
						    		
						    		
							    }
						    }
			 				
			 				
				    		}			 			
			 		}        	
	        	
	        	
	        }
	        if (!item.isSelected())
	        {
	        selectedProjects.remove(item.toString());	        	
	        TestCaseSelector.clearSourceListModel();
			TestSuiteSelector.clearSourceListModel();
			TestCaseSelector.clearDestinationListModel();
			TestSuiteSelector.clearDestinationListModel();
			
			progressBar.setForeground(Color.LIGHT_GRAY);
			progressBar.setString("");

	        
	        
	        if(selectedProjects.size()!=0) {
	 			if(testingTypes.size()!=0) {
	 					 				
	 				for(int i=0;i<selectedProjects.size();i++) {
	 					
	 					String projectName = selectedProjects.get(i);	
	 						 					
	 					File folder = new File(Constants.TESTCASEDIR_PATH(projectName.toLowerCase()));
						File[] listOfFiles = folder.listFiles();
						String testCaseFileName;
				    	
				    	for(int j=0;j<testingTypes.size();j++) {
				    		String testingType =projectName+"_"+testingTypes.get(j);
				    		
				    		dualListBox2.addSourceElements(new String[] { testingType });
				    		projectName =projectName.toLowerCase();
				    		testingType =testingType.toLowerCase();
				    		
							for (int fileCount = 0; fileCount < listOfFiles.length; fileCount++) {			
								testCaseFileName = listOfFiles[fileCount].getName();
							    String testSuiteName = testCaseFileName.toLowerCase();

							    if(testSuiteName.contains("_testsuite")) {
							    	testCaseFileName =testCaseFileName.substring(0, testCaseFileName.length()-14);
							    	testSuiteName =testCaseFileName.toLowerCase();
								    try {
								    	if(testSuiteName.equals(testingType)) {
									    	
									    	File testCasesXml = new File(Constants.TESTCASESXML_PATH(projectName,testCaseFileName));

										   	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
										   	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
										   	Document doc = dBuilder.parse(testCasesXml);			
										   	doc.getDocumentElement().normalize();	   	
											
											
										    for (int n=1;n<doc.getDocumentElement().getChildNodes().getLength();n++) {
										          if (n % 2 != 0){
										          	// nodeName = TestCase Name from XML
										  		String nodeName = doc.getDocumentElement().getChildNodes().item(n).getNodeName();										  		
											    dual.addSourceElements(new String[] { nodeName });	
											  
											    }
										      }	     
									    	
									    }
								    }catch(Exception e) {
								    	System.out.println(e);
								    }
								    
							    }
							}
				    		
				    		
					    }
				    }
	 				
	 				
		    		}
	 			
	 		}	     	
 			//	dualListBox2.clearSourceListModel();
	        }

	      }
	    });
	    
	    System.out.println("4-->"+System.currentTimeMillis());

	    
    	  list.setToolTipText("Select a Project for Test Execution");
	      JScrollPane scrollPane = new JScrollPane();
	      scrollPane.setToolTipText("Select a Project for Test Execution");
	      scrollPane.setViewportView(list);
	      list.setLayoutOrientation(JList.VERTICAL);
	      JLabel label = new JLabel("PROJECTS");
	      label.setFont(new Font("Tahoma", Font.BOLD, 11));
	      label.setBackground(new Color(192, 192, 192));
	      scrollPane.setColumnHeaderView(label);
		  
		  
		  JButton btnUi = new JButton("Web Applications");
			btnUi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {						
					projList("WebApplications", "Admin");
					applicationType="WebApplications";
					System.out.println(applicationType);
					TestCaseSelector.clearSourceListModel();
					TestSuiteSelector.clearSourceListModel();
					TestCaseSelector.clearDestinationListModel();
					TestSuiteSelector.clearDestinationListModel();
					testingTypesList();

					/*int dialogButton = 0;	 
	           		 int dialogResult = JOptionPane.showConfirmDialog(null, "Selected Test Suites and Test Cases will be removed", "Alert", dialogButton);
	           		 if (dialogResult == JOptionPane.NO_OPTION) {
	           			 
	           			 // place holder for Progress bar message
	           		 } else if (dialogResult == JOptionPane.YES_OPTION) {
	           		//	TestCaseSelector.destList.clearSelection();
		           		// TestSuiteSelector.destList.clearSelection();
		           		TestSuiteSelector.clearSourceListModel();
		           		TestSuiteSelector.clearDestinationListModel();
	           		 }*/
			}
			});
		
		JButton btnApi = new JButton("API");
		btnApi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				projList("WebServices", "Admin");
				applicationType="WebServices";
				System.out.println(applicationType);
				TestCaseSelector.clearSourceListModel();
				TestSuiteSelector.clearSourceListModel();
				TestCaseSelector.clearDestinationListModel();
				TestSuiteSelector.clearDestinationListModel();
				testingTypesList();


				}
		});
		
		
		JButton btnDatabase = new JButton("Database");
		btnDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				projList("Database", "Admin");
				applicationType="Database";
				System.out.println(applicationType);
				TestCaseSelector.clearSourceListModel();
				TestSuiteSelector.clearSourceListModel();
				TestCaseSelector.clearDestinationListModel();
				TestSuiteSelector.clearDestinationListModel();
				testingTypesList();


			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Constants.DefaultDirectory+"\\framework\\images\\Hello.jpg"));

		
// Testing Type Selection from the List	------------------------------------------------------------------------------------------------------------------------------------	
	   	
	/*	JList<?> list_1 = new JList<Object>(new CheckListItem[] { 
				new CheckListItem("Sanity"),
		        new CheckListItem("Functional"),
		        new CheckListItem("Regression"),
		        new CheckListItem("EndtoEnd")});*/
		
		JList<CheckListItem> list_1=new JList<CheckListItem>(model1);
		model1.addElement(new CheckListItem("Sanity"));
		model1.addElement(new CheckListItem("Functional"));
		model1.addElement(new CheckListItem("Regression"));
		model1.addElement(new CheckListItem("EndtoEnd"));

		list_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		list_1.setCellRenderer(new CheckListRenderer());
	    list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	    
	    list_1.addMouseListener(new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent event) {
	        JList<?> list_1 = (JList<?>) event.getSource();
	        int index = list_1.locationToIndex(event.getPoint());                                                           
	        CheckListItem item = (CheckListItem) list_1.getModel()
	            .getElementAt(index);
	        item.setSelected(!item.isSelected()); 
	        list_1.repaint(list_1.getCellBounds(index, index));
	        
	        if (item.isSelected())
	        {
	        	testingTypes.add(item.toString());	        	
	        	
				 if(selectedProjects.size()!=0) {
			 			if(testingTypes.size()!=0) {
			 		
			 				
			 				for(int i=0;i<selectedProjects.size();i++) {
					    		String projectName =selectedProjects.get(i);
					    		
			 					File folder = new File(Constants.TESTCASEDIR_PATH(projectName.toLowerCase()));
								File[] listOfFiles = folder.listFiles();
								String testCaseFileName;
						    	
						    	for(int j=0;j<testingTypes.size();j++) {
						    		String testingType =projectName+"_"+testingTypes.get(j);
						    		System.out.println(testingType);
						    		dualListBox2.addSourceElements(new String[] { testingType });
						    		String projectNameLowerCase =projectName.toLowerCase();  
						    		testingType =testingType.toLowerCase();

									for (int fileCount = 0; fileCount < listOfFiles.length; fileCount++) {			
										testCaseFileName = listOfFiles[fileCount].getName();
									    String testSuiteName = testCaseFileName.toLowerCase();

									    if(testSuiteName.contains("_testsuite")) {
									    	testCaseFileName =testCaseFileName.substring(0, testCaseFileName.length()-14);
									    	testSuiteName =testCaseFileName.toLowerCase();
										    try {
										    	if(testSuiteName.equals(testingType)) {
											    	
											    	File testCasesXml = new File(Constants.TESTCASESXML_PATH(projectNameLowerCase,testCaseFileName));

												   	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
												   	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
												   	Document doc = dBuilder.parse(testCasesXml);			
												   	doc.getDocumentElement().normalize();	   	
													
													
												    for (int n=1;n<doc.getDocumentElement().getChildNodes().getLength();n++) {
												          if (n % 2 != 0){
												          	// nodeName = TestCase Name from XML
												  		String nodeName = doc.getDocumentElement().getChildNodes().item(n).getNodeName();												  		
													    dual.addSourceElements(new String[] { nodeName });	
													  
													    }
												      }	    
											    	
											    }
										    }catch(Exception e) {
										    	System.out.println(e);
										    }
										    
									    }
									}
						    		
						    		
							    }
						    }
			 				
			 				
				    		}/*else {
				    	       // JOptionPane.showMessageDialog(TCB_AUTO_UI_FRAME, "Please select testing type for execution","Select Testing Types", JOptionPane.INFORMATION_MESSAGE);
				    	        progressBar.setValue(100);
								progressBar.setStringPainted(true);
		    		        	progressBar.setForeground(Color.red);
		    					progressBar.setString("Please select testing type for execution");
				    		}*/
			 			
			 		}
	        	
	        		        	
	        	
	        }

	        if (!item.isSelected())
	        {
	        	testingTypes.remove(item.toString());
				progressBar.setForeground(Color.LIGHT_GRAY);
				progressBar.setString("");
	        	
	        	
	        	
				  TestCaseSelector.clearSourceListModel();
					TestSuiteSelector.clearSourceListModel();
					 TestCaseSelector.clearDestinationListModel();
					TestSuiteSelector.clearDestinationListModel();

		        
		        
		        if(selectedProjects.size()!=0) {
		 			if(testingTypes.size()!=0) {
		 						 				
		 				for(int i=0;i<selectedProjects.size();i++) {
				    		String projectName =selectedProjects.get(i);
				    		
		 					File folder = new File(Constants.TESTCASEDIR_PATH(projectName.toLowerCase()));
							File[] listOfFiles = folder.listFiles();
							String testCaseFileName;
					    	
					    	for(int j=0;j<testingTypes.size();j++) {
					    		String testingType =projectName+"_"+testingTypes.get(j);
					    		
					    		dualListBox2.addSourceElements(new String[] { testingType });
					    		String projectNameLowerCase =projectName.toLowerCase();
					    		testingType =testingType.toLowerCase();
					    		
								for (int fileCount = 0; fileCount < listOfFiles.length; fileCount++) {			
									testCaseFileName = listOfFiles[fileCount].getName();
								    String testSuiteName = testCaseFileName.toLowerCase();

								    if(testSuiteName.contains("_testsuite")) {
								    	testCaseFileName =testCaseFileName.substring(0, testCaseFileName.length()-14);
								    	testSuiteName =testCaseFileName.toLowerCase();
									    try {
									    	if(testSuiteName.equals(testingType)) {
										    	
										    	File testCasesXml = new File(Constants.TESTCASESXML_PATH(projectNameLowerCase,testCaseFileName));

											   	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
											   	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
											   	Document doc = dBuilder.parse(testCasesXml);			
											   	doc.getDocumentElement().normalize();	   	
												
												
											    for (int n=1;n<doc.getDocumentElement().getChildNodes().getLength();n++) {
											          if (n % 2 != 0){
											          	// nodeName = TestCase Name from XML
											  		String nodeName = doc.getDocumentElement().getChildNodes().item(n).getNodeName();
											  		
												    dual.addSourceElements(new String[] { nodeName });	
												  
												    }
											      }	     
										    	
										    }
									    }catch(Exception e) {
									    	System.out.println(e);
									    }
									    
								    }
								}
					    		
					    		
						    }
					    }
		 				
		 				
			    		}
		 			
		 		}

	        	
	        	
	        	
	        	
	        }
	      }
	    });
		
	    System.out.println("5-->"+System.currentTimeMillis());

	    
	     JScrollPane scrollPane_1 = new JScrollPane();
	     scrollPane_1.setViewportView(list_1);
	     list_1.setLayoutOrientation(JList.VERTICAL);
	     JLabel lblTestingSuites = new JLabel("TESTING TYPES");
	     lblTestingSuites.setFont(new Font("Tahoma", Font.BOLD, 11));
	     scrollPane_1.setColumnHeaderView(lblTestingSuites);
		 
// Head Less Selection Option.---------------------------------------------------------------------------------------------------------------------------------------------	
        String s1[] = {"Default Browser","Chrome","ChromeHeadless","IE","Mozilla","Safari"}; 
        
	    JComboBox<?> comboBox = new JComboBox<Object>(s1);
	    comboBox.setToolTipText("Saves 20% of execution time by selecting Headless");

	    comboBox.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		browserType.add(comboBox.getSelectedItem().toString());
	    		if(browserType.size()>0) {
	    			browserType.set(0, comboBox.getSelectedItem().toString());
	    			
	    		}

	    	}
	    });
		 comboBox.setEditable(true);
		 
		 
		 
		 ActionListener progressbarEID = new ActionListener() {
				
				public void actionPerformed(ActionEvent actionEvent) {

					String tcName = UIDriver.executedCase();
					tcCount = UIDriver.executedCount();
				    String executedCount = Integer.toString(tcCount); 
					progressBar.setString("Executing TestCase ----"+tcName +"----"+ executedCount +"/" + totalCount +"----In Progress");										
					progressBar.setValue(tcCount);
					progressBar.setStringPainted(true);
					progressBar.setForeground(Color.blue);
				}
			};

		timerval = new Timer(1000,progressbarEID);
		
		System.gc();		
// Executing the Test cases based upon the selection.-----------------------------------------------------------------------------------------------------------------------	 
		 btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		           	 protected Void doInBackground() throws InterruptedException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
		           		
		     		    System.out.println("Start -->"+System.currentTimeMillis());

		           		 String progressBarMessage = "";
		           		totalCount = totalSelectedtestCaseCount();
		           		progressBar.setMaximum(totalCount);
						//progressBar.setStringPainted(true);
		           		timerval.start();   
						progressBar.setStringPainted(true);
						btnNewButton_1.setEnabled(false);		           		 
		           		 int testCaseCount = TestCaseSelector.destList.getModel().getSize();
		           		 int testSuiteCount = TestSuiteSelector.destList.getModel().getSize();
		           		 
		           		 String testCandidate_testCase ="TestCase";
		           		 String testCandidate_testSuite="TestSuite";
		           		 if(browserType.size()!=0) {
		           			browserSelected = browserType.get(0);	
		           		 }else if(browserType.size()==0 && browserSelected=="") {
		           			browserSelected = "Default Browser";
		           		 }        		 
		     		    System.out.println("Web-2nd-->"+System.currentTimeMillis());

		           		 //-----------------------------------------------------------AppType Selection---------------------------------------------------------------------------------------
		           if(applicationType.equals("WebApplications")) {
		   		    System.out.println("Web -3rd-->"+System.currentTimeMillis());

		           		 if(testCaseCount == 0 && testSuiteCount ==0) {  // -- If Test cases and Test suites are not selected
		    		        	progressBar.setValue(100);
								progressBar.setStringPainted(true);
		    		        	progressBar.setForeground(Color.red);
		    					progressBar.setString("Please select Test case or Test suite for Execution");
		    					timerval.stop();
		           		 }else if(testCaseCount == 0  && testSuiteCount!=0) { // if Test suites are only selected
		           			String randomNumber = GenericLibrary.randomNumber();
		           			rep = ExtentManager.GetInstance(randomNumber);
		           			 for(int i=0;i<testSuiteCount;i++) {
		           				
		    					String testSuiteName = TestSuiteSelector.destList.getModel().getElementAt(i).toString();	    					
		    					String [] parts = 	testSuiteName.split("_");
		    					String projectName = parts[0];
		    					UIDriver.Start(projectName, testSuiteName,browserSelected,testCandidate_testSuite,randomNumber,rep);
			    						    				
			    				System.gc();
		           			}
		           			rep.flush();
		           			rep = null;
		           					           			
		           		//email Reports
		           			ReportCustomization.forTCB();
		           			if(emailList.size()!= 0) {
		        				emailReports=emailList.get(0);	
		        				if(emailReports!="No Email") {			        				
			        				EmailReport.email(emailReports);
		        				}
		        			}
		           			
		           				timerval.stop();
		           				progressBar.setValue(100);
								progressBar.setStringPainted(true);
		    		        	progressBar.setForeground(Color.blue);
		    		        	
		    		        	if(emailReports.equals("Self")) {
		    		        		progressBarMessage ="Please View test results in your Inbox";
		    		        	}else if(emailReports.equals("Team")) {
		    		        		progressBarMessage ="Test results sent to Team members, report available in Inbox";
		    		        	}
		    					progressBar.setString("Test Execution Completed  " + progressBarMessage);
		    					
		           			 
		           		 } else if(testCaseCount!=0 && testSuiteCount==0) {  // If test cases are only selected
		           			String randomNumber = GenericLibrary.randomNumber();
		           			rep = ExtentManager.GetInstance(randomNumber);
		           			 testCandidate_testCase = "TestCase";

		           			 	
		           			 if(browserType.size()!=0) {
				           			browserSelected = browserType.get(0);	
				           		 }else if(browserType.size()==0 && browserSelected== "") {
					           			browserSelected = "Default Browser";
				           		 }

		           			 	for(int i=0;i<testCaseCount;i++) {		

		    					String testCaseName = TestCaseSelector.destList.getModel().getElementAt(i).toString();	
		    					UIDriver.Start("", testCaseName,browserSelected,testCandidate_testCase,randomNumber,rep);
			    				System.gc();
		           			}
		           			
		           			 
		           			rep.flush();
		           			rep = null; 
		           			
	        				ReportCustomization.forTCB();

		           		//email Reports
		           			if(emailList.size()!= 0) {
		        				emailReports=emailList.get(0);	
		        				if(emailReports!="No Email") {
			        				EmailReport.email(emailReports);
		        				}
		        			}
		           				timerval.stop();
		           				progressBar.setValue(100);
								progressBar.setStringPainted(true);
		    		        	progressBar.setForeground(Color.blue);
		    		        	if(emailReports.equals("Self")) {
		    		        		progressBarMessage ="Please View test results in your Inbox";
		    		        	}else if(emailReports.equals("Team")) {
		    		        		progressBarMessage ="Test Results sent to Team members, report available in Inbox";
		    		        	}
		    					progressBar.setString("Test Execution Completed  " + progressBarMessage);		    					

		           		 }else if(testCaseCount!=0 && testSuiteCount!=0) {  // if test cases and suites are selected
		           			String randomNumber = GenericLibrary.randomNumber();
		           			rep = ExtentManager.GetInstance(randomNumber);
		           			
		           			
		           		 int dialogButton = 0;	 
		           		 int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to execute Testcases and Test suites?", "Alert", dialogButton);
		           		 if (dialogResult == JOptionPane.NO_OPTION) {
		           			 
		           			 // place holder for Progress bar message
		           		 } else if (dialogResult == JOptionPane.YES_OPTION) {
		           			 
		           			 
		           			 testCandidate_testCase ="TestCase";
		           			if(browserType.size()!=0) {
			           			browserSelected = browserType.get(0);	
			           		 }else if(browserType.size()==0 && browserSelected== "") {
				           			browserSelected = "Default Browser";
			           		 }
		           			 for(int i=0;i<testCaseCount;i++) {
		           				
		    					String testCaseName = TestCaseSelector.destList.getModel().getElementAt(i).toString();	  
		    					UIDriver.Start("", testCaseName,browserSelected,testCandidate_testCase,randomNumber,rep);
			    				System.gc();
		           			}
		           			 
		           			 
		           			 
		           			 for(int i=0;i<testSuiteCount;i++) {
		           				
		    					String testSuiteName = TestSuiteSelector.destList.getModel().getElementAt(i).toString();	    					
		    					String [] parts = 	testSuiteName.split("_");
		    					String projectName = parts[0];
		    					UIDriver.Start(projectName, testSuiteName,browserSelected,testCandidate_testSuite,randomNumber,rep);
			    				System.gc();
		    					
		           			}
		           			 
		           			
		           		 }
		           			 		           			

		           		rep.flush();
		           		rep = null;  
        				ReportCustomization.forTCB();

		           	//email Reports
	           			if(emailList.size()!= 0) {
	        				emailReports=emailList.get(0);	
	        				if(emailReports!="No Email") {
		        				EmailReport.email(emailReports);
	        				}
	        			}
	           				timerval.stop();
	           				progressBar.setValue(100);
							progressBar.setStringPainted(true);
	    		        	progressBar.setForeground(Color.blue);
	    		        	if(emailReports.equals("Self")) {
	    		        		progressBarMessage ="Please View test results in your Inbox";
	    		        	}else if(emailReports.equals("Team")) {
	    		        		progressBarMessage ="Test Results sent to Team members, report available in Inbox";
	    		        	}
	    					progressBar.setString("Test Execution Completed  " + progressBarMessage);	           			}
	           			
		     		    System.out.println("End of Web-->"+System.currentTimeMillis());
 
		           }else if(applicationType.equals("WebServices")) {
		        	   
		        	   String randomNumber = GenericLibrary.randomNumber();
	           			rep = ExtentManager.GetInstance(randomNumber);
		        	   
		        	   ProjectTestRunner.webServices(rep);
		        	   System.gc();
		        	   
		        		rep.flush();
		           		rep = null;
		        	   ReportCustomization.forTCB();

			           	//email Reports
		           			if(emailList.size()!= 0) {
		        				emailReports=emailList.get(0);	
		        				if(emailReports!="No Email") {
			        				EmailReport.email(emailReports);
		        				}
		        			}
		           			
		           		
		        	   
		           }else if(applicationType.equals("Database")) {
		        	   

			   			try {
							rep = ExtentManager.GetInstance("DB2DBReport");
						} catch (InvocationTargetException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						//DbToDbComparision db2dbCompare = new DbToDbComparision();
						try {
							progressBar.setStringPainted(true);
							progressBar.setString("In Progress");
							try {
								DbToDbComparision.dbToDb(rep);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							progressBar.setStringPainted(true);
							String message = DbToDbComparision.CompletedMessage;
							progressBar.setString(message);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							int dialogButton = 0;
							int dialogResult = JOptionPane.showConfirmDialog (null, "Test Strategy Sheet Not Available","Alert",dialogButton);
							progressBar.setStringPainted(true);
							progressBar.setString("Test Strategy Sheet Not Available");
						} finally {
							//db2dbCompare = null;
							rep.flush();
		           			rep = null;
							System.gc();
						}
		        	   
		        	   
		        	   
		           }
					btnNewButton_1.setEnabled(true);
					

					System.gc();
					return null;
		            	 }
		            };
					
		            worker.execute();
					
				}
			});
	
		// --------------------Scheduler-----------------------------------------------
		
		JXDatePicker picker = new JXDatePicker();
	    final org.jdesktop.swingx.JXMonthView monthView = picker.getMonthView();
	    monthView.addActionListener(new ActionListener() {
	     public void actionPerformed(ActionEvent e) {
	       System.out.println((monthView.getSelection()));
	     }
	    });
        
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SpinnerDateModel model1 = new SpinnerDateModel();
        model1.setValue(calendar.getTime());

        JSpinner spinner = new JSpinner(model1);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);
        spinner.setEditor(editor);
	

		JLabel lblTestingTypes = new JLabel("");
		 
		 JButton btnDesktopApplications = new JButton("Desktop Applications");
		 btnDesktopApplications.setToolTipText("Only Desktop related projects are displayed");

		 btnDesktopApplications.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		projList("DesktopApplications", "Admin");
		 		applicationType="DesktopApplications";
				System.out.println(applicationType);
				TestCaseSelector.clearSourceListModel();
				TestSuiteSelector.clearSourceListModel();
				TestCaseSelector.clearDestinationListModel();
				TestSuiteSelector.clearDestinationListModel();
				testingTypesList();

				
				

		 	}
		 });
		 
		 JButton btnMobileApplications = new JButton("Mobile Applications");
		 btnMobileApplications.setToolTipText("Only Mobile related projects are displayed");

		 btnMobileApplications.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		projList("MobileApplications", "Admin");
		 		applicationType="MobileApplications";
				System.out.println(applicationType);
				TestCaseSelector.clearSourceListModel();
				TestSuiteSelector.clearSourceListModel();
				TestCaseSelector.clearDestinationListModel();
				TestSuiteSelector.clearDestinationListModel();
				testingTypesList();


		 	}
		 });
		 
		 
		 if(LoginDialog.roleName().equals("WebApplications")) {
			 btnApi.setEnabled(false);
			 btnDatabase.setEnabled(false);
			 btnDesktopApplications.setEnabled(false);
			 btnMobileApplications.setEnabled(false);
		 }else if(LoginDialog.roleName().equals("WebServices")) {
			 btnUi.setEnabled(false);
			 btnDatabase.setEnabled(false);
			 btnDesktopApplications.setEnabled(false);
			 btnMobileApplications.setEnabled(false);
		 }else if(LoginDialog.roleName().equals("DesktopApplications")) {
			 btnApi.setEnabled(false);
			 btnDatabase.setEnabled(false);
			 btnUi.setEnabled(false);
			 btnMobileApplications.setEnabled(false);
		 }else if(LoginDialog.roleName().equals("MobileApplications")) {
			 btnApi.setEnabled(false);
			 btnDatabase.setEnabled(false);
			 btnDesktopApplications.setEnabled(false);
			 btnUi.setEnabled(false);
		 }else if(LoginDialog.roleName().equals("Database")) {
			 btnApi.setEnabled(false);
			 btnUi.setEnabled(false);
			 btnDesktopApplications.setEnabled(false);
			 btnMobileApplications.setEnabled(false);
		 }
		 
	        String email[] = {"No Email","Self","Team"}; 
	        
		    JComboBox<?> comboBox_1 = new JComboBox<Object>(email);
		    comboBox_1.setMinimumSize(new Dimension(105, 20));
		    comboBox_1.setPreferredSize(new Dimension(105, 20));
		    comboBox_1.setToolTipText("Select an option for emailing Test Results");
		    comboBox_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		emailList.add(comboBox_1.getSelectedItem().toString());
		    		if(emailList.size()>0) {
		    			emailList.set(0, comboBox_1.getSelectedItem().toString());
		    		}

		    	}
		    });
		    comboBox_1.setEditable(true);
			
			JButton btnNewButton = new JButton("Clear Selection");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					if(applicationType.equals("WebApplications")) {
						projList("WebApplications", "Admin");
						testingTypesList();						
					}else if(applicationType.equals("WebServices")) {
						projList("WebServices", "Admin");
						testingTypesList();	
					}else if(applicationType.equals("DesktopApplications")) {
						projList("DesktopApplications", "Admin");
						testingTypesList();	
					}else if(applicationType.equals("MobileApplications")) {
						projList("MobileApplications", "Admin");
						testingTypesList();	
					}else if(applicationType.equals("Database")) {
						projList("Database", "Admin");
						testingTypesList();	
					}
					TestCaseSelector.clearSourceListModel();
					TestSuiteSelector.clearSourceListModel();
					TestCaseSelector.clearDestinationListModel();
					TestSuiteSelector.clearDestinationListModel();					
				}
			});
			GroupLayout groupLayout = new GroupLayout(TCB_AUTO_UI_FRAME.getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnMobileApplications, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnDesktopApplications, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnDatabase, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnApi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnUi, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(9)
										.addComponent(lblTestingTypes, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
											.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))))))
						.addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(dual, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
							.addComponent(dualListBox2, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(10)
								.addComponent(picker, GroupLayout.PREFERRED_SIZE, 101, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox_1, 0, 98, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
								.addGap(2))))
					.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 1234, Short.MAX_VALUE)
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(dual, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
								.addGap(1)
								.addComponent(dualListBox2, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
											.addComponent(picker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
											.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
											.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
										.addGap(6))))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(12)
										.addComponent(btnUi, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnApi, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
										.addGap(11)
										.addComponent(btnDatabase, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
										.addGap(11)
										.addComponent(btnDesktopApplications, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
										.addGap(11)
										.addComponent(btnMobileApplications, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
										.addGap(9))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(1)
										.addComponent(lblTestingTypes)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
											.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
											.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
			);
			TCB_AUTO_UI_FRAME.getContentPane().setLayout(groupLayout);

		 		
		JMenuBar menuBar = new JMenuBar();
		TCB_AUTO_UI_FRAME.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Projects");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmWebServices = new JMenuItem("Web services");
		mntmWebServices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   
			
			
			    
			}
		});
		mnNewMenu.add(mntmWebServices);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("UI Automation");
		mnNewMenu.add(mntmNewMenuItem);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		
		JMenuItem mntmDatabses = new JMenuItem("Databases");
		mnNewMenu.add(mntmDatabses);

		
		JMenu mnUtilities = new JMenu("View");
		menuBar.add(mnUtilities);
		
		JMenuItem mntmXmlexcel = new JMenuItem("View/Update Test Suites");
		mnUtilities.add(mntmXmlexcel);
		
		JSeparator separator_2 = new JSeparator();
		mnUtilities.add(separator_2);
		
		JMenuItem mntmExcelXml = new JMenuItem("View/Update Test Data");
		mnUtilities.add(mntmExcelXml);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmViewReport = new JMenuItem("View Latest Report");
		mntmViewReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Thread t = new Thread() {
					@Override
					public void run() {

						try {
							
							File theNewestFile = null;
						    File dir = new File(Constants.Results);
						    FileFilter fileFilter = new WildcardFileFilter("*." + "html");
						    File[] files = dir.listFiles(fileFilter);

						    if (files.length > 0) {
						        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
						        theNewestFile = files[0];
						        System.out.println(theNewestFile);
								Desktop.getDesktop().open(theNewestFile);

						    }else {
						    	Component frame = null;
								JOptionPane.showMessageDialog(frame,"No Reports Available", "Reports", 1);
						    }
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				};
				t.start(); // call back run()
			}
		});
		mnReports.add(mntmViewReport);
		
		JSeparator separator_5 = new JSeparator();
		mnReports.add(separator_5);
		
		JMenuItem mntmViewPreviousReport = new JMenuItem("View Previous Report");
		mntmViewPreviousReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread t = new Thread() {
					@Override
					public void run() {

						try {
							
							File theNewestFile = null;
						    File dir = new File(Constants.Results);
						    FileFilter fileFilter = new WildcardFileFilter("*." + "html");
						    File[] files = dir.listFiles(fileFilter);

						    if (files.length > 0) {
						        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
						        theNewestFile = files[1];
								Desktop.getDesktop().open(theNewestFile);

						    }else {
						    	Component frame = null;
								JOptionPane.showMessageDialog(frame,"No Reports Available", "Reports", 1);
						    }
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				};
				t.start(); // call back run()
			}
		});
		mnReports.add(mntmViewPreviousReport);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("User Guide");
		mnHelp.add(mntmAbout);
		
		JSeparator separator_3 = new JSeparator();
		mnHelp.add(separator_3);
		
		JMenuItem mntmUserGuide = new JMenuItem("About");
		mntmUserGuide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Thread t = new Thread() {
					@Override
					public void run() { 

						Component frame = null;
						JOptionPane.showMessageDialog(frame,"Texas Capital Bank QA Automation." + "\r\n" + "           Version: "+Constants.Version + "\r\n", "About Me", 1);

					}
				};
				t.start(); 
			}
		});
		mnHelp.add(mntmUserGuide);
		
		menuBar.add(Box.createGlue());
		JMenu UserName = new JMenu(LoginDialog.getUsername());
		menuBar.add(UserName);
		
		JSeparator separator_4 = new JSeparator();
		UserName.add(separator_4);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Logout");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TCB_AUTO_UI_FRAME.dispose();
				new LoginDialog();	
			}
		});
		UserName.add(mntmNewMenuItem_1);
		
		JMenu role = new JMenu(LoginDialog.roleName());
		menuBar.add(role);
		
		TCB_AUTO_UI_FRAME.setVisible(true);

	}
	
	public void projList(String ApplicationType, String Role) {
		for (int j=1;j<appdoc.getDocumentElement().getChildNodes().getLength();j++) {
			if (j % 2 != 0){
			  	String nodeName = appdoc.getDocumentElement().getChildNodes().item(j).getNodeName();				  
			  if(LoginDialog.roleName().equals(ApplicationType) ||LoginDialog.roleName().equals(Role)) {

			  	if(nodeName.equals(ApplicationType)) {	
			  		selectedProjects.clear();
					  model.clear();
							if(ApplicationType.equals("")) {					
							}else {					
								for (int l=1;l<appdoc.getDocumentElement().getChildNodes().item(j).getChildNodes().getLength();l++) {
									 if (l % 2 != 0){
										 // Test Case Parameters
											String childNodeNametc = appdoc.getDocumentElement().getChildNodes().item(j).getChildNodes().item(l).getNodeName();
											if(childNodeNametc.equals("Project")) {
												String parameters = appdoc.getDocumentElement().getChildNodes().item(j).getChildNodes().item(l).getTextContent();														
									                model.addElement(new CheckListItem(parameters));

											}											
									 }
								}				  		
							}
				          }
			          }else {
			    	        JOptionPane.showMessageDialog(TCB_AUTO_UI_FRAME, "You are Not authorized to View","Access Denied", JOptionPane.INFORMATION_MESSAGE);
			    	        break;

			          }		  		
			  		
			      }
			   	
			    }
	}
	
	public void testingTypesList() {
		
		testingTypes.clear();
		model1.clear();
		model1.addElement(new CheckListItem("Sanity"));
		model1.addElement(new CheckListItem("Functional"));
		model1.addElement(new CheckListItem("Regression"));
		model1.addElement(new CheckListItem("EndtoEnd"));
	}
	

	
	public int totalSelectedtestCaseCount() throws ParserConfigurationException, SAXException, IOException {
 		 int testCaseCount = TestCaseSelector.destList.getModel().getSize();
 		 int testSuiteCount = TestSuiteSelector.destList.getModel().getSize();
 		 int totalTestCaseCount = 0;

		for(int m=0;m<testSuiteCount;m++) {
			String testSuiteName = TestSuiteSelector.destList.getModel().getElementAt(m).toString();	
			String [] parts = 	testSuiteName.split("_");
			String projectName = parts[0];
			File folder = new File(Constants.TESTCASEDIR_PATH(projectName.toLowerCase()));
			File[] listOfFiles = folder.listFiles();
			String testCaseFileName;
			for (int fileCount = 0; fileCount < listOfFiles.length; fileCount++) {			
				testCaseFileName = listOfFiles[fileCount].getName();								    
			    if(testCaseFileName.contains("_TestSuite")) {
			    	testCaseFileName =testCaseFileName.substring(0, testCaseFileName.length()-14);									    
				    if(testCaseFileName.equals(testSuiteName)) {
	//------------------------------------------------------------From Test Case Sheet XML---------------------------------------------------------------------

				    	File testCasesXml = new File(Constants.TESTCASESXML_PATH(projectName.toLowerCase(),testCaseFileName));
				    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				    	Document doc = dBuilder.parse(testCasesXml);			
				    	doc.getDocumentElement().normalize();										    	
				    	for (int i=1;i<doc.getDocumentElement().getChildNodes().getLength();i++) {
				            if (i % 2 != 0){
				            	// Test Case Numbers/ Names
				    		String nodeName = doc.getDocumentElement().getChildNodes().item(i).getNodeName();
				    		if(nodeName.equals("")) {
				    			
				    		}else {
				    			for (int j=1;j<doc.getDocumentElement().getChildNodes().item(i).getChildNodes().getLength();j++) {
				    				 if (j % 2 != 0){
				    					 // Test Case Parameters
				    						String childNodeName = doc.getDocumentElement().getChildNodes().item(i).getChildNodes().item(j).getNodeName();
				    						if(childNodeName.equals("execute")) {
				    							String executeValue = doc.getDocumentElement().getChildNodes().item(i).getChildNodes().item(j).getTextContent();
				    							if(executeValue.equals("Yes")) {				    								
				    								totalTestCaseCount =totalTestCaseCount +1;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}


		}
		return totalTestCaseCount+testCaseCount;

	}
	
	public class CheckListItem {

		  private String label;
		  private boolean isSelected = false;

		  public CheckListItem(String label) {
		    this.label = label;
		  }

		  public boolean isSelected() {
		    return isSelected;
		  }

		  public void setSelected(boolean isSelected) {
		    this.isSelected = isSelected;
		  }

		  @Override
		  public String toString() {
		    return label;
		  }
		}
	
	@SuppressWarnings("serial")
	public class CheckListRenderer extends JCheckBox implements ListCellRenderer<Object> {
		  public Component getListCellRendererComponent(JList<?> list, Object value,
		      int index, boolean isSelected, boolean hasFocus) {
		    setEnabled(list.isEnabled());
		    setSelected(((CheckListItem) value).isSelected());
		    setFont(list.getFont());
		    setBackground(list.getBackground());
		    setForeground(list.getForeground());
		    setText(value.toString());
		    return this;
		  }
		}
}
