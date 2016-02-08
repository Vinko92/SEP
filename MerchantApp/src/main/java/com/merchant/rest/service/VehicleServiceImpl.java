package com.merchant.rest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.VehicleDAOImpl;
import com.merchant.rest.model.Vehicle;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	@Qualifier("vehicleDAO")
	private VehicleDAOImpl vehicleDAO;
	
	@Autowired(required = true)
	public void setVehicleDAO(VehicleDAOImpl vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}
	
	@Transactional
	@Override
	public void addVehicle(Vehicle v) {
		this.vehicleDAO.addVehicle(v);
	}

	@Transactional
	@Override
	public double getPriceByOwnerJmbg(String ownerJmbg) {
		return this.vehicleDAO.getPriceByOwnerJmbg(ownerJmbg);
	}

}
