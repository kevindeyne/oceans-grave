package com.cardprototype.bootstrap.pool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.cardprototype.core.domain.Player;
import com.cardprototype.core.domain.ability.Ability;
import com.cardprototype.core.domain.ability.AbilityCategory;

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
	 * Finds all abilities
	 * @return {@link Ability}
	 */
	public Collection<String> getAbilities(){
		return this.abilities.keySet();
	}

	/**
	 * Finds all abilities
	 * @return {@link Ability}
	 */
	public List<Ability> getAbilitiesLoaded(){
		List<Ability> abilities = new ArrayList<Ability>();
		abilities.addAll(this.abilities.values());
		return abilities;
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

		abilities.add(getAbility(Ability.COOLANT_FLUID));
		abilities.add(getAbility(Ability.FLAK_CANNON));
		abilities.add(getAbility(Ability.GATTLING_GUN));
		abilities.add(getAbility(Ability.GAUSS_MINE_DRONE));
		abilities.add(getAbility(Ability.HARPOON));
		abilities.add(getAbility(Ability.OVERDRIVE));

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

	public List<String> getAbilities(Player player, AbilityCategory category) {
		List<Ability> abilities = player.getAbilities();
		List<String> result = new ArrayList<String>();

		if(CollectionUtils.isNotEmpty(abilities)){
			for(Ability ability : abilities){
				if(ability.getCategories().contains(category)){
					result.add(ability.getId());
				}
			}
		}

		return result;
	}
}
