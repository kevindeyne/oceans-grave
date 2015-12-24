package com.cardprototype.bootstrap.pool;

import com.cardprototype.core.domain.Character;

/**
 * A pool is a singleton factory that can produce or find generated or in-memory instances
 *
 * This specific class handles {@link Character}
 *
 * @author Kevin Deyne
 */
public class CharacterPool {

	private static CharacterPool pool;

	/**
	 * Private constructor ensures Singleton usage
	 */
	private CharacterPool(){

	}

	/**
	 * Retrieves the Singleton pool
	 * @return {@link CharacterPool}
	 */
	public static CharacterPool getCharacterPool(){
		if(pool == null){
			pool = new CharacterPool();
		}

		return pool;
	}

	public Character generateCharacter(String name, String title, String busyDescription) {
		Character character = new Character();
		character.setId(Character.EXAMPLE_ID);
		character.setName(name + ", " + title);
		character.setDescription(busyDescription);
		return character;
	}




}
