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
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import jdl.controller.AutoCompletion;
import jdl.controller.Runner;
import jdl.controller.TableColumnAdjuster;
import jdl.controller.objectFilter;
import jdl.dao.Queries;
import jdl.dao.databaseProperties;

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
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class ActivityHistory extends JFrame{
	private JTextField tables_clientBirthdateTxt;
	private String clientSelectedName;
	private boolean tables_validator = true;
	private JTable table;
	private JTextField tables_searchTxt;

	/**
	 * Create the application.
	 */
	

    
	public ActivityHistory() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Transaction Activity History");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(1000, 680));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		//Input Section (Labels and Associated Textfields)
		
		tables_searchTxt = new JTextField();
		tables_searchTxt.setText("Enter keywords here");
		tables_searchTxt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		tables_searchTxt.setColumns(10);
		tables_searchTxt.setBorder(null);
		tables_searchTxt.setBounds(531, 61, 237, 34);
		getContentPane().add(tables_searchTxt);
		
		JLabel tables_filterIcon = new JLabel("");
		tables_filterIcon.setIcon(new ImageIcon(ActivityHistory.class.getResource("/jdl/Assets/client_filterIcon.png")));
		tables_filterIcon.setForeground(Color.WHITE);
		tables_filterIcon.setFont(new Font("Segoe UI", Font.BOLD, 15));
		tables_filterIcon.setBounds(493, 60, 35, 37);
		getContentPane().add(tables_filterIcon);
		
		JLabel tables_filterTableLbl = new JLabel("Filter Table:");
		tables_filterTableLbl.setForeground(Color.WHITE);
		tables_filterTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		tables_filterTableLbl.setBounds(405, 60, 89, 37);
		getContentPane().add(tables_filterTableLbl);
		
		
		//Birthdate
		
		UtilDateModel birthdateModel = new UtilDateModel();
		Properties birthdate = new Properties();
		birthdate.put("text.today", "Date Today");
		birthdate.put("text.month", "Month");
		birthdate.put("text.year", "Year");
		birthdateModel.setDate(1980, 1, 1);

		JDatePanelImpl BirthdatePanel = new JDatePanelImpl(birthdateModel, birthdate);
		getContentPane().setLayout(null);
		
		JLabel transaction_historylbl = new JLabel("Transaction Activity History:");
		transaction_historylbl.setBounds(21, 57, 374, 37);
		transaction_historylbl.setForeground(Color.WHITE);
		transaction_historylbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		getContentPane().add(transaction_historylbl);
		
		JLabel transaction_historyLbl = new JLabel("Transaction Activity History");
		transaction_historyLbl.setBounds(22, 54, 368, 37);
		transaction_historyLbl.setForeground(new Color(255, 255, 255));
		transaction_historyLbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		
		
		JLabel emp_back = new JLabel("");
		emp_back.setBounds(0, 0, 57, 37);
		getContentPane().add(emp_back);
		emp_back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Runner.destroyAH();
				Runner.openOptionList();
				
			}
		});
		
		emp_back.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_back.png")));
		emp_back.setHorizontalAlignment(SwingConstants.CENTER);
		emp_back.setForeground(Color.WHITE);
		emp_back.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel transaction_titleLbl = new JLabel("Transaction Activity History");
		transaction_titleLbl.setBounds(365, 0, 242, 37);
		getContentPane().add(transaction_titleLbl);
		transaction_titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		transaction_titleLbl.setForeground(Color.WHITE);
		transaction_titleLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		//Images
		
		JLabel emp_minimize = new JLabel("");
		emp_minimize.setBounds(964, 0, 26, 46);
		getContentPane().add(emp_minimize);
		emp_minimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		
		emp_minimize.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel transaction_selectuserlbl = new JLabel("Select User to View Its Transaction History:");
		transaction_selectuserlbl.setBounds(21, 635, 309, 41);
		transaction_selectuserlbl.setForeground(Color.WHITE);
		transaction_selectuserlbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		getContentPane().add(transaction_selectuserlbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 105, 955, 520);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		getContentPane().add(scrollPane);
		
		
		JComboBox history_userHistory = new JComboBox(objectFilter.getUsernames());
		AutoCompletion.enable(history_userHistory);
		history_userHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TableModel md = Queries.getClientTransactions2(history_userHistory.getSelectedItem().toString());
				table.setModel(md);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
				table.setRowSorter(sorter);
				TableColumnAdjuster tca = new TableColumnAdjuster(table);
				tca.adjustColumns();
				
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
				
			}
		});
		history_userHistory.setBounds(340, 641, 302, 28);
		history_userHistory.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		
		table = new JTable();
		table.setForeground(Color.DARK_GRAY);
		TableModel md = Queries.getClientTransactions(history_userHistory.getSelectedItem().toString());
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(68), new Integer(230), "P12345678", "12345", "123sdfs", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(229), "P12345678", "1234423", "", null, null, "asdsadf", null, null, "", null, null, null},
				{new Integer(68), new Integer(228), "P12345678", "132342", "675yfh", null, null, "uihiu", null, null, "", null, null, null},
				{new Integer(68), new Integer(227), "P12345678", "123523", "jy7", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(226), "P12345678", "1243432", "23d", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(225), "P12345678", "1235", "", null, null, "3fvh", null, null, "12342", null, null, null},
				{new Integer(68), new Integer(224), "P12345678", "12354", "34", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(223), "P12345678", "123456", "56hg", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(222), "P12345678", "12235467", "", null, null, "", null, null, "12ghj", null, null, null},
				{new Integer(68), new Integer(221), "P12345678", "123456", "", null, null, "6yhg", null, null, "", null, null, null},
				{new Integer(68), new Integer(220), "P12345678", "123321", "fg6", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(219), "P12345678", "1234532", "9k", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(218), "P12345678", "12345", "8h", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(217), "P12345678", "12346", "", null, null, "fgh5", null, null, "", null, null, null},
				{new Integer(68), new Integer(216), "P12345678", "12332", "rf", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(215), "P12345678", "1233243", "f4", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(214), "P12345678", "12345", "", null, null, "", null, null, "132sds", null, null, null},
				{new Integer(68), new Integer(213), "P12345678", "13221434", "", null, null, "", null, null, "a12341", null, null, null},
				{new Integer(68), new Integer(212), "P12345678", "123323", "3d", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(211), "P12345678", "1233434", "", null, null, "", null, null, "", null, null, null},
				{new Integer(68), new Integer(210), "P12345678", "123456", "", null, null, "SWD", null, null, "", null, null, null},
				{new Integer(68), new Integer(209), "P12345678", "12345", "9g", null, null, "", null, null, "", null, null, null},
				{new Integer(70), new Integer(182), "PH6310288", "9584344742", "Pre-arragend Employment Visa (9G)", null, null, "Special Working Permit (SWP)", null, null, "NCR-000-000-000-070", null, null, null},
				{new Integer(68), new Integer(181), "PH7041062", "8808072894", "Pre-arragend Employment Visa (9G)", null, null, "Special Working Permit (SWP)", null, null, "NCR-000-000-000-068", null, null, null},
				{new Integer(68), new Integer(123), "123412123", "123213", "oi8", null, null, "", null, null, "", null, null, null},
			},
			new String[] {
				"Client ID", "Transaction ID", "Passport No", "TIN ID", "Visa Type", "Visa Start Date", "Visa Expiry Date", "Permit Type", "Permit Start Date", "Permit Expiry Date", "AEP ID", "AEP Start Date", "AEP Expiry Date", "Action Done"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(100);
		table.getColumnModel().getColumn(11).setPreferredWidth(90);
		table.getColumnModel().getColumn(12).setPreferredWidth(90);
		table.getColumnModel().getColumn(13).setPreferredWidth(80);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(table);
		tca.adjustColumns();
		
		table.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		table.setBounds(495, 198, 125, 68);
		table.setRowHeight(30);
		table.setBorder(null);
		scrollPane.setViewportView(table);
		
		for(String s:objectFilter.getUsernames());
		getContentPane().add(history_userHistory);
		
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
	    header.setBackground(new Color(155, 177, 166));
	    header.setForeground(Color.WHITE);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		

		databaseProperties dP = new databaseProperties();
		JButton AC_reloadBtn = new JButton("Reset and Reload");
		AC_reloadBtn.setBackground(new Color(0, 102, 102));
		AC_reloadBtn.setIcon(new ImageIcon(ActivityHistory.class.getResource("/jdl/Assets/main_refresh.png")));
		AC_reloadBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		AC_reloadBtn.setForeground(new Color(255, 255, 255));
		AC_reloadBtn.setBounds(778, 60, 198, 36);
		getContentPane().add(AC_reloadBtn);
		AC_reloadBtn.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/main_refresh.png")));
		
		JLabel tables_background = new JLabel("");
		tables_background.setIcon(new ImageIcon(ActivityHistory.class.getResource("/jdl/Assets/background_tables4.jpg")));
		tables_background.setBounds(0, 0, 1000, 680);
		getContentPane().add(tables_background);
		AC_reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection(dP.url, dP.username, dP.password);
					PreparedStatement ps = conn.prepareStatement("SELECT client_id AS 'Client ID',"
							+ "trans_transId AS 'Transaction ID'" +
							", trans_passportNo AS 'Passport No' "+ 
							", trans_tinID AS 'TIN ID' " + 
							", trans_visaType AS 'Visa Type' " + 
							", DATE_ADD(trans_visaStartDate, INTERVAL 1 DAY) AS 'Visa Start Date' " + 
							", DATE_ADD(trans_visaEndDate, INTERVAL 1 DAY) AS 'Visa Expiry Date' " + 
							", trans_permitType AS 'Permit Type' " + 
							", DATE_ADD(trans_permitStartDate, INTERVAL 1 DAY) AS 'Permit Start Date' " + 
							", DATE_ADD(trans_permitEndDate, INTERVAL 1 DAY) AS 'Permit Expiry Date' " + 
							", trans_aepID AS 'AEP ID' " + 
							", DATE_ADD(trans_aepStartDate, INTERVAL 1 DAY) AS 'AEP Start Date' " + 
							", DATE_ADD(trans_aepEndDate, INTERVAL 1 DAY) AS 'AEP Expiry Date' " + 
							", DATE_ADD(trans_transTimestamp, INTERVAL 1 DAY) AS 'Timestamp' "+
							", trans_transAuthor AS 'Author' "+
							", trans_transAction AS 'Action' "+
							" FROM transactions WHERE trans_transAuthor = ? ORDER BY trans_transId DESC");
					
					ps.setString(1, history_userHistory.getSelectedItem().toString());
					ResultSet rs = ps.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumnAdjuster tca = new TableColumnAdjuster(table);
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
					table.setRowSorter(sorter);
					tca.adjustColumns();
					
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
					
					
					

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		AC_reloadBtn.doClick();
		
	}
}

