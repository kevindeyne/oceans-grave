package com.cardprototype.task;

import com.cardprototype.bootstrap.pool.AbilityPool;
import com.cardprototype.core.domain.Ability;
import com.cardprototype.core.domain.EventQueue;
import com.cardprototype.core.domain.Player;

public abstract class AbstractPlayerServiceImpl {

	protected String getPlayerId(){
		return Player.EXAMPLE_ID;
	}

	protected Ability retrieveAbility(EventQueue event) {
		return AbilityPool.getAbilityPool().getAbility(event.getAbilityId());
	}

}
