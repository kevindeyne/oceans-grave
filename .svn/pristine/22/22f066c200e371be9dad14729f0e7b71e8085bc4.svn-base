package com.cardprototype.bootstrap.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.cardprototype.bootstrap.pool.AreaPool;
import com.cardprototype.bootstrap.pool.CharacterPool;
import com.cardprototype.core.domain.Area;
import com.cardprototype.core.domain.Character;
import com.cardprototype.core.repository.CharacterRepository;

/**
 * Loaders are classes that listen for an application start up and then fill
 * either a database or in-memory storage with instances of classes
 * This can be used for either test data or actual objects that only exist in-memory
 *
 * Implementing the {@link Ordered} interface ensures we can control when it is executed
 *
 * This specific class handles the {@link CharacterLoader}
 *
 * @author Kevin Deyne
 */
@Component
public class CharacterLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	@Autowired
	private CharacterRepository characterRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		CharacterPool pool = CharacterPool.getCharacterPool();

		Character character = pool.generateCharacter("Aiko Duyster", "Engineer", "Busy working on the steam pressure valves");
		character.setArea(simpleArea(AreaLoader.DELTA_BAR));
		this.characterRepository.save(character);
	}

	private Area simpleArea(String id) {
		return AreaPool.getAreaPool().generateSmallArea(id);
	}

	/**
	 * Determines the order executed
	 */
	@Override
	public int getOrder() {
		return 5;
	}
}