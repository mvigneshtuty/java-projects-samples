package org.nfjs.spring.aop.service;

import java.net.ConnectException;

import org.nfjs.spring.aop.core.Service;

public class MessageService implements Service{
	private String messageId;
	private String sender;
	private String receiver;
	private boolean resend;

	MessageService(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageId() {
		return messageId;
	}

	public String getSender() {
		return sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public boolean isResend() {
		return resend;
	}

	public void send(String message, String receiver) throws ConnectException {
		
		System.out.println("Sending Message ::[" + message + "] to receiver :: " + receiver);
		
		if(message.contains("spam")){
				throw new ConnectException();
		}
	}

}
