package com.cardprototype.core.domain.battle.helper.changes.common;

import java.io.Serializable;
import java.util.Map;

public abstract class ActorChanges implements Serializable {

	private static final long serialVersionUID = 1L;

	private int health;
	private boolean scorched;
	private boolean stunned;

	private int acc;
	private int def;

	private Map<String, Integer> abilitiesOnCooldown;

	public int getHealth() {
		if(isStunned()){
			return 0;
		}
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isScorched() {
		return this.scorched;
	}

	public void setScorched(boolean scorched) {
		this.scorched = scorched;
	}

	public boolean isStunned() {
		return this.stunned;
	}

	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}

	public Map<String, Integer> getAbilitiesOnCooldown() {
		return this.abilitiesOnCooldown;
	}

	public void setAbilitiesOnCooldown(Map<String, Integer> abilitiesOnCooldown) {
		this.abilitiesOnCooldown = abilitiesOnCooldown;
	}

	public int getAcc() {
		return this.acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

	public int getDef() {
		return this.def;
	}

	public void setDef(int def) {
		this.def = def;
	}
}