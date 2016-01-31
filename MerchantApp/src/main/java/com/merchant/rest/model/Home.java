package com.merchant.rest.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "HOME")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Home{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "owner")
	private String owner;
	
	@Column(name = "ownerJmbg")
	private String ownerJmbg;
	
	@Column(name = "surface")
	private double surface;
	
	@Column(name = "age")
	private double age;
	
	@Column(name = "estimatedValue")
	private double estimatedValue;
	
	@Column(name = "durationOfInsurance")
	private int durationOfInsurance;

	@Column(name = "typeOfRisk")
	private String typeOfRisk;
	
	@Column(name = "price")
	private double price;
	
	public Home(){}
	
	public Home(int id, String address, String owner, String ownerJmbg,
			double surface, double age, double estimatedValue,int durationOfInsurance,String typeOfRisk,double price) {
		super();
		this.id = id;
		this.address = address;
		this.owner = owner;
		this.ownerJmbg = ownerJmbg;
		this.surface = surface;
		this.age = age;
		this.estimatedValue = estimatedValue;
		this.durationOfInsurance = durationOfInsurance;
		this.typeOfRisk = typeOfRisk;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTypeOfRisk() {
		return typeOfRisk;
	}

	public void setTypeOfRisk(String typeOfRisk) {
		this.typeOfRisk = typeOfRisk;
	}

	public int getDurationOfInsurance() {
		return durationOfInsurance;
	}

	public void setDurationOfInsurance(int durationOfInsurance) {
		this.durationOfInsurance = durationOfInsurance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public double getEstimatedValue() {
		return estimatedValue;
	}

	public void setEstimatedValue(double estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	public double homeInsurancePrice(int durationOfInsurance,double surface,double age,double estimetedValue,double riskPrice) {
		return durationOfInsurance*(surface + age/2 + estimetedValue/4 + riskPrice);
	}
}
