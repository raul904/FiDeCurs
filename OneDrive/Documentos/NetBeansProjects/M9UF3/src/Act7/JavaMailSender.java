/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Act7;

/**
 *
 * @author Raul
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 *
 * @author Josep Cañellas <jcanell4@ioc.cat>
 */
public class JavaMailSender {
	private ArrayList<String> sendTo =new ArrayList<>();
	private String sender;
	private String subject;
	private String body;
	Properties properties=new Properties();
	Authenticator authenticator=null;
	
	
	public JavaMailSender(String host, int port) {
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", String.valueOf(port));
	}
	
	public void sendUsingAuthentication(String user, String pass){
		String host = properties.getProperty("mail.smtp.host");
		String port = properties.getProperty("mail.smtp.port");
		properties=new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		authenticator = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("username","password");
			}
		};
		try {
			send();
		} catch (MessagingException e){
			System.out.println("Error...");
		}
	}
	
	public void sendUsingSSLAuthentication(final String user, final String pass) throws MessagingException {
		String host = properties.getProperty("mail.smtp.host");
		String port = properties.getProperty("mail.smtp.port");
		properties=new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.socketFactory.port", port);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		authenticator = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user,pass);
			}
		};
		send();
	}
	
	public void sendUsingTLSAuthentication(final String user, final String pass) throws MessagingException {
		String host = properties.getProperty("mail.smtp.host");
		String port = properties.getProperty("mail.smtp.port");
		properties=new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		authenticator = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user,pass);
			}
		};
		send();
	}
	
	public void addRecipient(String[] recipients) {
		for(String r: recipients){
			sendTo.add(r);
		}
	}
	
	public void addRecipient(String recipient) {
		sendTo.add(recipient);
	}
	
	public void setSender(String psender){
		sender = psender;
	}
	
	public void setMailText(String pbody){
		body = pbody;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void send() throws MessagingException {
		Session session;
		if(authenticator==null) {
			session= Session.getInstance(properties);
		} else {
			session=Session.getInstance(properties, authenticator);
		}
		
		Message message = new MimeMessage(session);
		InternetAddress[] addresses = new InternetAddress[sendTo.size()];
		for(int i=0; i<sendTo.size(); i++){
			addresses[i]=new InternetAddress(sendTo.get(i));
		}
		message.addRecipients(Message.RecipientType.TO, addresses);
		message.setFrom(new InternetAddress(sender));
		message.setSentDate(new Date());
		message.setSubject(subject);
		message.setText(body);
		
		Transport.send(message);
	}
}
