package com.example.demo1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

	
	@GetMapping("/events")
	public String events(Model model) {

		List<Event> events = new ArrayList<Event>();
		
		Event event1 = new Event();
		event1.setName("1차 스터디");
		event1.setStartDateTime(LocalDateTime.of(2019, 3, 12, 8, 00));
		
		Event event2 = new Event();
		event2.setName("1차 스터디");
		event2.setStartDateTime(LocalDateTime.of(2019, 3, 12, 10, 00));
		
		events.add(event1);
		events.add(event2);
		
		model.addAttribute("events", events);
		model.addAttribute("msg", "I'am Msg");
		
		return "events";
	}
}
