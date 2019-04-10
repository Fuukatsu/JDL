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

import jdl.controller.Runner;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class GenerateExpiry extends JFrame{
	private String admin_username;
	private String admin_password;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateExpiry window = new GenerateExpiry();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GenerateExpiry() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerateExpiry.class.getResource("/jdl/Assets/login_small.png")));
		
		//Main Panel
		
		setTitle("JDL: Options");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(690, 480));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		getContentPane().setLayout(null);
		
		JLabel generate_minimizeBtn = new JLabel("");
		generate_minimizeBtn.setBounds(654, 0, 26, 46);
		getContentPane().add(generate_minimizeBtn);
		generate_minimizeBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		generate_minimizeBtn.setIcon(new ImageIcon(GenerateExpiry.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel generate_expiry = new JLabel("Number of Expiry");
		generate_expiry.setBounds(285, 0, 138, 46);
		getContentPane().add(generate_expiry);
		generate_expiry.setForeground(new Color(255, 255, 255));
		generate_expiry.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel generate_reportBackBtn = new JLabel("");
		generate_reportBackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Runner.destroyGE();
				Runner.openGR();
			}
		});
		
		generate_reportBackBtn.setIcon(new ImageIcon(GenerateExpiry.class.getResource("/jdl/Assets/button_back.png")));
		generate_reportBackBtn.setBounds(10, 15, 48, 14);
		getContentPane().add(generate_reportBackBtn);
		
		JLabel label = new JLabel("SELECT A RANGE OF TIME YOU WANT TO TOTALIZE");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		label.setBounds(173, 64, 360, 34);
		getContentPane().add(label);
		
		JLabel icon_weekly = new JLabel("");
		icon_weekly.setIcon(new ImageIcon(GenerateExpiry.class.getResource("/jdl/Assets/generate_weekly2.png")));
		icon_weekly.setBounds(116, 135, 52, 50);
		getContentPane().add(icon_weekly);
		
		JButton expiry_weeklyBtn = new JButton("Weekly");
		expiry_weeklyBtn.setForeground(Color.WHITE);
		expiry_weeklyBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		expiry_weeklyBtn.setBackground(new Color(0, 102, 102));
		expiry_weeklyBtn.setBounds(80, 196, 131, 34);
		getContentPane().add(expiry_weeklyBtn);
		
		JLabel icon_monthly = new JLabel("");
		icon_monthly.setIcon(new ImageIcon(GenerateExpiry.class.getResource("/jdl/Assets/generate_month.png")));
		icon_monthly.setBounds(319, 135, 52, 50);
		getContentPane().add(icon_monthly);
		
		JButton expiry_monthlyBtn = new JButton("Monthly");
		expiry_monthlyBtn.setForeground(Color.WHITE);
		expiry_monthlyBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		expiry_monthlyBtn.setBackground(new Color(0, 102, 102));
		expiry_monthlyBtn.setBounds(281, 196, 131, 34);
		getContentPane().add(expiry_monthlyBtn);
		
		JLabel icon_yearly = new JLabel("");
		icon_yearly.setIcon(new ImageIcon(GenerateExpiry.class.getResource("/jdl/Assets/generate_yearly.png")));
		icon_yearly.setBounds(520, 135, 52, 50);
		getContentPane().add(icon_yearly);
		
		JButton expiry_yearlyBtn = new JButton("Yearly");
		expiry_yearlyBtn.setForeground(Color.WHITE);
		expiry_yearlyBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		expiry_yearlyBtn.setBackground(new Color(0, 102, 102));
		expiry_yearlyBtn.setBounds(481, 196, 131, 34);
		getContentPane().add(expiry_yearlyBtn);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255, 60));
		panel.setBounds(249, 259, 188, 195);
		getContentPane().add(panel);
		
		JLabel expiry_countName = new JLabel("<html><center>TOTAL  NO. OF EXPIRING VISAS</center></html>");
		expiry_countName.setForeground(Color.WHITE);
		expiry_countName.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		expiry_countName.setBounds(33, 143, 126, 41);
		panel.add(expiry_countName);
		
		JLabel expiry_countLbl = new JLabel("");
		expiry_countLbl.setForeground(Color.WHITE);
		expiry_countLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 99));
		expiry_countLbl.setBounds(10, 26, 168, 105);
		panel.add(expiry_countLbl);
		
		JLabel options_background = new JLabel("");
		options_background.setIcon(new ImageIcon(GenerateExpiry.class.getResource("/jdl/Assets/background_optionList4.jpg")));
		options_background.setBounds(0, 0, 690, 480);
		getContentPane().add(options_background);
		
	}
}
