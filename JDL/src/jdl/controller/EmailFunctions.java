package jdl.controller;

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
							"Your "+Document+" is expiring on "+ExpiryDate+"";
		EmailSender.EmailSendFunction(ExpiryDate, c.getClient_email(), newMessage);
	}
	public static void checkExpiration()
	{
		String newweek = objectFilter.addWeek(objectFilter.getDateToday());
		ArrayList<Transaction> tlist = Queries.getTransactions();
		for(Transaction t:tlist)
		{
			int client_id = t.getClient_id();
			Client c = Queries.getClient(client_id);
			if(t.getVisaEndDate() != null)
			{
				if(newweek.equals(t.getVisaEndDate().toString()))
					composeEmail(c, newweek, "Visa");
			}
			if(t.getPermitEndDate() != null)
			{
				if(newweek.equals(t.getPermitEndDate()))
					composeEmail(c, newweek, "Permit");
			}
			if(t.getAepEndDate() != null)
			{
				if(newweek.equals(t.getAepEndDate()))
					composeEmail(c, newweek, "AEP");
			}
		}
	}
}
