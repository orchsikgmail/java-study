package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Single {

	@Autowired
	Proto proto;
		// ObjectProvider<Proto> proto;

	public Proto getProto() {
		return proto;
			// return proto.getIfAvailable();
				
	}

	
	
	
}
