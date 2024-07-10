package com.api.planner.participant;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantsRepository extends JpaRepository<Participant, UUID> {

	List<Participant> findByTripId(UUID id);
	
}
