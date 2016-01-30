package com.merchant.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.RiskDAOImpl;
import com.merchant.rest.model.Risk;

@Service("riskService")
public class RiskServiceImpl implements RiskService {

	@Autowired
	@Qualifier("riskDAO")
	private RiskDAOImpl riskDAO;
	
	@Autowired(required = true)
	public void setRiskDAO(RiskDAOImpl riskDAO) {
		this.riskDAO = riskDAO;
	}
	
	@Transactional
	@Override
	public Risk findRiskByName(String name) {
		return this.riskDAO.findRiskByName(name);
	}

	@Override
	public List<Risk> getAllRisks() {
		return this.riskDAO.getAllRisks();
	}

	@Override
	public Risk findRiskById(int id) {
		return this.riskDAO.findRiskById(id);
	}

}
