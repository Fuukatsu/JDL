package jdl.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import jdl.dao.Queries;
import jdl.model.Client;
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
		return email.contains("@");
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
	
}
