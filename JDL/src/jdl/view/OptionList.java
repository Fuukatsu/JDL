package jdl.view;


import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import jdl.controller.CSVLoaderMain;
import jdl.controller.EmailFunctions;
import jdl.controller.Runner;
import jdl.controller.objectFilter;
import jdl.dao.Queries;
import jdl.model.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OptionList extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7050098502031098232L;

	/**
	 * Create the application.
	 */
	public OptionList() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptionList.class.getResource("/jdl/Assets/login_small.png")));
		
		//Main Panel
		
		setTitle("JDL: Options");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(690, 480));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		getContentPane().setLayout(null);
		
		//Images
		
		JLabel options_tableIcon = new JLabel("");
		options_tableIcon.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		options_tableIcon.setToolTipText("<html> This is where you can create client information, create transactions\r\n<br> as well as create transaction's status and remark. <html>");
		options_tableIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) 
			{
				Runner.destroyOptionList();
				Runner.openTables();
			}
		});
		
		JLabel options_close = new JLabel("");
		options_close.setFont(new Font("Dialog", Font.BOLD, 13));
		options_close.setToolTipText("Minimize");
		options_close.setBounds(654, 0, 26, 46);
		getContentPane().add(options_close);
		options_close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		options_close.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel options_profileView = new JLabel("");
		options_profileView.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		options_profileView.setToolTipText("<html>View or edit your user account credentials as well as your primary and\r\n<br>secondary information.</html>");
		options_profileView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) 
			{				
				Runner.destroyOptionList();
				Runner.openViewAdminAcc();
			}
		});
		
		options_profileView.setBounds(10, 11, 26, 29);
		getContentPane().add(options_profileView);
		options_profileView.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/button_viewUser.png")));
		
		JLabel options_logout = new JLabel("");
		options_logout.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		options_logout.setToolTipText("<html>Logout.</html>");
		options_logout.setBounds(140, 10, 37, 29);
		getContentPane().add(options_logout);
		options_logout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
				 	UIManager.put("Button.background", Color.WHITE);
				 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
				 	
				 
			    int reply = JOptionPane.showConfirmDialog(null, "<html><font color = #ffffff> Are you sure you want to logout of the system? </font color = #ffffff></html>", "Proceed to Logout?", JOptionPane.YES_NO_OPTION);
			    	if (reply == JOptionPane.YES_OPTION) {
			    		Runner.setUser(null);
			    		Runner.openLogin();
			    		Runner.destroyOptionList();
			    	}
			}
		});
		options_logout.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/button_logout.png")));
		
		JLabel options_welcomeLbl = new JLabel("Welcome Administrator");
		options_welcomeLbl.setBounds(263, 0, 168, 46);
		getContentPane().add(options_welcomeLbl);
		options_welcomeLbl.setForeground(new Color(255, 255, 255));
		options_welcomeLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		options_tableIcon.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/options_tableSheet.png")));
		options_tableIcon.setBounds(159, 148, 52, 58);
		getContentPane().add(options_tableIcon);
		
		JLabel options_reportIcon = new JLabel("");
		options_reportIcon.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		options_reportIcon.setToolTipText("<html>Generate and view a list for recently entered transactions within a selected \r\n<br>time span or generate and view list of any upcoming expiring dates of any\r\n<br>kind of transactions.");
		options_reportIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Runner.destroyOptionList();
				Runner.openGR();
			}
		});
		options_reportIcon.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/options_generateReport.png")));
		options_reportIcon.setBounds(477, 148, 52, 58);
		getContentPane().add(options_reportIcon);
		
		JLabel options_manageIcon = new JLabel("");
		options_manageIcon.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		options_manageIcon.setToolTipText("<html> Create, view and delete accounts who gained access to this system \r\n<br>and create or edit their primary and secondary information useful for\r\n<br> your employee references. </html>");
		options_manageIcon.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				Runner.destroyOptionList();
				Runner.openAccountManagement();
			}
		});
		options_manageIcon.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/options_management.png")));
		options_manageIcon.setBounds(149, 322, 62, 58);
		getContentPane().add(options_manageIcon);
		
		JLabel options_historyIcon = new JLabel("");
		options_historyIcon.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		options_historyIcon.setToolTipText("<html>Views a list of entries of a specific user of this system who either created or\r\n<br>deleted any kind of transaction from the database. Keeps track of those who \r\n<br>are responsible of entering of deleting a data.</html>");
		options_historyIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Runner.destroyOptionList();
				Runner.openAH();
			}
		});
		options_historyIcon.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/options_history.png")));
		options_historyIcon.setBounds(477, 322, 62, 58);
		getContentPane().add(options_historyIcon);
		
		//Labels
		
		JLabel options_instructionLbl = new JLabel("What do you want to do?");
		options_instructionLbl.setForeground(Color.WHITE);
		options_instructionLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		options_instructionLbl.setBounds(231, 76, 232, 29);
		getContentPane().add(options_instructionLbl);
		
		JLabel options_tableLbl = new JLabel("SEE TABLES");
		options_tableLbl.setForeground(Color.WHITE);
		options_tableLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		options_tableLbl.setHorizontalAlignment(SwingConstants.CENTER);
		options_tableLbl.setBounds(106, 220, 151, 25);
		getContentPane().add(options_tableLbl);
		
		JLabel options_reportLbl = new JLabel("GENERATE REPORT");
		options_reportLbl.setHorizontalAlignment(SwingConstants.CENTER);
		options_reportLbl.setForeground(Color.WHITE);
		options_reportLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		options_reportLbl.setBounds(429, 220, 166, 25);
		getContentPane().add(options_reportLbl);
		
		JLabel options_manageLbl = new JLabel("MANAGE ACCOUNTS");
		options_manageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		options_manageLbl.setForeground(Color.WHITE);
		options_manageLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		options_manageLbl.setBounds(92, 391, 188, 25);
		getContentPane().add(options_manageLbl);
		
		JLabel options_historyLbl = new JLabel("<HTML><CENTER>TRANSACTION <br>ACTIVITY HISTORY</br></CENTER></HTML>");
		options_historyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		options_historyLbl.setForeground(Color.WHITE);
		options_historyLbl.setFont(new Font("Segoe UI Semilight", Font.BOLD, 15));
		options_historyLbl.setBounds(429, 391, 172, 44);
		getContentPane().add(options_historyLbl);
		
		JLabel options_upload = new JLabel("");
		options_upload.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		options_upload.setToolTipText("<html>Upload a CSV-formatted file.</html>");
		options_upload.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				CSVLoaderMain csvImport = new CSVLoaderMain();
				csvImport.run();
			}
		});
		options_upload.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/button_upload.png")));
		options_upload.setBounds(42, 11, 26, 29);
		getContentPane().add(options_upload);
		
		JLabel options_faq = new JLabel("");
		options_faq.setToolTipText("FAQ (Frequently Asked Questions)");
		options_faq.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		options_faq.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Runner.openFAQ();
			}
		});
		options_faq.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/options_FAQ.png")));
		options_faq.setBounds(109, 12, 31, 29);
		getContentPane().add(options_faq);
		
		JLabel options_sendEmail = new JLabel("");
		try {
    		String date = objectFilter.addDay(objectFilter.getDateToday());
    		Date d;
			d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			boolean dateCheck = Queries.checkNotification(new java.sql.Date(d.getTime()));
			if(dateCheck == false) {
				options_sendEmail.setEnabled(false);
			}
    	}
			catch (ParseException e1) {
			e1.printStackTrace();
		}	
		
		if(options_sendEmail.isEnabled() == true) {
		
			options_sendEmail.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			Runner.openES();
			Runner.destroyOptionList();
		}
		});
		}
		options_sendEmail.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/options_sendEmailExp.png")));
		options_sendEmail.setToolTipText("<html> Send email to expiring clients (Note: This will pause any <br>of your currently running operations.) </html>");
		options_sendEmail.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		options_sendEmail.setBounds(75, 11, 37, 29);
		getContentPane().add(options_sendEmail);
		

		JLabel options_background = new JLabel("");
		options_background.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/background_optionList4.jpg")));
		options_background.setBounds(0, 0, 690, 480);
		getContentPane().add(options_background);
		
		
		int ifAdmin = Runner.getUser().getUser_ifAdmin();
		
		if (ifAdmin == 0) {
			options_manageIcon.setVisible(false);
			options_manageLbl.setVisible(false);
		
			options_historyIcon.setVisible(false);
			options_historyLbl.setVisible(false);
			
			options_tableIcon.setBounds(161, 214, 52, 58);
			options_tableLbl.setBounds(108, 286, 151, 25);
			
			options_reportIcon.setBounds(477, 214, 52, 58);
			options_reportLbl.setBounds(429,286, 166, 25);
			
			options_welcomeLbl.setText("Welcome Employee");
			options_welcomeLbl.setBounds(275, 0, 168, 46);
			
		}
		
	}
}