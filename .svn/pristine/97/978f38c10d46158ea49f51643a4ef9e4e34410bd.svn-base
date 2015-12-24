package com.cardprototype.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * An enemy
 * @author Kevin Deyne
 *
 */
@Entity
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String EXAMPLE_ID = "example-area"; //TODO

	@Column(name = "id", updatable = false, nullable = false, length = 32)
	@Id
	private String id;

	@Column(name = "name",  nullable = false, length = 32)
	private String name;

	@Column(name = "description",  nullable = true, length = 4000)
	private String description;

	@ManyToOne
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