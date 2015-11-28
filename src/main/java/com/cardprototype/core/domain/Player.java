package com.cardprototype.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.cardprototype.bootstrap.pool.AbilityPool;

@Entity
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String EXAMPLE_ID = "example-player"; //TODO

	@Column(name = "id", updatable = false, nullable = false, length = 32)
	@Id
	private String id;

	@ElementCollection
	private List<String> abilities;

	@Column(name = "round", nullable = false)
	private int round;

	public Player(){
		this.abilities = new ArrayList<String>();
	}

	public void setupStartAbilities(){
		this.round = 0;
		List<Ability> starterAbilities = AbilityPool.getAbilityPool().getStartAbilities();
		for(Ability ability : starterAbilities){
			this.abilities.add(ability.getId());
		}
	}

	public List<String> getAbilityIds() {
		return this.abilities;
	}

	public List<Ability> getAbilities() {
		AbilityPool pool = AbilityPool.getAbilityPool();
		return pool.getAbilities(this.abilities);
	}

	public void setAbilities(List<String> abilities) {
		this.abilities = abilities;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRound() {
		return this.round;
	}

	public void setRound(int round) {
		this.round = round;
	}
}
