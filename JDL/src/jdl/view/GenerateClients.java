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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.JTableHeader;

import jdl.controller.Runner;
import jdl.controller.TableColumnAdjuster;
import jdl.controller.objectFilter;
import jdl.dao.Queries;
import jdl.model.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class GenerateClients extends JFrame{
	private String admin_username;
	private String admin_password;
	private JTable table_1;
	private ArrayList<Transaction> tlist;


	public GenerateClients() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerateClients.class.getResource("/jdl/Assets/login_small.png")));
		
		//Main Panel
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Client ID", "Transaction ID", "Passport No.", "TIN ID", "Visa Type", "Visa Start Date", "Visa End Date", "Permit Type", "Permit Start Date", "Permit End Date", "AEP ID", "AEP Start Date", "AEP End Date", "Date Created", "Date Created"
			}
		));
		TableColumnAdjuster tca1 = new TableColumnAdjuster(table_1);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tca1.adjustColumns();
		setTitle("JDL: Generate Clients");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(1000, 690));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		getContentPane().setLayout(null);
		
		JLabel generate_actualCountLbl = new JLabel("");
		generate_actualCountLbl.setHorizontalAlignment(SwingConstants.CENTER);
		generate_actualCountLbl.setForeground(Color.WHITE);
		generate_actualCountLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 90));
		generate_actualCountLbl.setBounds(10, 422, 227, 166);
		getContentPane().add(generate_actualCountLbl);
		
		JLabel generate_minimizeBtn = new JLabel("");
		generate_minimizeBtn.setBounds(964, 0, 26, 46);
		getContentPane().add(generate_minimizeBtn);
		generate_minimizeBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		generate_minimizeBtn.setIcon(new ImageIcon(GenerateClients.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel generate_client = new JLabel("Number of Client Transactions");
		generate_client.setBounds(419, 0, 213, 46);
		getContentPane().add(generate_client);
		generate_client.setForeground(new Color(255, 255, 255));
		generate_client.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel generate_reportBackBtn = new JLabel("");
		generate_reportBackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Runner.destroyGC();
				Runner.openGR();
			}
		});
		
		generate_reportBackBtn.setIcon(new ImageIcon(GenerateClients.class.getResource("/jdl/Assets/button_back.png")));
		generate_reportBackBtn.setBounds(10, 15, 48, 14);
		getContentPane().add(generate_reportBackBtn);
		
		JLabel generate_clientRangeLbl = new JLabel("SELECT A RANGE OF TIME:");
		generate_clientRangeLbl.setForeground(Color.WHITE);
		generate_clientRangeLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		generate_clientRangeLbl.setBounds(35, 88, 180, 56);
		getContentPane().add(generate_clientRangeLbl);
		
		JButton generate_weeklyBtn = new JButton("Weekly");
		generate_weeklyBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				String date = objectFilter.getDateToday();
				Date sd = java.sql.Date.valueOf(objectFilter.getSundayoftheWeek(date));
				Date ed = java.sql.Date.valueOf(objectFilter.getSaturdayoftheWeek(date));
				tlist = Queries.getBetweenTransactionDate(sd, ed);
				Object[][] tl = new Object[tlist.size()][13];
				for(int i = 0; i < tlist.size();i++)
				{
					Object[] ttl = new Object[14];
					Transaction ttemp = tlist.get(i);
					ttl[0] = Integer.toString(ttemp.getClient_id());
					ttl[1] = Integer.toString(ttemp.getTransID());
					ttl[2] = ttemp.getPassportNo();
					ttl[3] = ttemp.getTinID();
					ttl[4] = ttemp.getVisaType();
					ttl[5] = ttemp.getVisaStartDate();
					ttl[6] = ttemp.getVisaEndDate();
					ttl[7] = ttemp.getPermitType();
					ttl[8] = ttemp.getPermitStartDate();
					ttl[9] = ttemp.getPermitEndDate();
					ttl[10] = ttemp.getAepID();
					ttl[11] = ttemp.getAepStartDate();
					ttl[12] = ttemp.getAepEndDate();
					ttl[14] = ttemp.getTransTimestamp();
					tl[i] = ttl;
				}
				table_1.setModel(applyTableModel(tl));
				TableColumnAdjuster tca1 = new TableColumnAdjuster(table_1);
				generate_actualCountLbl.setText(Integer.toString(countClients(tl)));
				table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tca1.adjustColumns();
				//Query nung table with constraints to weekly
			}
		});
		generate_weeklyBtn.setBackground(new Color(0, 102, 102));
		generate_weeklyBtn.setForeground(new Color(255, 255, 255));
		generate_weeklyBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		generate_weeklyBtn.setBounds(27, 143, 188, 39);
		getContentPane().add(generate_weeklyBtn);
		
		JButton generate_monthlyBtn = new JButton("Monthly");
		generate_monthlyBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String date = objectFilter.getDateToday();
				String[] range = objectFilter.rangeMonth(date);
				Date sd = java.sql.Date.valueOf(range[0]);
				Date ed = java.sql.Date.valueOf(range[1]);
				tlist = Queries.getBetweenTransactionDate(sd, ed);
				Object[][] tl = new Object[tlist.size()][13];
				for(int i = 0; i < tlist.size();i++)
				{
					Object[] ttl = new Object[14];
					Transaction ttemp = tlist.get(i);
					ttl[0] = Integer.toString(ttemp.getClient_id());
					ttl[1] = Integer.toString(ttemp.getTransID());
					ttl[2] = ttemp.getPassportNo();
					ttl[3] = ttemp.getTinID();
					ttl[4] = ttemp.getVisaType();
					ttl[5] = ttemp.getVisaStartDate();
					ttl[6] = ttemp.getVisaEndDate();
					ttl[7] = ttemp.getPermitType();
					ttl[8] = ttemp.getPermitStartDate();
					ttl[9] = ttemp.getPermitEndDate();
					ttl[10] = ttemp.getAepID();
					ttl[11] = ttemp.getAepStartDate();
					ttl[12] = ttemp.getAepEndDate();
					ttl[14] = ttemp.getTransTimestamp();
					tl[i] = ttl;
				}
				table_1.setModel(applyTableModel(tl));
				TableColumnAdjuster tca1 = new TableColumnAdjuster(table_1);
				generate_actualCountLbl.setText(Integer.toString(countClients(tl)));
				table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tca1.adjustColumns();
				//Query nung table with constraints to weekly
			
				//Query nung table with constraints to monthly
			}
		});
		generate_monthlyBtn.setForeground(Color.WHITE);
		generate_monthlyBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		generate_monthlyBtn.setBackground(new Color(0, 102, 102));
		generate_monthlyBtn.setBounds(27, 193, 188, 39);
		getContentPane().add(generate_monthlyBtn);
		
		JButton generate_yearlyBtn = new JButton("Yearly");
		generate_yearlyBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				String date = objectFilter.getDateToday();
				String[] range = objectFilter.rangeYear(date);
				Date sd = java.sql.Date.valueOf(range[0]);
				Date ed = java.sql.Date.valueOf(range[1]);
				tlist = Queries.getBetweenTransactionDate(sd, ed);
				Object[][] tl = new Object[tlist.size()][13];
				for(int i = 0; i < tlist.size();i++)
				{
					Object[] ttl = new Object[15];
					Transaction ttemp = tlist.get(i);
					ttl[0] = Integer.toString(ttemp.getClient_id());
					ttl[1] = Integer.toString(ttemp.getTransID());
					ttl[2] = ttemp.getPassportNo();
					ttl[3] = ttemp.getTinID();
					ttl[4] = ttemp.getVisaType();
					ttl[5] = ttemp.getVisaStartDate();
					ttl[6] = ttemp.getVisaEndDate();
					ttl[7] = ttemp.getPermitType();
					ttl[8] = ttemp.getPermitStartDate();
					ttl[9] = ttemp.getPermitEndDate();
					ttl[10] = ttemp.getAepID();
					ttl[11] = ttemp.getAepStartDate();
					ttl[12] = ttemp.getAepEndDate();
					ttl[14] = ttemp.getTransTimestamp();
					tl[i] = ttl;
				}
				table_1.setModel(applyTableModel(tl));
				TableColumnAdjuster tca1 = new TableColumnAdjuster(table_1);
				generate_actualCountLbl.setText(Integer.toString(countClients(tl)));
				table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tca1.adjustColumns();
				//Query nung table with constraints to weekly
			
				//Query nung table with constraints to monthly
			}
		});
		generate_yearlyBtn.setForeground(Color.WHITE);
		generate_yearlyBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		generate_yearlyBtn.setBackground(new Color(0, 102, 102));
		generate_yearlyBtn.setBounds(27, 245, 188, 39);
		getContentPane().add(generate_yearlyBtn);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255, 60));
		panel.setBounds(32, 261, 188, 195);
		
		JLabel generate_countNameLbl = new JLabel("<html><center>TOTAL CLIENT <br>TRANSACTIONS</center></html>");
		generate_countNameLbl.setBounds(33, 142, 126, 41);
		panel.add(generate_countNameLbl);
		generate_countNameLbl.setForeground(Color.WHITE);
		generate_countNameLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		
		JLabel generate_countLbl = new JLabel("");
		generate_countLbl.setBounds(10, 26, 168, 105);
		panel.add(generate_countLbl);
		generate_countLbl.setForeground(Color.WHITE);
		generate_countLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 99));
		
		JLabel generate_clientListLbl = new JLabel("LIST OF CLIENT TRANSACTIONS:");
		generate_clientListLbl.setForeground(Color.WHITE);
		generate_clientListLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		generate_clientListLbl.setBounds(243, 56, 219, 34);
		getContentPane().add(generate_clientListLbl);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_1.setBounds(243, 101, 735, 567);
		getContentPane().add(scrollPane_1);
		
		table_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		table_1.setBounds(495, 198, 125, 68);
		table_1.setRowHeight(32);
		table_1.setBorder(null);
		
		JTableHeader header1 = table_1.getTableHeader();
		header1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header1.setBackground(new Color(155, 177, 166));
	    header1.setForeground(Color.WHITE);
		scrollPane_1.setViewportView(table_1);
		
		UIDefaults defaults1 = UIManager.getLookAndFeelDefaults();
		if (defaults1.get("Table.alternateRowColor") == null)
		    defaults1.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		JLabel generate_clientCountLbl = new JLabel("<HTML><CENTER>NUMBER OF CLIENTS<br> IN RANGE</br></CENTER></HTML>");
		generate_clientCountLbl.setForeground(Color.WHITE);
		generate_clientCountLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		generate_clientCountLbl.setBounds(51, 354, 138, 39);
		getContentPane().add(generate_clientCountLbl);
		
		JLabel options_background = new JLabel("");
		options_background.setIcon(new ImageIcon(GenerateClients.class.getResource("/jdl/Assets/background_tables4.jpg")));
		options_background.setBounds(0, 0, 1000, 690);
		getContentPane().add(options_background);
		
	}
	public static DefaultTableModel resetModel()
	{
		return new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Client ID", "Transaction ID", "Passport No.", "TIN ID", "Visa Type", "Visa Start Date", "Visa End Date", "Permit Type", "Permit Start Date", "Permit End Date", "AEP ID", "AEP Start Date", "AEP End Date", "Date Created"
				}
			);
	}
	public static DefaultTableModel applyTableModel(Object[][] rows)
	{
		return new DefaultTableModel(
				rows,
				new String[] {
					"Client ID", "Transaction ID", "Passport No", "TIN ID", "Visa Type", "Visa Start Date", "Visa End Date", "Permit Type", "Permit Start Date", "Permit End Date", "AEP ID", "AEP Start Date", "AEP End Date", "Date Created"
				}
			);
	}
	public static int countClients(Object[][] rows)
	{
		ArrayList<Object> list = new ArrayList<Object>();
		for(Object[] row:rows)
		{
			if(!list.contains(row[0]))
				list.add(row[0]);
		}
		return list.size();
	}
}
