package com.stella.alephart.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="eventmode")
public class EventMode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_event_mode;
	
	@Column
	private String mode_name;
	
	@OneToMany(mappedBy = "eventMode", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Events> events = new HashSet<>(); 
	// set pa no permitir duplicados (solo un eventMode por event)
	// inizializarlo para que apunte a algún lado
	
	public EventMode() {}

	public EventMode(Long id_event_mode, String mode_name, Set<Events> events) {
		super();
		this.id_event_mode = id_event_mode;
		this.mode_name = mode_name;
		this.events = events;
	}

	public Long getId_event_mode() {
		return id_event_mode;
	}

	public void setId_event_mode(Long id_event_mode) {
		this.id_event_mode = id_event_mode;
	}

	public String getMode_name() {
		return mode_name;
	}

	public void setMode_name(String mode_name) {
		this.mode_name = mode_name;
	}

	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "EventMode [id_event_mode=" + id_event_mode + ", mode_name=" + mode_name + ", events=" + events + "]";
	}
	
	
	
}