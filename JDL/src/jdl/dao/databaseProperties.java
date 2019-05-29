package jdl.dao;

import java.io.BufferedReader;
import java.io.FileReader;

public class databaseProperties 
{
	public String url;
	public String username;
	public String password;
	public databaseProperties()
	{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("dbproperties.txt"));

		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) 
		    {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String[] everything = sb.toString().split("\n");
		    setUrl(everything[0].split("=")[1].trim());
		    setUsername(everything[1].split("=")[1].trim());
		    setPassword(everything[2].split("=")[1].trim());
		    System.out.println(this.url+" "+this.username+" "+this.password);
		} finally {
		    br.close();
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setUrl(String s)
	{
		this.url = "jdbc:mysql://"+s+"/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	}
	public void setUsername(String s)
	{
		this.username = s;
	}
	public void setPassword(String s)
	{
		this.password = s;
	}
}
