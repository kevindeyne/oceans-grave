package com.cardprototype.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.cardprototype.core.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, String> {

}