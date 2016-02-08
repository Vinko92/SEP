package com.merchant.rest.dao;

import java.util.List;

import com.merchant.rest.model.Risk;

public interface RiskDAO {

	public Risk findRiskById(int id);
	public Risk findRiskByName(String name);
	public List<Risk> getAllRisks();
}
