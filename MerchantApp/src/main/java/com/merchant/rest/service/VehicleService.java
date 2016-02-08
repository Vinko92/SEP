package com.merchant.rest.service;

import com.merchant.rest.model.Vehicle;

public interface VehicleService {

	public void addVehicle(Vehicle v);
	public double getPriceByOwnerJmbg(String ownerJmbg);
}
