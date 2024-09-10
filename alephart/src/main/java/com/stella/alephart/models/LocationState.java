package com.stella.alephart.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class LocationState {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_location_state;
	private String state_name;
	
}
