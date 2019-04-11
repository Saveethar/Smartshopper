package com.retail.shopper.SmartShopperProduct.util;

import org.springframework.stereotype.Service;




import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
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
import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.shopper.SmartShopperProduct.constants.ProductConstants;

@Service
public class Utils {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	public void sendEmail(String subject, String message) {
		Properties properties = System.getProperties();

		properties.setProperty(ProductConstants.MAIL_HOST_STRING.value(), ProductConstants.HOST.value());
		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage mimeMessage = new MimeMessage(session);

			mimeMessage.setFrom(new InternetAddress(ProductConstants.FROM.value()));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(ProductConstants.TO.value()));
			mimeMessage.setSubject(subject);

			mimeMessage.setText(message);

			Transport.send(mimeMessage);
			logger.info("Mail sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			logger.info("Error occured while sending the mail to the recipients");
		}
	}

	/**
	 * This method is used to get StackTrace as a single line string.
	 * 
	 * @param throwable
	 * @return String StackTrace in single-line format
	 */
	public String getPrintableStackTrace(Throwable throwable) {
		if (throwable != null) {
			StringBuilder sb = new StringBuilder();

			StackTraceElement[] ste = throwable.getStackTrace();
			for (int i = 0; i < ste.length; i++) {
				sb.append(ste[i].getClassName());
				sb.append(":");
				sb.append(ste[i].getMethodName());
				sb.append(":");
				sb.append(ste[i].getLineNumber());
				sb.append(" " + "-" + " ");
			}
			// This is to remove the last hyphen
			return sb.substring(0, sb.length() - 3);
		} else {
			return "";
		}
	}
	
	/**
	 * This method is used to authenticate to the API Gateway and generate a token
	 * to perform any operations
	 * 
	 * @param apikey
	 * @param secret
	 * @param username
	 * @param password
	 * @param areaUuid
	 * @return
	 * @throws Exception
	 */
	public String authenticate(String apikey, String secret, String username, String password, String areaUuid)
			throws Exception {
		URL urlObj = new URL("Token end point URL");
		HttpsURLConnection con = (HttpsURLConnection) urlObj.openConnection();

		String userpass = apikey + ":" + secret;
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		con.setRequestProperty("Authorization", basicAuth);
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		String body = "grant_type=password&username=" + username + "&password=" + password + "&scope=" + areaUuid;
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(body);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("responseCode=" + responseCode);
		InputStream is;
		if (responseCode >= 400) {
			is = con.getErrorStream();
		} else {
			is = con.getInputStream();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		if (response != null) {
			response.toString().substring(0, response.length() - 1);
			String[] cells = response.substring(0, response.length() - 1).split(",");
			for (int i = 0; i < cells.length; i++) {
				if (cells[i].contains("access_token")) {
					return cells[i].replaceAll("\"", "").split(":")[1];
				}
			}
		}
		return response.toString();
	}
	
	
	/**
	 * This method is used to call the API URL's
	 * 
	 * @param token
	 * @param method
	 * @param resource
	 * @param parameters
	 * @param body
	 * @return
	 */
	public String callApi(String token, String method, String resource, String parameters, String body) {
		try {
			URL urlObj = new URL("API Provider Host URL" + resource
					+ (parameters != null && parameters.length() > 0 ? "?" + parameters : ""));

			HttpsURLConnection con = (HttpsURLConnection) urlObj.openConnection();

			con.setRequestMethod(method);

			//con.setRequestProperty("Authorization", "Bearer " + token);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			if (method.equals("POST") || method.equals("PUT")) {
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(body);
				wr.flush();
				wr.close();
			}

			int responseCode = con.getResponseCode();
			InputStream is;
			if (responseCode >= 400) {
				is = con.getErrorStream();
			} else {
				is = con.getInputStream();
			}
			if (is != null) {
				BufferedReader in = new BufferedReader(new InputStreamReader(is));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				return response.toString();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
