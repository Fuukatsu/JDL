package jdl.controller;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;

import java.awt.Color;
import java.awt.Font;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import au.com.bytecode.opencsv.CSVReader;
import jdl.dao.CSVQueries;
import jdl.dao.Queries;
import jdl.model.Transaction;

public class CSVLoaderMain {
	
	String word = "NULL";
		public void run() {
		//Finds the file in the directory
		
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("CSV Files","csv"));
		fc.setCurrentDirectory(new java.io.File("C:/"));
		fc.setDialogTitle("Find CSV File");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			//
		}
		String directory = fc.getSelectedFile().getAbsolutePath(); //The CSV file location
		System.out.println(directory);
		
		//Read CSV
		try 
		{
			
			UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
		 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
		 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
		 	UIManager.put("Button.background", Color.WHITE);
		 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
			Path path = Paths.get(directory);
			Charset charset = StandardCharsets.UTF_8;
			String content = new String(Files.readAllBytes(path), charset);
			Files.write(path, content.getBytes(charset));
			String[] r = content.split("\n");
			String[] columns = r[0].split(",");
			columns[columns.length-1] = columns[columns.length-1].trim();
			String[][] rows = new String[r.length-1][columns.length];
			for(int i = 0; i < rows.length;i++)
			{
				rows[i] = r[i+1].split(",");
				rows[i][rows[i].length-1] = rows[i][rows[i].length-1].trim();
			}
			for(String[] ss: rows)
			{
				for(String s: ss)
				{
					System.out.print(s+" ");
				}
				System.out.println();
			}
			//System.out.println(checkColumns(columns));
			if(checkColumns(columns))
			{
				int c = 0;
				for(String[] ss: rows)
				{
					//System.out.println(ss[0]+" "+checkInput(ss)); 
					boolean status = false;
					Transaction t = new Transaction();
					if(checkInput(ss))
					{
						t.setPassportNo(ss[0]);
						t.setTinID(ss[1]);
						t.setVisaType(ss[2]);
						t.setVisaStartDate(new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(ss[3]).getTime())));
						t.setVisaEndDate(new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(ss[4]).getTime())));
						t.setPermitType(ss[5]);
						t.setPermitStartDate(new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(ss[6]).getTime())));
						t.setPermitEndDate(new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(ss[7]).getTime())));
						t.setAepID(ss[8]);
						t.setAepStartDate(new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(ss[9]).getTime())));
						t.setAepEndDate(new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(ss[10]).getTime())));
						t.setTransID(Integer.parseInt(ss[11]));
						t.setClient_id(Integer.parseInt(ss[12]));
						t.setTransTimestamp(new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(ss[13]).getTime())));
						t.setTransAuthor(ss[14]);
						status = Queries.insertTransaction(t);
					}
					else
					{
						status = false;
					}
					if(status)
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Transaction Data import from path "+directory+" row["+Integer.toString(c)+" has been successful</font color = #ffffff></html>", "Insert Successful", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Transaction Data import on row["+Integer.toString(c)+" has failed</font color = #ffffff></html>", "Insert Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
					c++;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Transaction Data import from path "+directory+" has incorrect column names (Check row 1 for correct corresponding row)</font color = #ffffff></html>", "Insert Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
			}
			
			//CSVLoader loader = new CSVLoader(conn1);
			//loader.loadCSV(directory, "transactions", true);
		
			//JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Data import from path "+directory+"has been successful</font color = #ffffff></html>", "Insert Successful", JOptionPane.INFORMATION_MESSAGE);
		
		}catch(Exception exc) {
			exc.printStackTrace();
			
		}
	} 
	public static boolean checkColumns(String[] c)
	{
		String[] cc = CSVQueries.getColumns().toArray(new String[0]);
		for(int i = 0; i < cc.length; i++)
		{
			if(!cc[i].equals(c[i]))
				return false;
		}
		return true;
	}
	public static boolean checkInput(String[] c)
	{
		try {
			String vs = c[3];//[index 3]
			String ve = c[4];//[index 4]
			String ps = c[5];//[index 6]
			String pe = c[6];//[index 7]
			String as = c[7];//[index 9]
			String ae  = c[8];//[index 10]
			if(objectFilter.inputCheck("Passport No.",c[0])){
				if(objectFilter.inputCheck("TIN ID",c[1])) {
					if(objectFilter.dateCheckTransaction("Visa", c[2], ve, vs)) {
						if(objectFilter.dateCheckTransaction("AEP", c[8], as, ae)) {
							if(objectFilter.dateCheckTransaction("Permit", c[5], ps, pe)) 
							{
								return true;
							}
						}
					}
				}
			}			
			
		}catch (Exception e3) {
			e3.printStackTrace();
		}
		return false;
	}
}
