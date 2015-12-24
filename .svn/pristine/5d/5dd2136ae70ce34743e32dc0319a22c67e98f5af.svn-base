package com.cardprototype.bootstrap.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.cardprototype.bootstrap.pool.AreaPool;
import com.cardprototype.core.domain.Area;
import com.cardprototype.core.repository.AreaRepository;

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

	public static final String DELTA_BAR = "delta-bar";

	@Autowired
	private AreaRepository areaRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Area area = AreaPool.getAreaPool().generateTestArea();
		area.setId(DELTA_BAR);
		this.areaRepository.save(area);

	}

	/**
	 * Determines the order executed
	 */
	@Override
	public int getOrder() {
		return 4;
	}
}