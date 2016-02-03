package com.merchant.rest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.SportDAOImpl;
import com.merchant.rest.model.Sport;

@Service("sportService")
public class SportServiceImpl implements SportService {

	@Autowired
	@Qualifier("sportDAO")
	private SportDAOImpl sportDAO;
	
	@Autowired(required = true)
	public void setSportDAO(SportDAOImpl sportDAO) {
		this.sportDAO = sportDAO;
	}
	
	@Transactional
	@Override
	public Sport getSportByName(String name) {
		return sportDAO.getSportByName(name);
	}

}
