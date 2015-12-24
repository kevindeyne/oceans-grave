package com.cardprototype.bootstrap.pool;

import org.apache.commons.lang3.RandomUtils;
import org.thymeleaf.util.StringUtils;

import com.cardprototype.core.domain.Station;
import com.cardprototype.core.domain.StationName;

/**
 * A pool is a singleton factory that can produce or find generated or in-memory instances
 *
 * This specific class handles {@link Station}
 *
 * @author Kevin Deyne
 */
public class StationPool {

	private static StationPool pool;

	/**
	 * Private constructor ensures Singleton usage
	 */
	private StationPool(){

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

	/**
	 * Generates a new basic enemy for a player
	 * @param delta
	 * @param playerId
	 * @return newly generated {@link Station}, unpersisted
	 */
	public Station generateNewStation(StationName name){

		Station station = new Station();

		station.setName(formatName(name));

		switch(name){
		case DELTA:
			handleDeltaStation(station);
			break;
		default:
			break;
		}

		return station;
	}

	private void handleDeltaStation(Station station) {
		station.setDescription("The docking bay is a collection of loud machinery, pressure doors and damaged ships looming over the grated bay. It is a very open space, and generally not the most comfortable area of the station. Not many people are here right now, but the large area echoes their conversations around.");

	}

	private String formatName(StationName name) {
		return StringUtils.capitalize(name.toString().toLowerCase()) + " Station";
	}

	private String generateStationName() {
		int size = StationName.values().length;
		int ordinal = RandomUtils.nextInt(0, size-1);
		String stationName = StationName.values()[ordinal].toString();
		return StringUtils.capitalize(stationName.toLowerCase()) + " Station";
	}
}
