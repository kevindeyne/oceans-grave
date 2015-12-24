package com.cardprototype.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.cardprototype.core.domain.conversation.Line;

public interface LineRepository extends CrudRepository<Line, String> {

	public Line findByCharacterIdAndConversationStartTrue(String id);
}