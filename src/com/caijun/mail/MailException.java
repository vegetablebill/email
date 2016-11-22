package com.caijun.mail;

@SuppressWarnings("serial")
public class MailException extends RuntimeException {

	public MailException(String message) {
		super(message);
	}

	public MailException(String message, Throwable t) {
		super(message, t);
	}

	public MailException(Throwable t) {
		super(t);
	}
}
