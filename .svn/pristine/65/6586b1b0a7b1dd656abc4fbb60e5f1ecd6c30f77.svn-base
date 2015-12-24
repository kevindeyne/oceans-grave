package com.cardprototype.core.domain.conversation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.cardprototype.core.domain.Character;

/**
 * An line
 * @author Kevin Deyne
 *
 */
@Entity
public class Line implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", updatable = false, nullable = false, length = 32)
	@Id
	private String id;

	@OneToOne
	private Character character;

	@OneToMany
	private List<Answer> answers = new ArrayList<Answer>();

	@Column(name = "conversation_start", updatable = false, nullable = false)
	private Boolean conversationStart = Boolean.FALSE;

	@Column(name = "text", updatable = false, nullable = false, length = 999)
	private String text;

	public Line() {
	}

	public Line(boolean start, String characterId, String text) {
		setId(UUID.randomUUID().toString().replace("-", ""));
		setConversationStart(start);

		Character character = new Character();
		character.setId(characterId);
		setCharacter(character);

		setText(text);
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Boolean getConversationStart() {
		return this.conversationStart;
	}

	public void setConversationStart(Boolean conversationStart) {
		this.conversationStart = conversationStart;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void addAnswers(Answer ... answer) {
		this.answers.addAll(Arrays.asList(answer));
	}
}