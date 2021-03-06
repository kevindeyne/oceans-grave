package com.cardprototype.bootstrap.pool;

import java.util.HashMap;
import java.util.Map;

import com.cardprototype.core.domain.Station;

/**
 * A pool is a singleton factory that can produce or find generated or in-memory instances
 *
 * This specific class handles {@link Station}
 *
 * @author Kevin Deyne
 */
public class StationPool {

	private static StationPool pool;

	private Map<String, Station> stations;

	/**
	 * Private constructor ensures Singleton usage
	 */
	private StationPool(){
		this.stations = new HashMap<String, Station>();
	}


	/**
	 * Persists a single ability in the pool
	 * @param {@link Station} to persist
	 */
	public void loadUp(Station station) {
		this.stations.put(station.getId(), station);
	}

	/**
	 * Retrieves the Singleton pool
	 * @return {@link StationPool}
	 */
	public static StationPool getStationPool(){
		if(pool == null){
			pool = new StationPool();
		}

		return pool;
	}

	public Station createDummyStation(String id) {
		Station station = new Station();
		station.setId(id);
		return station;
	}


	public Station getStation(String stationid) {
		return this.stations.get(stationid);
	}
}
