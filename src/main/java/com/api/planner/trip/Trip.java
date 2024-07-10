package com.api.planner.trip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.api.planner.participant.Participant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trips")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String destination;

	@Column(name = "starts_at", nullable = false)
	private LocalDateTime startsAt;

	@Column(name = "ends_at", nullable = false)
	private LocalDateTime endsAt;

	@Column(name = "is_confirmed", nullable = false)
	private Boolean isConfirmed;

	@Column(name = "owner_name", nullable = false)
	private String ownerName;

	@Column(name = "owner_email", nullable = false)
	private String ownerEmail;
	
	@OneToMany(mappedBy = "trip")
	private List<Participant> participants;

	public Trip(TripRequestPayload data) {
		this.destination = data.destination();
		this.isConfirmed = false;
		this.ownerEmail = data.ownerEmail();
		this.ownerName = data.ownerName();
		this.startsAt = LocalDateTime.parse(data.startsAt(), DateTimeFormatter.ISO_DATE_TIME);
		this.endsAt = LocalDateTime.parse(data.endsAt(), DateTimeFormatter.ISO_DATE_TIME);
	}
}
