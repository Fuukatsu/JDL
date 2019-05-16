package jdl.view;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
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

import jdl.controller.AutoCompletion;
import jdl.controller.DateLabelFormatter;
import jdl.controller.Runner;
import jdl.controller.TableColumnAdjuster;
import jdl.controller.objectFilter;
import jdl.dao.databaseProperties;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;
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

public class TablesUpdateClient extends JFrame{
	private JTextField tables_clientBirthdateTxt;
	private JTextField tables_clientPositionTxt;
	private String clientSelectedName;
	private JTextField tables_clientFirstnameTxt;
	private boolean tables_validator = true;
	private JTextField tables_clientLastnameTxt;
	private JTextField tables_clientAliasTxt;
	private JTextField tables_clientContactTxt;
	private JTextField tables_clientEmailTxt;
	private JTable table_1;
	private JTextField tables_clientCompanyTxt;
	private TableModel tm;
	private String client_id = "";
	private databaseProperties dP = new databaseProperties();
	public TablesUpdateClient() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Update Client");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(1550, 900));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		
		//Table
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(487, 203, 1040, 673);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		table_1 = new JTable();
		table_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		table_1.setBounds(495, 198, 125, 68);
		table_1.setRowHeight(32);
		table_1.setBorder(null);

		JTableHeader header = table_1.getTableHeader();
		header.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header.setBackground(new Color(155, 177, 166));
	    header.setForeground(Color.WHITE);
		scrollPane.setViewportView(table_1);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		//Input Section
		

		JButton tables_reloadBtn = new JButton("Reload");
		tables_reloadBtn.setBounds(1389, 154, 138, 38);
		tables_reloadBtn.setForeground(new Color(255, 255, 255));
		tables_reloadBtn.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/main_refresh.png")));
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(25, 141, 450, 735);
		tables_inputPanel.setBackground(new Color (255, 255, 255, 60));
		tables_inputPanel.setLayout(null);
		
		JComboBox tables_comboBox = new JComboBox();
		tables_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Click to see the list of registered client"}));
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
			       	
			    }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		tables_reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					Connection conn=DriverManager.getConnection(dP.url, dP.username, dP.password);
					Statement stat=conn.createStatement();
					Statement stat1=conn.createStatement();
					
					ResultSet rs=stat.executeQuery("SELECT client_id AS 'Client ID',"
							+ "client_lastname AS 'Lastname'" +
							", client_firstname AS 'Firstname'" + 
							", client_alias AS 'Alias' " + 
							", client_nationality AS 'Country' " + 
							", client_birthdate AS 'Birthdate' " + 
							", client_gender AS 'Gender' " + 
							", client_company AS 'Company' " + 
							", client_position AS 'Company Position' " + 
							", client_contact AS 'Contact No.' " + 
							", client_email AS 'Email' " + 
							" FROM jdl_accounts.clients WHERE client_isActive = 1 OR null ORDER BY client_id DESC");
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumnAdjuster tca = new TableColumnAdjuster(table_1);
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
		
		JLabel tables_inputSectionLbl = new JLabel("Update Section");
		tables_inputSectionLbl.setBounds(25, 96, 255, 44);
		tables_inputSectionLbl.setForeground(new Color(255, 255, 255));
		tables_inputSectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_clientNationalityLbl = new JLabel("Country:");
		tables_clientNationalityLbl.setForeground(new Color(255, 255, 255));
		tables_clientNationalityLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientNationalityLbl.setBounds(20, 299, 204, 29);
		tables_inputPanel.add(tables_clientNationalityLbl);
		
		JLabel tables_clientBirthdateLbl = new JLabel("Birthdate:");
		tables_clientBirthdateLbl.setForeground(new Color(255, 255, 255));
		tables_clientBirthdateLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientBirthdateLbl.setBounds(20, 356, 197, 29);
		tables_inputPanel.add(tables_clientBirthdateLbl);
			
		getContentPane().add(scrollPane);
		
		//Birthdate
		
		UtilDateModel birthdateModel = new UtilDateModel();
		Properties birthdate = new Properties();
		birthdate.put("text.today", "Date Today");
		birthdate.put("text.month", "Month");
		birthdate.put("text.year", "Year");
		birthdateModel.setDate(1980, 1, 1);

		JDatePanelImpl BirthdatePanel = new JDatePanelImpl(birthdateModel, birthdate);
		JDatePickerImpl birthdatePicker = new JDatePickerImpl(BirthdatePanel, new DateLabelFormatter());
		birthdatePicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));

		birthdatePicker.setLocation(20, 384);
		birthdatePicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		birthdatePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		birthdatePicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		birthdatePicker.setSize(400, 23);
		
		tables_inputPanel.add(birthdatePicker);
		
		JLabel tables_clientGenderLbl = new JLabel("Gender:");
		tables_clientGenderLbl.setForeground(new Color(255, 255, 255));
		tables_clientGenderLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientGenderLbl.setBounds(20, 407, 190, 29);
		tables_inputPanel.add(tables_clientGenderLbl);
		
		JLabel tables_clientCompanyLbl = new JLabel("Company:");
		tables_clientCompanyLbl.setForeground(Color.WHITE);
		tables_clientCompanyLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientCompanyLbl.setBounds(20, 462, 190, 29);
		tables_inputPanel.add(tables_clientCompanyLbl);
		
		JLabel tables_clientPositionLbl = new JLabel("Position in the Company:");
		tables_clientPositionLbl.setForeground(Color.WHITE);
		tables_clientPositionLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientPositionLbl.setBounds(20, 520, 197, 29);
		tables_inputPanel.add(tables_clientPositionLbl);
		
		tables_clientPositionTxt = new JTextField();
		tables_clientPositionTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientPositionTxt.setColumns(10);
		tables_clientPositionTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientPositionTxt.setBounds(20, 550, 400, 23);
		tables_inputPanel.add(tables_clientPositionTxt);
		
		JLabel tables_clientLastnameLbl = new JLabel("Lastname:");
		tables_clientLastnameLbl.setForeground(Color.WHITE);
		tables_clientLastnameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientLastnameLbl.setBounds(20, 102, 190, 41);
		tables_inputPanel.add(tables_clientLastnameLbl);
		
		tables_clientFirstnameTxt = new JTextField();
		tables_clientFirstnameTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientFirstnameTxt.setColumns(10);
		tables_clientFirstnameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientFirstnameTxt.setBounds(20, 199, 400, 23);
		tables_inputPanel.add(tables_clientFirstnameTxt);
		
		JLabel tables_clientFirstnameLbl = new JLabel("Firstname:");
		tables_clientFirstnameLbl.setForeground(Color.WHITE);
		tables_clientFirstnameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientFirstnameLbl.setBounds(20, 162, 298, 41);
		tables_inputPanel.add(tables_clientFirstnameLbl);
		
		tables_clientLastnameTxt = new JTextField();
		tables_clientLastnameTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientLastnameTxt.setColumns(10);
		tables_clientLastnameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientLastnameTxt.setBounds(20, 141, 400, 23);
		tables_inputPanel.add(tables_clientLastnameTxt);
		
		tables_clientAliasTxt = new JTextField();
		tables_clientAliasTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientAliasTxt.setColumns(10);
		tables_clientAliasTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientAliasTxt.setBounds(20, 254, 400, 23);
		tables_inputPanel.add(tables_clientAliasTxt);
		
		JLabel tables_clientAliasLbl = new JLabel("Alias:");
		tables_clientAliasLbl.setForeground(Color.WHITE);
		tables_clientAliasLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientAliasLbl.setBounds(20, 224, 204, 29);
		tables_inputPanel.add(tables_clientAliasLbl);
		
		tables_clientContactTxt = new JTextField();
		tables_clientContactTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientContactTxt.setColumns(10);
		tables_clientContactTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientContactTxt.setBounds(20, 605, 400, 23);
		tables_inputPanel.add(tables_clientContactTxt);
		
		JLabel tables_clientContactLbl = new JLabel("Contact No.:");
		tables_clientContactLbl.setForeground(Color.WHITE);
		tables_clientContactLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientContactLbl.setBounds(20, 575, 190, 29);
		tables_inputPanel.add(tables_clientContactLbl);
		
		tables_clientEmailTxt = new JTextField();
		tables_clientEmailTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientEmailTxt.setColumns(10);
		tables_clientEmailTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientEmailTxt.setBounds(20, 660, 400, 23);
		tables_inputPanel.add(tables_clientEmailTxt);
		
		JLabel tables_clientEmailLbl = new JLabel("Email:");
		tables_clientEmailLbl.setForeground(Color.WHITE);
		tables_clientEmailLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientEmailLbl.setBounds(20, 630, 190, 29);
		tables_inputPanel.add(tables_clientEmailLbl);
		
		JButton tables_registerBtn = new JButton("Update Client");
		tables_registerBtn.setForeground(new Color(255, 255, 255));
		
		tables_comboBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			try {
				Connection conn=DriverManager.getConnection(dP.url, dP.username, dP.password);
				Statement stat=conn.createStatement();
				Statement stat1=conn.createStatement();
					
					if(tables_comboBox.getSelectedItem().toString() == "Click to see the list of registered client") {
						tables_reloadBtn.setEnabled(false);
						tables_registerBtn.setEnabled(false);
						tables_clientLastnameTxt.setText("");
						tables_clientFirstnameTxt.setText("");
						tables_clientAliasTxt.setText("");
						birthdatePicker.getJFormattedTextField().setText("");
						tables_clientCompanyTxt.setText("");
						tables_clientPositionTxt.setText("");
						tables_clientContactTxt.setText("");
						tables_clientEmailTxt.setText("");
				
					}else if (tables_comboBox.getSelectedItem().toString() != "Click to see the list of registered client") {
						tables_reloadBtn.setEnabled(true);
					
					clientSelectedName = tables_comboBox.getSelectedItem().toString();
			       	
					ResultSet rs=stat.executeQuery("SELECT * FROM jdl_accounts.clients WHERE client_id = "+Integer.parseInt(clientSelectedName.substring(clientSelectedName.lastIndexOf(",")+2, clientSelectedName.length())));
					while(rs.next()) {
						tables_clientLastnameTxt.setText(rs.getString("client_lastname"));
						tables_clientFirstnameTxt.setText(rs.getString("client_firstname"));
						tables_clientAliasTxt.setText(rs.getString("client_alias"));
						birthdatePicker.getJFormattedTextField().setText((rs.getString("client_birthdate")));
						tables_clientCompanyTxt.setText(rs.getString("client_company"));
						tables_clientPositionTxt.setText(rs.getString("client_position"));
						tables_clientContactTxt.setText(rs.getString("client_contact"));
						tables_clientEmailTxt.setText(rs.getString("client_email"));
					}
					
				}} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	});
		
		tables_reloadBtn.setEnabled(false);
		
		tables_comboBox.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		tables_comboBox.setBounds(20, 49, 400, 29);
		
		AutoCompletion.enable(tables_comboBox);
		tables_inputPanel.add(tables_comboBox);
		
		
		JLabel tables_primaryInformationLbl = new JLabel("-------------------------- Primary Information ---------------------------");
		tables_primaryInformationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_primaryInformationLbl.setForeground(Color.WHITE);
		tables_primaryInformationLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_primaryInformationLbl.setBounds(20, 85, 400, 29);
		tables_inputPanel.add(tables_primaryInformationLbl);
		
		JLabel tables_secondaryInformationLbl = new JLabel("-------------------------- Secondary Information ------------------------");
		tables_secondaryInformationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_secondaryInformationLbl.setForeground(Color.WHITE);
		tables_secondaryInformationLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_secondaryInformationLbl.setBounds(20, 282, 400, 23);
		tables_inputPanel.add(tables_secondaryInformationLbl);
		
		JLabel tables_registeredClientsLbl = new JLabel("Registered Clients");
		tables_registeredClientsLbl.setForeground(Color.WHITE);
		tables_registeredClientsLbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		tables_registeredClientsLbl.setBounds(489, 155, 255, 37);
		getContentPane().add(tables_registeredClientsLbl);
		
		
		
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		
		tables_registerBtn.setBackground(new Color(0, 102, 102));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_registerBtn.setBounds(142, 698, 173, 29);
		tables_inputPanel.add(tables_registerBtn);
		
		JLabel tables_clientCreateTransactionLbl = new JLabel("Create New Transaction", SwingConstants.CENTER);
		tables_clientCreateTransactionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Runner.openTables();
				Runner.destroyTUC();
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
				Runner.destroyTUC();
			}
		});
		tables_clientStatusTableLbl.setForeground(Color.LIGHT_GRAY);
		tables_clientStatusTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel tables_clientRemarksTableLbl = new JLabel("Client Remarks Table", SwingConstants.CENTER);
		tables_clientRemarksTableLbl.setBounds(1290, 48, 209, 37);
		tables_clientRemarksTableLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				Runner.openTR();
				Runner.destroyTUC();
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
		tables_line.setBounds(309, 84, 57, 27);
		tables_line.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/line.png")));
		tables_line.setHorizontalAlignment(SwingConstants.CENTER);
		tables_line.setForeground(Color.WHITE);
		tables_line.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_updateTransactionLbl = new JLabel("Update Transaction", SwingConstants.CENTER);
		tables_updateTransactionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Runner.openTUT();
				Runner.destroyTUC();
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
				Runner.destroyTUC();
			}
		});
		tables_addClientLbl.setBounds(25, 48, 183, 37);
		tables_addClientLbl.setForeground(Color.LIGHT_GRAY);
		tables_addClientLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel tables_allClientTransactionLbl = new JLabel("Registered Clients");
		tables_allClientTransactionLbl.setBounds(495, 158, 171, 37);
		tables_allClientTransactionLbl.setForeground(Color.WHITE);
		tables_allClientTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
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
		
		JComboBox tables_genderBox = new JComboBox();
		tables_genderBox.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_genderBox.setBounds(20, 436, 400, 24);
		tables_inputPanel.add(tables_genderBox);
		tables_genderBox.setEditable(false);
		tables_genderBox.addItem("Male");
		tables_genderBox.addItem("Female");
		getContentPane().add(scrollPane);
		
		tables_clientCompanyTxt = new JTextField();
		tables_clientCompanyTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientCompanyTxt.setColumns(10);
		tables_clientCompanyTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientCompanyTxt.setBounds(20, 493, 400, 23);
		tables_inputPanel.add(tables_clientCompanyTxt);
		
		JComboBox tables_nationalityBox = new JComboBox (getAllCountries());
		tables_nationalityBox.setBounds(20, 328, 400, 23);
		tables_nationalityBox.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_inputPanel.add(tables_nationalityBox);	
		tables_nationalityBox.setEditable(true);
		AutoCompletion.enable(tables_nationalityBox);
		
		JLabel tables_chooseLbl = new JLabel("Choose Client:");
		tables_chooseLbl.setForeground(Color.WHITE);
		tables_chooseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_chooseLbl.setBounds(20, 11, 114, 41);
		tables_inputPanel.add(tables_chooseLbl);
		
		JLabel tables_back = new JLabel("");
		tables_back.setBounds(0, 0, 57, 37);
		getContentPane().add(tables_back);
		tables_back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Runner.destroyTUC();
				Runner.openOptionList();
			}
		});
		tables_back.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_back.png")));
		tables_back.setHorizontalAlignment(SwingConstants.CENTER);
		tables_back.setForeground(Color.WHITE);
		tables_back.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_seeTablesLbl = new JLabel("See Tables");
		tables_seeTablesLbl.setBounds(685, 4, 168, 37);
		getContentPane().add(tables_seeTablesLbl);
		tables_seeTablesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tables_seeTablesLbl.setForeground(Color.WHITE);
		tables_seeTablesLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
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
		
		JLabel tables_editClientsLbl = new JLabel("Update Clients", SwingConstants.CENTER);
		tables_editClientsLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Runner.openTAC();
				Runner.destroyTAC();
			}
		});
		tables_editClientsLbl.setForeground(Color.WHITE);
		tables_editClientsLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		tables_editClientsLbl.setBounds(245, 48, 183, 37);
		getContentPane().add(tables_editClientsLbl);
		
		JButton btnDeleteClient = new JButton("Delete\r\n");
		btnDeleteClient.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Runner.openTDC();
			}
		});
		btnDeleteClient.setIcon(new ImageIcon(TablesUpdateClient.class.getResource("/jdl/Assets/button_delete.png")));
		btnDeleteClient.setSelectedIcon(null);
		btnDeleteClient.setForeground(Color.WHITE);
		btnDeleteClient.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		btnDeleteClient.setBorder(null);
		btnDeleteClient.setBackground(new Color(0, 102, 102));
		btnDeleteClient.setBounds(1243, 154, 138, 38);
		getContentPane().add(btnDeleteClient);
		
		JLabel background_tables = new JLabel("New label");
		background_tables.setIcon(new ImageIcon(TablesUpdateClient.class.getResource("/jdl/Assets/background_tables4.jpg")));
		background_tables.setBounds(0, 0, 1551, 900);
		getContentPane().add(background_tables);
		
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
			 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
			 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
			 	UIManager.put("Button.background", Color.WHITE);
			 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
				Connection conn2;
				
				try {
					String sql = "UPDATE jdl_accounts.clients SET client_lastname = ?, client_firstname = ?, client_nationality = ?, client_birthdate = ?, client_gender = ?, client_company = ?, client_position = ?, client_alias = ?, client_contact = ?, client_email = ?"
							+ "WHERE client_id = "+Integer.parseInt(clientSelectedName.substring(clientSelectedName.lastIndexOf(",")+2, clientSelectedName.length()));
					
					conn2 = DriverManager.getConnection(dP.url, dP.username, dP.password);
					PreparedStatement statement1 = conn2.prepareStatement(sql);
					
					String[] input = new String[10];
					input[0] = tables_clientLastnameTxt.getText().trim();
					input[1] = tables_clientFirstnameTxt.getText().trim();
					input[2] = tables_nationalityBox.getSelectedItem().toString();
					input[3] = birthdatePicker.getJFormattedTextField().getText().toString();
					input[4] = tables_genderBox.getSelectedItem().toString();
					input[5] = tables_clientCompanyTxt.getText().trim();
					input[6] = tables_clientPositionTxt.getText().trim();
					input[7] = tables_clientAliasTxt.getText().trim();
					input[8] = tables_clientContactTxt.getText().trim();
					input[9] = tables_clientEmailTxt.getText().trim();
					
					String[] name = new String[10];
					name[0] = "Client's Lastname";
					name[1] = "Client's Firstname";
					name[2] = "Client's Nationality";
					name[3] = "Client's Birthdate";
					name[4] = "Client's Gender";
					name[5] = "Client's Company";
					name[6] = "Client's Position";
					name[7] = "Client's Alias";
					name[8] = "Client's Contact Number";
					name[9] = "Client's Email";
					
					if(objectFilter.validateEmptyStrings(input, name)) {
						System.out.print("IM HERE" + objectFilter.validateEmptyStrings(input, name));
						statement1.setString(1, tables_clientLastnameTxt.getText().trim());
						statement1.setString(2, tables_clientFirstnameTxt.getText().trim());
						statement1.setString(3, tables_nationalityBox.getSelectedItem().toString());
						if(input[5].equals(""))
							statement1.setDate(4, null);
						else
						statement1.setDate(4, java.sql.Date.valueOf(objectFilter.addDay(birthdatePicker.getJFormattedTextField().getText().toString())));
						statement1.setString(5, tables_genderBox.getSelectedItem().toString());
						statement1.setString(6, tables_clientCompanyTxt.getText().trim());
						statement1.setString(7, tables_clientPositionTxt.getText().trim());
						statement1.setString(8, tables_clientAliasTxt.getText().trim());
						statement1.setString(9, tables_clientContactTxt.getText().trim());
						statement1.setString(10, tables_clientEmailTxt.getText().trim());
						statement1.executeUpdate();
						tables_reloadBtn.doClick();
						
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Client has been added successfully.</font color = #ffffff></html>", "Client has been recorded successfully.", JOptionPane.INFORMATION_MESSAGE);
						
						Runner.destroyTUC();
						Runner.openTUC();
					}
				}

				 catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
	});
	
		int ifAdmin = Runner.getUser().getUser_ifAdmin();
		
		if (ifAdmin == 0) {
			
			btnDeleteClient.setVisible(false);
		}
	}
	
	public String[] getAllCountries() {
	    String[] countries = new String[Locale.getISOCountries().length];
	    String[] countryCodes = Locale.getISOCountries();
	    for (int i = 0; i < countryCodes.length; i++) {
	        Locale obj = new Locale("", countryCodes[i]);
	        countries[i] = obj.getDisplayCountry();
	    }
	    return countries;
	 }
}

