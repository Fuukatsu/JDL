package jdl.view;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import jdl.controller.AutoCompletion;
import jdl.controller.Runner;
import jdl.controller.objectFilter;
import jdl.dao.Queries;
import jdl.dao.databaseProperties;
import jdl.model.Transaction;

import java.util.Properties;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;

public class TablesDeleteTransaction extends JFrame{
	private JTextField tables_clientBirthdateTxt;
	private String clientSelectedName;
	private boolean tables_validator = true;
	private JTextField client_userIdTxt;
	private Transaction t;
	private databaseProperties dP = new databaseProperties();
	public TablesDeleteTransaction() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Account Delete");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(488, 379));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		//Input Section
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(22, 96, 441, 263);
		tables_inputPanel.setBackground(new Color (255,255,255,60));
		tables_inputPanel.setLayout(null);
		
		JComboBox client_transactionBox = new JComboBox(new Object[]{});
		client_transactionBox.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		client_transactionBox.setEditable(true);
		client_transactionBox.setBounds(20, 126, 400, 29);
		tables_inputPanel.add(client_transactionBox);
		
		JComboBox client_comboBox = new JComboBox(objectFilter.getClientList());
		client_comboBox.setEditable(true);
		AutoCompletion.enable(client_comboBox);
		
		client_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn;
				try {
					conn = DriverManager.getConnection(dP.url, dP.username, dP.password);
					String sql = "SELECT * FROM jdl_accounts.clients WHERE client_id=?";
					String sql1 = "SELECT * FROM jdl_accounts.transactions WHERE client_id=? AND trans_isActive = 1 OR null";
					
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					PreparedStatement statement1 = (PreparedStatement) conn.prepareStatement(sql1);
					
					String info = (String)client_comboBox.getSelectedItem().toString();
	
					if(client_comboBox.getSelectedItem().toString() == "Click to see the list of registered client") {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Please Select a Client</font color = #ffffff></html>", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
					}
					else {
					int temp = Integer.parseInt(info.substring(info.lastIndexOf(":")+2, info.length()));
					System.out.print(info);
					client_userIdTxt.setText(String.valueOf(temp));
					statement.setInt(1, temp);
					
					statement.executeQuery();
					
					client_transactionBox.removeAllItems();
					statement1.setInt(1, temp);
					ResultSet rs1 = statement1.executeQuery();
					while(rs1.next()) {
						client_transactionBox.addItem(rs1.getString("trans_transId"));
					}
					}
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		client_comboBox.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		client_comboBox.setBounds(20, 42, 318, 29);
		
		tables_inputPanel.add(client_comboBox);
		
		JLabel emp_assignLbl = new JLabel("Select Transaction From:");
		emp_assignLbl.setForeground(Color.WHITE);
		emp_assignLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_assignLbl.setBounds(20, 0, 231, 41);
		tables_inputPanel.add(emp_assignLbl);
		
		JLabel emp_userIdLbl = new JLabel("User ID:");
		emp_userIdLbl.setForeground(Color.WHITE);
		emp_userIdLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_userIdLbl.setBounds(348, 0, 63, 41);
		tables_inputPanel.add(emp_userIdLbl);
		
		client_userIdTxt = new JTextField();
		client_userIdTxt.setEditable(false);
		client_userIdTxt.setBounds(348, 42, 72, 29);
		tables_inputPanel.add(client_userIdTxt);
		client_userIdTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		client_userIdTxt.setColumns(10);
		client_userIdTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		//Birthdate
		
		UtilDateModel birthdateModel = new UtilDateModel();
		Properties birthdate = new Properties();
		birthdate.put("text.today", "Date Today");
		birthdate.put("text.month", "Month");
		birthdate.put("text.year", "Year");
		birthdateModel.setDate(1980, 1, 1);

		JDatePanelImpl BirthdatePanel = new JDatePanelImpl(birthdateModel, birthdate);
		
		JLabel emp_LastnameLbl = new JLabel("Select Transaction ID:");
		emp_LastnameLbl.setForeground(Color.WHITE);
		emp_LastnameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_LastnameLbl.setBounds(20, 84, 190, 41);
		tables_inputPanel.add(emp_LastnameLbl);
		getContentPane().setLayout(null);
		
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		
		JLabel label = new JLabel("");
		label.setBounds(1178, 48, 57, 37);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_allClientTransactionLbl = new JLabel("Registered Clients");
		tables_allClientTransactionLbl.setBounds(495, 158, 171, 37);
		tables_allClientTransactionLbl.setForeground(Color.WHITE);
		tables_allClientTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		getContentPane().add(label);
		getContentPane().add(tables_inputPanel);
		
		JButton tables_registerBtn = new JButton("Delete This Transaction");
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection(dP.url, dP.username, dP.password);
					String sql1 = "UPDATE jdl_accounts.transactions SET trans_transAuthor = ?, trans_transAction = ?, trans_isActive = ? WHERE trans_transId = ?";
					String sql2 = "DELETE FROM jdl_accounts.status_visa WHERE trans_transId = ?";
					String sql3 = "DELETE FROM jdl_accounts.status_permits WHERE trans_transId = ?";
					String sql4 = "DELETE FROM jdl_accounts.remarks WHERE trans_transId = ?";
					
					PreparedStatement statement1 = (PreparedStatement) conn.prepareStatement(sql1);
					PreparedStatement statement2 = (PreparedStatement) conn.prepareStatement(sql2);
					PreparedStatement statement3 = (PreparedStatement) conn.prepareStatement(sql3);
					PreparedStatement statement4 = (PreparedStatement) conn.prepareStatement(sql4);
					
					if(client_userIdTxt.getText().trim().equals("")) {
						statement1.setString(1, null);
						statement2.setString(1, null);
						statement3.setString(1, null);
						statement4.setString(1, null);
					}
					else {
						statement1.setString(1, Runner.getUser().getUser_username());
						statement1.setString(2, "DELETED");
						statement1.setInt(3, 0);
						statement1.setInt(4, Integer.parseInt(client_transactionBox.getSelectedItem().toString()));
		
						statement2.setInt(1, Integer.parseInt(client_transactionBox.getSelectedItem().toString()));
						statement3.setInt(1, Integer.parseInt(client_transactionBox.getSelectedItem().toString()));
						statement4.setInt(1, Integer.parseInt(client_transactionBox.getSelectedItem().toString()));
					}
					
					UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
				 	UIManager.put("Button.background", Color.WHITE);
				 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
				 	
				 	if(client_userIdTxt.getText().trim().equals("")) {
				 		JOptionPane.showMessageDialog(null, "<html><font color = #ffffff> No client is specified. Please select one. </font color = #ffffff></html>", "No User Selected", JOptionPane.INFORMATION_MESSAGE);
				 	}
				 	else {
				 		int message = JOptionPane.showConfirmDialog(null, "<html><font color = #ffffff> Are you sure you want to delete this transaction? This will"
				 			+ "<br>also delete any status and remarks of this transaction.</br></font color = #ffffff></html>", "Delete this Transaction?", JOptionPane.YES_NO_OPTION);
				 			if (message == JOptionPane.YES_OPTION) {
				 				statement1.executeUpdate();
				 				statement2.executeUpdate();
				 				statement3.executeUpdate();
				 				statement4.executeUpdate();
			    		
				 				JOptionPane.showMessageDialog(null, "<html><font color = #ffffff> Transaction Successfully deleted. </font color = #ffffff></html>", "Deleted Successfully", JOptionPane.INFORMATION_MESSAGE);
				 				Runner.destroyTDT();
				 				Runner.openTDT();
			    	}
					
				} 
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		tables_registerBtn.setForeground(new Color(255, 255, 255));
		tables_registerBtn.setBounds(121, 195, 197, 36);
		tables_inputPanel.add(tables_registerBtn);

		
		tables_registerBtn.setBackground(new Color(0, 102, 102));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel emp_employeeDeleteLbl = new JLabel("Delete a Transaction");
		emp_employeeDeleteLbl.setBounds(158, 2, 168, 41);
		getContentPane().add(emp_employeeDeleteLbl);
		emp_employeeDeleteLbl.setHorizontalAlignment(SwingConstants.CENTER);
		emp_employeeDeleteLbl.setForeground(Color.WHITE);
		emp_employeeDeleteLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel emp_close = new JLabel("");
		emp_close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Runner.destroyTDT();
				Runner.destroyTUT();
				Runner.openTUT();
			}
		});
		emp_close.setIcon(new ImageIcon(TablesDeleteTransaction.class.getResource("/jdl/Assets/button_back.png")));
		emp_close.setBounds(10, 0, 26, 37);
		getContentPane().add(emp_close);
		
		JLabel emp_warningLbl = new JLabel("Note: This will also delete any remarks or status associated to this transaction");
		emp_warningLbl.setForeground(new Color(255, 255, 255));
		emp_warningLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
		emp_warningLbl.setBounds(23, 64, 441, 21);
		getContentPane().add(emp_warningLbl);
		
		JLabel emp_background = new JLabel("New label");
		emp_background.setIcon(new ImageIcon(TablesDeleteTransaction.class.getResource("/jdl/Assets/background_tables4.jpg")));
		emp_background.setBounds(0, 0, 488, 379);
		getContentPane().add(emp_background);
		

	}
}

