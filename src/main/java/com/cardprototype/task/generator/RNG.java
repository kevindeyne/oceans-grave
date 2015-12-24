package com.cardprototype.task.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cardprototype.bootstrap.pool.AbilityPool;

public class RNG {

	private static final Random random = new Random();

	public static int generate(int min, int max) {
		return random.nextInt((max - min) + 1) + min;
	}

	public static String enemyAbility() {
		List<String> abilities = new ArrayList<String>();
		abilities.addAll(AbilityPool.getAbilityPool().getAbilities());
		return abilities.get(generate(0, abilities.size()-1));
	}

}
