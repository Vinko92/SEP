package com.merchant.rest.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@Column(name = "durationOfInsurance")
	private int durationOfInsurance;
	
//	@OneToMany(targetEntity=Vehicle.class, mappedBy="packages", fetch=FetchType.EAGER)
//	private List<String> packages;
	
	@Column(name = "packages")
	private String packages;
	
	@Column(name = "carBrandName")
	private String carBrandName;
	
	@Column(name = "carBrandTypeName")
	private String carBrandTypeName;
	
	@Column(name = "yearOfProduction")
	private int yearOfProduction;
	
	@Column(name = "registrationNumber")
	private String registrationNumber;
	
	@Column(name = "chasissNumber")
	private String chasissNumber;
	
	@Column(name = "owner")
	private String owner;
	
	@Column(name = "ownerJmbg")
	private String ownerJmbg;
	
	@Column(name = "price")
	private double price;
	
	public Vehicle(){}

	public Vehicle(int id, int durationOfInsurance, String packages,
			String carBrandName, String carBrandTypeName, int yearOfProduction,
			String registrationNumber, String chasissNumber, String owner,
			String ownerJmbg,double price) {
		super();
		this.id = id;
		this.durationOfInsurance = durationOfInsurance;
		this.packages = packages;
		this.carBrandName = carBrandName;
		this.carBrandTypeName = carBrandTypeName;
		this.yearOfProduction = yearOfProduction;
		this.registrationNumber = registrationNumber;
		this.chasissNumber = chasissNumber;
		this.owner = owner;
		this.ownerJmbg = ownerJmbg;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public String getCarBrandTypeName() {
		return carBrandTypeName;
	}

	public void setCarBrandTypeName(String carBrandTypeName) {
		this.carBrandTypeName = carBrandTypeName;
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

	public String getChasissNumber() {
		return chasissNumber;
	}

	public void setChasissNumber(String chasissNumber) {
		this.chasissNumber = chasissNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnerJmbg() {
		return ownerJmbg;
	}

	public void setOwnerJmbg(String ownerJmbg) {
		this.ownerJmbg = ownerJmbg;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", durationOfInsurance="
				+ durationOfInsurance + ", packages=" + packages
				+ ", carBrandName=" + carBrandName + ", carBrandTypeName="
				+ carBrandTypeName + ", yearOfProduction=" + yearOfProduction
				+ ", registrationNumber=" + registrationNumber
				+ ", chasissNumber=" + chasissNumber + ", owner=" + owner
				+ ", ownerJmbg=" + ownerJmbg + ", price=" + price + "]";
	}
	
	public double getVehicleInsurancePriceTow(int duration,double packagePrice){
		return duration*(packagePrice/5);
	}
	public double getVehicleInsurancePriceRepair(int duration,double packagePrice){
		return duration*(packagePrice/5);
	}
	public double getVehicleInsurancePriceHotel(int duration,double packagePrice){
		return duration*packagePrice;
	}
	public double getVehicleInsurancePriceAlternativeTransport(int duration,double packagePrice){
		return duration*(packagePrice/5);
	}
}
