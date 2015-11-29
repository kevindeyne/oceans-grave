package com.cardprototype.bootstrap.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.cardprototype.bootstrap.pool.EnemyPool;
import com.cardprototype.core.domain.Enemy;
import com.cardprototype.core.domain.Player;
import com.cardprototype.core.repository.EnemyRepository;

/**
 * Loaders are classes that listen for an application start up and then fill
 * either a database or in-memory storage with instances of classes
 * This can be used for either test data or actual objects that only exist in-memory
 *
 * Implementing the {@link Ordered} interface ensures we can control when it is executed
 *
 * This specific class handles the {@link Enemy}
 *
 * @author Kevin Deyne
 */
@Component
public class EnemyLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	@Autowired
	private EnemyRepository enemyRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Enemy enemy = EnemyPool.getEnemyPool().generateNewEnemy(Player.EXAMPLE_ID);
		this.enemyRepository.save(enemy);
	}

	/**
	 * Determines the order executed
	 */
	@Override
	public int getOrder() {
		return 3;
	}
}