package com.cardprototype.bootstrap.pool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cardprototype.core.domain.Ability;

public class AbilityPool {

	private static AbilityPool pool;

	private Map<String, Ability> abilities;

	private AbilityPool(){
		this.abilities = new HashMap<String, Ability>();
	}

	public static AbilityPool getAbilityPool(){
		if(pool == null){
			pool = new AbilityPool();
		}

		return pool;
	}

	public Ability getAbility(String key){
		return this.abilities.get(key);
	}

	public void loadUp(Ability ability) {
		this.abilities.put(ability.getId(), ability);
	}

	public List<Ability> getStartAbilities() {
		List<Ability> abilities = new ArrayList<Ability>();

		abilities.add(getAbility(Ability.BASIC_ATTACK));
		abilities.add(getAbility(Ability.BASIC_REPAIR));

		return abilities;
	}

	public List<Ability> getAbilities(List<String> abilityIds) {
		List<Ability> abilities = new ArrayList<Ability>();
		for(String id : abilityIds){ abilities.add(getAbility(id)); }
		return abilities;
	}
}
