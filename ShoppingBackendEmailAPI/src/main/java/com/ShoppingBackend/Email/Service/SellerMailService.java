package com.ShoppingBackend.Email.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ShoppingBackend.Email.DTO.requestDTO.AddProductDTO;
import com.ShoppingBackend.Email.Utils.MailUtils;

import jakarta.mail.internet.MimeMessage;

@Service
public class SellerMailService {

	
	@Autowired
	MailUtils mailUtils;
	
	public void sendAddProductMail(AddProductDTO addProductDTO) throws Exception {
		
		String emailId= addProductDTO.getMailId();
		String subject= addProductDTO.getSubjectLine();
		MimeMessage message = mailUtils.getMimeMessage();
		MimeMessageHelper messageHelper= mailUtils.getMimeMessageHelper();
		JavaMailSender mailSender = mailUtils.getJavaMailSender();
		
		try {
			messageHelper.setTo(emailId);
			messageHelper.setSubject(subject);
			messageHelper.setText(addProductDTO.getMailMessage());
			mailSender.send(message);
		}catch(Exception e) {
			throw e;
		}
	}
}
