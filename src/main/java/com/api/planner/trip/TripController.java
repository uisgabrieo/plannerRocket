package com.api.planner.trip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.planner.activity.ActivityCreateResponse;
import com.api.planner.activity.ActivityRequestPayload;
import com.api.planner.activity.ActivityResponse;
import com.api.planner.activity.ActivityService;
import com.api.planner.link.LinkRequestPayload;
import com.api.planner.link.LinkCreateResponse;
import com.api.planner.link.LinkService;
import com.api.planner.participant.ParticipantCreateResponse;
import com.api.planner.participant.ParticipantData;
import com.api.planner.participant.ParticipantRequestPayload;
import com.api.planner.participant.ParticipantService;

@RestController
@RequestMapping("/trips")
public class TripController {
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private ParticipantService participantService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private TripRepository repository;
	
	@PostMapping("/create")
	public ResponseEntity<TripCreateResponse> createTrip(@RequestBody TripRequestPayload payload) {
		
		Trip newTrip = new Trip(payload);
		this.repository.save(newTrip);
		this.participantService.registerParticipantsToEvent(payload.emailsToInvite(), newTrip);
		
		return ResponseEntity.ok().body(new TripCreateResponse(newTrip.getId()));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Trip> getTripDetails(@PathVariable UUID id) { 
		Optional<Trip> trip = this.repository.findById(id);
		
		return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody TripRequestPayload payload) { 
		Optional<Trip> trip = this.repository.findById(id);
		
		if (trip.isPresent()) {
			Trip rawTrip = trip.get();
			
			rawTrip.setEndsAt(LocalDateTime.parse(payload.endsAt(), DateTimeFormatter.ISO_DATE_TIME));
			rawTrip.setStartsAt(LocalDateTime.parse(payload.startsAt(), DateTimeFormatter.ISO_DATE_TIME));
			rawTrip.setDestination(payload.destination());
			
			this.repository.save(rawTrip);
			
			return ResponseEntity.ok(rawTrip);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/confirm")
	public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) { 
		Optional<Trip> trip = this.repository.findById(id);
		
		if (trip.isPresent()) {
			Trip rawTrip = trip.get();
			
			rawTrip.setIsConfirmed(true);
			
			this.repository.save(rawTrip);
			this.participantService.triggerConfirmationEmailToParticipants(id);
			
			return ResponseEntity.ok(rawTrip);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/{id}/invite")
	public ResponseEntity<ParticipantCreateResponse> inviteParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload) {
		Optional<Trip> trip = this.repository.findById(id);
		
		if (trip.isPresent()) {
			Trip rawTrip = trip.get();

			ParticipantCreateResponse participantResponse = this.participantService.registerParticipantToEvent(payload.email(), rawTrip);
			
			if (rawTrip.getIsConfirmed()) this.participantService.triggerConfirmationEmailToParticipant(payload.email());
			
			return ResponseEntity.ok(participantResponse);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/{id}/participants")
	public ResponseEntity<List<ParticipantData>> allParticipantsTrip(@PathVariable UUID id) {
		Optional<Trip> trip = this.repository.findById(id);
		if (trip.isPresent()) {
			List<ParticipantData> participants = this.participantService.findParticipantsByTripId(id);
			
			return ResponseEntity.ok().body(participants);
			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/{id}/activity")
	public ResponseEntity<ActivityCreateResponse> createActivity(@PathVariable UUID id, @RequestBody ActivityRequestPayload payload) {
		Optional<Trip> trip = this.repository.findById(id);
		
		if (trip.isPresent()) {
			Trip rawTrip = trip.get();

			ActivityCreateResponse activityResponse = this.activityService.registerActivity(payload, rawTrip);
			
			return ResponseEntity.ok(activityResponse);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/activities")
	public ResponseEntity<List<ActivityResponse>> allActivitiesTrip(@PathVariable UUID id) {
		Optional<Trip> trip = this.repository.findById(id);
		
		if(trip.isPresent()) {
			
			List<ActivityResponse> activityResponses = this.activityService.findParticipantsByTripId(id);
			
			return ResponseEntity.ok(activityResponses);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/{id}/links")
	public ResponseEntity<LinkCreateResponse> registerLink(@PathVariable UUID id, @RequestBody LinkRequestPayload payload) {
		Optional<Trip> trip = this.repository.findById(id);
		
		if(trip.isPresent()) {
			Trip rawTrip = trip.get();
			
			LinkCreateResponse linkResponse = this.linkService.registerLink(payload, rawTrip);
			
			return ResponseEntity.ok(linkResponse);
			}
		
		return ResponseEntity.notFound().build();
	}
}
