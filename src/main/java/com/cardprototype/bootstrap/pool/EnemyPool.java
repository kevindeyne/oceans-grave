package com.cardprototype.bootstrap.pool;

import java.util.ArrayList;
import java.util.List;

import com.cardprototype.core.domain.Ability;
import com.cardprototype.core.domain.Enemy;
import com.cardprototype.core.domain.Player;

public class EnemyPool {

	private static EnemyPool pool;

	private EnemyPool(){

	}

	public static EnemyPool getEnemyPool(){
		if(pool == null){
			pool = new EnemyPool();
		}

		return pool;
	}

	public Enemy generateNewEnemy(String playerId){

		Enemy enemy = new Enemy();

		enemy.setName("Enemy");
		enemy.setHealth(200);
		enemy.setId(Enemy.EXAMPLE_ID);

		Player player = new Player();
		player.setId(Player.EXAMPLE_ID);
		enemy.setPlayer(player);

		List<String> enemyAbilities = new ArrayList<String>();
		List<Ability> starterAbilities = AbilityPool.getAbilityPool().getStartAbilities();
		for(Ability ability : starterAbilities){
			enemyAbilities.add(ability.getId());
		}
		enemy.setAbilities(enemyAbilities);

		return enemy;
	}
}
