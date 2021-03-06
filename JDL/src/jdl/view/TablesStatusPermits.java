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
import jdl.dao.databaseProperties;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TablesStatusPermits extends JFrame{
	private JTextField tables_aepCancellationTxt;
	private JTextField tables_downgradingTxt;
	private JTextField tables_aepExitClearanceTxt;
	private String clientSelectedName;
	private boolean tables_validator = true;
	private JTable table_1;
	private String client_id = "";
	private JTable table;
	private JTextField tables_instructionsTxt;
	private JTextField tables_updatedVisaExtendTxt;
	private JTextField tables_documentationTxt;
	private JTextField tables_addRequirementsTxt;
	private JTextField tables_acrICardTxt;
	private JDatePickerImpl tables_dateReceivedTxt, tables_aepDateFiledTxt, tables_aepdateReleasedTxt, tables_permitDateFiledTxt, tables_permitDateReleasedTxt;
	private databaseProperties dP = new databaseProperties();
	private JTextField tables_searchTxt;
	public TablesStatusPermits() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Status (Permits)");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(1550, 850));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(487, 519, 1036, 298);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(32);
		table.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		table.setBorder(null);
		table.setBounds(495, 541, 1036, 298);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setSize(1036, 280);
		scrollPane_1.setLocation(487, 198);
		
		table_1 = new JTable();
		table_1.setForeground(Color.DARK_GRAY);
		table_1.setRowHeight(32);
		table_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		table_1.setBorder(null);
		table_1.setBounds(492, 217, 1040, 138);
		
		JTableHeader header_1 = table_1.getTableHeader();
		header_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
	    header_1.setBackground(new Color(155, 177, 166));
	    header_1.setForeground(Color.WHITE);
		scrollPane_1.setViewportView(table_1);
		
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
	    header.setBackground(new Color(155, 177, 166));
	    header.setForeground(Color.WHITE);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		JButton tables_reloadBtn = new JButton("Reset and Reload");
		tables_reloadBtn.setBounds(1316, 148, 203, 38);
		tables_reloadBtn.setForeground(new Color(255, 255, 255));
		tables_reloadBtn.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/main_refresh.png")));
		
		//Input Section (Declaration of Panel) and Client_id Textfield
		
		JLabel tables_clientTransactionsLbl = new JLabel("Client Transactions");
		tables_clientTransactionsLbl.setForeground(Color.WHITE);
		tables_clientTransactionsLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tables_clientTransactionsLbl.setBounds(487, 481, 677, 37);
		getContentPane().add(tables_clientTransactionsLbl);
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(25, 153, 450, 664);
		tables_inputPanel.setBackground(new Color (255, 255, 255, 60));
		tables_inputPanel.setLayout(null);

		JComboBox tables_comboBox1 = new JComboBox();
		tables_comboBox1.setEditable(true);
		tables_comboBox1.setBounds(17, 109, 407, 25);
		tables_inputPanel.add(tables_comboBox1);
		tables_comboBox1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		AutoCompletion.enable(tables_comboBox1);
		
		
		JLabel tables_cancellationLbl = new JLabel("AEP Cancellation:");
		tables_cancellationLbl.setForeground(Color.WHITE);
		tables_cancellationLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_cancellationLbl.setBounds(224, 219, 197, 29);
		tables_inputPanel.add(tables_cancellationLbl);
		

		JButton tables_registerBtn = new JButton("Insert Status");
		
		JComboBox tables_comboBox = new JComboBox();
		tables_comboBox.setEditable(true);
		AutoCompletion.enable(tables_comboBox);
		tables_comboBox.insertItemAt("Click to see the list of registered client", 0);
		
		tables_reloadBtn.setEnabled(false);
		tables_registerBtn.setEnabled(false);
		tables_comboBox.setSelectedIndex(0);
		
		tables_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tables_comboBox.getSelectedItem().toString() == "Click to see the list of registered client") {
					tables_clientTransactionsLbl.setText("Client Transactions");
					tables_reloadBtn.setEnabled(false);
					tables_registerBtn.setEnabled(false);
					tables_comboBox1.removeAllItems();
				}else if (tables_comboBox.getSelectedItem().toString() != "Click to see the list of registered client") {
					tables_reloadBtn.setEnabled(true);
									
				Connection conn;
				Connection conn2;
				
				try {
					conn = DriverManager.getConnection(dP.url, dP.username, dP.password);
					String sql = "SELECT * FROM jdl_accounts.clients WHERE client_id=?";
					String sql2 = "SELECT * FROM jdl_accounts.transactions WHERE client_id=? AND trans_isActive = 1 OR null";
					String sql3 = "SELECT client_id AS 'Client ID' "
							+ ", trans_transId AS 'Transaction ID' " + 
							", statusP_dateReceived AS 'Date Received' " +
							", statusP_instructions AS 'Instructions' " +
							", statusP_aepCancellation AS 'AEP Cancellation' " +
							", statusP_downgrading AS 'Downgrading' " +
							", statusP_aepExitClearance AS 'AEP Exit Clearance' " +
							", statusP_updatedVisaExtend AS 'Visa Extend' " +
							", statusP_documentation AS 'Documentation' " + 
							", statusP_addRequirements AS 'Add Requirements' " + 
							", statusP_aepDateFiled AS 'AEP Date Filed'" +
							", statusP_aepDateRelease AS 'AEP Date Released'" + 
							", statusP_permitDateFiled AS 'Permit Date Filed'" +
							", statusP_permitDateReleased AS 'Permit Date Released'" + 
							", statusP_acrIcard AS 'ACR I-card'" + 
							" FROM jdl_accounts.status_permits WHERE client_id = ?";
					PreparedStatement statement = conn.prepareStatement(sql);
					PreparedStatement statement3= conn.prepareStatement(sql2);
					PreparedStatement statement4= conn.prepareStatement(sql3);
					
					String info = (String)tables_comboBox.getSelectedItem().toString();
					
					int temp = Integer.parseInt(info.substring(info.lastIndexOf(",")+2, info.length()));
					
					client_id = String.valueOf(temp);
					statement.setInt(1, temp);
					statement3.setInt(1, temp);
					statement4.setInt(1, temp);
					
					tables_comboBox1.removeAllItems();
					ResultSet rs = statement.executeQuery();
					
					 while(rs.next()) {
						 tables_clientTransactionsLbl.setText(info.substring(0, info.lastIndexOf(","))+" Transactions");
						}
					
					ResultSet rs1 = statement3.executeQuery();
						
						 while(rs1.next()){        
						       	tables_comboBox1.addItem(rs1.getString("trans_transId"));       
								    }
						 
							ResultSet rs2 = statement4.executeQuery();
							table_1.setModel(DbUtils.resultSetToTableModel(rs1));
							table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						 
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				tables_reloadBtn.doClick();
			}
		}});
		
		tables_reloadBtn.setEnabled(false);
		tables_registerBtn.setEnabled(false);
			
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
		tables_comboBox.setBounds(17, 55, 407, 25);
		
		tables_inputPanel.add(tables_comboBox);
		
		tables_reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection(dP.url, dP.username, dP.password);
					Statement stat1=conn.createStatement();
	
					ResultSet rs1 = stat1.executeQuery("SELECT client_id AS 'Client ID' "
							+ ", trans_transId AS 'Transaction ID' " + 
							", statusP_dateReceived AS 'Date Received' " +
							", statusP_instructions AS 'Instructions' " +
							", statusP_aepCancellation AS 'AEP Cancellation' " +
							", statusP_downgrading AS 'Downgrading' " +
							", statusP_aepExitClearance AS 'AEP Exit Clearance' " +
							", statusP_updatedVisaExtend AS 'Visa Extend' " +
							", statusP_documentation AS 'Documentation' " + 
							", statusP_addRequirements AS 'Add Requirements' " + 
							", statusP_aepDateFiled AS 'AEP Date Filed' " + 
							", statusP_aepDateRelease AS 'AEP Date Released' " + 
							", statusP_permitDateFiled AS 'Permit Date Filed' " + 
							", statusP_permitDateReleased AS 'Permit Date Released' " +
							", statusP_acrIcard AS 'ACR I-card'" + 
							" FROM jdl_accounts.status_permits WHERE client_id ="+client_id+"");
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs1));
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_1.getModel());
					table_1.setRowSorter(sorter);
					table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					tables_searchTxt.setText("");
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
					
					Statement stat2=conn.createStatement();
					
					ResultSet rs2 = stat2.executeQuery("SELECT client_id AS 'Client ID',"
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
							" FROM jdl_accounts.transactions WHERE client_id = "+Integer.parseInt(client_id)+" AND trans_isActive = 1 OR null");
					
					table.setModel(DbUtils.resultSetToTableModel(rs2));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(table.getModel());
					table.setRowSorter(sorter1);
					
					TableColumnAdjuster tca = new TableColumnAdjuster(table);
					tca.adjustColumns();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		tables_reloadBtn.doClick();
		
		tables_reloadBtn.setBackground(new Color(0, 102, 102));
		tables_reloadBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		tables_reloadBtn.setBorder(null);
		tables_reloadBtn.setBorder(null);
		
		//Input Section (Labels and Associated Textfields)
		
		JLabel tables_inputSectionLbl = new JLabel("Input Section:");
		tables_inputSectionLbl.setBounds(30, 107, 132, 37);
		tables_inputSectionLbl.setForeground(new Color(255, 255, 255));
		tables_inputSectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		
		JLabel tables_dateReceivedLbl = new JLabel("Date Received:");
		tables_dateReceivedLbl.setForeground(new Color(255, 255, 255));
		tables_dateReceivedLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_dateReceivedLbl.setBounds(17, 154, 91, 54);
		tables_inputPanel.add(tables_dateReceivedLbl);
		
		JLabel tables_instructionsLbl = new JLabel("Instructions:");
		tables_instructionsLbl.setForeground(new Color(255, 255, 255));
		tables_instructionsLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_instructionsLbl.setBounds(17, 219, 197, 29);
		tables_inputPanel.add(tables_instructionsLbl);
		
		tables_aepCancellationTxt = new JTextField();
		tables_aepCancellationTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_aepCancellationTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_aepCancellationTxt.setColumns(10);
		tables_aepCancellationTxt.setBounds(224, 250, 197, 23);
		tables_inputPanel.add(tables_aepCancellationTxt);
		
		//Date Input
		
		//Date Filed
		UtilDateModel dateFiledModel = new UtilDateModel();
		Properties dateFiled = new Properties();
		dateFiled.put("text.today", "Date Today");
		dateFiled.put("text.month", "Month");
		dateFiled.put("text.year", "Year");
		
		JDatePanelImpl dateFiledPanel = new JDatePanelImpl(dateFiledModel, dateFiled);

		tables_dateReceivedTxt = new JDatePickerImpl(dateFiledPanel, new DateLabelFormatter());

		tables_dateReceivedTxt.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		tables_dateReceivedTxt.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		tables_dateReceivedTxt.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		tables_dateReceivedTxt.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_dateReceivedTxt.setSize(407, 23);

		tables_inputPanel.add(tables_dateReceivedTxt);
		
		//Hearing Date
		UtilDateModel hearingDateModel = new UtilDateModel();
		Properties hearingDate = new Properties();
		hearingDate.put("text.today", "Date Today");
		hearingDate.put("text.month", "Month");
		hearingDate.put("text.year", "Year");
		
		JDatePanelImpl hearingDatePanel = new JDatePanelImpl(hearingDateModel, hearingDate);
	
		
		//Early Hearing Date
		UtilDateModel earlyHearingDateModel = new UtilDateModel();
		Properties earlyHearingDate = new Properties();
		earlyHearingDate.put("text.today", "Date Today");
		earlyHearingDate.put("text.month", "Month");
		earlyHearingDate.put("text.year", "Year");

		JDatePanelImpl earlyHearingDatePanel = new JDatePanelImpl(earlyHearingDateModel, earlyHearingDate);


		tables_dateReceivedTxt.setLocation(17, 196);
		tables_dateReceivedTxt.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		tables_dateReceivedTxt.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		tables_dateReceivedTxt.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		tables_dateReceivedTxt.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_dateReceivedTxt.setSize(407, 23);

		tables_inputPanel.add(tables_dateReceivedTxt);
		
		JLabel tables_aepCancellationLbl = new JLabel("AEP Cancellation:");
		tables_dateReceivedTxt.add(tables_aepCancellationLbl);
		tables_aepCancellationLbl.setForeground(new Color(255, 255, 255));
		tables_aepCancellationLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		JLabel tables_aepDowngradingLbl = new JLabel("AEP Downgrading:");
		tables_aepDowngradingLbl.setForeground(Color.WHITE);
		tables_aepDowngradingLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_aepDowngradingLbl.setBounds(17, 278, 190, 25);
		tables_inputPanel.add(tables_aepDowngradingLbl);
		
		tables_downgradingTxt = new JTextField();
		tables_downgradingTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_downgradingTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_downgradingTxt.setColumns(10);
		tables_downgradingTxt.setBounds(17, 306, 197, 23);
		tables_inputPanel.add(tables_downgradingTxt);
		
		JLabel tables_aepExitClearanceLbl = new JLabel("AEP Exit Clearance:");
		tables_aepExitClearanceLbl.setForeground(Color.WHITE);
		tables_aepExitClearanceLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_aepExitClearanceLbl.setBounds(224, 276, 197, 29);
		tables_inputPanel.add(tables_aepExitClearanceLbl);
		
		tables_aepExitClearanceTxt = new JTextField();
		tables_aepExitClearanceTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_aepExitClearanceTxt.setColumns(10);
		tables_aepExitClearanceTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_aepExitClearanceTxt.setBounds(224, 306, 197, 23);
		tables_inputPanel.add(tables_aepExitClearanceTxt);
		
		JLabel tables_aepDateFiledLbl = new JLabel("AEP Date Filed:");
		tables_aepDateFiledLbl.setForeground(Color.WHITE);
		tables_aepDateFiledLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_aepDateFiledLbl.setBounds(17, 442, 190, 29);
		tables_inputPanel.add(tables_aepDateFiledLbl);
		
		//Permit Date Filed
		UtilDateModel permitDateFiledModel = new UtilDateModel();
		Properties permitDateFiled = new Properties();
		permitDateFiled.put("text.today", "Date Today");
		permitDateFiled.put("text.month", "Month");
		permitDateFiled.put("text.year", "Year");
		
		JDatePanelImpl permitDateFiledPanel = new JDatePanelImpl(permitDateFiledModel, permitDateFiled);
		
		tables_permitDateFiledTxt = new JDatePickerImpl(permitDateFiledPanel, new DateLabelFormatter());

		tables_permitDateFiledTxt.setLocation(17, 522);
		tables_permitDateFiledTxt.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		tables_permitDateFiledTxt.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		tables_permitDateFiledTxt.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		tables_permitDateFiledTxt.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_permitDateFiledTxt.setSize(202, 23);
		tables_inputPanel.add(tables_permitDateFiledTxt);
		
		//Permit Date Released
		UtilDateModel permitDateReleasedModel = new UtilDateModel();
		Properties permitDateReleased = new Properties();
		permitDateReleased.put("text.today", "Date Today");
		permitDateReleased.put("text.month", "Month");
		permitDateReleased.put("text.year", "Year");
		
		JDatePanelImpl permitDateReleasedPanel = new JDatePanelImpl(permitDateReleasedModel, permitDateReleased);
		
		tables_permitDateReleasedTxt = new JDatePickerImpl(permitDateReleasedPanel, new DateLabelFormatter());

		tables_permitDateReleasedTxt.setLocation(227, 522);
		tables_permitDateReleasedTxt.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		tables_permitDateReleasedTxt.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		tables_permitDateReleasedTxt.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		tables_permitDateReleasedTxt.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_permitDateReleasedTxt.setSize(197, 23);
		tables_inputPanel.add(tables_permitDateReleasedTxt);

		
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		
		JLabel tables_clientInformationLbl = new JLabel("------------------------------- Client Selection ------------------------------");
		tables_clientInformationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_clientInformationLbl.setForeground(Color.WHITE);
		tables_clientInformationLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_clientInformationLbl.setBounds(20, 11, 411, 23);
		tables_inputPanel.add(tables_clientInformationLbl);
		
		JLabel tables_clientCreateTransactionLbl = new JLabel("Create New Transaction", SwingConstants.CENTER);
		tables_clientCreateTransactionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Runner.openTables();
				Runner.destroyTSP();
			}
		});
		tables_clientCreateTransactionLbl.setBounds(475, 48, 227, 37);
		tables_clientCreateTransactionLbl.setForeground(Color.LIGHT_GRAY);
		tables_clientCreateTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel tables_clientStatusTableLbl = new JLabel("Client Status Table", SwingConstants.CENTER);
		tables_clientStatusTableLbl.setBounds(1043, 48, 209, 37);

		tables_clientStatusTableLbl.setForeground(Color.WHITE);
		tables_clientStatusTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel tables_clientRemarksTableLbl = new JLabel("Client Remarks Table", SwingConstants.CENTER);
		tables_clientRemarksTableLbl.setBounds(1290, 48, 209, 37);
		tables_clientRemarksTableLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				Runner.openTR();
				Runner.destroyTSP();
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
		tables_line.setBounds(1119, 97, 57, 22);
		tables_line.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/line.png")));
		tables_line.setHorizontalAlignment(SwingConstants.CENTER);
		tables_line.setForeground(Color.WHITE);
		tables_line.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_updateTransactionLbl = new JLabel("Update Transaction", SwingConstants.CENTER);
		tables_updateTransactionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Runner.openTUT();
				Runner.destroyTSP();
			}
		});
		tables_updateTransactionLbl.setBounds(774, 48, 227, 37);
		tables_updateTransactionLbl.setForeground(Color.LIGHT_GRAY);
		tables_updateTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel tables_addClientLbl = new JLabel("Add New Client", SwingConstants.CENTER);
		tables_addClientLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Runner.openTAC();
				Runner.destroyTSP();
			}
		});

		tables_addClientLbl.setBounds(25, 48, 183, 37);
		tables_addClientLbl.setForeground(Color.LIGHT_GRAY);
		tables_addClientLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel lblSpecificClient = new JLabel("Client Status (For Permit Filing)");
		lblSpecificClient.setBounds(485, 158, 382, 37);
		lblSpecificClient.setForeground(Color.WHITE);
		lblSpecificClient.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		tables_aepDateFiledTxt = new JDatePickerImpl(earlyHearingDatePanel, new DateLabelFormatter());
		tables_aepDateFiledTxt.setBounds(17, 470, 202, 23);
		tables_inputPanel.add(tables_aepDateFiledTxt);
		tables_aepDateFiledTxt.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		tables_aepDateFiledTxt.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		tables_aepDateFiledTxt.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		tables_aepDateFiledTxt.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		
				tables_aepdateReleasedTxt = new JDatePickerImpl(hearingDatePanel, new DateLabelFormatter());
				tables_aepdateReleasedTxt.setBounds(227, 470, 197, 23);
				tables_inputPanel.add(tables_aepdateReleasedTxt);
				tables_aepdateReleasedTxt.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
				tables_aepdateReleasedTxt.getJFormattedTextField().setBackground(new Color(255, 255, 255));
				tables_aepdateReleasedTxt.getJFormattedTextField().setForeground(new Color(220, 20, 60));
				tables_aepdateReleasedTxt.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
				
				JLabel tables_permitDateReleasedLbl = new JLabel("Permit Date Released:");
				tables_permitDateReleasedLbl.setForeground(Color.WHITE);
				tables_permitDateReleasedLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
				tables_permitDateReleasedLbl.setBounds(229, 494, 190, 34);
				tables_inputPanel.add(tables_permitDateReleasedLbl);
				
				JLabel lblAcriCa = new JLabel("ACR-I Card:");
				lblAcriCa.setForeground(Color.WHITE);
				lblAcriCa.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
				lblAcriCa.setBounds(17, 547, 104, 29);
				tables_inputPanel.add(lblAcriCa);
				
				tables_acrICardTxt = new JTextField();
				tables_acrICardTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
				tables_acrICardTxt.setColumns(10);
				tables_acrICardTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
				tables_acrICardTxt.setBounds(17, 573, 407, 23);
				tables_inputPanel.add(tables_acrICardTxt);
		
		// Add to Panels 
		
		getContentPane().setLayout(null);
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
		
		JLabel tables_aepDateReleasedLbl = new JLabel("AEP Date Released:");
		tables_aepDateReleasedLbl.setForeground(Color.WHITE);
		tables_aepDateReleasedLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_aepDateReleasedLbl.setBounds(227, 443, 197, 26);
		tables_inputPanel.add(tables_aepDateReleasedLbl);
		
		JLabel tables_permitDateFiledLbl = new JLabel("Permit Date Filed:");
		tables_permitDateFiledLbl.setForeground(Color.WHITE);
		tables_permitDateFiledLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_permitDateFiledLbl.setBounds(17, 504, 190, 14);
		tables_inputPanel.add(tables_permitDateFiledLbl);
		
		JLabel lblSelectATransaction = new JLabel("Select Transaction ID:");
		lblSelectATransaction.setForeground(Color.WHITE);
		lblSelectATransaction.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblSelectATransaction.setBounds(20, 85, 146, 25);
		tables_inputPanel.add(lblSelectATransaction);

		tables_registerBtn.setForeground(new Color(255, 255, 255));
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkFields() && DateCheck(tables_aepDateFiledTxt.getJFormattedTextField().getText().toString(),tables_aepdateReleasedTxt.getJFormattedTextField().getText().toString())
								  && DateCheck(tables_permitDateFiledTxt.getJFormattedTextField().getText().toString(), tables_permitDateReleasedTxt.getJFormattedTextField().getText().toString())) 
				{
					UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
				 	UIManager.put("Button.background", Color.WHITE);
				 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
				 	
					JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>There should be at least one input to record a status.</font color = #ffffff></html>", "Detected no entries", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				Connection conn3;
				try {
					String sql = "INSERT INTO jdl_accounts.status_permits (statusP_dateReceived, statusP_instructions, statusP_aepCancellation, statusP_downgrading, statusP_aepExitClearance, statusP_updatedVisaExtend, statusP_documentation, statusP_addRequirements, statusP_aepDateFiled, "
							+ "statusP_aepDateRelease, statusP_permitDateFiled, statusP_permitDateReleased, statusP_acrIcard, client_id, trans_transId)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE statusP_dateReceived = ?, statusP_instructions = ?, statusP_aepCancellation = ?,  statusP_downgrading = ?, statusP_aepExitClearance = ?,  statusP_updatedVisaExtend = ?, statusP_documentation = ?, statusP_addRequirements = ?, statusP_aepDateFiled = ?," 
							+ "statusP_aepDateRelease = ?, statusP_permitDateFiled = ?, statusP_permitDateReleased = ?,statusP_acrIcard = ?,  client_id = ?, trans_transId = ? ";
					conn3 =  DriverManager.getConnection(dP.url, dP.username, dP.password);
					PreparedStatement statement2= conn3.prepareStatement(sql);
					
					
					if(tables_dateReceivedTxt.getJFormattedTextField().getText().trim().toString().equals("")) 
						statement2.setDate(1, null);
					else
						statement2.setDate(1, java.sql.Date.valueOf(objectFilter.addDay(tables_dateReceivedTxt.getJFormattedTextField().getText().trim().toString())));
					
						statement2.setString(2, tables_instructionsTxt.getText().trim());
						statement2.setString(3, tables_aepCancellationTxt.getText().trim());
						statement2.setString(4, tables_downgradingTxt.getText().trim());
						statement2.setString(5, tables_aepExitClearanceTxt.getText().trim());
						statement2.setString(6, tables_updatedVisaExtendTxt.getText().trim());
						statement2.setString(7, tables_documentationTxt.getText().trim());
						statement2.setString(8, tables_addRequirementsTxt.getText().trim());
					
					if(tables_aepDateFiledTxt.getJFormattedTextField().getText().toString().equals(""))
						statement2.setDate(9, null);
					else
						statement2.setDate(9, java.sql.Date.valueOf(objectFilter.addDay(tables_aepDateFiledTxt.getJFormattedTextField().getText().trim().toString())));
					
					if(tables_aepdateReleasedTxt.getJFormattedTextField().getText().toString().equals(""))
						statement2.setDate(10, null);
					else
						statement2.setDate(10, java.sql.Date.valueOf(objectFilter.addDay(tables_aepdateReleasedTxt.getJFormattedTextField().getText().trim().toString())));
					
					if(tables_permitDateFiledTxt.getJFormattedTextField().getText().toString().equals(""))
						statement2.setDate(11, null);	
					else
						statement2.setDate(11, java.sql.Date.valueOf(objectFilter.addDay(tables_permitDateFiledTxt.getJFormattedTextField().getText().trim().toString())));
					
					if(tables_permitDateReleasedTxt.getJFormattedTextField().getText().toString().equals(""))
						statement2.setDate(12, null);
					else
						statement2.setDate(12, java.sql.Date.valueOf(objectFilter.addDay(tables_permitDateReleasedTxt.getJFormattedTextField().getText().trim().toString())));
					
						statement2.setString(13, tables_acrICardTxt.getText().trim());
						statement2.setString(14, client_id);
						statement2.setString(15, tables_comboBox1.getSelectedItem().toString());
						
						if(tables_dateReceivedTxt.getJFormattedTextField().getText().toString().equals("")) 
							statement2.setDate(16, null);
						else
							statement2.setDate(16, java.sql.Date.valueOf(objectFilter.addDay(tables_dateReceivedTxt.getJFormattedTextField().getText().trim().toString())));
							statement2.setString(17, tables_instructionsTxt.getText().trim());
							statement2.setString(18, tables_aepCancellationTxt.getText().trim());
							statement2.setString(19, tables_downgradingTxt.getText().trim());
							statement2.setString(20, tables_aepExitClearanceTxt.getText().trim());
							statement2.setString(21, tables_updatedVisaExtendTxt.getText().trim());
							statement2.setString(22, tables_documentationTxt.getText().trim());
							statement2.setString(23, tables_addRequirementsTxt.getText().trim());

						
						if(tables_aepDateFiledTxt.getJFormattedTextField().getText().trim().toString().equals(""))
							statement2.setDate(24, null);
						else
							statement2.setDate(24, java.sql.Date.valueOf(objectFilter.addDay(tables_aepDateFiledTxt.getJFormattedTextField().getText().trim().toString())));
						
						if(tables_aepdateReleasedTxt.getJFormattedTextField().getText().toString().equals(""))
							statement2.setDate(25, null);
						else
							statement2.setDate(25, java.sql.Date.valueOf(objectFilter.addDay(tables_aepdateReleasedTxt.getJFormattedTextField().getText().toString())));
						
						if(tables_permitDateFiledTxt.getJFormattedTextField().getText().trim().toString().equals(""))
							statement2.setDate(26, null);	
						else
							statement2.setDate(26, java.sql.Date.valueOf(objectFilter.addDay(tables_permitDateFiledTxt.getJFormattedTextField().getText().trim().toString())));
						
						if(tables_permitDateReleasedTxt.getJFormattedTextField().getText().trim().toString().equals(""))
							statement2.setDate(27, null);	
						else
							statement2.setDate(27, java.sql.Date.valueOf(objectFilter.addDay(tables_permitDateReleasedTxt.getJFormattedTextField().getText().trim().toString())));
						
							statement2.setString(28, tables_acrICardTxt.getText().trim());
							statement2.setString(29, client_id);
							statement2.setString(30, tables_comboBox1.getSelectedItem().toString());
						
					statement2.executeUpdate();
					tables_inputPanel.revalidate();
					
					tables_dateReceivedTxt.getJFormattedTextField().setText(null);
					tables_instructionsTxt.setText("");
					tables_aepCancellationTxt.setText("");
					tables_downgradingTxt.setText("");
					tables_aepExitClearanceTxt.setText("");
					tables_updatedVisaExtendTxt.setText("");
					tables_documentationTxt.setText("");
					tables_addRequirementsTxt.setText("");
					tables_aepDateFiledTxt.getJFormattedTextField().setText(null);
					tables_aepdateReleasedTxt.getJFormattedTextField().setText(null);
					tables_permitDateFiledTxt.getJFormattedTextField().setText(null);
					tables_permitDateReleasedTxt.getJFormattedTextField().setText(null);
					tables_acrICardTxt.setText("");
					
					JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Permit Status has been made to this transaction.</font color = #ffffff></html>", "Permit Status Inserted Successfully", JOptionPane.INFORMATION_MESSAGE);
					tables_reloadBtn.doClick();
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					
				}
			}
		});
		tables_registerBtn.setBounds(132, 619, 170, 34);
		tables_inputPanel.add(tables_registerBtn);
		

		tables_registerBtn.setBackground(new Color(0, 102, 102));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel tables_clientDetailsLbl = new JLabel("------------------------- Client Transaction Details ------------------------");
		tables_clientDetailsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_clientDetailsLbl.setForeground(Color.WHITE);
		tables_clientDetailsLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_clientDetailsLbl.setBounds(17, 145, 411, 23);
		tables_inputPanel.add(tables_clientDetailsLbl);
		
		JLabel lblSelectClient = new JLabel("Choose a Client:");
		lblSelectClient.setForeground(Color.WHITE);
		lblSelectClient.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblSelectClient.setBounds(17, 27, 146, 29);
		tables_inputPanel.add(lblSelectClient);
		
		tables_instructionsTxt = new JTextField();
		tables_instructionsTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_instructionsTxt.setColumns(10);
		tables_instructionsTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_instructionsTxt.setBounds(17, 250, 197, 23);
		tables_inputPanel.add(tables_instructionsTxt);
		
		JLabel tables_updatedVisaExtendLbl = new JLabel("Updated Visa Extend");
		tables_updatedVisaExtendLbl.setForeground(Color.WHITE);
		tables_updatedVisaExtendLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_updatedVisaExtendLbl.setBounds(17, 327, 197, 41);
		tables_inputPanel.add(tables_updatedVisaExtendLbl);
		
		tables_updatedVisaExtendTxt = new JTextField();
		tables_updatedVisaExtendTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_updatedVisaExtendTxt.setColumns(10);
		tables_updatedVisaExtendTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_updatedVisaExtendTxt.setBounds(17, 362, 197, 23);
		tables_inputPanel.add(tables_updatedVisaExtendTxt);
		
		JLabel tables_documentationLbl = new JLabel("Documentation:");
		tables_documentationLbl.setForeground(Color.WHITE);
		tables_documentationLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_documentationLbl.setBounds(224, 327, 197, 41);
		tables_inputPanel.add(tables_documentationLbl);
		
		tables_documentationTxt = new JTextField();
		tables_documentationTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_documentationTxt.setColumns(10);
		tables_documentationTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_documentationTxt.setBounds(224, 362, 197, 23);
		tables_inputPanel.add(tables_documentationTxt);
		
		JLabel tables_addRequirementsLbl = new JLabel("Add Requirements:");
		tables_addRequirementsLbl.setForeground(Color.WHITE);
		tables_addRequirementsLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_addRequirementsLbl.setBounds(17, 387, 197, 29);
		tables_inputPanel.add(tables_addRequirementsLbl);
		
		tables_addRequirementsTxt = new JTextField();
		tables_addRequirementsTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_addRequirementsTxt.setColumns(10);
		tables_addRequirementsTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_addRequirementsTxt.setBounds(17, 417, 407, 23);
		tables_inputPanel.add(tables_addRequirementsTxt);
						
		getContentPane().add(scrollPane_1);
		
		JButton btnVisa = new JButton("Visa");
		btnVisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runner.openTS();
				Runner.destroyTSP();
			}
		});
		btnVisa.setForeground(Color.WHITE);
		btnVisa.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		btnVisa.setBorder(null);
		btnVisa.setBackground(new Color(0, 102, 102));
		btnVisa.setBounds(172, 107, 86, 38);
		getContentPane().add(btnVisa);
		
		JButton btnPermit = new JButton("Permits");

		btnPermit.setForeground(Color.WHITE);
		btnPermit.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		btnPermit.setBorder(null);
		btnPermit.setBackground(new Color(255, 153, 51));
		btnPermit.setBounds(268, 107, 86, 38);
		getContentPane().add(btnPermit);
		
		JLabel tables_back = new JLabel("");
		tables_back.setBounds(0, 0, 57, 37);
		getContentPane().add(tables_back);
		tables_back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Runner.destroyTSP();
				Runner.openOptionList();
			}
		});
		tables_back.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_back.png")));
		tables_back.setHorizontalAlignment(SwingConstants.CENTER);
		tables_back.setForeground(Color.WHITE);
		tables_back.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_seeTablesLbl = new JLabel("See Tables");
		tables_seeTablesLbl.setBounds(685, 0, 168, 37);
		getContentPane().add(tables_seeTablesLbl);
		tables_seeTablesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tables_seeTablesLbl.setForeground(Color.WHITE);
		tables_seeTablesLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		
		tables_comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tables_comboBox1.getSelectedIndex() == -1){
					tables_registerBtn.setEnabled(false);
				}
				else {
					tables_registerBtn.setEnabled(true);
				}
					
			}
		});
		
		//Images
		
		JLabel tables_minimize = new JLabel("");
		tables_minimize.setBounds(1505, 0, 35, 41);
		getContentPane().add(tables_minimize);
		tables_minimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		tables_minimize.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		getContentPane().add(lblSpecificClient);
						
						JLabel tables_editClientsLbl = new JLabel("Update Clients", SwingConstants.CENTER);
						tables_editClientsLbl.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								Runner.openTUC();
								Runner.destroyTSP();
							}
						});
						tables_editClientsLbl.setForeground(Color.LIGHT_GRAY);
						tables_editClientsLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
						tables_editClientsLbl.setBounds(245, 48, 183, 37);
						getContentPane().add(tables_editClientsLbl);
						
						tables_searchTxt = new JTextField();
						tables_searchTxt.setText("Enter keywords here");
						tables_searchTxt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
						tables_searchTxt.setColumns(10);
						tables_searchTxt.setBorder(null);
						tables_searchTxt.setBounds(1069, 150, 237, 35);
						getContentPane().add(tables_searchTxt);
						
						JLabel tables_filterIcon = new JLabel("");
						tables_filterIcon.setIcon(new ImageIcon(TablesStatusPermits.class.getResource("/jdl/Assets/client_filterIcon.png")));
						tables_filterIcon.setForeground(Color.WHITE);
						tables_filterIcon.setFont(new Font("Segoe UI", Font.BOLD, 15));
						tables_filterIcon.setBounds(1031, 149, 35, 37);
						getContentPane().add(tables_filterIcon);
						
						JLabel tables_filterTableTxt = new JLabel("Filter Table:");
						tables_filterTableTxt.setForeground(Color.WHITE);
						tables_filterTableTxt.setFont(new Font("Segoe UI", Font.BOLD, 15));
						tables_filterTableTxt.setBounds(943, 148, 89, 37);
						getContentPane().add(tables_filterTableTxt);
						
						JLabel tables_background = new JLabel("");
						tables_background.setIcon(new ImageIcon(TablesStatusPermits.class.getResource("/jdl/Assets/background_tables4.jpg")));
						tables_background.setBounds(0, 0, 1550, 850);
						getContentPane().add(tables_background);
	}
	
	public boolean checkFields()
	{
		JTextField[] fieldtexts = {
		tables_instructionsTxt,
		tables_aepCancellationTxt,
		tables_downgradingTxt,
		tables_aepExitClearanceTxt,
		tables_updatedVisaExtendTxt,
		tables_documentationTxt,
		tables_addRequirementsTxt,
		tables_acrICardTxt
		};
		
		for(JTextField s:fieldtexts)
		{
			if(!s.getText().trim().isEmpty())
				return true;
		}
		
		JDatePickerImpl[] datefieldtexts = {tables_dateReceivedTxt,
				tables_aepDateFiledTxt, 
				tables_aepdateReleasedTxt, 
				tables_permitDateFiledTxt, 
				tables_permitDateReleasedTxt,	
		};
		
		for(JDatePickerImpl jd:datefieldtexts)	
		{
			System.out.println(jd == null);
			if(!jd.getJFormattedTextField().getText().trim().isEmpty())
				return true;
		}
		return false;
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
	
	public static boolean DateCheck(String date1, String date2) {
	 	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean approved = false;
		if((date1.isEmpty()) && (date2.isEmpty())) {
			return approved = true;
		}
		else if (!date1.isEmpty() && !date2.isEmpty()){
			try {
				Date datex = sdf.parse(date1);
				Date datey = sdf.parse(date2);
				if (datex.compareTo(datey) > 0) {
					//System.out.println("Date1 is after Date2"); FALSE
					JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Date Filed must be before the Date Released.</font color = #ffffff></html>", "Detected an error in date fields.", JOptionPane.ERROR_MESSAGE);
					approved = false;
				} else if (datex.compareTo(datey) < 0) {
					//System.out.println("Date1 is before Date2");TRUE
					approved = true;
				}
				
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//Start of snippet
		else if (date1.isEmpty() && !date2.isEmpty()) {
			JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Date Filed must not be blank.</font color = #ffffff></html>", "Detected an error in date fields.", JOptionPane.ERROR_MESSAGE);
		}
		else if (date2.isEmpty() && !date1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Hearing date must not be blank.</font color = #ffffff></html>", "Detected an error in date fields.", JOptionPane.ERROR_MESSAGE);
		}
		
		return approved;
	}
}

