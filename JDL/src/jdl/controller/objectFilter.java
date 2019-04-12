package jdl.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import javax.swing.JOptionPane;

import jdl.dao.databaseProperties;
import jdl.dao.Queries;
import jdl.model.Client;
import jdl.model.Transaction;
import jdl.model.User;

public class objectFilter 
{
	public static String[] getClientList()
	{
		ArrayList<Client> cl = Queries.getClients();
		ArrayList<String> clist = new ArrayList<String>();
		clist.add("Click to see the list of registered client");
		for(Client c: cl)
		{
			clist.add(c.getClient_lastname()+", "+c.getClient_firstname()+": "+c.getClient_id());
		}
		return clist.toArray(new String[clist.size()]);
	}
	public static boolean containsDigit(String s) {
	    boolean containsDigit = false;

	    if (s != null && !s.isEmpty()) {
	        for (char c : s.toCharArray()) {
	            if (containsDigit = Character.isDigit(c)) {
	                break;
	            }
	        }
	    }

	    return containsDigit;
	}
	public static boolean containsAlpha(String name) {
	    char[] chars = name.toCharArray();
	    for (char c : chars) {
	        if(Character.isLetter(c)) 
	        {
	            return true;
	        }
	    }
	    return false;
	}
	public static String[] getUsernames() {
	    ArrayList<User> ulist = Queries.getUsers();
	    String[] ul = new String[ulist.size()];
	    for(int i = 0; i < ul.length;i++)
	    {
	    	ul[i] = ulist.get(i).getUser_username();
	    	System.out.println(ul[i]);
	    }
	    return ul;
	}
	
	public static boolean checkEmail(String email) {
		boolean t = false;
		final String EMAIL_PATTERN = 
			    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (email.matches(EMAIL_PATTERN)) {
			t = true;
		}
		return t;
	}
	
	public static String addDay(String date) {
		//String d = birthdatePicker.getJFormattedTextField().getText().toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(date));
		}catch(ParseException ex) {
			ex.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, 1);
		String newDate = format.format(c.getTime());  
		return newDate;
	}
	public static String addWeek(String date) {
		//String d = birthdatePicker.getJFormattedTextField().getText().toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(date));
		}catch(ParseException ex) {
			ex.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, 7);
		String newDate = format.format(c.getTime());  
		return newDate;
	}
	public static String getDateToday() 
	{
		Date date = new Date();
		//String d = birthdatePicker.getJFormattedTextField().getText().toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		String newDate = format.format(date);
		return newDate;
	}
	public static String addMonth(String date) {
		//String d = birthdatePicker.getJFormattedTextField().getText().toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(date));
		}catch(ParseException ex) {
			ex.printStackTrace();
		}
		c.add(Calendar.MONTH, 1);
		String newDate = format.format(c.getTime());  
		return newDate;
	}
	public static String addYear(String date) {
		//String d = birthdatePicker.getJFormattedTextField().getText().toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(date));
		}catch(ParseException ex) {
			ex.printStackTrace();
		}
		c.add(Calendar.YEAR, 1);
		String newDate = format.format(c.getTime());  
		return newDate;
	}
	
	//checking two dates
	public static boolean dateCheckTransaction(String typeName, String type, String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean approved = false;
		type = type.trim();
		
		if((date1.isEmpty()) && (date2.isEmpty()) && type.isEmpty()) {
			approved = true;
			return approved;
		}
		if(type.isEmpty()) {
			JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>"+typeName+" type must not be empty</font color = #ffffff></html>", "Detected an error in "+type+" type", JOptionPane.ERROR_MESSAGE);
		}
		else if((!date1.isEmpty() && date2.isEmpty())) {
			JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>"+typeName+" end date must not be empty</font color = #ffffff></html>", "Detected an error in date fields", JOptionPane.ERROR_MESSAGE);
			return approved = false;
		}
		else if((date1.isEmpty() && !date2.isEmpty())){
			JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>"+typeName+" start date must not be empty</font color = #ffffff></html>", "Detected an error in date fields", JOptionPane.ERROR_MESSAGE);
			return approved = false;
		}
		else if((date1.isEmpty() && date2.isEmpty())){
			JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>"+typeName+" start and end dates must not be empty</font color = #ffffff></html>", "Detected an error in date fields", JOptionPane.ERROR_MESSAGE);
			return approved = false;
		}
		
		else if ((!date1.isEmpty() && !date2.isEmpty()) ){
			try {
				Date datex = sdf.parse(date1);
				Date datey = sdf.parse(date2);
				if (datex.compareTo(datey) > 0) {
					//System.out.println("Date1 is after Date2"); FALSE
					JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>"+typeName+" start date must be before expiry date</font color = #ffffff></html>", "Detected an error in date fields", JOptionPane.ERROR_MESSAGE);
					approved = false;
				} else if (datex.compareTo(datey) < 0) {
					//System.out.println("Date1 is before Date2");TRUE
					approved = true;
				} else if (datex.compareTo(datey) == 0) {
					//System.out.println("Date1 is equal to Date2"); FALSE
					JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>"+typeName +" start date cannot be equal to expiry date</font color = #ffffff></html>", "Detected an error in date fields", JOptionPane.ERROR_MESSAGE);
					approved = false;
				}
				
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return approved;
		
		
	}
	
	//putting constraints before writing to database
	public static boolean inputCheck(String what/*Ex. Passport No.*/ ,String input) {
		boolean t = false;
		input = input.trim();
		if(!input.isEmpty()) {
			if(!(input.length() >25)) {
				if(validatePassport(what ,input)) {
					t = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>The "+what+": "+input+" is not a valid "+what+"</font color = #ffffff></html>", "Detected an invalid Passport No. field", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Please limit your input to 25 characters only.</font color = #ffffff></html>", "Detected an empty Passport No. field", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>The "+what+" field must not be empty. Please specify one.</font color = #ffffff></html>", "Detected an empty Passport No. field", JOptionPane.ERROR_MESSAGE);
		}
		
		
		return t;
	}
	
	//for validating passport numbers
	public static boolean validatePassport(String what, String input) {
		boolean t = false;
		String alphanum = "^[\\p{L}0-9]*$";
		String tin = "^[-\\p{L}0-9]*$";
		
		if(what.equals("Passport No.") && (input.matches(alphanum) && input.length() == 9)) {
			t = true;
		}else if(what.equals("TIN ID") && (input.matches(tin))){
			t = true;
		}
		
		return t;
		
	}
	
	//writing dates to database
	
	public static Transaction writeDates(Transaction trans, String[] input) {

		if(input[0].equals(""))
			trans.setVisaEndDate(null);
		else
			trans.setVisaEndDate(java.sql.Date.valueOf(objectFilter.addDay(input[0])));
		
		if(input[1].equals(""))
			trans.setVisaStartDate(null);
		else
			trans.setVisaStartDate(java.sql.Date.valueOf(objectFilter.addDay(input[1])));
		
		if(input[2].equals(""))
			trans.setPermitStartDate(null);
		else
			trans.setPermitStartDate(java.sql.Date.valueOf(objectFilter.addDay(input[2])));
		
		if(input[3].equals(""))
			trans.setPermitEndDate(null);
		else
			trans.setPermitEndDate(java.sql.Date.valueOf(objectFilter.addDay(input[3])));
		
		if(input[4].equals(""))
			trans.setAepStartDate(null);
		else
			trans.setAepStartDate(java.sql.Date.valueOf(objectFilter.addDay(input[4])));
		
		if(input[5].equals(""))
			trans.setAepEndDate(null);
		else
			trans.setAepEndDate(java.sql.Date.valueOf(objectFilter.addDay(input[5])));
		return trans;
	}
	
	
}
