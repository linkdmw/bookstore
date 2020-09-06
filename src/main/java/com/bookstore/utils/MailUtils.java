package com.bookstore.utils;

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
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * 发送邮件的工具类
 */
public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		//设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.qq.com");
		//邮件服务器验证
		props.setProperty("mail.smtp.auth", "true");

		//定义验证信息
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("cxwl.h@qq.com", "pdpgtlfxangjdijd");
			}
		};

		//创建和邮件服务器的会话
		Session session = Session.getInstance(props, auth);

		Message message = new MimeMessage(session);
		//设置邮件发送方
		message.setFrom(new InternetAddress("cxwl.h@qq.com"));
		//设置邮件接收方
		message.setRecipient(RecipientType.TO, new InternetAddress(email));
		//设置邮件主题
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");


		Transport.send(message);
	}
}
