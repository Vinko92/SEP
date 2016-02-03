package com.merchant.rest.model;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "INSURANCE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Insurance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "durationOfInsurance")
	private int durationOfInsurance;
	
	@Column(name = "numberOfPersons")
	private int numberOfPersons;
	
//	@OneToMany(targetEntity=Insurance.class, mappedBy="ageOfPersons", fetch=FetchType.EAGER)
//	private List<Integer> ageOfPersons;
	
	@Column(name = "ageOfPersons")
	private int ageOfPersons;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "sport")
	private String sport;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "additionalInsuranceId")
	private int additionalInsuranceId;
	
	public Insurance(){}

	public Insurance(int id,int durationOfInsurance, int numberOfPersons, int ageOfPersons,
			double price, String region, String sport, double amount,int additionalInsuranceId) {
		super();
		this.id = id;
		this.durationOfInsurance = durationOfInsurance;
		this.numberOfPersons = numberOfPersons;
		this.ageOfPersons = ageOfPersons;
		this.price = price;
		this.region = region;
		this.sport = sport;
		this.amount = amount;
		this.additionalInsuranceId = additionalInsuranceId;
	}

	public int getAdditionalInsuranceId() {
		return additionalInsuranceId;
	}

	public void setAdditionalInsuranceId(int additionalInsuranceId) {
		this.additionalInsuranceId = additionalInsuranceId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDurationOfInsurance() {
		return durationOfInsurance;
	}

	public void setDurationOfInsurance(int durationOfInsurance) {
		this.durationOfInsurance = durationOfInsurance;
	}

	public int getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	public int getAgeOfPersons() {
		return ageOfPersons;
	}

	public void setAgeOfPersons(int ageOfPersons) {
		this.ageOfPersons = ageOfPersons;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	
}
