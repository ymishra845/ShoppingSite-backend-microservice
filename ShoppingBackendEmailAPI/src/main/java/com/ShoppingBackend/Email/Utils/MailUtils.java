package com.ShoppingBackend.Email.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class MailUtils {

	// Java Mail sender instance -> This instance we required to send mail
	//MimeMessage instance -> In the object of MimeMessage our mail will get created
	//MimeMessageHelper ->  With the help of mimeMessahe Helper we are going to create MimeMessage i.e Mail
	JavaMailSender javaMailSender;
	
	
	MimeMessageHelper mimeMessageHelper;
	MimeMessage mimeMessage;
	
	public MimeMessageHelper getMimeMessageHelper() {
		return mimeMessageHelper;
	}
	public MimeMessage getMimeMessage() {
		return mimeMessage;
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public MailUtils() {
		
		javaMailSender = new JavaMailSenderImpl();
		mimeMessage= javaMailSender.createMimeMessage();
		mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		
	}
	
}
