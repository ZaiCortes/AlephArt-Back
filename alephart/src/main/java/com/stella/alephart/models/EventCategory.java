package com.stella.alephart.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.micrometer.observation.Observation.Event;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="eventcategory")
public class EventCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_event_category;
	
	@Column
	private String category_name;
	
	@OneToMany(mappedBy = "eventCategory", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Events> events = new HashSet<>(); 
	// set pa no permitir duplicados (solo un eventCategory por event)
	// inizializarlo para que apunte a algún lado
	
	public EventCategory(){}

	public EventCategory(Long id_event_category, String category_name, Set<Events> events) {
		super();
		this.id_event_category = id_event_category;
		this.category_name = category_name;
		this.events = events;
	}

	public Long getId_event_category() {
		return id_event_category;
	}

	public void setId_event_category(Long id_event_category) {
		this.id_event_category = id_event_category;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "EventCategory [id_event_category=" + id_event_category + ", category_name=" + category_name
				+ ", events=" + events + "]";
	}
	
		
}
