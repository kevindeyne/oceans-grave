package com.cardprototype.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cardprototype.core.domain.EventQueue;

public interface EventQueueRepository extends CrudRepository<EventQueue, String> {

	public EventQueue findTopByPlayerIdOrderByRoundAsc(String playerId);

	public List<EventQueue> findByPlayerIdAndRound(String playerId, int round);

	public List<EventQueue> findByPlayerId(String playerId);

}