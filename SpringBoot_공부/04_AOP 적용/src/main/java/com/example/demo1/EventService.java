package com.example.demo1;

import org.springframework.stereotype.Service;

@Service
public interface EventService {

	void createEvent ();
	void publishEvent ();
	void deleteEvent();

}
