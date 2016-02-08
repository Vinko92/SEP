package com.merchant.rest.dao;



import com.merchant.rest.model.Insurance;

public interface InsuranceDAO {

	public void addInsurance(Insurance insurance);
	public Insurance getInsuranceByOwnerName(String ownerName);
	public void saveInsurance(Insurance i);
}
