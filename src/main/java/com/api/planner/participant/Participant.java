package com.api.planner.participant;

import java.util.UUID;

import com.api.planner.trip.Trip;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "participants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(name = "is_confirmed", nullable = false)
	private Boolean isConfirmed;
	
	@ManyToOne
	@JoinColumn(name = "trip_id", nullable = false)
	@JsonIgnore
	private Trip trip;
	
	public Participant(String email, Trip trip) {
		this.email = email;
		this.trip = trip;
		this.isConfirmed = false;
		this.name = "";
	}
}
