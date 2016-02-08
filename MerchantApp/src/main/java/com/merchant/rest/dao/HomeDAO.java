package com.merchant.rest.dao;
import com.merchant.rest.model.Home;

public interface HomeDAO {

	public void addHomeInsurance(Home h);
	public Home findHomeById(int id); 
	public Home findHomeByIdAndPrice(int id, double price);

	public double getPriceByOwnerJmbg(String ownerJmbg);

}
