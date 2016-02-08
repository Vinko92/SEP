package com.merchant.rest.service;

import java.util.List;

import com.merchant.rest.model.Risk;

public interface RiskService {

	public Risk findRiskById(int id);
	public Risk findRiskByName(String name);
	public List<Risk> getAllRisks();
}
