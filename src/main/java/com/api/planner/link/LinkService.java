package com.api.planner.link;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.planner.trip.Trip;

@Service
public class LinkService {
	
	@Autowired
	private LinkRepository repository;
	
	public LinkCreateResponse registerLink(LinkRequestPayload link, Trip trip) {
		Link newLink = new Link(link, trip);
		this.repository.save(newLink);
		
		return new LinkCreateResponse(newLink.getId());
	}
	
	public List<LinkResponse> findLinksByTripId(UUID id) {
		
		return this.repository.findByTripId(id).stream().map(
				link -> new LinkResponse(link.getId(), link.getTitle(), link.getUrl())).toList();
	}
}
