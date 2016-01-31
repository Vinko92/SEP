package com.merchant.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.RoadAssistenceDAOImpl;
import com.merchant.rest.model.RoadAssistence;

@Service("roadAssistenceService")
public class RoadAssistenceServiceImpl implements RoadAssistenceService {

	@Autowired
	@Qualifier(value = "roadAssistenceDAO")
	private RoadAssistenceDAOImpl roadAssistenceDAO;
	
	@Autowired(required = true)
	public void setRoadAssistenceDAO(RoadAssistenceDAOImpl roadAssistenceDAO) {
		this.roadAssistenceDAO = roadAssistenceDAO;
	}
	
	@Transactional
	@Override
	public List<RoadAssistence> getAllRoadAssistence() {
		return this.roadAssistenceDAO.getAllRoadAssistence();
	}

	@Transactional
	@Override
	public List<RoadAssistence> findByName(String name) {
		return this.roadAssistenceDAO.findByName(name);
	}

	@Transactional
	@Override
	public void addRoadAssistence(RoadAssistence ra) {
		this.roadAssistenceDAO.addRoadAssistence(ra);
	}

	@Transactional
	@Override
	public RoadAssistence getByName(String name) {
		return this.roadAssistenceDAO.getByName(name);
	}

}
