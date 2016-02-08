package com.merchant.rest.dao;

import java.util.List;

import com.merchant.rest.model.Customer;

public interface CustomerDAO {

	public void addCustomer(Customer c);
	public void updateCustomer(Customer c);
	public List<Customer> listCustomers();
	public Customer getCustomerById(int id);
	public void removeCustomer(int id);
	public Customer getCustomerByName(String name);
	public int getCustomerIdByCustomerName(String name);
	public void setCustomerOwnerByJmbg(String jmbg);

}
