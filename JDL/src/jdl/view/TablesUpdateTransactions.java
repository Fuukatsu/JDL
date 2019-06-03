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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import jdl.controller.AutoCompletion;
import jdl.controller.DateLabelFormatter;
import jdl.controller.Runner;
import jdl.controller.TableColumnAdjuster;
import jdl.controller.objectFilter;
import jdl.dao.Queries;
import jdl.dao.databaseProperties;
import jdl.model.Transaction;

import java.util.Calendar;
import java.util.Date;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;

public class TablesUpdateTransactions extends JFrame{
	private JTextField tables_passportNoTxt;
	private JTextField tables_tinIdTxt;
	private JTextField tables_aepIdTxt;
	private String clientSelectedName;
	private JTable table_1;
	private String client_id = "";
	private JTextField adminAcc_usernameTxt;
	private JTextField adminAcc_passwordTxt;
	private JComboBox tables_comboBox1;
	private TableModel tm;
	private databaseProperties dP = new databaseProperties();
	private JTextField tables_searchTxt;

	/**
	 * Create the application.
	 */
	public TablesUpdateTransactions() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Transactions (Update)");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(1550, 870));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setSize(1040, 289);
		scrollPane_1.setLocation(493, 208);
		
		table_1 = new JTable();
		table_1.setForeground(Color.DARK_GRAY);
		table_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		table_1.setRowHeight(32);
		table_1.setBorder(null);
		table_1.setBounds(492, 217, 1040, 138);

		
		JTableHeader header_1 = table_1.getTableHeader();
		header_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
	    header_1.setBackground(new Color(155, 177, 166));
	    header_1.setForeground(Color.WHITE);
		scrollPane_1.setViewportView(table_1);
		
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		//Input Section (Declaration of Panel) and Client_id Textfield
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(25, 137, 450, 710);
		tables_inputPanel.setBackground(new Color(255,255,255,60));
		tables_inputPanel.setLayout(null);
		
		//Buttons
		
		JComboBox tables_comboBox = new JComboBox();
		tables_comboBox.setEditable(true);
		tables_comboBox.addItem("Click to see the list of registered client");
		Connection conn1;
		try {
			conn1 = DriverManager.getConnection(dP.url, dP.username, dP.password);
			Statement stat=conn1.createStatement();
			ResultSet rs1=stat.executeQuery("SELECT * FROM jdl_accounts.clients WHERE client_isActive = 1 OR null");
			 while(rs1.next()){        
				 	String client_lastname = rs1.getString("client_lastname");
				 	String client_firstname = rs1.getString("client_firstname");
				 	client_id = rs1.getString("client_id");
			
			       	tables_comboBox.addItem(client_lastname+", "+client_firstname+", "+client_id);
			       
			       	clientSelectedName = tables_comboBox.getSelectedItem().toString();
			       	
			    }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		tables_comboBox.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		tables_comboBox.setBounds(20, 77, 400, 29);
		tables_inputPanel.add(tables_comboBox);
		AutoCompletion.enable(tables_comboBox);
		
		tables_searchTxt = new JTextField();
		tables_searchTxt.setText("Enter keywords here");
		tables_searchTxt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		tables_searchTxt.setColumns(10);
		tables_searchTxt.setBorder(null);
		tables_searchTxt.setBounds(929, 162, 237, 35);
		getContentPane().add(tables_searchTxt);
		
		JLabel tables_filterIcon = new JLabel("");
		tables_filterIcon.setIcon(new ImageIcon(TablesUpdateTransactions.class.getResource("/jdl/Assets/client_filterIcon.png")));
		tables_filterIcon.setForeground(Color.WHITE);
		tables_filterIcon.setFont(new Font("Segoe UI", Font.BOLD, 15));
		tables_filterIcon.setBounds(893, 162, 35, 37);
		getContentPane().add(tables_filterIcon);
		
		JLabel tables_filterTableLbl = new JLabel("Filter Table:");
		tables_filterTableLbl.setForeground(Color.WHITE);
		tables_filterTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		tables_filterTableLbl.setBounds(799, 162, 89, 37);
		getContentPane().add(tables_filterTableLbl);
		
		JButton tables_reloadBtn = new JButton("Reset and Reload");
		tables_reloadBtn.setBounds(1177, 160, 204, 38);
		tables_reloadBtn.setForeground(new Color(255, 255, 255));
		tables_reloadBtn.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/main_refresh.png")));
		tables_reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(tables_comboBox.getSelectedItem() != null)
				{
					if(tables_comboBox.getSelectedItem().toString().equals(""))
						client_id = "-1";
					else
						client_id = tables_comboBox.getSelectedItem().toString().split(",")[2].trim();
				}
				//System.out.println(tables_comboBox1.getSelectedItem().toString());
				try 
				{
					Connection conn=DriverManager.getConnection(dP.url, dP.username, dP.password);
					Statement stat=conn.createStatement();
					Statement stat1=conn.createStatement();
	
					ResultSet rs1 = stat1.executeQuery("SELECT client_id AS 'Client ID',"
							+ "trans_transId AS 'Transaction ID'" +
							", trans_passportNo AS 'Passport No' "+ 
							", trans_tinID AS 'TIN ID' " + 
							", trans_visaType AS 'Visa Type' " + 
							", trans_visaStartDate AS 'Visa Start Date' " + 
							", trans_visaEndDate AS 'Visa Expiry Date' " + 
							", trans_permitType AS 'Permit Type' " + 
							", trans_permitStartDate AS 'Permit Start Date' " + 
							", trans_permitEndDate AS 'Permit Expiry Date' " + 
							", trans_aepID AS 'AEP ID' " + 
							", trans_aepStartDate AS 'AEP Start Date' " + 
							", trans_aepEndDate AS 'AEP Expiry Date' " + 
							" FROM jdl_accounts.transactions WHERE client_id = "+Integer.parseInt(client_id)+" AND trans_isActive = 1 OR null ORDER BY trans_transId");
					tm = DbUtils.resultSetToTableModel(rs1);
					table_1.setModel(tm);
				
					tables_searchTxt.setText("");
					table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_1.getModel());
					table_1.setRowSorter(sorter);
					
					tables_searchTxt.getDocument().addDocumentListener(new DocumentListener(){

						@Override
						public void insertUpdate(DocumentEvent e) {
							 String text = tables_searchTxt.getText();

				                if (text.trim().length() == 0) {
				                    sorter.setRowFilter(null);
				                } else {
				                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				                }
				            
							
						}

						@Override
						public void removeUpdate(DocumentEvent e) {
							 String text = tables_searchTxt.getText();

				                if (text.trim().length() == 0) {
				                    sorter.setRowFilter(null);
				                } else {
				                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				                }						
						}

						@Override
						public void changedUpdate(DocumentEvent e) {
							 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			            }
					 });
					
					
					TableColumnAdjuster tca1 = new TableColumnAdjuster(table_1);
					tca1.adjustColumns();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		tables_reloadBtn.setBackground(new Color(0, 102, 102));
		tables_reloadBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		tables_reloadBtn.setBorder(null);
		tables_reloadBtn.setBorder(null);
		
		//Input Section (Labels and Associated Textfields)
		
		adminAcc_usernameTxt = new JTextField();
		adminAcc_usernameTxt.setBounds(10, 839, 0, 0);
		getContentPane().add(adminAcc_usernameTxt);
		adminAcc_usernameTxt.setColumns(10);
		
		adminAcc_passwordTxt = new JTextField();
		adminAcc_passwordTxt.setColumns(10);
		adminAcc_passwordTxt.setBounds(10, 839, 0, 0);
		getContentPane().add(adminAcc_passwordTxt);
		
		JLabel tables_inputSectionLbl = new JLabel("Input Section");
		tables_inputSectionLbl.setBounds(25, 96, 255, 41);
		tables_inputSectionLbl.setForeground(new Color(255, 255, 255));
		tables_inputSectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		
		JLabel tables_passportNoLbl = new JLabel("Passport Number:");
		tables_passportNoLbl.setForeground(new Color(255, 255, 255));
		tables_passportNoLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_passportNoLbl.setBounds(20, 190, 204, 41);
		tables_inputPanel.add(tables_passportNoLbl);
		
		tables_passportNoTxt = new JTextField();
		tables_passportNoTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_passportNoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_passportNoTxt.setBounds(20, 227, 400, 23);
		tables_inputPanel.add(tables_passportNoTxt);
		tables_passportNoTxt.setColumns(10);
		
		JLabel tables_tinIdLbl = new JLabel("TIN ID:");
		tables_tinIdLbl.setForeground(new Color(255, 255, 255));
		tables_tinIdLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_tinIdLbl.setBounds(20, 254, 197, 29);
		tables_inputPanel.add(tables_tinIdLbl);
		
		tables_tinIdTxt = new JTextField();
		tables_tinIdTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_tinIdTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_tinIdTxt.setColumns(10);
		tables_tinIdTxt.setBounds(20, 283, 400, 23);
		tables_inputPanel.add(tables_tinIdTxt);
		
		JLabel tables_visaLbl = new JLabel("Visa Type:");
		tables_visaLbl.setForeground(new Color(255, 255, 255));
		tables_visaLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_visaLbl.setBounds(20, 310, 190, 29);
		tables_inputPanel.add(tables_visaLbl);
		
		JLabel tables_visaStartLbl = new JLabel("Visa Start Date:");
		tables_visaStartLbl.setForeground(new Color(255, 255, 255));
		tables_visaStartLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_visaStartLbl.setBounds(20, 370, 190, 29);
		tables_inputPanel.add(tables_visaStartLbl);
		
		//Date Input
		
		// Start Dates
	
		//VISA
		UtilDateModel visaModel = new UtilDateModel();
		Properties visa = new Properties();
		visa.put("text.today", "Date Today");
		visa.put("text.month", "Month");
		visa.put("text.year", "Year");
		
		JDatePanelImpl visaDatePanel = new JDatePanelImpl(visaModel, visa);

		JDatePickerImpl visaStartPick = new JDatePickerImpl(visaDatePanel, new DateLabelFormatter());

		visaStartPick.setLocation(230, 401);
		visaStartPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		visaStartPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		visaStartPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		visaStartPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		visaStartPick.setSize(190, 23);

		tables_inputPanel.add(visaStartPick);
		
		//PERMIT
		UtilDateModel permitModel = new UtilDateModel();
		Properties permit = new Properties();
		permit.put("text.today", "Date Today");
		permit.put("text.month", "Month");
		permit.put("text.year", "Year");
		
		JDatePanelImpl permitDatePanel = new JDatePanelImpl(permitModel, visa);

		JDatePickerImpl permitStartPick = new JDatePickerImpl(permitDatePanel, new DateLabelFormatter());

		permitStartPick.setLocation(20, 510);
		permitStartPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		permitStartPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		permitStartPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		permitStartPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		permitStartPick.setSize(190, 23);

		tables_inputPanel.add(permitStartPick);
		
		//AEP
		UtilDateModel aepModel = new UtilDateModel();
		Properties aep = new Properties();
		aep.put("text.today", "Date Today");
		aep.put("text.month", "Month");
		aep.put("text.year", "Year");
		
		JDatePanelImpl aepDatePanel = new JDatePanelImpl(aepModel, visa);

		JDatePickerImpl aepStartPick = new JDatePickerImpl(aepDatePanel, new DateLabelFormatter());

		aepStartPick.setLocation(20, 619);
		aepStartPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		aepStartPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		aepStartPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		aepStartPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		aepStartPick.setSize(190, 23);
		
		tables_inputPanel.add(aepStartPick);
		
		// End Dates
		
		//VISA
		UtilDateModel visaModel1 = new UtilDateModel();
		Properties visa1 = new Properties();
		visa1.put("text.today", "Date Today");
		visa1.put("text.month", "Month");
		visa1.put("text.year", "Year");

		JDatePanelImpl visaDatePanel1 = new JDatePanelImpl(visaModel1, visa1);

		JDatePickerImpl visaEndPick = new JDatePickerImpl(visaDatePanel1, new DateLabelFormatter());

		visaEndPick.setLocation(20, 401);
		visaEndPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		visaEndPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		visaEndPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		visaEndPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		visaEndPick.setSize(190, 23);
		
		tables_inputPanel.add(visaEndPick);
		
		//PERMIT
		UtilDateModel permitModel1 = new UtilDateModel();
		Properties permit1 = new Properties();
		permit1.put("text.today", "Date Today");
		permit1.put("text.month", "Month");
		permit1.put("text.year", "Year");
		
		JDatePanelImpl permitDatePanel1 = new JDatePanelImpl(permitModel1, permit1);

		JDatePickerImpl permitEndPick = new JDatePickerImpl(permitDatePanel1, new DateLabelFormatter());

		permitEndPick.setLocation(230, 510);
		permitEndPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		permitEndPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		permitEndPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		permitEndPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		permitEndPick.setSize(192, 23);
		
		tables_inputPanel.add(permitEndPick);
		
		//AEP
		UtilDateModel aepModel1 = new UtilDateModel();
		Properties aep1 = new Properties();
		aep1.put("text.today", "Date Today");
		aep1.put("text.month", "Month");
		aep1.put("text.year", "Year");
		
		JDatePanelImpl aepDatePanel1 = new JDatePanelImpl(aepModel1, aep1);

		JDatePickerImpl aepEndPick = new JDatePickerImpl(aepDatePanel1, new DateLabelFormatter());

		aepEndPick.setLocation(228, 619);
		aepEndPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		aepEndPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		aepEndPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		aepEndPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		aepEndPick.setSize(192, 23);
		
		tables_inputPanel.add(aepEndPick);
		
		//Input Section (Labels)
		
		JComboBox tables_visaTypeTxt = new JComboBox();
		tables_visaTypeTxt.setEditable(true);
		tables_visaTypeTxt.setModel(new DefaultComboBoxModel(new String[] {"", "Pre-arranged Employment Visa (9g/Working Visa) ", "Pre-arranged Employment Visa (9g/Missionary)", "Permanent Resident Visa - Section 13 Series", "Special Non-Immigrant Visa - Section 47 (a)(2)", "Special Investor's Resident Visa (SIRV) ", "Special Resident Retiree's Visa (E.O 1037)"}));
		tables_visaTypeTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		tables_visaTypeTxt.setBounds(20, 340, 400, 23);
		tables_inputPanel.add(tables_visaTypeTxt);
		
		JComboBox tables_permitTypeTxt = new JComboBox();
		tables_permitTypeTxt.setEditable(true);
		tables_permitTypeTxt.setModel(new DefaultComboBoxModel(new String[] {"", "Special Working Permit(SWP)", "Provisional Work Permit (PWP)"}));
		tables_permitTypeTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		tables_permitTypeTxt.setBounds(20, 454, 400, 23);
		tables_inputPanel.add(tables_permitTypeTxt);
		
		JLabel tables_visaExpireLbl = new JLabel("Visa Expiry Date:");
		tables_visaExpireLbl.setForeground(Color.WHITE);
		tables_visaExpireLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_visaExpireLbl.setBounds(230, 370, 192, 29);
		tables_inputPanel.add(tables_visaExpireLbl);
		
		JLabel tables_permitLbl = new JLabel("Permit Type:");
		tables_permitLbl.setForeground(Color.WHITE);
		tables_permitLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_permitLbl.setBounds(20, 425, 190, 29);
		tables_inputPanel.add(tables_permitLbl);
		
		JLabel tables_permitStartLbl = new JLabel("Permit Start Date:");
		tables_permitStartLbl.setForeground(Color.WHITE);
		tables_permitStartLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_permitStartLbl.setBounds(20, 482, 190, 29);
		tables_inputPanel.add(tables_permitStartLbl);
		
		JLabel tables_permitExpireLbl = new JLabel("Permit Expiry Date:");
		tables_permitExpireLbl.setForeground(Color.WHITE);
		tables_permitExpireLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_permitExpireLbl.setBounds(230, 482, 192, 29);
		tables_inputPanel.add(tables_permitExpireLbl);
		
		JLabel tables_aepIdLbl = new JLabel("AEP ID:");
		tables_aepIdLbl.setForeground(Color.WHITE);
		tables_aepIdLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_aepIdLbl.setBounds(20, 534, 197, 29);
		tables_inputPanel.add(tables_aepIdLbl);
		
		tables_aepIdTxt = new JTextField();
		tables_aepIdTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_aepIdTxt.setColumns(10);
		tables_aepIdTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_aepIdTxt.setBounds(20, 565, 400, 23);
		tables_inputPanel.add(tables_aepIdTxt);
		
		JLabel lblAepStartDate = new JLabel("AEP Start Date:");
		lblAepStartDate.setForeground(Color.WHITE);
		lblAepStartDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblAepStartDate.setBounds(20, 590, 190, 29);
		tables_inputPanel.add(lblAepStartDate);
		
		JLabel lblAepExpiryDate = new JLabel("AEP Expiry Date:");
		lblAepExpiryDate.setForeground(Color.WHITE);
		lblAepExpiryDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblAepExpiryDate.setBounds(228, 590, 192, 29);
		tables_inputPanel.add(lblAepExpiryDate);
		
		JLabel tables_chooseLbl = new JLabel("Choose a Client:");
		tables_chooseLbl.setForeground(Color.WHITE);
		tables_chooseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_chooseLbl.setBounds(20, 40, 190, 41);
		tables_inputPanel.add(tables_chooseLbl);
		
		JLabel tables_lastnameLbl = new JLabel("Lastname:");
		tables_lastnameLbl.setForeground(Color.WHITE);
		tables_lastnameLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_lastnameLbl.setBounds(624, 570, 418, 31);
		getContentPane().add(tables_lastnameLbl);

		JLabel tables_firstnameLbl = new JLabel("Firstname:");
		tables_firstnameLbl.setForeground(Color.WHITE);
		tables_firstnameLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_firstnameLbl.setBounds(624, 604, 414, 31);
		getContentPane().add(tables_firstnameLbl);
		
		JLabel tables_aliasLbl = new JLabel("Alias:");
		tables_aliasLbl.setForeground(Color.WHITE);
		tables_aliasLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_aliasLbl.setBounds(624, 634, 418, 37);
		getContentPane().add(tables_aliasLbl);
		
		JLabel tables_nationalityLbl = new JLabel("Nationality:");
		tables_nationalityLbl.setForeground(Color.WHITE);
		tables_nationalityLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_nationalityLbl.setBounds(624, 715, 418, 31);
		getContentPane().add(tables_nationalityLbl);
		
		JLabel tables_birthdateLbl = new JLabel("Birthdate:");
		tables_birthdateLbl.setForeground(Color.WHITE);
		tables_birthdateLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_birthdateLbl.setBounds(624, 749, 414, 31);
		getContentPane().add(tables_birthdateLbl);
		
		JLabel tables_genderLbl = new JLabel("Gender:");
		tables_genderLbl.setForeground(Color.WHITE);
		tables_genderLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_genderLbl.setBounds(624, 779, 418, 37);
		getContentPane().add(tables_genderLbl);
		
		JLabel tables_companyLbl = new JLabel("Company:");
		tables_companyLbl.setForeground(Color.WHITE);
		tables_companyLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_companyLbl.setBounds(1173, 725, 358, 37);
		getContentPane().add(tables_companyLbl);
		
		JLabel tables_emaiLbl = new JLabel("Email:");
		tables_emaiLbl.setForeground(Color.WHITE);
		tables_emaiLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_emaiLbl.setBounds(1170, 570, 361, 37);
		getContentPane().add(tables_emaiLbl);
		
		JLabel tables_contactLbl = new JLabel("Contact No.:");
		tables_contactLbl.setForeground(Color.WHITE);
		tables_contactLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_contactLbl.setBounds(1170, 604, 361, 37);
		getContentPane().add(tables_contactLbl);
		
		JLabel tables_companyPositionLbl = new JLabel("Company Position:");
		tables_companyPositionLbl.setForeground(Color.WHITE);
		tables_companyPositionLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_companyPositionLbl.setBounds(1173, 760, 358, 31);
		getContentPane().add(tables_companyPositionLbl);
		
		JButton tables_registerBtn = new JButton("Update Info");
		tables_registerBtn.setEnabled(false);
		tables_registerBtn.setBorder(null);
		tables_registerBtn.setForeground(new Color(255, 255, 255));
		
		tables_comboBox1 = new JComboBox();
		tables_comboBox1.setEditable(true);
		AutoCompletion.enable(tables_comboBox1);
		tables_comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				tables_reloadBtn.doClick();
				int row = tables_comboBox1.getSelectedIndex();
				
				if( row == -1 || row == 0 || tables_comboBox1.getItemCount() == 0) {
					
					tables_registerBtn.setEnabled(false);
				}
				
				if(row > -1)
				{
					tables_registerBtn.setEnabled(true);
					tables_passportNoTxt.setText(tm.getValueAt(row, 2).toString());
					tables_tinIdTxt.setText(tm.getValueAt(row, 3).toString());
					tables_visaTypeTxt.setSelectedItem(tm.getValueAt(row, 4).toString());
					tables_permitTypeTxt.setSelectedItem(tm.getValueAt(row, 7).toString());
				
					if(tm.getValueAt(row, 5) != null)
						visaEndPick.getJFormattedTextField().setText(tm.getValueAt(row, 5).toString());
					else
						visaEndPick.getJFormattedTextField().setText("");
					if(tm.getValueAt(row, 6) != null)
						visaStartPick.getJFormattedTextField().setText(tm.getValueAt(row, 6).toString());
					else
						visaStartPick.getJFormattedTextField().setText("");
					if(tm.getValueAt(row, 8) != null)
						permitStartPick.getJFormattedTextField().setText(tm.getValueAt(row, 8).toString());
					else
						permitStartPick.getJFormattedTextField().setText("");
					if(tm.getValueAt(row, 9) != null)
						permitEndPick.getJFormattedTextField().setText(tm.getValueAt(row, 9).toString());
					else
						permitEndPick.getJFormattedTextField().setText("");
					tables_aepIdTxt.setText(tm.getValueAt(row, 10).toString());
					if(tm.getValueAt(row, 11) != null)
						aepStartPick.getJFormattedTextField().setText(tm.getValueAt(row, 11).toString());
					else
						aepStartPick.getJFormattedTextField().setText("");
					if(tm.getValueAt(row, 12) != null)
						aepEndPick.getJFormattedTextField().setText(tm.getValueAt(row, 12).toString());
					else
						aepEndPick.getJFormattedTextField().setText("");
				}
			}
		});
			
		tables_comboBox1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_comboBox1.setBounds(20, 142, 400, 26);
		tables_inputPanel.add(tables_comboBox1);
		
		tables_reloadBtn.setEnabled(false);
		tables_comboBox.setSelectedIndex(0);
		
		tables_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tables_passportNoTxt.setText("");
				tables_tinIdTxt.setText("");
				tables_visaTypeTxt.setSelectedIndex(0);
				visaStartPick.getJFormattedTextField().setText("");
				visaEndPick.getJFormattedTextField().setText("");
				tables_permitTypeTxt.setSelectedIndex(0);
				permitStartPick.getJFormattedTextField().setText("");
				permitEndPick.getJFormattedTextField().setText("");
				tables_aepIdTxt.setText("");
				aepStartPick.getJFormattedTextField().setText("");
				aepEndPick.getJFormattedTextField().setText("");
				
				if(tables_comboBox.getSelectedItem().toString() == "Click to see the list of registered client") {
					tables_reloadBtn.setEnabled(false);
					tables_comboBox1.removeAllItems();
				}else if (tables_comboBox.getSelectedItem().toString() != "Click to see the list of registered client") {
					tables_reloadBtn.setEnabled(true);
				}
				
				if(tables_comboBox.getSelectedIndex() == 0) {
					tables_passportNoTxt.setEditable(false);
					tables_tinIdTxt.setEditable(false);
					tables_registerBtn.setEnabled(false);
					tables_visaTypeTxt.setEnabled(false);
					visaStartPick.setEnabled(false);
					visaEndPick.setEnabled(false);
					tables_permitTypeTxt.setEnabled(false);
					permitStartPick.setEnabled(false);
					permitEndPick.setEnabled(false);
					tables_aepIdTxt.setEnabled(false);
					aepStartPick.setEnabled(false);
					aepEndPick.setEnabled(false);
				}
				
				if(tables_comboBox.getSelectedIndex() != 0) {
					tables_passportNoTxt.setEditable(false);
					tables_tinIdTxt.setEditable(false);
					tables_visaTypeTxt.setEnabled(true);
					visaStartPick.setEnabled(true);
					visaEndPick.setEnabled(true);
					tables_permitTypeTxt.setEnabled(true);
					permitStartPick.setEnabled(true);
					permitEndPick.setEnabled(true);
					tables_aepIdTxt.setEnabled(true);
					aepStartPick.setEnabled(true);
					aepEndPick.setEnabled(true);
					
				Connection conn;
				try {
					conn = DriverManager.getConnection(dP.url, dP.username, dP.password);
					String sql = "SELECT * FROM jdl_accounts.clients WHERE client_id=?";
					String sql1 = "SELECT * FROM jdl_accounts.transactions WHERE client_id=? AND trans_isActive = 1 OR null";
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					PreparedStatement statement1 = (PreparedStatement) conn.prepareStatement(sql1);
					
					
					String info = (String)tables_comboBox.getSelectedItem().toString();
					
					if(tables_comboBox.getSelectedItem() == tables_comboBox.getItemAt(0)) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Please Select a Client</font color = #ffffff></html>", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
					}
					else {
					
					int temp = Integer.parseInt(info.substring(info.lastIndexOf(",")+2, info.length()));
					
					client_id = String.valueOf(temp);
					statement.setInt(1, temp);
					ResultSet rs = statement.executeQuery();
					
					tables_comboBox1.removeAllItems();
					statement1.setInt(1, temp);
					ResultSet rs1 = statement1.executeQuery();
					while(rs1.next()) {
						tables_comboBox1.addItem(rs1.getString("trans_transId"));
					}
					while(rs.next()) {
						tables_lastnameLbl.setText("Lastname: "+rs.getString("client_lastname"));
						tables_firstnameLbl.setText("Firstname: "+rs.getString("client_firstname"));
						tables_aliasLbl.setText("Alias: "+rs.getString("client_alias"));
						tables_nationalityLbl.setText("Nationality: "+rs.getString("client_nationality"));
						tables_birthdateLbl.setText("Birthdate: "+rs.getString("client_birthdate"));
						tables_genderLbl.setText("Gender: "+rs.getString("client_gender"));
						tables_companyLbl.setText("Company: "+rs.getString("client_company"));
						tables_companyPositionLbl.setText("Company Position: "+rs.getString("client_position"));
						tables_emaiLbl.setText("Email: "+rs.getString("client_email"));
						tables_contactLbl.setText("Contact No.: "+rs.getString("client_contact"));
					}
					tables_passportNoTxt.setEditable(true);
					tables_tinIdTxt.setEditable(true);

					}
					}catch (SQLException e1) {
					e1.printStackTrace();
				}
				tables_reloadBtn.doClick();
			}
		}});
		
		tables_reloadBtn.setEnabled(false);
		tables_registerBtn.setEnabled(false);
		
		JLabel lblClientTransaction = new JLabel("------------------------ Client Transaction Details -----------------------");
		lblClientTransaction.setHorizontalAlignment(SwingConstants.LEFT);
		lblClientTransaction.setForeground(Color.WHITE);
		lblClientTransaction.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblClientTransaction.setBounds(20, 167, 400, 41);
		tables_inputPanel.add(lblClientTransaction);
		
		tables_comboBox1.setSelectedIndex(-1);
		tables_comboBox.setSelectedIndex(0);
		
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
			 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
			 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
			 	UIManager.put("Button.background", Color.WHITE);
			 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
				
				String vs = visaStartPick.getJFormattedTextField().getText().trim().toString();
				String ve = visaEndPick.getJFormattedTextField().getText().trim().toString();
				String ps = permitStartPick.getJFormattedTextField().getText().trim().toString();
				String pe = permitEndPick.getJFormattedTextField().getText().trim().toString();
				String as = aepStartPick.getJFormattedTextField().getText().trim().toString();
				String ae = aepEndPick.getJFormattedTextField().getText().trim().toString();
				try {
						
					if(objectFilter.inputCheck("Passport No.",tables_passportNoTxt.getText())){
						if(objectFilter.inputCheck("TIN ID",tables_tinIdTxt.getText())) {
							if(objectFilter.dateCheckTransaction("Visa", tables_visaTypeTxt.getSelectedItem().toString(), ve, vs)) {
								if(objectFilter.dateCheckTransaction("AEP", tables_aepIdTxt.getText(), as, ae)) {
									if(objectFilter.dateCheckTransaction("Permit", tables_permitTypeTxt.getSelectedItem().toString(), ps, pe)) {
										Register();
										tables_reloadBtn.doClick();
									}
								}
							}
						}
					}			
					
				}catch (Exception e3) {
					e3.printStackTrace();
				}
				
				tables_reloadBtn.doClick();
			}// end of action performed
			
			
		public void Register() {
			
			UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
		 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
		 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
		 	UIManager.put("Button.background", Color.WHITE);
		 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
		 	
			try {
				Calendar calendar = Calendar.getInstance();
				java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
				Transaction trans = new Transaction();
				
				String[] in = new String[6];
				in[0] = visaStartPick.getJFormattedTextField().getText().trim().toString();
				in[1] = visaEndPick.getJFormattedTextField().getText().trim().toString();
				in[2] = permitStartPick.getJFormattedTextField().getText().trim().toString();
				in[3] = permitEndPick.getJFormattedTextField().getText().trim().toString();
				in[4] = aepStartPick.getJFormattedTextField().getText().trim().toString();
				in[5] = aepEndPick.getJFormattedTextField().getText().trim().toString();
				objectFilter.writeDates(trans, in);
				
				trans.setPassportNo(tables_passportNoTxt.getText().trim());
				trans.setTinID(tables_tinIdTxt.getText().trim());
				trans.setVisaType(tables_visaTypeTxt.getSelectedItem().toString().trim());			
				trans.setPermitType(tables_permitTypeTxt.getSelectedItem().toString().trim());
				trans.setAepID(tables_aepIdTxt.getText().trim());
				trans.setTransTimestamp(currentDate);
				trans.setClient_id(Integer.parseInt(client_id));
				trans.setTransAuthor(Runner.getUser().getUser_username());
				trans.setTransAction("UPDATED");
				trans.setTransID(Integer.parseInt(tables_comboBox1.getSelectedItem().toString()));
				
				Queries.updateTransaction(trans);
				tables_inputPanel.revalidate();
				
				JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Transaction has been updated.</font color = #ffffff></html>", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
			}

			 catch (Exception e1) {
				e1.printStackTrace();
				
			}
		}
	});//end of action listener
		
		tables_registerBtn.setBackground(new Color(0, 102, 102));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_registerBtn.setBounds(132, 664, 173, 35);
		tables_inputPanel.add(tables_registerBtn);
		
		JLabel lblClientInformation = new JLabel("------------------------ Client Information Details -----------------------");
		lblClientInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblClientInformation.setForeground(Color.WHITE);
		lblClientInformation.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblClientInformation.setBounds(20, 11, 400, 41);
		tables_inputPanel.add(lblClientInformation);
		
		JLabel tables_clientCreateTransactionLbl = new JLabel("Create New Transaction", SwingConstants.CENTER);
		tables_clientCreateTransactionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Runner.destroyTUT();
				Runner.openTables();
			}
		});
		tables_clientCreateTransactionLbl.setBounds(475, 48, 227, 37);
		tables_clientCreateTransactionLbl.setForeground(Color.LIGHT_GRAY);
		tables_clientCreateTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel tables_clientStatusTableLbl = new JLabel("Client Status Table", SwingConstants.CENTER);
		tables_clientStatusTableLbl.setBounds(1043, 48, 209, 37);
		tables_clientStatusTableLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				Runner.openTS();
				Runner.destroyTUT();
			}
		});
		tables_clientStatusTableLbl.setForeground(Color.LIGHT_GRAY);
		tables_clientStatusTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel tables_clientRemarksTableLbl = new JLabel("Client Remarks Table", SwingConstants.CENTER);
		tables_clientRemarksTableLbl.setBounds(1290, 48, 209, 37);
		tables_clientRemarksTableLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				Runner.openTR();
				Runner.destroyTUT();
			}
		});
		tables_clientRemarksTableLbl.setForeground(Color.LIGHT_GRAY);
		tables_clientRemarksTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel label = new JLabel("");
		label.setBounds(1178, 48, 57, 37);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_line = new JLabel("");
		tables_line.setBounds(861, 96, 57, 22);
		tables_line.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/line.png")));
		tables_line.setHorizontalAlignment(SwingConstants.CENTER);
		tables_line.setForeground(Color.WHITE);
		tables_line.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_updateTransactionLbl = new JLabel("Update Transaction", SwingConstants.CENTER);

		tables_updateTransactionLbl.setBounds(774, 48, 227, 37);
		tables_updateTransactionLbl.setForeground(Color.WHITE);
		tables_updateTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel tables_addClientLbl = new JLabel("Add New Client", SwingConstants.CENTER);
		tables_addClientLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				Runner.openTAC();
				Runner.destroyTUT();
			}
		});
		tables_addClientLbl.setBounds(25, 48, 183, 37);
		tables_addClientLbl.setForeground(Color.LIGHT_GRAY);
		tables_addClientLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel tables_specificClientLbl = new JLabel("Client Transactions");
		tables_specificClientLbl.setBackground(new Color(0, 153, 153));
		tables_specificClientLbl.setBounds(493, 160, 268, 37);
		tables_specificClientLbl.setForeground(Color.WHITE);
		tables_specificClientLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_clientInfoLbl = new JLabel("Client Information");
		tables_clientInfoLbl.setForeground(Color.WHITE);
		tables_clientInfoLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tables_clientInfoLbl.setBounds(493, 513, 227, 37);
		getContentPane().add(tables_clientInfoLbl);
		
		JLabel tables_primaryInfoImg = new JLabel("");
		tables_primaryInfoImg.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/client_infoIcon.png")));
		tables_primaryInfoImg.setBounds(503, 561, 104, 115);
		getContentPane().add(tables_primaryInfoImg);
		
		JLabel tables_clientSecondaryInfoImg = new JLabel("");
		tables_clientSecondaryInfoImg.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/client_secondaryInfoIcon.png")));
		tables_clientSecondaryInfoImg.setBounds(503, 711, 104, 105);
		getContentPane().add(tables_clientSecondaryInfoImg);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/client_company1.png")));
		label_2.setBounds(1052, 711, 104, 105);
		getContentPane().add(label_2);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/client_emails.png")));
		label_1.setBounds(1048, 561, 112, 105);
		getContentPane().add(label_1);
		
		// Add to Panels 
		
		getContentPane().setLayout(null);
		
		//Images
		
		JLabel tables_minimize = new JLabel("");
		tables_minimize.setBounds(1515, 0, 35, 41);
		getContentPane().add(tables_minimize);
		tables_minimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		tables_minimize.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel tables_seeTablesLbl = new JLabel("See Tables");
		tables_seeTablesLbl.setBounds(685, 4, 168, 37);
		getContentPane().add(tables_seeTablesLbl);
		tables_seeTablesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tables_seeTablesLbl.setForeground(Color.WHITE);
		tables_seeTablesLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_back = new JLabel("");
		tables_back.setBounds(0, 0, 57, 37);
		getContentPane().add(tables_back);
		tables_back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Runner.destroyTUT();
				Runner.openOptionList();
			}
		});
		tables_back.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_back.png")));
		tables_back.setHorizontalAlignment(SwingConstants.CENTER);
		tables_back.setForeground(Color.WHITE);
		tables_back.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		getContentPane().add(tables_inputSectionLbl);
		getContentPane().add(tables_reloadBtn);
		getContentPane().add(tables_addClientLbl);
		getContentPane().add(tables_clientCreateTransactionLbl);
		getContentPane().add(tables_updateTransactionLbl);
		getContentPane().add(tables_clientStatusTableLbl);
		getContentPane().add(label);
		getContentPane().add(tables_clientRemarksTableLbl);
		getContentPane().add(tables_line);
		getContentPane().add(tables_inputPanel);
		getContentPane().add(scrollPane_1);
		getContentPane().add(tables_specificClientLbl);
		
		JLabel tables_transactionIdLbl = new JLabel("Select Transaction ID to edit:");
		tables_transactionIdLbl.setForeground(Color.WHITE);
		tables_transactionIdLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_transactionIdLbl.setBounds(20, 110, 213, 29);
		tables_inputPanel.add(tables_transactionIdLbl);
	
		
		JLabel tables_editClientsLbl = new JLabel("Update Clients", SwingConstants.CENTER);
		tables_editClientsLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Runner.openTUC();
				Runner.destroyTUT();
			}
		});
		tables_editClientsLbl.setForeground(Color.LIGHT_GRAY);
		tables_editClientsLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		tables_editClientsLbl.setBounds(245, 48, 183, 37);
		getContentPane().add(tables_editClientsLbl);
		
		JButton tables_deleteBtn = new JButton("Delete\r\n");
		tables_deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runner.openTDT();
			}
		});
		tables_deleteBtn.setIcon(new ImageIcon(TablesUpdateTransactions.class.getResource("/jdl/Assets/button_delete.png")));
		tables_deleteBtn.setForeground(Color.WHITE);
		tables_deleteBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		tables_deleteBtn.setBorder(null);
		tables_deleteBtn.setBackground(new Color(0, 102, 102));
		tables_deleteBtn.setBounds(1393, 160, 138, 37);
		getContentPane().add(tables_deleteBtn);
		
		
		JLabel background_tables = new JLabel("");
		background_tables.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/background_tables4.jpg")));
		background_tables.setBounds(0, 0, 1550, 870);
		getContentPane().add(background_tables);
		
		int ifAdmin = Runner.getUser().getUser_ifAdmin();
		
		if (ifAdmin == 0) {
			
			tables_deleteBtn.setVisible(false);
			tables_searchTxt.setBounds(1079, 163, 237, 35);
			tables_reloadBtn.setBounds(1327, 161, 204, 38);
			tables_filterIcon.setBounds(1043, 163, 35, 37);
			tables_filterTableLbl.setBounds(953, 161, 89, 37);
			
		}
	}
	
	
	
	//Username
    public void setUser(String user) {
    	this.adminAcc_usernameTxt.setText(user);
    	}
    public String getUser() {
    	return this.adminAcc_usernameTxt.getText().trim();
    	}
    //Password
    public void setPass(String pass) {
    	this.adminAcc_passwordTxt.setText(pass);
    	}
    public String getPass() {
    	return this.adminAcc_passwordTxt.getText().trim();
    	}
}

