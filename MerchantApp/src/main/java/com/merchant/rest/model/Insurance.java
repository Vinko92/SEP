package com.merchant.rest.model;
import java.math.BigDecimal;

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
	
	@Column(name = "duration")
	private int duration;
	
	@Column(name = "numberOfPersons")
	private int numberOfPersons;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "additionalInsurance")
	private String additionalInsurance;

	@Column(name = "region")
	private String region;
	
	@Column(name = "sport")
	private String sport;
	
	public Insurance(){}
	
	public Insurance(int id, int duration, int numberOfPersons, BigDecimal price,
			String additionalInsurance,String region,String sport) {
		super();
		this.id = id;
		this.duration = duration;
		this.numberOfPersons = numberOfPersons;
		this.price = price;
		this.additionalInsurance = additionalInsurance;
		this.region = region;
		this.sport = sport;
	}



	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getAdditionalInsurance() {
		return additionalInsurance;
	}

	public void setAdditionalInsurance(String additionalInsurance) {
		this.additionalInsurance = additionalInsurance;
	}

	@Override
	public String toString() {
		return "Insurance [id=" + id + ", duration=" + duration
				+ ", numberOfPersons=" + numberOfPersons + ", price=" + price
				+ ", additionalInsurance=" + additionalInsurance + "]";
	}

	public double price(int duration,double regionPrice,double sportPrice,int numberOfPersons){
		return duration*(regionPrice + sportPrice + numberOfPersons);
	}
}
