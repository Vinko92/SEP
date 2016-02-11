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
@Table(name = "SPORT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name; 
	
	@Column(name = "extreme")
	private boolean extreme;
	
	public Sport(){}

	public Sport(int id, String name, boolean extreme) {
		super();
		this.id = id;
		this.name = name;
		this.extreme = extreme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isExtreme() {
		return extreme;
	}

	public void setExtreme(boolean extreme) {
		this.extreme = extreme;
	}

	@Override
	public String toString() {
		return "Sport [id=" + id + ", name=" + name + ", extreme=" + extreme
				+ "]";
	}
	
	public double price(boolean extreme){
		if(extreme == true){
			return 1000.0;
		}else{
			return 500.0;
		}
	}
}
