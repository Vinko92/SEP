package com.merchant.rest.dao;

import java.util.List;

import com.merchant.rest.model.AdditionalInsurance;

public interface AdditionalInsuranceDAO {

	public void addAdditionalInsurance(AdditionalInsurance ai);
	public List<AdditionalInsurance> getAllAdditionalInsurance();
}
