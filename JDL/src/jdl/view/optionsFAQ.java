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

import jdl.controller.CSVLoaderMain;
import jdl.controller.Runner;
import jdl.model.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class optionsFAQ extends JFrame{
	
	public optionsFAQ() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(optionsFAQ.class.getResource("/jdl/Assets/login_small.png")));
		
		//Main Panel
		
		setTitle("JDL: FAQ");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(690, 480));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		getContentPane().setLayout(null);
		
		JLabel options_close = new JLabel("");
		options_close.setBounds(654, 0, 26, 46);
		getContentPane().add(options_close);
		options_close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		options_close.setIcon(new ImageIcon(optionsFAQ.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel faq_welcomeLbl = new JLabel("Frequently Asked Questions");
		faq_welcomeLbl.setBounds(254, 0, 195, 46);
		getContentPane().add(faq_welcomeLbl);
		faq_welcomeLbl.setForeground(new Color(255, 255, 255));
		faq_welcomeLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		//Labels
		
		JLabel faq_question1 = new JLabel("1. How does the system notify clients?");
		faq_question1.setForeground(Color.WHITE);
		faq_question1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		faq_question1.setBounds(27, 129, 297, 29);
		getContentPane().add(faq_question1);
		
		JLabel faq_back = new JLabel("");
		faq_back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Runner.destroyFAQ();
			}
		});
		faq_back.setIcon(new ImageIcon(optionsFAQ.class.getResource("/jdl/Assets/button_back.png")));
		faq_back.setBounds(10, 11, 26, 25);
		getContentPane().add(faq_back);
		
		JLabel faq_answer1 = new JLabel("<html>ANSWER: The system regularly checks the entries in the database for any expiring visas, permits or AEP IDs that will expire within the week of their expiration date. If you wish to see the list of those who are being notified by system, go to the main menu and select GENERATE REPORT > NUMBER OF EXPIRATION DATES > WEEKLY. (You may refer to the manual for more information)</html>");
		faq_answer1.setForeground(Color.WHITE);
		faq_answer1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		faq_answer1.setBounds(47, 163, 602, 100);
		getContentPane().add(faq_answer1);
		
		JLabel faq_answer2 = new JLabel("<html>ANSWER: In the upper left corner of the main menu, there's an arrow pointing upwards, the upload works by copying all the transactions you've made in other applications such as Microsoft Excel. You need to upload or convert a file in a 'CSV' format, and follow a specific format that suits the format of the database before uploading. (You may refer to the manual on how to convert a file to 'CSV' format and follow the database's format.)</html>");
		faq_answer2.setForeground(Color.WHITE);
		faq_answer2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		faq_answer2.setBounds(47, 309, 602, 100);
		getContentPane().add(faq_answer2);
		
		JLabel faq_question2 = new JLabel("2. How does the 'upload' thing work?");
		faq_question2.setForeground(Color.WHITE);
		faq_question2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		faq_question2.setBounds(27, 275, 297, 29);
		getContentPane().add(faq_question2);
		
		JLabel faq_faqIcon = new JLabel("");
		faq_faqIcon.setIcon(new ImageIcon(optionsFAQ.class.getResource("/jdl/Assets/options_FAQ1.png")));
		faq_faqIcon.setBounds(317, 57, 57, 61);
		getContentPane().add(faq_faqIcon);
		
		JLabel faq_tip = new JLabel("Tip: Hover your mouse to the icons you want to select in order to view their functions.");
		faq_tip.setForeground(new Color(255, 204, 51));
		faq_tip.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		faq_tip.setBounds(63, 427, 586, 29);
		getContentPane().add(faq_tip);
		
		JLabel faq_tipIcon = new JLabel("");
		faq_tipIcon.setIcon(new ImageIcon(optionsFAQ.class.getResource("/jdl/Assets/options_tip.png")));
		faq_tipIcon.setBounds(27, 427, 26, 29);
		getContentPane().add(faq_tipIcon);
		
		JLabel options_background = new JLabel("");
		options_background.setIcon(new ImageIcon(optionsFAQ.class.getResource("/jdl/Assets/background_optionList4.jpg")));
		options_background.setBounds(0, 0, 690, 480);
		getContentPane().add(options_background);
		
	}
}