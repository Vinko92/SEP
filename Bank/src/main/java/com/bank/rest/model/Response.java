package com.bank.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Response {

	private List<String> messages;
	
	private boolean success;
	
	private long acquirerOrderId;
	
	private Date acquirerTimestamp;
	
	private long issuerOrderId;
	
	private Date issuerTimestamp;
	
	public Response(){
		messages = new ArrayList<String>();
	}

	public List<String> getMessage() {
		return messages;
	}

	public void setMessage(List<String> messages) {
		this.messages = messages;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void addMessage(String message)
	{
		messages.add(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public long getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public long getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(long issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public Date getIssuerTimestamp() {
		return issuerTimestamp;
	}

	public void setIssuerTimestamp(Date issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}
	
	
}
