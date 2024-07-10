package com.api.planner.activities;

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
	
	
}
