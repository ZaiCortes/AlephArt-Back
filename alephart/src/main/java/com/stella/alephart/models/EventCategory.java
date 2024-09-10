package com.stella.alephart.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class EventCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_event_category;
	private String category_name;	
	
}
