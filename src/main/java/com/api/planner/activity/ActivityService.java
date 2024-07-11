package com.api.planner.activity;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.planner.trip.Trip;

@Service
public class ActivityService {
	
	@Autowired
	private ActivityRepository repository;

	public ActivityCreateResponse registerActivity(ActivityRequestPayload activity, Trip trip) {
		Activity newActivity = new Activity(activity, trip);
		this.repository.save(newActivity);
		
		return new ActivityCreateResponse(newActivity.getId());
	}
	
	public List<ActivityResponse> findParticipantsByTripId(UUID id) {
		
		return this.repository.findByTripId(id).stream().map(
				activity -> new ActivityResponse(activity.getId(), activity.getTitle(), activity.getOccursAt())).toList();
		
	}
	
}
