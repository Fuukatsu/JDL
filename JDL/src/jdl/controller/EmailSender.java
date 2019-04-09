package jdl.controller;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jdl.model.Email;

public class EmailSender{
	public static void EmailSendFunction(String date, String recipient, String message, String subject) 
	{
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		  // Get a Properties object
		     Properties props = System.getProperties();
		     props.setProperty("mail.smtp.host", "smtp.gmail.com");
		     props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		     props.setProperty("mail.smtp.socketFactory.fallback", "false");
		     props.setProperty("mail.smtp.port", "465");
		     props.setProperty("mail.smtp.socketFactory.port", "465");
		     props.put("mail.smtp.auth", "true");
		     props.put("mail.debug", "true");
		     props.put("mail.store.protocol", "pop3");
		     props.put("mail.transport.protocol", "smtp");
		     Email em = new Email();
		     em.setEmailCredentials();
		     final String username = em.getEmail();
		     final String password = em.getPassword();
		     //final String username = "testingmoto.jdl@gmail.com";//
		     //final String password = "clockwise2";
		     try{
		     Session session = Session.getDefaultInstance(props, 
		                          new Authenticator(){
		                             protected PasswordAuthentication getPasswordAuthentication() {
		                                return new PasswordAuthentication(username, password);
		                             }});
		
		   // -- Create a new message --
		     Message msg = new MimeMessage(session);
		
		  // -- Set the FROM and TO fields --
		     msg.setFrom(new InternetAddress("xxxx@gmail.com"));
		     msg.setRecipients(Message.RecipientType.TO, 
		                      InternetAddress.parse(recipient, false));
		     msg.setSubject(subject);
		     msg.setText(message);
		     msg.setSentDate(new Date());
		     Transport.send(msg);
		     System.out.println("Message sent.");
	     }catch (MessagingException e){ e.printStackTrace();}
	}
}