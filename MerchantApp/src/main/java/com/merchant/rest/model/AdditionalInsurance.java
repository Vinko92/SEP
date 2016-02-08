package com.merchant.rest.model;
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
	
	@Column(name = "priceOfAdditionalInsurance")
	private double priceOfAdditionalInsurance;
	
	@Column(name = "durationOfAdditionalInsurance")
	private int durationOfAdditionalInsurance;
	
	@Column(name = "nameOfAdditionalInsurance")
	private String nameOfAdditionalInsurance;
	
	@Column(name = "ownerId")
	private int owenerId;
	
	public AdditionalInsurance(){}

	public AdditionalInsurance(int id, int typeId,
			double priceOfAdditionalInsurance,
			int durationOfAdditionalInsurance,
			String nameOfAdditionalInsurance, int owenerId) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.priceOfAdditionalInsurance = priceOfAdditionalInsurance;
		this.durationOfAdditionalInsurance = durationOfAdditionalInsurance;
		this.nameOfAdditionalInsurance = nameOfAdditionalInsurance;
		this.owenerId = owenerId;
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

	public double getPriceOfAdditionalInsurance() {
		return priceOfAdditionalInsurance;
	}

	public void setPriceOfAdditionalInsurance(double priceOfAdditionalInsurance) {
		this.priceOfAdditionalInsurance = priceOfAdditionalInsurance;
	}

	public int getDurationOfAdditionalInsurance() {
		return durationOfAdditionalInsurance;
	}

	public void setDurationOfAdditionalInsurance(int durationOfAdditionalInsurance) {
		this.durationOfAdditionalInsurance = durationOfAdditionalInsurance;
	}

	public String getNameOfAdditionalInsurance() {
		return nameOfAdditionalInsurance;
	}

	public void setNameOfAdditionalInsurance(String nameOfAdditionalInsurance) {
		this.nameOfAdditionalInsurance = nameOfAdditionalInsurance;
	}

	public int getOwenerId() {
		return owenerId;
	}

	public void setOwenerId(int owenerId) {
		this.owenerId = owenerId;
	}

	@Override
	public String toString() {
		return "AdditionalInsurance [id=" + id + ", typeId=" + typeId
				+ ", priceOfAdditionalInsurance=" + priceOfAdditionalInsurance
				+ ", durationOfAdditionalInsurance="
				+ durationOfAdditionalInsurance
				+ ", nameOfAdditionalInsurance=" + nameOfAdditionalInsurance
				+ ", owenerId=" + owenerId + "]";
	}

	
	
	
	
}
