package com.stella.alephart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stella.alephart.models.EventMode;
import com.stella.alephart.repository.EventModeRepository;

@Service
public class EventModeService {

	// Inyección de dependencias
	@Autowired
	private EventModeRepository eventModeRepository;
	
	// GET all
	public List<EventMode> findAllEventModes(){
		return eventModeRepository.findAll();
	}
	
	// GET single
	public Optional<EventMode> findEventModeById(Long id){
		return eventModeRepository.findById(id);
	}
		
	// POST
	public EventMode saveEventMode(EventMode eventMode) {
		return eventModeRepository.save(eventMode);
	}
	
	// PUT #EDIT
	public EventMode updateEventMode(Long id, EventMode eventMode) {
		Optional<EventMode> existingEventModeOptional = eventModeRepository.findById(id);
		
		// Si el event mode existe...
		if (existingEventModeOptional.isPresent()) {
			EventMode existingEventMode = existingEventModeOptional.get();
			
			// Actualizar datos del event mode
			existingEventMode.setMode_name(eventMode.getMode_name());
			
			return eventModeRepository.save(eventMode);
		} else {
			// Si no se encuentra el event mode
			throw new RuntimeException("EventMode no encontrado");
		}
		
	}
	
	
	// DELETE 
	public void deleteEventMode(Long id) {
		eventModeRepository.deleteById(id);
	}
	
	
}