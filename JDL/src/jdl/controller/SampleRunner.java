package jdl.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jdl.dao.Queries;
import jdl.model.Email;
import jdl.model.Transaction;

public class SampleRunner {

	public static void main(String[] args) throws ParseException 
	{
		String date = "2019-04-10";
		Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		date = objectFilter.addYear(date);
		Date dd = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		ArrayList<Transaction> tlist = Queries.getTransactionsBetweenDate(new java.sql.Date(d.getTime()), new java.sql.Date(dd.getTime()));
		System.out.println(tlist.size());
		for(Transaction t:tlist)
		{
			System.out.println(t.getTransID());
		}
	}

}
