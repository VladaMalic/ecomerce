package com.example.ecomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EcomerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomerceApplication.class, args);
	}

}
