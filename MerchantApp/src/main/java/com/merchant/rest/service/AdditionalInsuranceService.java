package com.merchant.rest.service;

import java.util.List;

import com.merchant.rest.model.AdditionalInsurance;

public interface AdditionalInsuranceService {

	public void addAdditionalInsurance(AdditionalInsurance ai);
	public List<AdditionalInsurance> getAllAdditionalInsurance();
}
