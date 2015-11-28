package com.cardprototype.bootstrap.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.cardprototype.core.domain.Player;
import com.cardprototype.core.repository.PlayerRepository;

@Component
public class PlayerLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setupPlayer();
	}

	@Override
	public int getOrder() {
		return 2;
	}

	public Player setupPlayer() {
		Player player = new Player();
		player.setupStartAbilities();
		player.setId(Player.EXAMPLE_ID);
		return this.playerRepository.save(player);
	}
}
