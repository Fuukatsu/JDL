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

public class GenerateReport extends JFrame{
	private String admin_username;
	private String admin_password;


	public GenerateReport() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerateReport.class.getResource("/jdl/Assets/login_small.png")));
		
		//Main Panel
		//setVisible(true);
		setTitle("JDL: Generate Report");
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
		generate_minimizeBtn.setIcon(new ImageIcon(GenerateReport.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel generate_titleLbl = new JLabel("Generate Report");
		generate_titleLbl.setBounds(288, 0, 138, 46);
		getContentPane().add(generate_titleLbl);
		generate_titleLbl.setForeground(new Color(255, 255, 255));
		generate_titleLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel generate_reportBtn = new JLabel("");
		generate_reportBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Runner.destroyGR();
				Runner.openOptionList();
			}
		});
		generate_reportBtn.setIcon(new ImageIcon(GenerateReport.class.getResource("/jdl/Assets/button_back.png")));
		generate_reportBtn.setBounds(10, 15, 48, 14);
		getContentPane().add(generate_reportBtn);
		
		JLabel generate_numpeopleBtn = new JLabel("");
		generate_numpeopleBtn.setToolTipText("<html>Generates a list of client transactions created within the week, month or\r\n<br>year.</html>");
		generate_numpeopleBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		generate_numpeopleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Runner.destroyGR();
				Runner.openGC();
				
			}
		});
		generate_numpeopleBtn.setIcon(new ImageIcon(GenerateReport.class.getResource("/jdl/Assets/generate_numPeople1.png")));
		generate_numpeopleBtn.setBounds(154, 173, 96, 96);
		getContentPane().add(generate_numpeopleBtn);
		
		JLabel generate_numExpiredBtn = new JLabel("");
		generate_numExpiredBtn.setToolTipText("<html>Generates a list of any expiring transaction dates covered within the week,\r\n<br>month, or year.</html>");
		generate_numExpiredBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Runner.destroyGR();
				Runner.openGE();
			}
		});
		
		generate_numExpiredBtn.setIcon(new ImageIcon(GenerateReport.class.getResource("/jdl/Assets/generate_numExpired1.png")));
		generate_numExpiredBtn.setBounds(445, 173, 96, 96);
		getContentPane().add(generate_numExpiredBtn);
		
		JLabel generate_numpeopleLbl = new JLabel("<html><center>NUMBER OF CLIENTS </center></html>");
		generate_numpeopleLbl.setForeground(Color.WHITE);
		generate_numpeopleLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		generate_numpeopleLbl.setBounds(113, 280, 194, 39);
		getContentPane().add(generate_numpeopleLbl);
		
		JLabel generate_numExpiredLbl = new JLabel("<html><center>NUMBER OF <br>EXPIRATION DATES </br></center></html>");
		generate_numExpiredLbl.setForeground(Color.WHITE);
		generate_numExpiredLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		generate_numExpiredLbl.setBounds(414, 269, 176, 57);
		getContentPane().add(generate_numExpiredLbl);
		
		JLabel lblSelectAnArea = new JLabel("Select an Area Where to Generate Data");
		lblSelectAnArea.setForeground(Color.WHITE);
		lblSelectAnArea.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblSelectAnArea.setBounds(201, 75, 290, 46);
		getContentPane().add(lblSelectAnArea);
		
		JLabel options_background = new JLabel("");
		options_background.setIcon(new ImageIcon(GenerateReport.class.getResource("/jdl/Assets/background_optionList4.jpg")));
		options_background.setBounds(0, 0, 690, 480);
		getContentPane().add(options_background);
		
	}
	
}
