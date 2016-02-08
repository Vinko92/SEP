package com.merchant.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.AdditionalInsuranceDAOImpl;
import com.merchant.rest.model.AdditionalInsurance;

@Service("additionalInsuranceService")
public class AdditionalInsuranceServiceImpl implements AdditionalInsuranceService {

	@Autowired
	@Qualifier(value = "additionalInsuranceDAO")
	private AdditionalInsuranceDAOImpl additionalInsuranceDAO;
	
	@Autowired(required = true)
	public void setAdditionalInsuranceDAO(
			AdditionalInsuranceDAOImpl additionalInsuranceDAO) {
		this.additionalInsuranceDAO = additionalInsuranceDAO;
	}

	@Transactional
	@Override
	public void addAdditionalInsurance(AdditionalInsurance ai) {
		this.additionalInsuranceDAO.addAdditionalInsurance(ai);
	}

	@Transactional
	@Override
	public List<AdditionalInsurance> getAllAdditionalInsurance() {
		return this.additionalInsuranceDAO.getAllAdditionalInsurance();
	}
	
	
}
