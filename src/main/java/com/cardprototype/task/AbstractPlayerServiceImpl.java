package com.cardprototype.task;

import com.cardprototype.bootstrap.pool.AbilityPool;
import com.cardprototype.core.domain.EventQueue;
import com.cardprototype.core.domain.Player;
import com.cardprototype.core.domain.ability.Ability;

public abstract class AbstractPlayerServiceImpl {

	protected String getPlayerId(){
		return Player.EXAMPLE_ID;
	}

	protected Ability retrieveAbility(EventQueue event) {
		return AbilityPool.getAbilityPool().getAbility(event.getAbilityId());
	}

}
