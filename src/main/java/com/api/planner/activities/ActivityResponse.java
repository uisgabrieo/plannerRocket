package com.api.planner.activities;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityResponse(
		UUID id,
		String title,
		LocalDateTime occursAt) {
}
