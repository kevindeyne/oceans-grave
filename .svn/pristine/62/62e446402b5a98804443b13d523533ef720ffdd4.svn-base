package com.cardprototype.bootstrap.loaders;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.cardprototype.bootstrap.pool.AbilityPool;
import com.cardprototype.core.domain.Ability;
import com.cardprototype.core.domain.AbilityType;

/**
 * Loaders are classes that listen for an application start up and then fill
 * either a database or in-memory storage with instances of classes
 * This can be used for either test data or actual objects that only exist in-memory
 *
 * Implementing the {@link Ordered} interface ensures we can control when it is executed
 *
 * This specific class handles the {@link Ability}
 *
 * @author Kevin Deyne
 */
@Component
public class AbilityLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		AbilityPool pool = AbilityPool.getAbilityPool();

		pool.loadUp(AbilityLoader.basicAttackAbility());
		pool.loadUp(AbilityLoader.basicHealAbility());
	}

	/**
	 * Determines the order executed
	 */
	@Override
	public int getOrder() {
		return 1;
	}

	/**
	 * Sets up a basic Attack (10) ability
	 * @return {@link Ability}
	 */
	public static Ability basicAttackAbility() {
		Ability ability = new Ability();

		ability.setId(Ability.BASIC_ATTACK);
		ability.setName("Attack (10)");
		ability.setType(AbilityType.ATTACK);
		ability.setStrength(10);

		return ability;
	}

	/**
	 * Sets up a basic Repair (5) ability
	 * @return {@link Ability}
	 */
	public static Ability basicHealAbility() {
		Ability ability = new Ability();

		ability.setId(Ability.BASIC_REPAIR);
		ability.setName("Repair (5)");
		ability.setType(AbilityType.REPAIR);
		ability.setStrength(5);

		return ability;
	}


}
