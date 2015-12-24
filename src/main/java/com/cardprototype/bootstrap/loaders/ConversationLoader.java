package com.cardprototype.bootstrap.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.cardprototype.core.domain.Character;
import com.cardprototype.core.domain.conversation.Answer;
import com.cardprototype.core.domain.conversation.Line;
import com.cardprototype.core.repository.AnswerRepository;
import com.cardprototype.core.repository.LineRepository;

/**
 * Loaders are classes that listen for an application start up and then fill
 * either a database or in-memory storage with instances of classes
 * This can be used for either test data or actual objects that only exist in-memory
 *
 * Implementing the {@link Ordered} interface ensures we can control when it is executed
 *
 * This specific class handles the {@link ConversationLoader}
 *
 * @author Kevin Deyne
 */
@Component
public class ConversationLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	@Autowired
	private LineRepository lineRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {



		Line line = new Line(Boolean.TRUE, Character.EXAMPLE_ID, "Hi! This is a conversation example. What would you like to say?");
		Answer answer1 = new Answer("This is my answer, how's it going?");
		Answer answer2 = new Answer("I'd love to talk, but this is a bad time.");

		this.answerRepository.save(answer1);
		this.answerRepository.save(answer2);

		line.addAnswers(answer1, answer2);

		this.lineRepository.save(line);
	}

	private Character simpleCharacter(String id) {
		Character character = new Character();
		character.setId(id);
		return character;
	}

	/**
	 * Determines the order executed
	 */
	@Override
	public int getOrder() {
		return 5;
	}
}