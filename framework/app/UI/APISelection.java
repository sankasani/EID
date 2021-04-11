package app.UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import common.Constants;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class APISelection extends JFrame{

		private JTextField txtPath;


	
	public APISelection() {
		JFrame frame = new JFrame("Enterprise Initiative Delivery-API Selection");
		ImageIcon img = new ImageIcon(
				"\\\\opscifs1\\group\\$ Enterprise Initiative Delivery\\Private\\QA\\TCB_EID_POC\\Images\\TCB_logo.png");
		 setIconImage(img.getImage());
		 setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	     setTitle("Enterprise Initiative Delivery-API Selection");
	     setSize(600, 400);
	     setLocationRelativeTo(frame);
	     getContentPane().setLayout(null);
	     
	     JRadioButton rdbtnFileService = new JRadioButton("FILE Service");
	     rdbtnFileService.setBounds(27, 224, 109, 23);
	     getContentPane().add(rdbtnFileService);
	     
	     JRadioButton rdbtnSoaprest = new JRadioButton("SOAP");
	     rdbtnSoaprest.setBounds(258, 224, 109, 23);
	     getContentPane().add(rdbtnSoaprest);
	     
	     txtPath = new JTextField();
	     txtPath.setBounds(10, 10, 414, 21);
	     frame.getContentPane().add(txtPath);
	     txtPath.setColumns(10);
	     
	     JButton btnNewButton = new JButton("OK");
	     btnNewButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	   		   //  JOptionPane.showMessageDialog(APISelection.this,"Development in Progress","Infromation",JOptionPane.INFORMATION_MESSAGE); 
	   		     dispose();
	 		    String AppType ="WebServices";
				try {
					new MainWindow(AppType,"");
				} catch (SAXException | IOException | ParserConfigurationException e) {
					e.printStackTrace();
				}				


	     	}
	     });
	     btnNewButton.setBounds(173, 280, 231, 37);
	     getContentPane().add(btnNewButton);
	     
	     JRadioButton rdbtnRestApi = new JRadioButton("REST API");
	     rdbtnRestApi.setBounds(438, 224, 109, 23);
	     getContentPane().add(rdbtnRestApi);
	     
	     JLabel lblNewLabel_1 = new JLabel("New label");
	     lblNewLabel_1.setBounds(0, 0, 584, 217);
	     getContentPane().add(lblNewLabel_1);
	     lblNewLabel_1.setIcon(new ImageIcon(Constants.DefaultDirectory+"\\framework\\images\\Api_logo.png"));

	}
}
