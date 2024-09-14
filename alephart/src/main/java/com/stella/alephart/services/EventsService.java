package com.stella.alephart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.stella.alephart.models.Events;
import com.stella.alephart.repository.EventsRepository;

public class EventsService {
	
	@Autowired
	private EventsRepository eventsRepository;
	
	public List<Events> findAllEvents(){
		return eventsRepository.findAll();
	}
		
	public Optional<Events> findEventsById(Long id) {
		return eventsRepository.findById(id);
	}
	
	public Events saveEvent(Events event) {
		return eventsRepository.save(event);
	}
	
	public Events updateEvent(Long id, Events event) {
		Optional<Events> existingEventsOptional = eventsRepository.findById(id);
		
		if (existingEventsOptional.isPresent()) {
			Events existingEvents = existingEventsOptional.get();
		
			 existingEvents.setEvent_description(event.getEvent_description());
			
			return eventsRepository.save(existingEvents);
		} else {
			
			throw new RuntimeException("Evento" + id + " no encontrado");
		}
	
	}
	
	public void deleteEvent(Long id) {
		eventsRepository.deleteById(id);
	}

}
