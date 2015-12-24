package com.cardprototype.bootstrap.pool;

import org.thymeleaf.util.StringUtils;

import com.cardprototype.core.domain.Area;
import com.cardprototype.core.domain.Station;
import com.cardprototype.core.domain.StationName;

/**
 * A pool is a singleton factory that can produce or find generated or in-memory instances
 *
 * This specific class handles {@link Station}
 *
 * @author Kevin Deyne
 */
public class AreaPool {

	private static AreaPool pool;

	/**
	 * Private constructor ensures Singleton usage
	 */
	private AreaPool(){

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

	/**
	 * Generates a new basic enemy for a player
	 * @param delta
	 * @param playerId
	 * @return newly generated {@link Station}, unpersisted
	 */
	public Area generateTestArea(){
		Area area = new Area();

		area.setId(Area.EXAMPLE_ID);
		area.setName("Barracks");
		area.setDescription("These run-down shacks offer a temporary place to stay for crews with little budget left. They're safe and dry. Those who end up here, usually have more troubles on their mind that sleeping comfort.");

		return area;
	}

	private void handleDeltaStation(Station station) {
		station.setDescription("The docking bay is a collection of loud machinery, pressure doors and damaged ships looming over the grated bay. It is a very open space, and generally not the most comfortable area of the station. Not many people are here right now, but the large area echoes their conversations around.");
	}

	private String formatName(StationName name) {
		return StringUtils.capitalize(name.toString().toLowerCase()) + " Station";
	}

	public Area generateSmallArea(String areaId) {
		Area area = new Area();
		area.setId(areaId);
		return area;
	}
}
