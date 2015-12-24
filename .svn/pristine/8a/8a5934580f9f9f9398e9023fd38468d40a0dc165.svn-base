package com.cardprototype.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cardprototype.core.domain.MissionStateCode;
import com.cardprototype.core.domain.POI;

public interface POIRepository extends CrudRepository<POI, String> {

	public List<POI> findByAreaIdAndState(String id, MissionStateCode missionStateCode);
}