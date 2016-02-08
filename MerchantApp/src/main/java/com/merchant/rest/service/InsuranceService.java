package com.merchant.rest.service;



import com.merchant.rest.model.Insurance;

public interface InsuranceService {
	
	public void addInsurance(Insurance insurance);
	public Insurance getInsuranceByOwnerName(String ownerName);
	public void saveInsurance(Insurance i);
}
