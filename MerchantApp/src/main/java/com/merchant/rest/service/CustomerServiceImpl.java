package com.merchant.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.CustomerDAOImpl;
import com.merchant.rest.model.Customer;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{


	@Autowired
	@Qualifier("customerDAO")
	private CustomerDAOImpl customerDAO;
	
	@Autowired(required = true)
	public void setCustomerDAO(CustomerDAOImpl customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	@Transactional
	@Override
	public void addCustomer(Customer c) {
		this.customerDAO.addCustomer(c);
		
	}

	@Transactional
	@Override
	public void updateCustomer(Customer c) {
		this.customerDAO.updateCustomer(c);
		
	}

	@Transactional
	@Override
	public List<Customer> listCustomers() {
		return this.customerDAO.listCustomers();
	}

	@Transactional
	@Override
	public Customer getCustomerById(int id) {
		return this.customerDAO.getCustomerById(id);
	}

	@Transactional
	@Override
	public void removeCustomer(int id) {
		this.customerDAO.removeCustomer(id);
	}

	
}
