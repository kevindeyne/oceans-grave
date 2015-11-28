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

@Component
public class EnemyLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	@Autowired
	private EnemyRepository enemyRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Enemy enemy = EnemyPool.getEnemyPool().generateNewEnemy(Player.EXAMPLE_ID);
		this.enemyRepository.save(enemy);
	}

	@Override
	public int getOrder() {
		return 3;
	}
}