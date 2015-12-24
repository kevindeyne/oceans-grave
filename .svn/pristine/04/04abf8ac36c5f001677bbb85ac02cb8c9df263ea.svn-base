package com.cardprototype.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * A mission state is key to the progression of players
 * Mission states determine the quests a player has and how far they've progressed
 * It is also linked to areas and characters to see what conversation it sparks
 * @author Kevin Deyne
 */
@Entity
public class MissionState implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", updatable = false, nullable = false, length = 32)
	@Id
	private String id;

	@Column(name = "name", updatable = false, nullable = false, length = 32)
	private final String name;

	@ManyToOne
	private Player player;

	@Enumerated(EnumType.STRING)
	@Column(name = "state", updatable = true, nullable = false, length = 32)
	private MissionStateCode state;

	public MissionState(String name, Player player, MissionStateCode missionStateCode) {
		super();
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public MissionStateCode getState() {
		return this.state;
	}

	public void setState(MissionStateCode state) {
		this.state = state;
	}

	public String getName() {
		return this.name;
	}
}