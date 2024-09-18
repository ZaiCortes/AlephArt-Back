package com.stella.alephart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.stella.alephart.models.Events;
import com.stella.alephart.services.EventsService;

@RestController
@RequestMapping("/api/events")
public class EventsController {
	
	@Autowired
	private EventsService eventsService;
	
	@GetMapping
	public List<Events> getAllEvents() {
		return eventsService.findAllEvents();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Events> getEventsById(@PathVariable("id") Long id) {
		return eventsService.findEventsById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());}
	 
	 @PostMapping
		public Events createEvents(@RequestBody Events events) {
			return eventsService.saveEvent(events);
		}
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Events> updateEvent(@PathVariable Long id, @RequestBody Events event) {
	        try {
	            Events updatedEvent = eventsService.updateEvent(id, event);
	            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	  @DeleteMapping("/{id}")
	  public ResponseEntity<Void> deleteEvents(@PathVariable("id") Long id) {
		  return eventsService.findEventsById(id)
			  	.map(events -> {
				  	eventsService.deleteEvent(id);
					return ResponseEntity.ok().<Void>build();
				})
				.orElse(ResponseEntity.notFound().build());
		}

}
