package com.cardprototype.bootstrap.pool;

import java.util.HashMap;
import java.util.Map;

import com.cardprototype.core.domain.Area;
import com.cardprototype.core.domain.Station;

/**
 * A pool is a singleton factory that can produce or find generated or in-memory instances
 *
 * This specific class handles {@link Station}
 *
 * @author Kevin Deyne
 */
public class AreaPool {

	private static AreaPool pool;

	private Map<String, Map<String, Area>> areas;

	/**
	 * Private constructor ensures Singleton usage
	 */
	private AreaPool(){
		this.areas = new HashMap<String, Map<String, Area>>();
	}

	/**
	 * Persists a single area in the pool
	 * @param {@link Area} to persist
	 */
	public void loadUp(String stationId, Area area) {
		if(null == this.areas.get(stationId)){
			this.areas.put(stationId, new HashMap<String, Area>());
		}

		this.areas.get(stationId).put(area.getId(), area);
	}

	/**
	 * Retrieves the Singleton pool
	 * @return {@link AreaPool}
	 */
	public static AreaPool getAreaPool(){
		if(pool == null){
			pool = new AreaPool();
		}

		return pool;
	}

	public Area getArea(String stationid, String areaid) {
		Area area = this.areas.get(stationid).get(areaid);
		area.setStation(StationPool.getStationPool().getStation(stationid));
		return area;
	}
}
