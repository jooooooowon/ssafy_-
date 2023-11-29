package com.ssafy.howmuch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ssafy.howmuch.model.dao")
public class HowmuchApplication {

	public static void main(String[] args) {
		SpringApplication.run(HowmuchApplication.class, args);
	}

}
