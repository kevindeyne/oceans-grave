package com.cardprototype.core.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * An enemy
 * @author Kevin Deyne
 *
 */
@Entity
public class Enemy implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String EXAMPLE_ID = "example-enemy"; //TODO

	@Column(name = "id", updatable = false, nullable = false, length = 32)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name = "name",  nullable = true, length = 32)
	private String name;

	@OneToOne(optional=true)
	private Player player;

	@Column(name = "health", nullable = true)
	private int health;

	@Column(name = "full_health", nullable = true)
	private int fullHealth;

	@Column(name = "def", nullable = false)
	private int def;

	@Column(name = "acc", nullable = false)
	private int acc;

	@ElementCollection
	private List<String> abilities;

	public Enemy() {
		setDef(0);
		setAcc(0);
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getFullHealth() {
		return this.fullHealth;
	}

	public void setFullHealth(int fullHealth) {
		this.fullHealth = fullHealth;
	}

	public List<String> getAbilities() {
		return this.abilities;
	}

	public void setAbilities(List<String> abilities) {
		this.abilities = abilities;
	}

	public int getDef() {
		return this.def;
	}

	public void setDef(int def) {
		this.def = addWithRange(def);
	}

	public int getAcc() {
		return this.acc;
	}

	public void setAcc(int acc) {
		this.acc = addWithRange(acc);
	}

	private int addWithRange(int value) {
		if(value > 5){
			value = 5;
		} else if(value < -5){
			value = -5;
		}
		return value;
	}
}