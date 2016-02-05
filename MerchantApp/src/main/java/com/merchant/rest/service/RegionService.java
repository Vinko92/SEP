package com.merchant.rest.service;

import java.util.List;

import com.merchant.rest.model.Region;

public interface RegionService {

	public Region getRegionByName(String name);
	public List<Region> getAllRegion();
}
