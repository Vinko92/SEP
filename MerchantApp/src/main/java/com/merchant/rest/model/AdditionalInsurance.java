package com.merchant.rest.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "ADDITIONALINSURANCE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AdditionalInsurance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "typeId")
	private int typeId;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "duration")
	private int duration;
	
	@Column(name = "typeName")
	private List<String> typeName;
	
	public AdditionalInsurance(){}

	public AdditionalInsurance(int id, int typeId, BigDecimal price, int duration,
			List<String> typeName) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.price = price;
		this.duration = duration;
		this.typeName = typeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<String> getTypeName() {
		return typeName;
	}

	public void setTypeName(List<String> typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "AdditionalInsurance [id=" + id + ", typeId=" + typeId
				+ ", price=" + price + ", duration=" + duration + ", typeName="
				+ typeName + "]";
	}
	
	
}
