package com.cardprototype.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * The event queue is a queue used during the battle system, using {@link Player} and round to figure out which are revelant
 * Different events on the same round are then to be concatenated into a single 'change' event
 *
 * @author Kevin Deyne
 */
@Entity
public class EventQueue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", updatable = false, nullable = false, length = 32)
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@OneToOne(optional=true)
	private Player player;

	@Column(name = "player_damage", nullable = false)
	private int playerDamage;

	@Column(name = "player_heal", nullable = false)
	private int playerHeal;

	@Column(name = "enemy_damage", nullable = false)
	private int enemyDamage;

	@Column(name = "enemy_heal", nullable = false)
	private int enemyHeal;

	@Column(name = "round", nullable = false)
	private int round;

	public EventQueue() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getPlayerDamage() {
		return this.playerDamage;
	}

	public void setPlayerDamage(int playerDamage) {
		this.playerDamage = playerDamage;
	}

	public int getPlayerHeal() {
		return this.playerHeal;
	}

	public void setPlayerHeal(int playerHeal) {
		this.playerHeal = playerHeal;
	}

	public int getEnemyDamage() {
		return this.enemyDamage;
	}

	public void setEnemyDamage(int enemyDamage) {
		this.enemyDamage = enemyDamage;
	}

	public int getEnemyHeal() {
		return this.enemyHeal;
	}

	public void setEnemyHeal(int enemyHeal) {
		this.enemyHeal = enemyHeal;
	}

	public int getRound() {
		return this.round;
	}

	public void setRound(int round) {
		this.round = round;
	}
}