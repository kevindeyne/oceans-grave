package com.cardprototype.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardprototype.bootstrap.pool.AbilityPool;
import com.cardprototype.core.domain.Ability;
import com.cardprototype.core.domain.Enemy;
import com.cardprototype.core.domain.EventQueue;
import com.cardprototype.core.domain.Player;
import com.cardprototype.core.domain.battle.helper.RoundChanges;
import com.cardprototype.core.domain.battle.helper.changes.common.ActorChanges;
import com.cardprototype.core.repository.EventQueueRepository;
import com.cardprototype.core.repository.PlayerRepository;
import com.cardprototype.task.generator.RNG;

@Service
public class AbilityEffectServiceImpl extends AbstractPlayerServiceImpl implements AbilityEffectService {

	private EventQueueRepository eventQueueRepository;
	private PlayerRepository playerRepository;

	@Autowired
	public AbilityEffectServiceImpl(EventQueueRepository eventQueueRepository, PlayerRepository playerRepository) {
		this.eventQueueRepository = eventQueueRepository;
		this.playerRepository = playerRepository;
	}

	@Override
	public void useAbility(String abilityId, int round, boolean asPlayer) {
		Ability ability = AbilityPool.getAbilityPool().getAbility(abilityId);

		for (int i = 0; i < ability.getLifespan(); i++) {
			EventQueue eventQueue = new EventQueue(round + i, getPlayerId(), abilityId, asPlayer);
			this.eventQueueRepository.save(eventQueue);
		}

		saveCooldownEvents(round, asPlayer, ability);
		saveBuffEvents(round, asPlayer, ability);
	}

	public void saveCooldownEvents(int round, boolean asPlayer, Ability ability) {
		for (int i = 0; i < ability.getCooldown(); i++) {
			EventQueue eventQueue = new EventQueue(round + i, getPlayerId(), ability.getId(), asPlayer);
			eventQueue.setCooldownTime(ability.getCooldown() - i);
			this.eventQueueRepository.save(eventQueue);
		}
	}

	public void saveBuffEvents(int round, boolean asPlayer, Ability ability) {
		if(ability.getAccBuff() != 0 || ability.getDefenseBuff() != 0){
			EventQueue eventQueue = new EventQueue(round + Ability.BUFF_LENGTH, getPlayerId(), ability.getId(), asPlayer, true);
			this.eventQueueRepository.save(eventQueue);

			Player player = this.playerRepository.findOne(getPlayerId());
			if(asPlayer){
				player.setAcc(player.getAcc() + ability.getAccBuff());
				player.setDef(player.getDef() + ability.getDefenseBuff());
			} else {
				player.getEnemy().setAcc(player.getEnemy().getAcc() + ability.getAccBuff());
				player.getEnemy().setDef(player.getEnemy().getDef() + ability.getDefenseBuff());
			}
			this.playerRepository.save(player);
		}
	}

	@Override
	public RoundChanges getRoundChanges(int round, boolean asPlayer){
		RoundChanges changes = new RoundChanges();
		List<EventQueue> events = this.eventQueueRepository.findByPlayerIdAndRoundAndAsPlayer(getPlayerId(), round, asPlayer);
		if(CollectionUtils.isNotEmpty(events)){
			for(EventQueue event : events){
				changes = appendEventToChanges(changes, event);
				changes = fillRecentAttackInfo(asPlayer, changes, event);
			}
		}

		Player player = this.playerRepository.findOne(getPlayerId());
		changes.getPlayerChanges().setAcc(player.getAcc());
		changes.getPlayerChanges().setDef(player.getDef());
		changes.getEnemyChanges().setAcc(player.getEnemy().getAcc());
		changes.getEnemyChanges().setDef(player.getEnemy().getDef());

		changes.setRound(round);

		return changes;
	}

	private RoundChanges appendEventToChanges(RoundChanges changes, EventQueue event) {
		Ability ability = retrieveAbility(event);

		if(event.getCooldownTime() > 0){
			changes = handleCooldownEvent(changes, event, ability);
		} else {
			changes = handleActionEvent(changes, event, ability);
		}

		destroyBuff(event, ability);

		return changes;
	}

	public void destroyBuff(EventQueue event, Ability ability) {
		if(event.isStopAbilityBuff()){
			Player player = this.playerRepository.findOne(getPlayerId());
			if(event.isAsPlayer()){
				player.setAcc(player.getAcc() - ability.getAccBuff());
				player.setDef(player.getDef() - ability.getDefenseBuff());
			} else {
				player.getEnemy().setAcc(player.getEnemy().getAcc() - ability.getAccBuff());
				player.getEnemy().setDef(player.getEnemy().getDef() - ability.getDefenseBuff());
			}
			this.playerRepository.save(player);
		}
	}


	private RoundChanges handleCooldownEvent(RoundChanges changes, EventQueue event, Ability ability) {
		ActorChanges actorChanges = changes.getEnemyChanges();
		if(event.isAsPlayer()){
			actorChanges = changes.getPlayerChanges();
		}

		Map<String, Integer> abilitiesOnCooldown = actorChanges.getAbilitiesOnCooldown();
		if(null == abilitiesOnCooldown){
			abilitiesOnCooldown = new HashMap<String, Integer>();
		}
		abilitiesOnCooldown.put(ability.getId(), event.getCooldownTime());

		if(event.isAsPlayer()){
			changes.getPlayerChanges().setAbilitiesOnCooldown(abilitiesOnCooldown);
		} else {
			changes.getEnemyChanges().setAbilitiesOnCooldown(abilitiesOnCooldown);
		}

		return changes;
	}

	public RoundChanges handleActionEvent(RoundChanges changes, EventQueue event, Ability ability) {
		switch(ability.getAbilityType()){
		case DAMAGE_DEALER :
			changes = handleDamageDealer(changes, ability, event.isAsPlayer());
			break;
		case SCORCHER :
			changes = handleDamageDealer(changes, ability, event.isAsPlayer());
			changes = handleScorcherMarker(changes, event.isAsPlayer());
			break;
		case EMP :
			changes = handleStunMarker(changes, event.isAsPlayer());
			break;
		case RESET_COOLDOWNS:
			changes = resetCooldowns(changes, event.isAsPlayer());
			break;
		default: break;
		}
		return changes;
	}

	private RoundChanges resetCooldowns(RoundChanges changes, boolean asPlayer) {
		List<EventQueue> eventsWithCooldownsForPlayer = this.eventQueueRepository.findByPlayerIdAndCooldownTimeGreaterThanAndAsPlayer(getPlayerId(), 0, asPlayer);
		this.eventQueueRepository.delete(eventsWithCooldownsForPlayer);
		return changes;
	}

	private RoundChanges handleStunMarker(RoundChanges changes, boolean asPlayer) {
		if(asPlayer){
			changes.getEnemyChanges().setStunned(true);
		} else {
			changes.getPlayerChanges().setStunned(true);
		}

		return changes;
	}

	private RoundChanges handleScorcherMarker(RoundChanges changes, boolean asPlayer) {
		if(asPlayer){
			changes.getEnemyChanges().setScorched(true);
		} else {
			changes.getPlayerChanges().setScorched(true);
		}

		return changes;
	}

	private RoundChanges fillRecentAttackInfo(boolean asPlayer, RoundChanges changes, EventQueue event) {
		Ability ability = AbilityPool.getAbilityPool().getAbility(event.getAbilityId());
		StringBuffer recentAttackAbility = new StringBuffer("");
		if(asPlayer){
			recentAttackAbility.append("Player uses ");
		}else {
			recentAttackAbility.append("Enemy uses ");
		}
		recentAttackAbility.append(ability.getName());

		changes.setRecentAttackAbility(recentAttackAbility.toString());

		return changes;
	}

	private RoundChanges handleDamageDealer(RoundChanges changes, Ability ability, boolean asPlayer) {
		int damage = generateDamage(ability.getMinDamage(), ability.getMaxDamage());

		/* * * */
		StringBuffer recentAttackDamage = new StringBuffer("");
		if(asPlayer){
			recentAttackDamage.append("Enemy takes ");
		}else {
			recentAttackDamage.append("Player takes ");
		}
		recentAttackDamage.append(damage);
		recentAttackDamage.append(" DMG ");
		/* * * */

		Player player = this.playerRepository.findOne(getPlayerId());
		Enemy enemy = player.getEnemy();

		if(asPlayer){
			recentAttackDamage.append(" (+" + player.getAcc() + " ACC, -" + enemy.getDef() + "DEF )");
			damage = damage + player.getAcc() - enemy.getDef();
			if(damage < 0){ damage = 0; }
			changes.getEnemyChanges().setHealth(-damage);
		} else {
			recentAttackDamage.append(" (+" + enemy.getAcc() + " ACC, -" + player.getDef() + "DEF )");
			damage = damage + enemy.getAcc() - player.getDef();
			if(damage < 0){ damage = 0; }
			changes.getPlayerChanges().setHealth(-damage);
		}

		/* * * */
		changes.setRecentAttackDamage(recentAttackDamage.toString());//TODO
		/* * * */

		return changes;
	}

	private int generateDamage(int minDamage, int maxDamage) {
		return RNG.generate(minDamage, maxDamage);
	}

	@Override
	public void enemyMove(int round) {
		useAbility(RNG.enemyAbility(), round, false);
	}
}