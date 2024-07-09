package com.api.planner.trip;

import java.util.List;

public record TripRequestPayload(
		String destination,
		String startAt,
		String endsAt,
		List<String> emailsToInvite,
		String ownerEmail,
		String ownerName
		) {

}
