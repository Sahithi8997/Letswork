package com.FrameWork.Hybrid.util;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.FrameWork.Hybrid.reusablecomponent.BaseClass;

public class SendEmail {

	public static void sendEmail(String reportPath) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("letsworkautomation.2018@gmail.com", "2018worklets");
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("letsworkautomation.2018@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("letsworkautomation.2018@gmail.com"));
			message.setSubject("Testing Subject");
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("This is message body");
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			String filename = reportPath;
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart2);
			multipart.addBodyPart(messageBodyPart1);
			message.setContent(multipart);
			Transport.send(message);
			BaseClass.APP_LOGS.info("Email Sent");
		} catch (MessagingException e) {
			BaseClass.APP_LOGS.error("Email Sending Failed");
			throw new RuntimeException(e);
		}
	}
}
