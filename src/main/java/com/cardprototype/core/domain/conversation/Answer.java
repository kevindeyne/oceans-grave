package com.cardprototype.core.domain.conversation;

import java.io.Serializable;
import java.util.UUID;

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
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", updatable = false, nullable = false, length = 32)
	@Id
	private String id;

	@ManyToOne
	private Line line;

	@Column(name = "text", updatable = false, nullable = false, length = 999)
	private String text;

	public Answer() {
	}

	public Answer(String text) {
		setId(UUID.randomUUID().toString().replace("-", ""));
		setText(text);
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Line getLine() {
		return this.line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}