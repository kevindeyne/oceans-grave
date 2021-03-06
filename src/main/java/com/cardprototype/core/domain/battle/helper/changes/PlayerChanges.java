package com.cardprototype.core.domain.battle.helper.changes;

import java.util.Map;

import com.cardprototype.core.domain.battle.helper.changes.common.ActorChanges;

public class PlayerChanges extends ActorChanges {

	private static final long serialVersionUID = 1802683125839262582L;

	private Map<String, Integer> abilitiesOnCooldown;

	public Map<String, Integer> getAbilitiesOnCooldown() {
		return this.abilitiesOnCooldown;
	}

	public void setAbilitiesOnCooldown(Map<String, Integer> abilitiesOnCooldown) {
		this.abilitiesOnCooldown = abilitiesOnCooldown;
	}

}