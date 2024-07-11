package com.api.planner.activity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID> {
	
	List<Activity> findByTripId(UUID id);
	
}
