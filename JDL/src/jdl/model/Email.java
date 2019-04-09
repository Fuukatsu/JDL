package jdl.model;

import java.io.BufferedReader;
import java.io.FileReader;

public class Email 
{
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	public void setEmailCredentials()
	{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("EmailCredential.txt"));

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
		    setEmail(everything[0].split("=")[1].trim());
		    setPassword(everything[1].split("=")[1].trim());
		} finally {
		    br.close();
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
