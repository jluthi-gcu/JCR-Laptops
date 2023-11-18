package com.jcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.jcr" })
public class JcrLaptopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JcrLaptopsApplication.class, args);
	}

}
