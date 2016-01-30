package com.merchant.rest.service;

import com.merchant.rest.model.Home;

public interface HomeService {

	public void addHomeInsurance(Home h);
	public Home findHomeById(int id); 
	public Home findHomeByIdAndPrice(int id, double price);
}
