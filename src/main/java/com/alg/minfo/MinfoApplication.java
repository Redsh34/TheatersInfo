package com.alg.minfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MinfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinfoApplication.class, args);
	}

}
