package com.example.demo1;

import java.time.LocalDateTime;

public class Event {
	
	private String name;
	private LocalDateTime startDateTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	
	
}
