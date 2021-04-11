package app.UI;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import app.UI.MainWindow;
import common.Constants;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class BrowserSelection extends JDialog
{
	private JFrame frame;
	public String browserName = null;
	public BrowserSelection()
  {
    frame = new JFrame("Enterprise Initiative Delivery");
    ImageIcon img1 = new ImageIcon(Constants.DefaultDirectory+"\\framework\\images\\TCB_fav_Icon.png");
    setIconImage(img1.getImage());

     setTitle("Enterprise Initiative Delivery-Select Browser");
     setSize(600, 400);
     setLocationRelativeTo(frame);     
     getContentPane().setLayout(null);    
     
    
  
// -----------------------------Radio Button Browsers ----------------------------------------------------------------------------------------------------------------
     
  //   String[] stringArray = new String[1];

     JRadioButton rdbtnNewRadioButton = new JRadioButton("Chrome");
     rdbtnNewRadioButton.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent arg0) {
     	     rdbtnNewRadioButton.setSelected(true);

     		     	}
     });
     rdbtnNewRadioButton.setBounds(6, 124, 83, 23);
     getContentPane().add(rdbtnNewRadioButton);
     
     JRadioButton rdbtnHeadlessChrome = new JRadioButton("ChromeHeadless");
     rdbtnHeadlessChrome.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent arg0) {
     		rdbtnHeadlessChrome.setSelected(true);


     	}
     });
     rdbtnHeadlessChrome.setBounds(6, 150, 151, 23);
     getContentPane().add(rdbtnHeadlessChrome); 
    
     
     JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("IE");
     rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent arg0) {
     		rdbtnNewRadioButton_1.setSelected(true);

     	}
     });
     rdbtnNewRadioButton_1.setBounds(6, 176, 151, 23);
     getContentPane().add(rdbtnNewRadioButton_1);
     
     JRadioButton rdbtnMozilla = new JRadioButton("Mozilla");
     rdbtnMozilla.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent arg0) {
     		rdbtnMozilla.setSelected(true);

     	}
     });
     rdbtnMozilla.setBounds(6, 202, 115, 23);
     getContentPane().add(rdbtnMozilla);
     
     JRadioButton rdbtnSafari = new JRadioButton("Safari");
     rdbtnSafari.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent arg0) {
     		rdbtnSafari.setSelected(true);

     	}
     });
     rdbtnSafari.setBounds(6, 228, 115, 23);
     getContentPane().add(rdbtnSafari);
     
     ButtonGroup group = new ButtonGroup();
     group.add(rdbtnNewRadioButton);
     group.add(rdbtnHeadlessChrome);
     group.add(rdbtnNewRadioButton_1);
     group.add(rdbtnMozilla);
     group.add(rdbtnSafari);

     rdbtnNewRadioButton.setSelected(true);
     
          
     JButton btnOk = new JButton("OK");
     btnOk.addActionListener(new ActionListener() {
     	@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent arg0) {

		    dispose();		    
		     
		     if(rdbtnNewRadioButton.isSelected()) {
		    	 browserName=rdbtnNewRadioButton.getText();
		     }else if(rdbtnHeadlessChrome.isSelected()) {
		    	 browserName=rdbtnHeadlessChrome.getText();
		     }else if(rdbtnNewRadioButton_1.isSelected()) {
		    	 browserName=rdbtnNewRadioButton_1.getText();
		     }else if(rdbtnMozilla.isSelected()) {
		    	 browserName=rdbtnMozilla.getText();
		     }else if(rdbtnSafari.isSelected()) {
		    	 browserName=rdbtnSafari.getText();
		     }
		    
		    String appType ="WebApplications";
			
				try {
					MainWindow ts = new MainWindow(appType,browserName);				
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}				
			
		      
     	}
     });
     btnOk.setBounds(217, 301, 151, 37);
     getContentPane().add(btnOk);
     
     JLabel lblNewLabel = new JLabel("Place an Image");
     lblNewLabel.setBackground(UIManager.getColor("Button.darkShadow"));
     lblNewLabel.setBounds(0, 0, 584, 123);
     getContentPane().add(lblNewLabel);
     
     JLabel lblNewLabel_1 = new JLabel("Place an Image");
     lblNewLabel_1.setBounds(163, 128, 411, 175);
     getContentPane().add(lblNewLabel_1);
     lblNewLabel_1.setIcon(new ImageIcon(Constants.DefaultDirectory+"\\framework\\images\\Browser_logo.png"));

     


  }
 }