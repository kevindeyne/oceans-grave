package com.cardprototype.task;

import com.cardprototype.core.domain.battle.helper.RoundChanges;

public interface AbilityEffectService {

	/**
	 * Apply ability within current round
	 * @param abilityId
	 * @param round
	 * @param asPlayer
	 */
	public void useAbility(String abilityId, int round, boolean asPlayer);

	/**
	 * Accumilate round changes for the provided round
	 * @param round
	 * @param asPlayer
	 * @return RoundChanges object
	 */
	public RoundChanges getRoundChanges(int round, boolean asPlayer);

	/**
	 * Apply ability within current round
	 * @param round
	 */
	public void enemyMove(int round);



}
