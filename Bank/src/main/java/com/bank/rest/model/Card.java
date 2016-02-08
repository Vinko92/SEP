package com.bank.rest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "number")
	private String number;
	
	@Column(name ="amount")
	private float amount;
	
	@Column(name ="cardHolder")
	private String cardHolder;
	
	
	@Column(name ="securityCode")
	private String securityCode;
	
	@Column(name = "expirationDate")
	private Date expirationDate;
	
	@Column(name = "reservedAmount")
	private float resrevedAmount;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getResrevedAmount() {
		return resrevedAmount;
	}

	public void setResrevedAmount(float resrevedAmount) {
		this.resrevedAmount = resrevedAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
	
	
}
