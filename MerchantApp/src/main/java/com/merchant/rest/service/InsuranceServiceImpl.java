package com.merchant.rest.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.InsuranceDAOImpl;

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
	public BigDecimal getPrice(int id) {
		return this.insuranceDAO.getPrice(id);
	}

}
