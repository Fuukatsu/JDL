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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import jdl.controller.Runner;
import jdl.controller.TableColumnAdjuster;
import jdl.controller.objectFilter;
import jdl.dao.Queries;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class ActivityHistory extends JFrame{
	private JTextField tables_clientBirthdateTxt;
	private String clientSelectedName;
	private boolean tables_validator = true;
	private JTable table;

	/**
	 * Create the application.
	 */
	

    
	public ActivityHistory() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Account Management");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(1000, 680));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		//Input Section (Labels and Associated Textfields)
		
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
		transaction_historylbl.setBounds(21, 48, 374, 37);
		transaction_historylbl.setForeground(Color.WHITE);
		transaction_historylbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		getContentPane().add(transaction_historylbl);
		
		JLabel transaction_historyLbl = new JLabel("Some title here");
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
		
		JLabel transaction_titleLbl = new JLabel("Activity History");
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
		
		JLabel transaction_selectuserlbl = new JLabel("Select User to View Transaction History:");
		transaction_selectuserlbl.setBounds(185, 628, 284, 41);
		transaction_selectuserlbl.setForeground(Color.WHITE);
		transaction_selectuserlbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		getContentPane().add(transaction_selectuserlbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 91, 955, 534);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		getContentPane().add(scrollPane);
		
		
		JComboBox comboBox = new JComboBox(objectFilter.getUsernames());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TableModel md = Queries.getClientTransactions(comboBox.getSelectedItem().toString());
				table.setModel(md);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				TableColumnAdjuster tca = new TableColumnAdjuster(table);
				tca.adjustColumns();

			}
		});
		comboBox.setBounds(477, 634, 302, 28);
		comboBox.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		
		table = new JTable();
		TableModel md = Queries.getClientTransactions(comboBox.getSelectedItem().toString());
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
		
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setBounds(495, 198, 125, 68);
		table.setRowHeight(25);
		table.setBorder(null);
		scrollPane.setViewportView(table);
		
		for(String s:objectFilter.getUsernames())
			System.out.println(s);
		getContentPane().add(comboBox);
		
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header.setBackground(new Color(155, 177, 166));
	    header.setForeground(Color.WHITE);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		
		JLabel emp_background = new JLabel("");
		emp_background.setBounds(0, 0, 1000, 680);
		emp_background.setIcon(new ImageIcon(ActivityHistory.class.getResource("/jdl/Assets/background_tables4.jpg")));
		getContentPane().add(emp_background);

		
	}
}

