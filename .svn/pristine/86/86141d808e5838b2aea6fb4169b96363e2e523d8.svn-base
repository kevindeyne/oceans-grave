package com.cardprototype.core.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * An enemy
 * @author Kevin Deyne
 *
 */
@Entity
public class Station implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String EXAMPLE_ID = "example-station"; //TODO

	@Column(name = "id", updatable = false, nullable = false, length = 32)
	@Id
	private String id;

	@Column(name = "name",  nullable = false, length = 32)
	private String name;

	@Column(name = "description",  nullable = true, length = 4000)
	private String description;

	@OneToMany
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