package com.cardprototype.core.domain;

import java.io.Serializable;

/**
 * An enemy
 * @author Kevin Deyne
 *
 */
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ANTWERP_STATION_BARRACKS = "barracks";
	public static final String ANTWERP_STATION_DOCKING_BAY = "docking-bay";

	private String id;
	private String name;
	private String description;
	private Station station;

	public Area() {
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

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public Station getStation() {
		return this.station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
}