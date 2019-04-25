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
import java.util.*;
import au.com.bytecode.opencsv.CSVReader;
import jdl.dao.CSVQueries;
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
			String[][] rows = new String[r.length-1][columns.length];
			for(int i = 0; i < rows.length;i++)
			{
				rows[i] = r[i+1].split(",");
			}
			for(String[] ss: rows)
			{
				for(String s: ss)
				{
					System.out.print(s+" ");
				}
				System.out.println();
			}
			//System.out.println(CSVQueries.checkColumns(columns));
			if(checkColumns(columns))
			{
				
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
	public static Transaction addtoModel(String[] c)
	{
		Transaction t = new Transaction();
		
		return t;
	}
}
