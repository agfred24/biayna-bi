package com.biayna.bi.services;

import java.net.URISyntaxException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.biayna.bi.common.utility.ReadConfiguration;
import com.biayna.bi.common.utility.ReadHTMLTemplates;
import com.biayna.bi.common.utility.SystemTime;


public class EmailSenderImpl implements EmailSender{

	private static Logger logger = LogManager.getLogger();

	public String processedFileName;
	public String subject;
	
	public String getProcessedFileName() {
		return processedFileName;
	}
	@Override
	public void setProcessedFileName(String processedFileName) {
		this.processedFileName = processedFileName;
	}
	
	public String getSubject() {
		return subject;
	}
	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getEmailMessage() throws URISyntaxException {
		ReadHTMLTemplates htmlTemp = new ReadHTMLTemplates();
		return htmlTemp.readFileUploadEmailTemplate(this.getProcessedFileName());
	}

	@Override
	public void sendMail() {
		
		final String host = "smtp.gmail.com";
		final String userName = "fred.agourian@gmail.com";
        final String password = "Disney2018";	
	
		
		ReadConfiguration objPropertiesFile = new ReadConfiguration();
		String recipients = objPropertiesFile.readKey("mail.properties", "mail.recipients");
		String ccs = objPropertiesFile.readKey("mail.properties", "mail.ccs");
		
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.host", host);
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.port", "465");		
		mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailProperties.put("mail.smtp.socketFactory.port", "465");
		
		Session session = Session.getDefaultInstance(mailProperties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userName, password);
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccs));
			message.setSubject(getSubject());
			message.setContent(getEmailMessage(), "text/html; charset=utf-8");

			Transport.send(message);
			logger.info(
					"Email has been sent to: " + recipients + " and CC: " + ccs + ", at " + SystemTime.getCurrentSystemTime());

		} catch (MessagingException e) {
			logger.error(
					"Email: Failed to create this Message " + recipients + " and CC: " + ccs + ", at " + SystemTime.getCurrentSystemTime());
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			logger.error("Email: String could not be parsed as URI.");
			e.printStackTrace();
		}
	}
}
