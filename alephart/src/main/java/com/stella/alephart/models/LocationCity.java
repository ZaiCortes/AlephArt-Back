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
@Table(name="locationcity")
public class LocationCity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_location_city")
	private Long id_location_city;
	
	@Column
	private String city_name;
	
	@OneToMany(mappedBy = "locationCity", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Events> events = new HashSet<>();
	
	public LocationCity() {}

	public LocationCity(Long id_location_city, String city_name, Set<Events> events) {
		super();
		this.id_location_city = id_location_city;
		this.city_name = city_name;
		this.events = events;
	}

	public Long getId_location_city() {
		return id_location_city;
	}

	public void setId_location_city(Long id_location_city) {
		this.id_location_city = id_location_city;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "LocationCity [id_location_city=" + id_location_city + ", city_name=" + city_name + ", events=" + events
				+ "]";
	}

	
}