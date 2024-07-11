package com.api.planner.link;

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

}
