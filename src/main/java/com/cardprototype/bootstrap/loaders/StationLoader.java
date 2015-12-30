package com.cardprototype.bootstrap.loaders;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.cardprototype.bootstrap.pool.StationPool;
import com.cardprototype.core.domain.Station;

/**
 * Loaders are classes that listen for an application start up and then fill
 * either a database or in-memory storage with instances of classes
 * This can be used for either test data or actual objects that only exist in-memory
 *
 * Implementing the {@link Ordered} interface ensures we can control when it is executed
 *
 * This specific class handles the {@link StationLoader}
 *
 * @author Kevin Deyne
 */
@Component
public class StationLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		StationPool pool = StationPool.getStationPool();

		pool.loadUp(StationLoader.antwerpStation());
		pool.loadUp(StationLoader.newKholnStation());
	}



	private static Station antwerpStation() {
		Station antwerp = new Station();

		antwerp.setId(Station.ANTWERP);
		antwerp.setName("Antwerp Station");
		antwerp.setDescription("Though run-down and remote, this station works as a major outpost for merchants in the area. It has a limited amount of security, but a booming bazaar.");

		return antwerp;
	}

	private static Station newKholnStation() {
		Station newKholn = new Station();

		newKholn.setId(Station.NEW_KHOLN);
		newKholn.setName("New Kholn Station");
		newKholn.setDescription("These run-down shacks offer a temporary place to stay for crews with little budget left. They're safe and dry. Those who end up here, usually have more troubles on their mind that sleeping comfort.");

		return newKholn;
	}

	/**
	 * Determines the order executed
	 */
	@Override
	public int getOrder() {
		return 7;
	}
}