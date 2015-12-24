package com.cardprototype.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cardprototype.core.domain.Character;
import com.cardprototype.core.domain.MissionStateCode;

public interface CharacterRepository extends CrudRepository<Character, String> {

	public List<Character> findByAreaIdAndState(String id, MissionStateCode missionStateCode);
}