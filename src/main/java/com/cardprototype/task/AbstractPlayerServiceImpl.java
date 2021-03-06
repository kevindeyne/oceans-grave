package com.cardprototype.task;

import org.springframework.security.core.context.SecurityContextHolder;

import com.cardprototype.bootstrap.pool.AbilityPool;
import com.cardprototype.core.domain.EventQueue;
import com.cardprototype.core.domain.Player;
import com.cardprototype.core.domain.ability.Ability;
import com.cardprototype.core.domain.security.PlayerUserDetails;
import com.cardprototype.core.repository.PlayerRepository;

public abstract class AbstractPlayerServiceImpl {

	protected PlayerRepository playerRepository;

	public AbstractPlayerServiceImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	protected Player getPlayer(){
		if("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())){
			throw new RuntimeException("Cannot call service when not logged in");
		} else {
			PlayerUserDetails userDetails = (PlayerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return this.playerRepository.findOne(userDetails.getId());
		}
	}

	protected String getPlayerId(){
		return getPlayer().getId();
	}

	protected Ability retrieveAbility(EventQueue event) {
		return AbilityPool.getAbilityPool().getAbility(event.getAbilityId());
	}

}
