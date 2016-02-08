package com.merchant.rest.service;

import java.util.List;

import com.merchant.rest.model.RoadAssistence;

public interface RoadAssistenceService {

	public List<RoadAssistence> getAllRoadAssistence();
	public List<RoadAssistence> findByName(String name);
	public void addRoadAssistence(RoadAssistence ra);
	public RoadAssistence getByName(String name);
}
