package com.merchant.rest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.RegionDAOImpl;
import com.merchant.rest.model.Region;

@Service("regionService")
public class RegionServiceImpl implements RegionService {

	@Autowired
	@Qualifier(value = "regionDAO")
	private RegionDAOImpl regionDAO;
	
	@Autowired(required = true)
	public void setRegionDAO(RegionDAOImpl regionDAO) {
		this.regionDAO = regionDAO;
	}
	
	@Transactional
	@Override
	public Region getRegionByName(String name) {
		return this.regionDAO.getRegionByName(name);
	}

}
