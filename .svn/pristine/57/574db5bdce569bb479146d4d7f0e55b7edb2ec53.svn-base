package com.cardprototype.bootstrap.pool;

/**
 * A pool is a singleton factory that can produce or find generated or in-memory instances
 *
 * This specific class handles {@link Mission}
 *
 * @author Kevin Deyne
 */
public class MissionPool {

	private static MissionPool pool;

	/**
	 * Private constructor ensures Singleton usage
	 */
	private MissionPool(){

	}

	/**
	 * Retrieves the Singleton pool
	 * @return {@link MissionPool}
	 */
	public static MissionPool getMissionPool(){
		if(pool == null){
			pool = new MissionPool();
		}

		return pool;
	}


}