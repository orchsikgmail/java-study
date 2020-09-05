package com.example.demo1;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test & !dev")  // !(not)	&(and)	|(or) 사용가능
public class TestBookRepository implements BookRepository {

}
