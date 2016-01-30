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
@Table(name = "ROADASSISTENCE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RoadAssistence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "priceOfRoadAssistence")
	private BigDecimal priceOfRoadAssistence;
	
	public RoadAssistence(){}

	public RoadAssistence(int id, String name, BigDecimal priceOfRoadAssistence) {
		super();
		this.id = id;
		this.name = name;
		this.priceOfRoadAssistence = priceOfRoadAssistence;
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

	public BigDecimal getPriceOfRoadAssistence() {
		return priceOfRoadAssistence;
	}

	public void setPriceOfRoadAssistence(BigDecimal priceOfRoadAssistence) {
		this.priceOfRoadAssistence = priceOfRoadAssistence;
	}

	@Override
	public String toString() {
		return "RoadAssistence [id=" + id + ", name=" + name
				+ ", priceOfRoadAssistence=" + priceOfRoadAssistence + "]";
	}
	
	
}
