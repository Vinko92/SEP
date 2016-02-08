package com.merchant.rest.dao;

import com.merchant.rest.model.Vehicle;

public interface VehicleDAO {

	public void addVehicle(Vehicle v);
	public double getPriceByOwnerJmbg(String ownerJmbg);
}
