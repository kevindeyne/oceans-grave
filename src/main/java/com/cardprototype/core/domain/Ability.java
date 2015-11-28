package com.cardprototype.core.domain;

public class Ability {

	private String id;
	private String name;
	private AbilityType type;
	private int strength;

	public static final String BASIC_ATTACK =  "basic-attack";
	public static final String BASIC_REPAIR = "basic-repair";

	public AbilityType getType() {
		return this.type;
	}

	public void setType(AbilityType type) {
		this.type = type;
	}

	public int getStrength() {
		return this.strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
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
}