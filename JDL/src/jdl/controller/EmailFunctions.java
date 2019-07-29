package jdl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jdl.dao.Queries;
import jdl.model.Client;
import jdl.model.Email;
import jdl.model.Transaction;
import jdl.model.User;

public class EmailFunctions 
{
	private static Email emCredential;
	public static void initiateEmail()
	{
		
		emCredential = new Email();
		emCredential.setEmailCredentials();
		
	}
	public static void composeEmail(Client c, String ExpiryDate, String Document)
	{

		String newMessage = "Good Day, "+c.getClient_lastname()+"\n\n"+
							"Your "+Document+" is about to expire on "+ExpiryDate+".\n"
							+"This is an automated message. Do not reply.";
		String subject = "JDL Business and Immigration Consultancy: Expiring "+Document;
		EmailSender.EmailSendFunction(ExpiryDate, c.getClient_email(), newMessage, subject);
	}
	public static void composeEmailEmployee(Client c, String ExpiryDate, String CurrentDate, String Document)
	{
		String subject = "Logs: Sent email to "+c.getClient_email();
		String newMessage = "Sent email to "+c.getClient_lastname()+", "+c.getClient_firstname()+" about expiring "+Document+" on "+ExpiryDate+", \n"+
							"Email sent on "+CurrentDate;
		EmailSender.EmailSendFunction(null, emCredential.getEmail(), newMessage, subject);
	}
	public static void checkExpiration()
	{
		try {
		String today = objectFilter.getDateToday();
		Date d = new SimpleDateFormat("yyyy-MM-dd").parse(today);
		String newweek = objectFilter.addWeek(objectFilter.getDateToday());
		Date dd = new SimpleDateFormat("yyyy-MM-dd").parse(newweek);
		System.out.print(today);
		System.out.println(newweek);
		ArrayList<Transaction> tlist = Queries.getTransactionsBetweenDate(new java.sql.Date(d.getTime()), new java.sql.Date(dd.getTime()));
		for(Transaction t:tlist)
		{
			int client_id = t.getClient_id();
			Client c = Queries.getClient(client_id);
			if(t.getVisaEndDate() != null)
			{
					System.out.print("Visa: "+t.getVisaEndDate());
					composeEmail(c, newweek, "Visa");
					composeEmailEmployee(c, newweek, objectFilter.getDateToday(), "Visa");
			}
			if(t.getPermitEndDate() != null)
			{
					System.out.print("Permit: "+t.getVisaEndDate());
					composeEmail(c, newweek, "Permit");
					composeEmailEmployee(c, newweek, objectFilter.getDateToday(), "Permit");
				}
		
			if(t.getAepEndDate() != null)
			{	
					System.out.print("AEP: "+t.getVisaEndDate());
					composeEmail(c, newweek, "Aep");
					composeEmailEmployee(c, newweek, objectFilter.getDateToday(), "Aep");
			}
		}
		}catch (ParseException e1) {
			e1.printStackTrace();
		}
	}
	public static void checkTodayNotification()
	{
		try {
		String date = objectFilter.addDay(objectFilter.getDateToday());
		Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		boolean dateCheck = Queries.checkNotification(new java.sql.Date(d.getTime()));
		//System.out.println(dateCheck);
		if(dateCheck)
		{
			checkExpiration();
			Queries.insertNotification(new java.sql.Date(d.getTime()));
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
