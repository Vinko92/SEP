package com.merchant.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "CUSTOMERINSURANCE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerInsurance {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "insuranceId")
	private int insuranceId;
	
	@Column(name = "customerId")
	private int customerId;
	
	public CustomerInsurance(){}

	public CustomerInsurance(int insuranceId, int customerId) {
		super();
		this.insuranceId = insuranceId;
		this.customerId = customerId;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "CustomerInsurance [insuranceId=" + insuranceId
				+ ", customerId=" + customerId + "]";
	}
	
	
	
}
