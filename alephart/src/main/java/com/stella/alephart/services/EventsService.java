package com.stella.alephart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stella.alephart.models.Events;
import com.stella.alephart.repository.EventsRepository;

@Service
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
			Events existingEvent = existingEventsOptional.get();
		
			// Actualizamos los campos necesarios del evento
            existingEvent.setEvent_name(event.getEvent_name());
            existingEvent.setEvent_description(event.getEvent_description());
            existingEvent.setEvent_photo(event.getEvent_photo());
            existingEvent.setEvent_date(event.getEvent_date());
            existingEvent.setEvent_time(event.getEvent_time());
            existingEvent.setEventMode(event.getEventMode());
            existingEvent.setEventCategory(event.getEventCategory());
            existingEvent.setLocationCity(event.getLocationCity());
            existingEvent.setLocationState(event.getLocationState());
			
			return eventsRepository.save(existingEvent);
		} else {
			
			throw new RuntimeException("Evento" + id + " no encontrado");
		}
	
	}
	
	public void deleteEvent(Long id) {
		eventsRepository.deleteById(id);
	}

}
