package com.merchant.rest.dao;

import java.util.List;

import com.merchant.rest.model.RoadAssistence;

public interface RoadAssistenceDAO {

	public List<RoadAssistence> getAllRoadAssistence();
	public List<RoadAssistence> findByName(String name);
	public void addRoadAssistence(RoadAssistence ra);
	public RoadAssistence getByName(String name);
}
