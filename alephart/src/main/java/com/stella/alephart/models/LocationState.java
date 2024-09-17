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
@Table(name="locationstate")
public class LocationState {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_location_state;
	
	@Column
	private String state_name;
	
	public LocationState() {}
	
	@OneToMany(mappedBy = "locationState", cascade = CascadeType.ALL) // si eliminamos un locationState, loe eventos relacionados con ese, también bye
	@JsonManagedReference
	private Set<Events> events = new HashSet<>();

	public LocationState(Long id_location_state, String state_name, Set<Events> events) {
		super();
		this.id_location_state = id_location_state;
		this.state_name = state_name;
		this.events = events;
	}

	public Long getId_location_state() {
		return id_location_state;
	}

	public void setId_location_state(Long id_location_state) {
		this.id_location_state = id_location_state;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "LocationState [id_location_state=" + id_location_state + ", state_name=" + state_name + ", events="
				+ events + "]";
	}

	
	
	
}
