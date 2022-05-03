package com.test.app.logic;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	public void sendMail(String email,String code) {

		String host = "smtp.naver.com"; // 사용할 사이트
		final String user = "sungkwang0908@naver.com"; // ID
		final String password = "비밀번호"; // PW

		String to = email; // 보낼 이메일 주소

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Subject
			message.setSubject("LOL 커뮤니티 인증코드 발송");

			// Text
			message.setText("안녕하세요, LOL 커뮤니티입니다.\r\n 인증코드는 ["+code+"] 입니다.");

			// send the message
			Transport.send(message);
			System.out.println("이메일 전송 성공!");
			System.out.println(code);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
}