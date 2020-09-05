package com.example.demo1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Builder;

@Service
public class EventService {

	public List<Event> getEvents(){
		
		Event event1 = Event.builder()
				.name("스터디 1차")
				.limitOfEnrollment(5)
				.startDateTime(LocalDateTime.of(2019, 3, 12, 1, 0))
				.endDateTime(LocalDateTime.of(2019, 3, 12, 3, 0))
				.build();
		
		Event event2 = Event.builder()
				.name("스터디 2차")
				.limitOfEnrollment(1)
				.startDateTime(LocalDateTime.of(2019, 3, 29, 13, 0))
				.endDateTime(LocalDateTime.of(2019, 3, 29, 15, 0))
				.build();
		
		List<Event> events = new ArrayList<Event>();
		events.add(event1);
		events.add(event2);
		
		return events; 
		
	}
	
	
}
