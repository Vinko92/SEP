package com.merchant.rest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.HomeDAOImpl;
import com.merchant.rest.model.Home;

@Service("homeService")
public class HomeServiceImpl implements HomeService{

	@Autowired
	@Qualifier("homeDAO")
	private HomeDAOImpl homeDAO;
	
	@Autowired(required = true)
	public void setHomeDAO(HomeDAOImpl homeDAO) {
		this.homeDAO = homeDAO;
	}
	
	@Transactional
	@Override
	public Home findHomeById(int id) {
		return this.homeDAO.findHomeById(id);
	}

	@Transactional
	@Override
	public Home findHomeByIdAndPrice(int id, double price) {
		return this.homeDAO.findHomeByIdAndPrice(id, price);
	}

	@Transactional
	@Override
	public void addHomeInsurance(Home h) {
		 this.homeDAO.addHomeInsurance(h);
	}

	
}
