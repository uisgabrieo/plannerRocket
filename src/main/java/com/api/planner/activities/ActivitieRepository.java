package com.api.planner.activities;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitieRepository extends JpaRepository<Activitie, UUID> {

}
