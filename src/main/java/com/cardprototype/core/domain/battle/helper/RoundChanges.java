package com.cardprototype.core.domain.battle.helper;

import java.io.Serializable;

import com.cardprototype.core.domain.battle.helper.changes.EnemyChanges;
import com.cardprototype.core.domain.battle.helper.changes.PlayerChanges;

public class RoundChanges implements Serializable {

	private static final long serialVersionUID = 8929053395063652959L;

	public static final int MIN_ROUND = 1;

	private EnemyChanges enemyChanges;
	private PlayerChanges playerChanges;

	private int round;
	private String recentAttackAbility;
	private String recentAttackDamage;
	private String recentAttackBuffs;

	public RoundChanges() {
		this.enemyChanges = new EnemyChanges();
		this.playerChanges = new PlayerChanges();
		setRound(1);
		setRecentAttackAbility("");
		setRecentAttackDamage("");
		setRecentAttackBuffs("");
	}

	public EnemyChanges getEnemyChanges() {
		return this.enemyChanges;
	}

	public void setEnemyChanges(EnemyChanges enemyChanges) {
		this.enemyChanges = enemyChanges;
	}

	public PlayerChanges getPlayerChanges() {
		return this.playerChanges;
	}

	public void setPlayerChanges(PlayerChanges playerChanges) {
		this.playerChanges = playerChanges;
	}

	public int getRound() {
		return this.round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public String getRecentAttackAbility() {
		return this.recentAttackAbility;
	}

	public void setRecentAttackAbility(String recentAttackAbility) {
		this.recentAttackAbility = recentAttackAbility;
	}

	public String getRecentAttackDamage() {
		return this.recentAttackDamage;
	}

	public void setRecentAttackDamage(String recentAttackDamage) {
		this.recentAttackDamage = recentAttackDamage;
	}

	public String getRecentAttackBuffs() {
		return this.recentAttackBuffs;
	}

	public void setRecentAttackBuffs(String recentAttackBuffs) {
		this.recentAttackBuffs = recentAttackBuffs;
	}
}