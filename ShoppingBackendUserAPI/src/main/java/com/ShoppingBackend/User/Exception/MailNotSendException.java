package com.ShoppingBackend.User.Exception;


public class MailNotSendException extends RuntimeException {

	public MailNotSendException(String format) {
		super(format);
	}
	
}
