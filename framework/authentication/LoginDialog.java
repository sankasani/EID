package authentication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import app.UI.APISelection;
import app.UI.BrowserSelection;
import app.UI.MainWindow;
import common.Constants;
import common.GenericLibrary;

import javax.swing.JComboBox;
import javax.swing.JDialog;;

public class LoginDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l1, l2, l3;
	static JTextField tf1;
	private JButton btn1;
	JPasswordField p1;
	private JButton btnCancel;
	private JLabel lblApplication;
	public static String userName;
	public static ArrayList<String> application;
	private JLabel lblNewLabel;
	public static String Role;
	public String appTypeName;

	public LoginDialog() {
		JFrame frame = new JFrame("Enterprise Initiative Delivery Login");
		ImageIcon img = new ImageIcon(Constants.DefaultDirectory+"\\framework\\images\\TCB_fav_Icon.png");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setIconImage(img.getImage());

		l1 = new JLabel("");
		l1.setForeground(Color.blue);
		l1.setIcon(new ImageIcon(Constants.DefaultDirectory+"\\framework\\images\\TCB_logo.png"));

		l1.setFont(new Font("Serif", Font.BOLD, 20));

		l2 = new JLabel("Username:");
		l2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		l2.setForeground(Color.WHITE);
		l3 = new JLabel("Password:");
		l3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		l3.setForeground(Color.WHITE);
		tf1 = new JTextField();
		p1 = new JPasswordField();

		btn1 = new JButton("Login");
		btn1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		frame.getRootPane().setDefaultButton(btn1);
		btn1.requestFocus();
		

		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				tf1.getText();
				p1.getText();

				if(application.size()==0) {
						JOptionPane.showMessageDialog(LoginDialog.this, "Please Select an application to proceed", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else if(application.size()!=0){
					appTypeName = application.get(0);
					if(appTypeName.equals("All")) {
						appTypeName="Admin";
					}
					if (Login.authenticate(getUsername(), getPassword(), appTypeName)) {
						frame.dispose();							

						try {
														
							Files.createDirectories(Paths.get(Constants.Results));
							Files.createDirectories(Paths.get(Constants.Screenshots));
							Files.createDirectories(Paths.get(Constants.Email));
							GenericLibrary.driverCopytoLocal();

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						
						if (appTypeName.equals("WebApplications")) {
							BrowserSelection bs = new BrowserSelection();
							bs.setVisible(true);
						} else if (appTypeName.equals("WebServices")) {
							APISelection as = new APISelection();
							as.setVisible(true);
						} else if (appTypeName.equals("Database")) {
							try {
								new MainWindow(appTypeName,"");				
							} catch (SAXException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParserConfigurationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						} else if(appTypeName.equals("Admin")) {
					
							try {
								new MainWindow(appTypeName,"");	
								System.gc();
							} catch (SAXException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParserConfigurationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						} else if(appTypeName.equals("DesktopApplications")) {
							try {
								new MainWindow(appTypeName,"");				
							} catch (SAXException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParserConfigurationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(appTypeName.equals("MobileApplications")) {
							try {
								new MainWindow(appTypeName,"");				
							} catch (SAXException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParserConfigurationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(LoginDialog.this, "Unathorized Access or Incorrect login or password", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					
					
				}


			}
		});

		btn1.addKeyListener(new SubmitButton() {

			public void keyPressed(KeyEvent e) {
				btn1.requestFocus(true);

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					if (Login.authenticate(getUsername(), getPassword(),appTypeName)) {
						userName = getUsername();
						frame.dispose();
						BrowserSelection bs = new BrowserSelection();
						bs.setVisible(true);
						JLabel label = new JLabel("Welcome:");
						bs.getContentPane().add(label);
					} else {
						JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
								JOptionPane.ERROR_MESSAGE);
						// reset username and password
						tf1.setText(getUsername());
						p1.setText("");
					}
				}

			}
		});

		l1.setBounds(0, 0, 394, 84);
		l2.setBounds(10, 167, 73, 30);
		l3.setBounds(10, 208, 73, 30);
		tf1.setBounds(103, 168, 235, 30);
		p1.setBounds(103, 209, 235, 30);
		btn1.setBounds(108, 262, 100, 30);
	//	btn1.setIcon(new ImageIcon(Constants.DefaultDirectory+"\\framework\\images\\Login.png"));


		frame.getContentPane().add(l1);
		frame.getContentPane().add(l2);
		frame.getContentPane().add(tf1);
		frame.getContentPane().add(l3);
		frame.getContentPane().add(p1);
		frame.getContentPane().add(btn1);

		frame.setSize(400, 400);
		frame.setLocationRelativeTo(frame);

		frame.getContentPane().setLayout(null);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});

		btnCancel.addKeyListener(new SubmitButton() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("Keyboard1");
					frame.dispose();
				}

			}
		});

		btnCancel.setBounds(233, 262, 100, 30);
		frame.getContentPane().add(btnCancel);

		lblApplication = new JLabel("Application");
		lblApplication.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblApplication.setForeground(Color.WHITE);
		lblApplication.setBounds(10, 126, 73, 30);
		frame.getContentPane().add(lblApplication);

		application = new ArrayList<String>();
		String appTypes[] = { "Select an Application", "All","Database", "DesktopApplications","MobileApplications","WebApplications", "WebServices"};
		JComboBox<Object> comboBox = new JComboBox<Object>(appTypes);
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				application.add(comboBox.getSelectedItem().toString());
				if (application.size() > 0) {
					application.set(0, comboBox.getSelectedItem().toString());
				}
			}
		});
		comboBox.setBounds(103, 126, 235, 30);
		frame.getContentPane().add(comboBox);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 317, 394, 54);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Constants.DefaultDirectory+"\\framework\\images\\Hitachi_logo.jpeg"));
		frame.setResizable(false);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static String getUsername() {
		return tf1.getText().trim();
	}

	public String getPassword() {
		return new String(p1.getPassword());
	}
	
	public static String roleName() {
		
		String roleName = application.get(0);
		if(roleName.equals("All")) {
			roleName = "Admin";
		}
		
		return new String(roleName);
	}

}
