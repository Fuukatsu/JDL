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

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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
		setMinimumSize(new Dimension(1422, 799));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
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
		transaction_historylbl.setForeground(Color.WHITE);
		transaction_historylbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		transaction_historylbl.setBounds(21, 61, 374, 49);
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
				dispose();
				new OptionList().setVisible(true);
				dispose();
			}
		});
		emp_back.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_back.png")));
		emp_back.setHorizontalAlignment(SwingConstants.CENTER);
		emp_back.setForeground(Color.WHITE);
		emp_back.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel transaction_titleLbl = new JLabel("Activity History");
		transaction_titleLbl.setBounds(627, 0, 242, 41);
		getContentPane().add(transaction_titleLbl);
		transaction_titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		transaction_titleLbl.setForeground(Color.WHITE);
		transaction_titleLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		//Images
		
		JLabel emp_minimize = new JLabel("");
		emp_minimize.setBounds(1377, 0, 35, 41);
		getContentPane().add(emp_minimize);
		emp_minimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		emp_minimize.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel transaction_selectuserlbl = new JLabel("Select User :");
		transaction_selectuserlbl.setForeground(Color.WHITE);
		transaction_selectuserlbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		transaction_selectuserlbl.setBounds(21, 739, 128, 49);
		getContentPane().add(transaction_selectuserlbl);
		
		JComboBox comboBox = new JComboBox(objectFilter.getUsernames());
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel md = Queries.getClientTransactions(comboBox.getSelectedItem().toString());
				table.setModel(md);
			}
		});
		for(String s:objectFilter.getUsernames())
			System.out.println(s);
		comboBox.setBounds(105, 754, 189, 22);
		getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 1040, 289);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		TableModel md = Queries.getClientTransactions(comboBox.getSelectedItem().toString());
		table.setModel(md);
		scrollPane.setViewportView(table);
		
		
		JLabel emp_background = new JLabel("");
		emp_background.setIcon(new ImageIcon(ActivityHistory.class.getResource("/jdl/Assets/background_tables4.jpg")));
		emp_background.setBounds(0, 0, 1422, 799);
		getContentPane().add(emp_background);

		
	}
    
}

