package com.stella.alephart.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class LocationCity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_location_city;
	private String city_name;	
	
}
