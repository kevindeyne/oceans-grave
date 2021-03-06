package com.cardprototype.core.domain;

import com.cardprototype.core.domain.ability.Ability;

/**
 * Different types of ranges
 * @see Ability
 * @author Kevin Deyne
 */
public enum RangeType {

	CLOSE("close range"),
	MID("mid range"),
	LONG("long range"),
	ANY("any range");

	private String description;

	RangeType(String description){
		this.description = description;
	}

	@Override
	public String toString() {
		return this.description;
	};



}
