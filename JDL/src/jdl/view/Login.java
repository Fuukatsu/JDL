package jdl.view;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.Border;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import jdl.controller.Runner;
import jdl.controller.loginFunction;
import jdl.controller.objectFilter;
import jdl.dao.Queries;
import jdl.dao.databaseProperties;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Login extends JFrame {

	/**
	 * 
	 */
	static databaseProperties dP = new databaseProperties();
	private static final long serialVersionUID = 2581971424563374008L;
	private JTextField login_usernameTxt;
	private JPasswordField login_passwordTxt;
	private JLabel login_password;
	public Login() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptionList.class.getResource("/jdl/Assets/login_small.png")));
		
		//Main Panel
		
		setTitle("JDL: Login");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
		setMinimumSize(new Dimension(850, 550));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(16,16,16));
		rightPanel.setBounds(417, 0, 433, 550);
		rightPanel.setLayout(null);
		
		getContentPane().add(rightPanel);
		

		
		//Labels
		
		JLabel login_usernameTitle = new JLabel("Username:");
		login_usernameTitle.setForeground(new Color(255, 255, 255));
		login_usernameTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		login_usernameTitle.setBounds(67, 264, 113, 25);
		login_usernameTitle.setBorder(emptyBorder);
		rightPanel.add(login_usernameTitle);
		
		JLabel login_passwordTitle = new JLabel("Password:");
		login_passwordTitle.setForeground(Color.WHITE);
		login_passwordTitle.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		login_passwordTitle.setBounds(67, 340, 113, 25);
		login_passwordTitle.setBorder(emptyBorder);
		rightPanel.add(login_passwordTitle);
		
		JLabel login_title = new JLabel("JDL", SwingConstants.CENTER);
		login_title.setBounds(137, 294, 146, 69);
		getContentPane().add(login_title);
		login_title.setForeground(Color.WHITE);
		login_title.setFont(new Font("Gugi", Font.PLAIN, 50));
		
		JLabel login_titleDesc = new JLabel("Business and Immigration Consultancy", SwingConstants.CENTER);
		login_titleDesc.setForeground(Color.WHITE);
		login_titleDesc.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));
		login_titleDesc.setBounds(18, 339, 389, 45);
		getContentPane().add(login_titleDesc);
		
		JLabel login_error = new JLabel("<html><center> Invalid username or password. <br>Please be aware of uppercases. </center></html>");
		login_error.setHorizontalAlignment(SwingConstants.CENTER);
		login_error.setBounds(67, 424, 313, 47);
		login_error.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		login_error.setForeground(new Color(255, 255, 255));
		login_error.setVisible(false);
		rightPanel.add(login_error);
		
		JLabel login_error1 = new JLabel("<html><center> Username or Password field is blank. </center></html>");
		login_error1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		login_error1.setForeground(new Color(255, 255, 255));
		login_error1.setBounds(111, 424, 249, 45);
		login_error1.setVisible(false);
		rightPanel.add(login_error1);
		
		JLabel login_error2 = new JLabel("<html><center> Password is too short. <br> It must be minimum of 8. </br></center></html>");
		login_error2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		login_error2.setForeground(new Color(255, 255, 255));
		login_error2.setBounds(154, 428, 164, 39);
		login_error2.setVisible(false);
		rightPanel.add(login_error2);
		
		JLabel login_success = new JLabel("<html><center> Attempting to Login. Please Wait. </center></html>");
		login_success.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		login_success.setForeground(new Color(255, 204, 51));
		login_success.setBounds(121, 424, 210, 47);
		login_success.setVisible(false);
		rightPanel.add(login_success);
		
		//buttons
		
		JButton login_loginBtn = new JButton("Login");
		
		login_loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String tUsername = login_usernameTxt.getText().trim();
				String tPassword = new String(login_passwordTxt.getPassword());
				if(tPassword.length() < 8)
				{
					login_error.setVisible(false);
					login_error1.setVisible(false);
					login_error2.setVisible(true);
				}
				else
				if(tUsername.length() == 0 || tPassword.length() == 0)
				{
					login_error1.setVisible(true);
					login_error2.setVisible(false);
					login_error.setVisible(false);
				}
				else
				{
					Runner.setUser(loginFunction.attemptLogin(tUsername, tPassword));
					if(Runner.getUser() != null)
					{
						login_success.setVisible(true);
						Runner.destroyLogin();
						Runner.openOptionList();
						
						String date = objectFilter.addDay(objectFilter.getDateToday());
						Date d;
						
						try {
							UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
						 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
						 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
						 	UIManager.put("Button.background", Color.WHITE);
						 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
						 	
							d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
							boolean dateCheck = Queries.checkNotification(new java.sql.Date(d.getTime()));
							if(dateCheck == false) {
			                	JOptionPane.showMessageDialog(null, "<html><center><font color = #ffffff> We already sent emails today, "+date+", to your clients notifying<br>"
			                			+ "that their visas or permits are about to expire. To view the list of <br>"
							 			+ "notified clients, kindly generate the report for the expiring dates.</br></center></font color = #ffffff></html>", "Emails Sent Already", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><center><font color = #ffffff> You haven't sent any messages today so far. Please send emails to<br>"
										+ "your clients in order to inform them about their current status. Click<br>"
										+ "the paper plane button at the top-left corner of your main screen.</center></font color = #ffffff></html>", "Forgetting something?", JOptionPane.INFORMATION_MESSAGE);
						}
							} catch (ParseException e1) {
							e1.printStackTrace();
						}
						
						
						
						UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
					 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
					 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
					 	UIManager.put("Button.background", Color.WHITE);
					 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));

					}
					{
						login_error1.setVisible(false);
						login_error2.setVisible(false);
						login_error.setVisible(true);
					}
				}
			}
		});
		
		
		login_loginBtn.setForeground(Color.BLACK);
		login_loginBtn.setBackground(new Color(255, 204, 102));
		login_loginBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		login_loginBtn.setBounds(138, 475, 180, 37);
		login_loginBtn.setBorder(emptyBorder);
		rightPanel.add(login_loginBtn);
		
		JLabel login_close = new JLabel("");
		login_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		//Textfields
		
		login_usernameTxt = new JTextField();
		login_usernameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		login_usernameTxt.setForeground(Color.BLACK);
		login_usernameTxt.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		login_usernameTxt.setBounds(101, 300, 279, 37);
		login_usernameTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {							
					login_loginBtn.doClick();							
                }
			}
		});
		rightPanel.add(login_usernameTxt);
		login_usernameTxt.setColumns(10);
		
		login_passwordTxt = new JPasswordField();
		login_passwordTxt.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER)
		                {							
							login_loginBtn.doClick();							
		                }
					}
				});
		
		login_passwordTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		login_passwordTxt.setForeground(Color.BLACK);
		login_passwordTxt.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		login_passwordTxt.setBounds(101, 376, 279, 37);
		rightPanel.add(login_passwordTxt);
		
		//Images
		
		JLabel login_adminLogo = new JLabel("");
		login_adminLogo.setIcon(new ImageIcon(Login.class.getResource("/jdl/Assets/login_adminLogo.png")));
		login_adminLogo.setBounds(125, 76, 202, 177);
		rightPanel.add(login_adminLogo);
		
		login_close.setIcon(new ImageIcon(Login.class.getResource("/jdl/Assets/button_close.png")));
		login_close.setBounds(398, 11, 25, 25);
		rightPanel.add(login_close);
		
		JLabel login_userName = new JLabel("");
		login_userName.setBackground(new Color(16,16,16));
		login_userName.setIcon(new ImageIcon(Login.class.getResource("/jdl/Assets/login_userName.png")));
		login_userName.setBounds(67, 300, 33, 37);
		rightPanel.add(login_userName);
		
		login_password = new JLabel("");
		login_password.setIcon(new ImageIcon(Login.class.getResource("/jdl/Assets/login_password.png")));
		login_password.setBounds(67, 376, 33, 37);
		rightPanel.add(login_password);
		
		JLabel login_background1 = new JLabel("");
		login_background1.setIcon(new ImageIcon(Login.class.getResource("/jdl/Assets/background_tables4.jpg")));
		login_background1.setBounds(0, 0, 433, 550);
		rightPanel.add(login_background1);
		
		JLabel login_logo = new JLabel("");
		login_logo.setIcon(new ImageIcon(Login.class.getResource("/jdl/Assets/login_JDLogo.png")));
		login_logo.setBounds(108, 135, 200, 173);
		getContentPane().add(login_logo);
		
		JLabel login_background = new JLabel("");
		login_background.setIcon(new ImageIcon(Login.class.getResource("/jdl/Assets/login_consultation.png")));
		login_background.setBounds(0, 0, 415, 550);
		getContentPane().add(login_background);
		
		
	}
}
