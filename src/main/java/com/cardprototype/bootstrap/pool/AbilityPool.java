package com.cardprototype.bootstrap.pool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cardprototype.core.domain.Ability;

/**
 * A pool is a singleton factory that can produce or find generated or in-memory instances
 *
 * This specific class handles {@link Ability}
 *
 * @author Kevin Deyne
 */
public class AbilityPool {

	private static AbilityPool pool;

	private Map<String, Ability> abilities;

	/**
	 * Private constructor ensures Singleton usage
	 */
	private AbilityPool(){
		this.abilities = new HashMap<String, Ability>();
	}

	/**
	 * Retrieves the Singleton pool
	 * @return {@link AbilityPool}
	 */
	public static AbilityPool getAbilityPool(){
		if(pool == null){
			pool = new AbilityPool();
		}

		return pool;
	}

	/**
	 * Finds a single ability based on a key
	 * @param id to find
	 * @return {@link Ability}
	 */
	public Ability getAbility(String key){
		return this.abilities.get(key);
	}

	/**
	 * Persists a single ability in the pool
	 * @param {@link Ability} to persist
	 */
	public void loadUp(Ability ability) {
		this.abilities.put(ability.getId(), ability);
	}

	/**
	 * Retrieves the collection of abilities a player starts with
	 * @return {@link List} of {@link Ability} instances
	 */
	public List<Ability> getStartAbilities() {
		List<Ability> abilities = new ArrayList<Ability>();

		abilities.add(getAbility(Ability.BASIC_ATTACK));
		abilities.add(getAbility(Ability.BASIC_REPAIR));

		return abilities;
	}

	/**
	 * Finds a {@link List} of abilities based on a list of ids
	 * @see getAbility(key)
	 * @param abilityIds
	 * @return {@link List} of {@link Ability}
	 */
	public List<Ability> getAbilities(List<String> abilityIds) {
		List<Ability> abilities = new ArrayList<Ability>();
		for(String id : abilityIds){ abilities.add(getAbility(id)); }
		return abilities;
	}
}
