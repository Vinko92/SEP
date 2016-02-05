package com.merchant.rest.dao;

import java.util.List;

import com.merchant.rest.model.Region;

public interface RegionDAO {

	public Region getRegionByName(String name);
	public List<Region> getAllRegion();
}
