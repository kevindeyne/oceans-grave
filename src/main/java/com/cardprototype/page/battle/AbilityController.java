package com.cardprototype.page.battle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardprototype.bootstrap.pool.AbilityPool;
import com.cardprototype.core.domain.Ability;
import com.cardprototype.core.domain.AbilityType;
import com.cardprototype.core.domain.Enemy;
import com.cardprototype.core.domain.EventQueue;
import com.cardprototype.core.domain.Player;
import com.cardprototype.core.repository.EnemyRepository;
import com.cardprototype.core.repository.EventQueueRepository;

/**
 * Controller in charge of {@link Ability} effects during a battle
 * @author Kevin Deyne
 *
 */
@Controller
public class AbilityController {

	@Autowired
	private EventQueueRepository eventQueueRepository;

	@Autowired
	private EnemyRepository enemyRepository;

	public static final String SKILL_ACTION = "/skill-action/{abilityId}";

	@RequestMapping(value={SKILL_ACTION})
	public @ResponseBody Map<String, String> skillAction(Model model, @PathVariable String abilityId) {
		EventQueue earliestEvent = this.eventQueueRepository.findTopByPlayerIdOrderByRoundAsc(Player.EXAMPLE_ID);
		int round = determineRound(earliestEvent);
		Map<String, String> response = buildResponse(abilityId, round);
		clearEarliestEventQueue(round);
		return response;
	}

	/**
	 * Builds the single 'changed' response, concatenated from all the items on this round on the event queue
	 * Also attaches the logic for the next attack, so we only have to go once to the server and back
	 *
	 * @param abilityId - the {@link Ability} just used
	 * @param round - the current round, pre-determined
	 * @return {@link Map} with response
	 */
	private Map<String, String> buildResponse(String abilityId, int round) {
		Map<String, String> response = new HashMap<String, String>();
		response.putAll(generateAttackEffects(abilityId, round));
		response.putAll(generateNextAttack());
		return response;
	}

	/**
	 * Determines what round we're in
	 * @param earliestEvent
	 * @return
	 */
	private int determineRound(EventQueue earliestEvent) {
		int round = 0;
		if(null != earliestEvent){
			round = earliestEvent.getRound();
		}
		return round;
	}

	/**
	 * Concatentation of events from the {@link EventQueue}
	 * for this round and this {@link Player}
	 * @param abilityId just used, to be added to the {@link EventQueue}
	 * @param round current round
	 * @return part of the reponse {@link Map}
	 */
	private Map<String, String> generateAttackEffects(String abilityId, int round) {
		Ability ability = AbilityPool.getAbilityPool().getAbility(abilityId);
		int nextRound = saveCurrentActionOnEventQueue(round, ability);
		return concatEventsOnQueueToResponse(nextRound);
	}

	/**
	 * Saves the current ability on the queue
	 * @param round
	 * @param ability
	 * @return nextRound
	 */
	private int saveCurrentActionOnEventQueue(int round, Ability ability) {
		EventQueue eventQueue = new EventQueue();
		eventQueue.setPlayer(createDummyPlayer(Player.EXAMPLE_ID));

		int nextRound = round+1;
		eventQueue.setRound(nextRound);

		//TODO
		if(AbilityType.ATTACK.equals(ability.getType())){
			eventQueue.setEnemyDamage(ability.getStrength());
		} else if(AbilityType.REPAIR.equals(ability.getType())) {
			eventQueue.setPlayerDamage(-ability.getStrength());
		}

		this.eventQueueRepository.save(eventQueue);
		return nextRound;
	}

	/**
	 * Actual concatenation of events
	 * @param nextRound
	 * @return part of the response {@link Map}
	 */
	private Map<String, String> concatEventsOnQueueToResponse(int nextRound) {
		List<EventQueue> eventQueues = this.eventQueueRepository.findByPlayerIdAndRound(Player.EXAMPLE_ID, nextRound);

		Map<String, String> result = new HashMap<String, String>();

		int enemyHPChange = 0;
		int playerHPChange = 0;

		for(EventQueue eventQueue : eventQueues){

			if(eventQueue.getEnemyDamage() != 0){
				enemyHPChange-=eventQueue.getEnemyDamage();
			} else if(eventQueue.getEnemyHeal() != 0){
				enemyHPChange+=eventQueue.getEnemyHeal();

			} else if(eventQueue.getPlayerDamage() != 0){
				playerHPChange-=eventQueue.getPlayerDamage();
			} else if(eventQueue.getPlayerHeal() != 0){
				playerHPChange+=eventQueue.getPlayerHeal();
			}
		}

		result.put("enemyHPChange", new Integer(enemyHPChange).toString());
		result.put("playerHPChange", new Integer(playerHPChange).toString());
		//
		return result;
	}

	/**
	 * Dummy {@link Player} object used to persist items linked with a {@link Player},
	 * but without having to retrieve the object. JPA knows to couple it to the right object
	 * based on the ID.
	 * 	 *
	 * @param player's actual ID
	 * @return empty {@link Player} object, except for the ID - to be used in persisting
	 */
	private Player createDummyPlayer(String dummyId) {
		Player player = new Player();
		player.setId(dummyId);
		return player;
	}

	/**
	 * Generates enemy round logic
	 * @return
	 */
	private Map<String, String> generateNextAttack() {
		Map<String, String> result = new HashMap<String, String>();

		Ability enemyAbility = randomlyPickAnAbility();

		int enemyHPChange = 0;
		int playerHPChange = 0;

		if(AbilityType.ATTACK.equals(enemyAbility.getType())){
			playerHPChange -= enemyAbility.getStrength();
		} else if(AbilityType.REPAIR.equals(enemyAbility.getType())) {
			enemyHPChange += enemyAbility.getStrength();
		}

		result.put("enemyturn_enemyHPChange", new Integer(enemyHPChange).toString());
		result.put("enemyturn_playerHPChange", new Integer(playerHPChange).toString());

		return result;
	}

	/**
	 * Primitive enemy AI
	 * TODO belongs in a different place
	 * @return
	 */
	private Ability randomlyPickAnAbility() {
		Enemy enemy = this.enemyRepository.findByPlayerId(Player.EXAMPLE_ID);

		List<String> abilities = enemy.getAbilities();
		String abilityID = abilities.get(RandomUtils.nextInt(0, abilities.size()));
		return AbilityPool.getAbilityPool().getAbility(abilityID);
	}

	/**
	 * Since all concatenations are done, this round is not neccesary any more
	 * @param round to be deleted
	 */
	private void clearEarliestEventQueue(int round) {
		List<EventQueue> eventQueues = this.eventQueueRepository.findByPlayerIdAndRound(Player.EXAMPLE_ID, round);
		this.eventQueueRepository.delete(eventQueues);
	}
}
