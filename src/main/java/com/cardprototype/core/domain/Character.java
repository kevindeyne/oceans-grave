package com.cardprototype.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * A character
 * @author Kevin Deyne
 *
 */
@Entity
public class Character implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String EXAMPLE_ID = "example-character"; //TODO

	@Column(name = "id", updatable = false, nullable = false, length = 32)
	@Id
	private String id;

	@Column(name = "name",  nullable = false, length = 64)
	private String name;

	@Column(name = "description",  nullable = true, length = 4000)
	private String description;

	@Column(name = "areaid",  nullable = true, length = 64)
	private String areaId;

	@Enumerated
	@Column(name = "missionstate_id", nullable = true)
	private MissionStateCode state;

	public Character() {
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

	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public MissionStateCode getState() {
		return this.state;
	}

	public void setState(MissionStateCode state) {
		this.state = state;
	}
}