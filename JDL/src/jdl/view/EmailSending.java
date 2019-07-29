package jdl.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.Rectangle;
import javax.swing.border.EmptyBorder;

import jdl.controller.EmailFunctions;
import jdl.controller.Runner;
import jdl.controller.objectFilter;
import jdl.dao.Queries;
import jdl.dao.databaseProperties;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmailSending extends JWindow {

    /**
	 * 
	 */
	static databaseProperties dP = new databaseProperties();
	static boolean isRegistered;
    private static JProgressBar splash_progressBar = new JProgressBar();
    private static int count = 112;
    private static Timer time;
    
    JLabel splash_sendTxt = new JLabel("<html><center>Sending...</html>");
    JLabel splash_close = new JLabel("");

    public EmailSending() 
    {
    	setAlwaysOnTop(true);
    	
    	getContentPane().setForeground(Color.WHITE);
    	setForeground(Color.WHITE);
    	
    	//Panels and Containers
    	
    	getContentPane().setBackground(new Color(0, 0, 0));
    	setBounds(new Rectangle(417, 0, 217, 225));
    	getContentPane().setBounds(new Rectangle(417, 0, 217, 225));
        Container container = getContentPane();
        container.setLayout(null);
        
        //Images 
        
        JLabel splash_loaderIcon = new JLabel("");
        splash_loaderIcon.setIcon(new ImageIcon(EmailSending.class.getResource("/jdl/Assets/splash_emailSend.gif")));
        splash_loaderIcon.setBounds(79, 0, 310, 176);
        getContentPane().add(splash_loaderIcon);
        
        //Labels
        
        JLabel splash_textDisplay = new JLabel("<html><center>We are now sending emails to your clients. This may take a while, please wait and do not close this window yet.</html>");
        splash_textDisplay.setForeground(new Color(255, 255, 255));
        splash_textDisplay.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
        splash_textDisplay.setBounds(25, 210, 441, 40);
        getContentPane().add(splash_textDisplay);
        setSize(502, 276);
        setLocationRelativeTo(null);
        setVisible(true);
        
        splash_sendTxt.setForeground(Color.ORANGE);
        splash_sendTxt.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
        splash_sendTxt.setBounds(215, 182, 65, 40);
        getContentPane().add(splash_sendTxt);
        splash_sendTxt.setVisible(false);
        
        splash_close.setVisible(false);
        splash_close.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		Runner.destroyES();
        		Runner.openOptionList();
        	}
        });
       
        splash_close.setIcon(new ImageIcon(EmailSending.class.getResource("/jdl/Assets/button_close.png")));
        splash_close.setForeground(Color.WHITE);
        splash_close.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
        splash_close.setBounds(472, 0, 30, 40);
        getContentPane().add(splash_close);
        
        //Progress Bar
        splash_progressBar.setBackground(new Color(255, 255, 255));
        splash_progressBar.setForeground(new Color(100, 149, 237));
        splash_progressBar.setMaximum(50);
        splash_progressBar.setBounds(0, 256, 502, 31);
        splash_progressBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        container.add(splash_progressBar);
    }
    public void loadProgressBar() 
    {
        ActionListener al = new ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {

					count++;
					splash_progressBar.setValue(count);
      
					splash_sendTxt.setVisible(true);

					if (count == 200) 
					{	 	
							EmailFunctions.initiateEmail();
							EmailFunctions.checkTodayNotification();
                	
							Runner.openOptionList();
							Runner.destroyES();
            	}	
                }
        };
        
        time = new Timer(50, al);
        time.start();
    }
}