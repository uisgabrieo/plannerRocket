package com.api.planner.trip;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_trip")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private String detination;
	
	@Column(name = "starts_at", nullable = false)
	private LocalDateTime startAt;
	
	@Column(name = "ends_at", nullable = false)
	private LocalDateTime endsAt;
	
	@Column(name = "is_confirmad", nullable = false)
	private Boolean isConfirmed = false;
	
	@Column(name = "owner_name", nullable = false)
	private String ownerName;

	@Column(name = "owner_email", nullable = false)
	private String ownerEmail;
	
	
	
}
