package com.cardprototype.bootstrap.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.cardprototype.bootstrap.pool.AreaPool;
import com.cardprototype.core.domain.Area;
import com.cardprototype.core.domain.MissionStateCode;
import com.cardprototype.core.domain.POI;
import com.cardprototype.core.domain.Station;
import com.cardprototype.core.repository.POIRepository;

/**
 * Loaders are classes that listen for an application start up and then fill
 * either a database or in-memory storage with instances of classes
 * This can be used for either test data or actual objects that only exist in-memory
 *
 * Implementing the {@link Ordered} interface ensures we can control when it is executed
 *
 * This specific class handles the {@link POILoader}
 *
 * @author Kevin Deyne
 */
@Component
public class POILoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	@Autowired
	private POIRepository poiRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setupTutorial1Antwerp();
	}

	private void setupTutorial1Antwerp() {
		createAntwerpBarrackPanel();

	}

	private void createAntwerpBarrackPanel() {
		POI antwerpBarracksPanel = POILoader.createAntwerpBarracksPanel();
		this.poiRepository.save(antwerpBarracksPanel);
	}

	private static POI createAntwerpBarracksPanel() {
		POI antwerpBarracksPanel = new POI();
		antwerpBarracksPanel.setId(POI.ANTWERP_BARRACKS_PANEL);
		antwerpBarracksPanel.setAreaId(AreaPool.getAreaPool().getArea(Station.ANTWERP, Area.ANTWERP_STATION_BARRACKS).getId());
		antwerpBarracksPanel.setDescription("It's a message panel. The red light is blinking brightly. The panels door is shut tight.");
		antwerpBarracksPanel.setName("Panel with a flashing red light");
		antwerpBarracksPanel.setState(MissionStateCode.TUTORIAL1);
		return antwerpBarracksPanel;
	}

	/**
	 * Determines the order executed
	 */
	@Override
	public int getOrder() {
		return 8;
	}
}