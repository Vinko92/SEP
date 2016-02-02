package com.bank.rest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "payment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {

	@Column(name ="amount")
	private float amount;
	
	@Column(name ="pan", unique = true)
	private String PAN;
	
	@Column(name ="cardHolder")
	private String cardHolder;
	
	@Column(name ="validTo")
	private Date validTo;
	
	@Column(name ="securityCode")
	private String securityCode;
	
	@Column(name = "timestamp")
	private Date timestamp;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
