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
@Table(name = "VEHICLE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "yearOfProduction")
	private int yearOfProduction;
	
	@Column(name = "registrationNumber")
	private String registrationNumber;
	
	@Column(name = "numberOfChassis")
	private String numberOfChassis;
	
	@Column(name = "vehicleInsurancePrice")
	private BigDecimal vehicleInsurancePrice;
	
	@Column(name = "ownerJmbg")
	private String ownerJmbg;
	
	public Vehicle(){}

	public Vehicle(int id, String type, int yearOfProduction,
			String registrationNumber, String numberOfChassis,
			BigDecimal vehicleInsurancePrice, String ownerJmbg) {
		super();
		this.id = id;
		this.type = type;
		this.yearOfProduction = yearOfProduction;
		this.registrationNumber = registrationNumber;
		this.numberOfChassis = numberOfChassis;
		this.vehicleInsurancePrice = vehicleInsurancePrice;
		this.ownerJmbg = ownerJmbg;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getNumberOfChassis() {
		return numberOfChassis;
	}

	public void setNumberOfChassis(String numberOfChassis) {
		this.numberOfChassis = numberOfChassis;
	}

	public BigDecimal getVehicleInsurancePrice() {
		return vehicleInsurancePrice;
	}

	public void setVehicleInsurancePrice(BigDecimal vehicleInsurancePrice) {
		this.vehicleInsurancePrice = vehicleInsurancePrice;
	}

	public String getOwnerJmbg() {
		return ownerJmbg;
	}

	public void setOwnerJmbg(String ownerJmbg) {
		this.ownerJmbg = ownerJmbg;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", type=" + type + ", yearOfProduction="
				+ yearOfProduction + ", registrationNumber="
				+ registrationNumber + ", numberOfChassis=" + numberOfChassis
				+ ", vehicleInsurancePrice=" + vehicleInsurancePrice
				+ ", ownerJmbg=" + ownerJmbg + "]";
	}
	
	
}
