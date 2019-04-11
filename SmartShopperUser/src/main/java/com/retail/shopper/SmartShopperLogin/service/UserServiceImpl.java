package com.retail.shopper.SmartShopperLogin.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.shopper.SmartShopperLogin.constants.UserConstants;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public void sendEmail(String subject, String message) {
		Properties properties = System.getProperties();

		properties.setProperty(UserConstants.MAIL_HOST_STRING.value(), UserConstants.HOST.value());
		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage mimeMessage = new MimeMessage(session);

			mimeMessage.setFrom(new InternetAddress(UserConstants.FROM.value()));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(UserConstants.TO.value()));
			mimeMessage.setSubject(subject);

			mimeMessage.setText(message);

			Transport.send(mimeMessage);
			logger.info("Mail sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			logger.info("Error occured while sending the mail to the recipients");
		}
	}
}
