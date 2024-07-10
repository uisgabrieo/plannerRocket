package com.api.planner.participant;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.planner.trip.Trip;

@Service
public class ParticipantService {
	
	@Autowired
	private ParticipantsRepository repository;

	public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip) {
        List<Participant> participants = participantsToInvite.stream()
                .map(email -> new Participant(email, trip))
                .toList();

            this.repository.saveAll(participants);
	}
	
	public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip) {
		Participant participants =  new Participant(email, trip);
		
		this.repository.save(participants);
		
		return new ParticipantCreateResponse(participants.getId());
	}
	
	public void triggerConfirmationEmailToParticipants(UUID tripId) {
		
	}

	public void triggerConfirmationEmailToParticipant(String email) {
		
	}
	
	public List<ParticipantData> findParticipantsByTripId(UUID id) {
		
		return this.repository.findByTripId(id).stream()
		.map(particpant -> new ParticipantData(
				particpant.getId(), particpant.getName(), particpant.getEmail(), particpant.getIsConfirmed()))
		.toList();
	}
	
}
