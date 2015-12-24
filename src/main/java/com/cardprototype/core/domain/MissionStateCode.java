package com.cardprototype.core.domain;

/**
 * Different types of mission states, documented
 * @see MissionState
 * @author Kevin Deyne
 */
public enum MissionStateCode {

	/**
	 * Tutorial 1 - Player started just the game
	 */
	TUTORIAL1("Pick up the phone"),

	/**
	 * Tutorial 2 - Player picked up the phone
	 */
	TUTORIAL2("Go to Hagen Square");

	/***/
	private String description;

	private MissionStateCode(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}
}
