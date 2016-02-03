package com.merchant.rest.service;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.InsuranceDAOImpl;
import com.merchant.rest.model.Insurance;

@Service("insuranceService")
public class InsuranceServiceImpl implements InsuranceService {
	
	@Autowired
	@Qualifier("insuranceDAO")
	private InsuranceDAOImpl insuranceDAO;
	
	@Autowired(required = true)
	public void setInsuranceDAO(InsuranceDAOImpl insuranceDAO) {
		this.insuranceDAO = insuranceDAO;
	}

	@Transactional
	@Override
	public void addInsurance(Insurance insurance) {
		this.insuranceDAO.addInsurance(insurance);
	}

}
