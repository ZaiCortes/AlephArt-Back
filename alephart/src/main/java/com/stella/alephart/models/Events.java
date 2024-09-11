package com.stella.alephart.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Events {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_events;
	private String event_name;
	private String event_description;
	private byte event_photo;
	private String event_date;
	private String event_time;
	
}
