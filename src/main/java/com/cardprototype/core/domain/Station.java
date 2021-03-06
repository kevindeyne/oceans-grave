package com.cardprototype.core.domain;

import java.io.Serializable;
import java.util.List;

/**
 * An enemy
 * @author Kevin Deyne
 *
 */
public class Station implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NEW_KHOLN = "new-kholn-station";
	public static final String ANTWERP = "antwerp-station";

	private String id;
	private String name;
	private String description;
	private List<Area> areas;

	public Station() {
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

	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
}