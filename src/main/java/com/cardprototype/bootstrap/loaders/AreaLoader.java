package com.cardprototype.bootstrap.loaders;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.cardprototype.bootstrap.pool.AreaPool;
import com.cardprototype.bootstrap.pool.StationPool;
import com.cardprototype.core.domain.Area;
import com.cardprototype.core.domain.Station;

/**
 * Loaders are classes that listen for an application start up and then fill
 * either a database or in-memory storage with instances of classes
 * This can be used for either test data or actual objects that only exist in-memory
 *
 * Implementing the {@link Ordered} interface ensures we can control when it is executed
 *
 * This specific class handles the {@link AreaLoader}
 *
 * @author Kevin Deyne
 */
@Component
public class AreaLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		AreaPool pool = AreaPool.getAreaPool();

		pool.loadUp(Station.ANTWERP, AreaLoader.antwerpStationBarracks());
		pool.loadUp(Station.ANTWERP, AreaLoader.antwerpStationDockingBay());
	}

	private static Area antwerpStationBarracks() {
		Area barracks = new Area();

		barracks.setId(Area.ANTWERP_STATION_BARRACKS);
		barracks.setName("Barracks");
		barracks.setDescription("These run-down shacks offer a temporary place to stay for crews with little budget left. They're safe and dry. Those who end up here, usually have more troubles on their mind that sleeping comfort.");
		barracks.setStation(StationPool.getStationPool().createDummyStation(Station.ANTWERP));

		return barracks;
	}


	private static Area antwerpStationDockingBay() {
		Area dockingBay = new Area();

		dockingBay.setId(Area.ANTWERP_STATION_DOCKING_BAY);
		dockingBay.setName("Docking Bay");
		dockingBay.setDescription("The docking bay is a collection of loud machinery, pressure doors and damaged ships looming over the grated bay. It is a very open space, and generally not the most comfortable area of the station. Not many people are here right now, but the large area echoes their conversations around.");
		dockingBay.setStation(StationPool.getStationPool().createDummyStation(Station.ANTWERP));

		return dockingBay;
	}

	/**
	 * Determines the order executed
	 */
	@Override
	public int getOrder() {
		return 4;
	}
}