package com.cardprototype.bootstrap.loaders;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.cardprototype.bootstrap.pool.AbilityPool;
import com.cardprototype.core.domain.Ability;
import com.cardprototype.core.domain.AbilityType;

@Component
public class AbilityLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		AbilityPool pool = AbilityPool.getAbilityPool();

		pool.loadUp(AbilityLoader.basicAttackAbility());
		pool.loadUp(AbilityLoader.basicHealAbility());
	}

	@Override
	public int getOrder() {
		return 1;
	}

	public static Ability basicAttackAbility() {
		Ability ability = new Ability();

		ability.setId(Ability.BASIC_ATTACK);
		ability.setName("Attack (10)");
		ability.setType(AbilityType.ATTACK);
		ability.setStrength(10);

		return ability;
	}

	public static Ability basicHealAbility() {
		Ability ability = new Ability();

		ability.setId(Ability.BASIC_REPAIR);
		ability.setName("Repair (5)");
		ability.setType(AbilityType.REPAIR);
		ability.setStrength(5);

		return ability;
	}


}
