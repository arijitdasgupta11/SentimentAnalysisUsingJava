package com.analysis.brand;


import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailNew {

	public static void sendMail(String msg, String sub, String to, String from) {
		String host = "smtp.gmail.com";
		Properties prop = System.getProperties();
		System.out.println("PROPERTIES" + prop);
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.ssl.enable", true);
		prop.put("mail.smtp.auth", true);

		Session session = Session.getInstance(prop, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("jobrecruitersuniverse@gmail.com", "zmstcbdtjnudxlkc");
			}

		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			Transport.send(message);
			System.out.println("Sent Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
