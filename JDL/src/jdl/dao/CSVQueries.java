package jdl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdl.model.Transaction;

public class CSVQueries 
{
	static databaseProperties dP = new databaseProperties();
	public static ArrayList<String> getColumns()
	{
		ArrayList<String> columns = null;
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{	
			PreparedStatement ps = con.prepareStatement("SHOW COLUMNS FROM transactions");
			ResultSet rs = ps.executeQuery();
			columns = new ArrayList<String>();
			while (rs.next())
			{
				columns.add(rs.getString("Field"));
			}
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return columns;		
	}
}