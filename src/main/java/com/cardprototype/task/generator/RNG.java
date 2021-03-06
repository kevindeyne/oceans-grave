package com.cardprototype.task.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.cardprototype.bootstrap.pool.AbilityPool;
import com.cardprototype.core.domain.Enemy;
import com.cardprototype.core.domain.Player;
import com.cardprototype.core.domain.ability.Ability;
import com.cardprototype.core.domain.ability.AbilityCategory;

public class RNG {

	private static final Random random = new Random();

	public static int generate(int min, int max) {
		return random.nextInt((max - min) + 1) + min;
	}

	public static String enemyAbility(Player player) {
		Enemy enemy = player.getEnemy();

		List<String> abilities = new ArrayList<String>();
		abilities.addAll(AbilityPool.getAbilityPool().getAbilities());
		abilities.removeAll(enemy.getAbilitiesOnCooldown());

		if(enemy.getStunned() > 0){
			abilities = abilityInList(abilities, AbilityCategory.KILL_STUN);
			if(CollectionUtils.isNotEmpty(abilities)){
				return abilities.get(generate(0, abilities.size()-1));
			}
		}


		if(enemy.getAcc() < 0){
			abilities = abilityInList(abilities, AbilityCategory.ACC_BUFF);
			if(CollectionUtils.isNotEmpty(abilities)){
				return abilities.get(generate(0, abilities.size()-1));
			}
		}

		if(enemy.getDef() < 0){
			abilities = abilityInList(abilities, AbilityCategory.DEF_BUFF);
			if(CollectionUtils.isNotEmpty(abilities)){
				return abilities.get(generate(0, abilities.size()-1));
			}
		}

		abilities = abilityInList(abilities, AbilityCategory.BIG_DAMAGE, AbilityCategory.SMALL_DAMAGE);
		if(CollectionUtils.isNotEmpty(abilities)){
			return abilities.get(generate(0, abilities.size()-1));
		}

		return abilities.get(generate(0, abilities.size()-1));
	}

	private static List<String> abilityInList(List<String> abilities, AbilityCategory... categories) {
		List<Ability> loadedAbilities = AbilityPool.getAbilityPool().getAbilitiesLoaded();
		Set<String> possibleAbilities = new HashSet<String>();

		for(Ability ability : loadedAbilities){
			for(AbilityCategory category : categories){
				if(ability.getCategories().contains(category)){
					possibleAbilities.add(ability.getId());
				}
			}
		}

		List<String> result = new ArrayList<String>();
		result.addAll(possibleAbilities);
		return result;
	}

}
